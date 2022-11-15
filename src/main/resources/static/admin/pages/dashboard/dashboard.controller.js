app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};
    $scope.userRolesList = [];


    $scope.initialize = function () {
        $scope.userPrincipal = JSON.parse(localStorage.getItem('userPrincipal')) || {};

        $http.get("/rest/authorities").then(resp => {
            $scope.userRolesList = resp.data;

            console.log($scope.userRolesList.filter((u) => u.role.id == 'STAFF').length);
        }).catch(error => {
            alert("Lỗi");
            console.log("Error", error);
        });




    };

    $scope.initialize();


});