app.controller("main-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};

    $scope.initialize = function () {
        $http.get("/rest/users/principal").then(resp => {
            $scope.userPrincipal = resp.data;
            if ($scope.userPrincipal.username === undefined) {
                alert('Xin hãy đăng nhập lại!');
                location.href = '/security/login/form';
            } else {
                localStorage.setItem("userPrincipal", JSON.stringify(resp.data));
            }
        }).catch(error => {
            console.log("Error", error);
        });

    };

    $scope.initialize();
});