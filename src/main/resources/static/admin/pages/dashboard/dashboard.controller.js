app.controller("dashboard-ctrl", function ($scope, $http) {
    $scope.userPrincipal = {};
    let userRolesList = [];
    $scope.visitorsCount = [];
    $scope.rolesList = [];
    $scope.ordersCount = 0;
    $scope.orderTotalRevenue = 0;

    let rolesLabel = [];

    //loading spinners 
    $scope.visitorLoading = true;
    $scope.orderLoading = true;
    $scope.revenueLoading = true;


    $scope.initialize = function () {
        $scope.userPrincipal = JSON.parse(localStorage.getItem('userPrincipal')) || {};

        // $http.get("/rest/roles").then(resp => {
        //     let i = 0;
        //     resp.data.forEach(role => {
        //         rolesLabel[i++] = role.id;
        //     });
        //     console.log(rolesLabel);
        // }).catch(error => {
        //     alert("Lỗi");
        //     console.log("Error", error);
        // });

        fetch('/rest/roles', { method: 'GET' }).then(resp => resp.json()).then((data) => {
            rolesLabel = data.map((role) => role.id);
            $scope.rolesList = data.map((role) => role.id);
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
        });

        fetch('/rest/authorities', { method: 'GET' }).then(resp => resp.json()).then((data) => {
            userRolesList = data;
            console.log(userRolesList.filter((u) => u.role.id == 'ADMIN').length);
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
        });


        fetch('/rest/visitors/count', { method: 'GET' }).then(resp => resp.json()).then((data) => {
            $scope.visitorsCount = data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.visitorLoading = false;
        });

        fetch('/rest/orders/count', { method: 'GET' }).then(resp => resp.json()).then((data) => {
            $scope.ordersCount = data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.orderLoading = false;
        });

        fetch('/rest/orders/revenue', { method: 'GET' }).then(resp => resp.json()).then((data) => {
            $scope.orderTotalRevenue = data;
        }).catch((error) => {
            console.error('Error:', error);
        }).finally(() => {
            $scope.revenueLoading = false;
        });

    };

    $scope.initialize();

    setTimeout(function () {
        const ctx = document.getElementById('myChart');
        const data = {
            labels: rolesLabel,
            datasets: [{
                label: 'Users Data',
                data:
                    [
                        userRolesList.filter((u) => u.role.id == 'ADMIN').length,
                        userRolesList.filter((u) => u.role.id == 'STAFF').length,
                        userRolesList.filter((u) => u.role.id == 'USER').length,
                    ],
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
        const myChart = new Chart('myChart', config);
    }, 7000);






});


