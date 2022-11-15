let app = angular.module("admin-app", ["ngRoute"]);

//routers
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "./pages/dashboard/index.html",
            controller: "dashboard-ctrl"
        })
        .when("/user", {
            templateUrl: "./pages/user/index.html",
            controller: "user-ctrl"
        })
        .when("/product", {
            templateUrl: "./pages/product/index.html",
            controller: "product-ctrl"
        })
        .when("/order", {
            templateUrl: "./pages/order/index.html",
            controller: "order-ctrl"
        })
        .when("/category", {
            templateUrl: "./pages/category/index.html",
            controller: "category-ctrl"
        })
        .when("/voucher", {
            templateUrl: "./pages/voucher/index.html",
            controller: "voucher-ctrl"
        })
        .when("/authorize", {
            templateUrl: "./pages/authorize/index.html",
            controller: "authorize-ctrl"
        })
        .otherwise({
            redirectTo: '/'
        });
});