app.controller("order-ctrl", orderCtrl);

function orderCtrl($scope, $http, $window) {
    $scope.list_items = [];
    $scope.detail_items = [];
    $scope.form = {};
    $scope.initializez = function () {
        let list;

        $http.get("/rest/order/list/100018").then(resp => {
            $scope.list_items = resp.data;
            console.log(resp.data);
        });

        if (localStorage.getItem('list') !== null) {
            list = JSON.parse(localStorage.getItem('list'));
            $scope.detail(list.id);
        }
    };
    $scope.switchPage = function (list) {
        localStorage.setItem("list", JSON.stringify(list));
        var url = "http://" + $window.location.host + "/order/detail";
        $window.location.href = url;
        console.log('order id: ' + list.id);
    };
    $scope.detail = function (id) {
        $http.get(`/rest/order/detail/${id}`).then(resp => {
            $scope.detail_items = resp.data;
            $scope.form = angular.copy($scope.detail_items[0]);
            console.log($scope.form);
        });
    };

    $scope.initializez();

}