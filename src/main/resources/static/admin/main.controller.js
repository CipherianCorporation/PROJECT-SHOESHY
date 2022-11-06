app.controller("main-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};

    $scope.initialize = function () {
        $http.get("/rest/users/principal").then(resp => {
            $scope.userPrincipal = resp.data;
            localStorage.setItem("userPrincipal", JSON.stringify(resp.data));
        }).catch(error => {
            console.log("Error", error);
        });

    };

    $scope.initialize();
});