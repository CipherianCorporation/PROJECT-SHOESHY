app.controller("order-ctrl", function ($scope, $http) {
    $scope.list_items = [];
    $scope.detail_items = [];
    $scope.form = {};
    $scope.user = {};
    $scope.initialize = function () {
        $http.get(`/rest/order/sortstatus`).then(resp => {
            $scope.list_items = resp.data;
        }).catch(error => {
            console.log("Error", error);
        });
    };

    $scope.initializez();
    
    $scope.update_status = function (item) {
        item.order_status = "success";
        $http.put(`/rest/order/order-status/${item.id}`,item).then(resp=>{
            $scope.initializez();
            alert("Cập nhập trạng thái đơn hàng thành công")
        }).catch(error=>{
            alert("Lỗi cập nhập trạng thái");
            console.log("Error",error);
        })
    }

    $scope.order_detail = function (item) {
        $(".nav-tabs a:eq(1)").tab('show');
        console.log(item)
        $http.get(`/rest/order/detail/${item.id}`).then(resp => {
            $scope.detail_items = resp.data;
            console.log($scope.detail_items)
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.initialize();

    $scope.update_status = function (item) {
        item.order_status = "success";
        $http.put(`/rest/order/order-status/${item.id}`, item).then(resp => {
            $scope.initialize();
            alert("Cập nhập trạng thái đơn hàng thành công");
        }).catch(error => {
            alert("Lỗi cập nhập trạng thái");
            console.log("Error", error);
        });
    };

    $scope.order_detail = function (item) {
        $(".nav-tabs a:eq(1)").tab('show');
        console.log(item);
        $http.get(`/rest/order/detail/${item.id}`).then(resp => {
            $scope.detail_items = resp.data;
            console.log($scope.detail_items);
        }).catch(error => {
            console.log("Error", error);
        });
    };
});