app.controller("user-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.providers = [];
    $scope.form = {};
    $scope.roles = [];

    $scope.initialize = function () {
        $http.get("/rest/users").then(resp => {
            $scope.items = resp.data;
            let i = 0;
            new Set(resp.data).forEach(user => {
                if (!$scope.providers.includes(user.provider)) {
                    $scope.providers.push(user.provider);
                }
            });
        }).finally(function () {
            $scope.loading = false;
        });
    };

    $scope.initialize();

    $scope.reset = function () {
        $scope.form = {
            image_url: 'default-user.jpg',
        };
    };

    $scope.update = function () {
        let item = angular.copy($scope.form);
        let check = confirm(`Are you sure to update this user?`);
        if (check) {
            $http.put(`/rest/users/${item.id}`, item).then(resp => {
                let index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                $scope.initialize();
                alert("Update user successfully!");
            }).catch(error => {
                alert("Error while updating user: " + error.message);
                console.log("Error", error);
            });
        }
    };

    $scope.imageChanged = function (files) {
        let data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image_url = resp.data.name;
        }).catch(error => {
            alert("Lối upload hình ảnh");
            console.log("Error", error);
        });
    };


});