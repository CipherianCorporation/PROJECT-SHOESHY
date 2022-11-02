app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.user = {};

    $scope.initialize = function () {
        $http.get("/rest/users/principal").then(resp => {
            $scope.user = resp.data;
            localStorage.setItem("user", JSON.stringify(resp.data.id));
        }).catch(error => {
            console.log("Error", error);
        });

    };

    $scope.initialize();
});