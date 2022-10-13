angular.module("shoeshy-app").controller("product-ctrl", productController);

function productController($scope, $http, $interval) {
    $scope.loading = true;
    $scope.productList = [];
    $scope.categories = [];
    $scope.sub_categories = [];
    $scope.colorArr = [
        {
            "id": 1,
            "name": "Đen",
        },
        {
            "id": 2,
            "name": "Trắng",
        }
        ,
        {
            "id": 3,
            "name": "Xanh",
        }
        ,
        {
            "id": 4,
            "name": "Vàng",
        }
    ];
    $scope.priceRangeArr = [
        {
            "id": 1,
            "name": "Dưới 100.000đ",
        },
        {
            "id": 2,
            "name": "100.000đ - 200.000đ",
        }
        ,
        {
            "id": 3,
            "name": "200.000đ - 300.000đ",
        }
        ,
        {
            "id": 4,
            "name": "400.000đ - 500.000đ",
        }
        ,
        {
            "id": 5,
            "name": "Trên 500.000đ",
        }
    ];

    $scope.initialize = function () {
        $http.get('/rest/products').then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });

        $http.get('/rest/categories').then(res => {
            $scope.categories = res.data;
        }).catch(error => { console.error(error); });

    };

    $scope.showSubCates = function (category_id) {
        $http.get('/rest/sub-categories/' + category_id).then(res => {
            $scope.sub_categories = res.data;
        }).catch(error => { console.error(error); });
    };

    $scope.filterProductByCategory = function (categoryId) {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products/category/' + categoryId).then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };

    $scope.filterProductBySaleOff = function () {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products/sale-off/').then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };

    $scope.filterProductByPriceRange = function () {
        $scope.productList = [];
        $scope.loading = true;
        $http.get('/rest/products/sale-off/').then(res => {
            $scope.productList = res.data;
        }).catch(error => { console.error(error); })
            .finally(function () {
                $scope.loading = false;
            });
    };

    $scope.pager = {
        page: 0,
        size: 8,
        get productList() {
            let start = this.page * this.size;
            return $scope.productList.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.productList.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    };

    $scope.initialize();
}