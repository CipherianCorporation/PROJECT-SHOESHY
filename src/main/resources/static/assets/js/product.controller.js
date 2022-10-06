angular.module("shoeshy-app").controller("product-ctrl", productController);

function productController($scope, $http, $interval) {
    $scope.loading = true;
    $scope.productList = [];
    $scope.categories = [];
    $scope.sub_categories = [];

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
        $http.get(url + '/rest/sub-categories/' + category_id).then(res => {
            console.log(res.data);
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

    $scope.pager = {
        page: 0,
        size: 10,
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