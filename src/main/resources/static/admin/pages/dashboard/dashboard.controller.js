app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};

    $scope.initialize = function () {
        $scope.getUserPrincipal();
    };

    $scope.getUserPrincipal = function () {
        $scope.userPrincipal = JSON.parse(localStorage.getItem('userPrincipal'));
    };

    $scope.initialize();
});