angular.module("shoeshy-app").controller("order-ctrl", orderCtrl);

function orderCtrl($scope, $http, $window) {
    $scope.list_items = [];
    $scope.detail_items = [];
    $scope.form = {};
    $scope.initializez = function () {

          $http.get("/rest/order/list/100018").then(resp => {
            $scope.list_items = resp.data;
            console.log(resp.data)
        });
    }
    $scope.initializez();
    $scope.switchPage = function (list) {
        var url = "http://" + $window.location.host + "/order/detail";
        $window.location.href = url;
        localStorage.setItem("list",JSON.stringify(list));
    }
    $scope.detail = function () {
        var list = JSON.parse(localStorage.getItem('list'))
        $http.get(`/rest/order/detail/${list.id}`).then(resp => {
            $scope.detail_items = resp.data;
            $scope.form = angular.copy($scope.detail_items[0]);
            console.log($scope.form);
        });
    }
    $scope.detail();


}