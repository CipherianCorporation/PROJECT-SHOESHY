// Utils - START
const MONTHS = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
];
let MonthUtils = (config) => {
    var cfg = config || {};
    var count = cfg.count || 12;
    var section = cfg.section;
    var values = [];
    var i, value;

    for (i = 0; i < count; ++i) {
        value = MONTHS[Math.ceil(i) % 12];
        values.push(value.substring(0, section));
    }

    return values;
};
// Utils - END

app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};
    $scope.usersCount = [];
    $scope.onlineUsersCount = 0;
    $scope.userRolesList = [];
    $scope.visitorsCount = [];
    $scope.visitorsList = [];
    $scope.rolesList = [];
    $scope.ordersCount = 0;
    $scope.orderTotalRevenue = 0;


    let rolesLabel = new Set();

    //loading spinners - index.html
    $scope.userLoading = true;
    $scope.onlineUsersLoading = true;
    $scope.visitorLoading = true;
    $scope.orderLoading = true;
    $scope.orderChartLoading = true;
    $scope.revenueLoading = true;
    $scope.userTypeChartLoading = true;
    $scope.prodSoldLoading = true;

    //loading spinners - modal/
    $scope.visitorsModalLoading = true;

    $scope.initialize = function () {
        $scope.userPrincipal = JSON.parse(localStorage.getItem('userPrincipal')) || {};

        $http.get('/rest/authorities').then((resp) => {
            $scope.userRolesList = resp.data;
            // thêm role vào rolesLabel độc nhât (Set)
            resp.data.forEach(a => {
                rolesLabel.add(a.role.id);
            });
            // chuyển về Array
            rolesLabel = Array.from(rolesLabel);
            $scope.usersCount = $scope.userRolesList.filter(userRole => userRole.role.id === 'USER' && userRole.user.provider === 'DATABASE').length;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.userLoading = false;
        });

        $http.get('/rest/user-roles/count-users-by-role').then((resp) => {
            $scope.fillUserTypeChartJS(resp.data, 'userTypeChart');
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.userTypeChartLoading = false;
        });

        $http.get('/rest/visitors').then((resp) => {
            $scope.visitorsList = resp.data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.visitorsModalLoading = false;
        });

        $http.get('/rest/visitors/count').then((resp) => {
            $scope.visitorsCount = resp.data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.visitorLoading = false;
        });

        $http.get('/rest/visitors/active-users-count').then((resp) => {
            $scope.onlineUsersCount = resp.data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.onlineUsersLoading = false;
        });

        $http.get('/rest/orders/count').then((resp) => {
            $scope.ordersCount = resp.data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.orderLoading = false;
        });

        $http.get('/rest/orders').then((resp) => {
            let orderByMonth = [];
            let result = [];
            resp.data.forEach((e) => {
                orderByMonth.push(MONTHS[new Date(e.createdAt).getMonth()]);
            });
            MONTHS.forEach((mth) => {
                result.push(
                    {
                        month: mth,
                        orderCount: orderByMonth.filter((orderMonth) => orderMonth === mth)
                    }
                );
            });
            $scope.orderChartJS(result, 'orderChart');
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.orderChartLoading = false;
        });

        $http.get('/rest/orders/revenue').then((resp) => {
            $scope.orderTotalRevenue = resp.data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.revenueLoading = false;
        });

        $http.get('/rest/sub-categories/product-sold').then((resp) => {
            $scope.fillProductSoldBySubCategoryChartJS(resp.data, 'prodSoldChart');
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.prodSoldLoading = false;
        });

    };

    $scope.initialize();

    $scope.visitorsListPager = {
        page: 0,
        size: 10,
        get items() {
            let start = this.page * this.size;
            return $scope.visitorsList.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.visitorsList.length / this.size);
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



    //chart.js - START
    $scope.fillUserTypeChartJS = function (_data, _elementId) {
        let ctx = document.getElementById(_elementId);
        let data = {
            labels: Array.from(_data.map((e) => e.roleId)),
            datasets: [{
                label: 'Users Type Data',
                data: Array.from(_data.map((e) => e.count)),
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
                hoverOffset: 4
            }]
        };
        let config = {
            type: 'pie',
            data: data,
        };
        let hehe = new Chart(ctx, config);
    };

    $scope.fillProductSoldBySubCategoryChartJS = function (_list, _elementId) {
        let ctx = document.getElementById(_elementId);
        let labels = _list.map((e) => e.subCategory);
        let data = {
            labels: Array.from(_list.map((e) => e.subCategory)),
            datasets: [{
                label: 'Top danh mục bán chạy',
                data: Array.from(_list.map((e) => e.productSold)),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                ],
                borderWidth: 1
            }]
        };
        let config = {
            type: 'bar',
            data: data,
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                }
            },
        };
        let bestSellerCategoryChart = new Chart(ctx, config);
    };

    $scope.orderChartJS = function (_data, _elementId) {
        let ctx = document.getElementById(_elementId);
        let data = {
            labels: MonthUtils({ count: 12 }),
            datasets: [{
                label: 'Order line chart',
                data: _data.map((e) => e.orderCount.length),
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        };
        let config = {
            type: 'line',
            data: data,
        };
        let userTypeChart = new Chart(ctx, config);
    };



    //chart.js - END





});




