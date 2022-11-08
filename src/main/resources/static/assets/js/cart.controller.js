app.controller("shopping-cart-ctrl", shoppingCartCtrl);

function shoppingCartCtrl($scope, $http) {

    $scope.userPrincipal = {};
    $scope.cartOrderType = '';
    $scope.checkoutSortUI = [
        { code: 'price_asc', name: 'Giá (Tăng dần)' },
        { code: 'price_desc', name: 'Giá (Giảm dần)' },
    ];
    $scope.voucherInput = '';
    $scope.voucherResponse = {};

    $scope.get_user_pricipal = function () {
        $http.get("/rest/users/principal").then(resp => {
            $scope.userPrincipal = resp.data;
            // localStorage.setItem("user", JSON.stringify(resp.data.id));
        }).catch(error => {
            console.log("Error", error);
        });
    };

    $scope.initialize = function () {
        $scope.get_user_pricipal();
    };

    $scope.initialize();

    $scope.cart = {
        items: [],
        //Thêm
        add(id) {
            let item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                });
            }
        },
        //Xóa sản phẩm
        remove(id) {
            let index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
            if (this.items.length === 0) {
                alert('Bạn không có sản phẩm trong giỏ!');
                location.href = '/';
            }
        },
        //Dọn giỏ hàng
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        ammount_of(item) {
        },
        //Tính Tổng SP trong giỏ hàng
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        //Tính tổng giá tiền trong giỏ hàng
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        //Lưu giỏ hàng đổi sang Json
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem('cart', json);

        },
        //Đọc giỏ hàng từ Local Storage
        loadFromLocalStorage() {
            let json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        }
    };
    $scope.cart.loadFromLocalStorage();
    // let user = {};
    // fetch('/rest/users/' + $('#login-username').text()).then(res => res.json()).then(data => { user = data; });
    // console.log(user);

    $scope.sortCheckoutItems = function (orderType) {
        if (orderType === 'price_asc') {
            $scope.cartOrderType = 'price';
        } else if (orderType === 'price_desc') {
            $scope.cartOrderType = '-price';
        }
    };

    $scope.checkVoucherValid = function (voucherCode) {
        $scope.voucherResponse = {};
        $http.get(`/rest/vouchers/code/${voucherCode}`).then(resp => {
            console.log('voucher exist');
            let todayDate = new Date();
            let voucherEndDate = new Date(resp.data.endDate);
            if (todayDate > voucherEndDate) {
                $scope.voucherResponse = {};
                console.log('voucher hết hạn!');
            } else if (todayDate < voucherEndDate) {
                $scope.voucherResponse = resp.data;
                console.log('voucher còn hạn!');
            }

        }).catch(err => {
            $scope.voucherResponse = {};
            console.log("voucher didn't exist!!!");
        });
    };

    $scope.isObjEmpty = function (obj) {
        return Object.keys(obj).length === 0;
    };

    $scope.order = {
        // createdAt: new Date(),
        total: 0.0,
        address: '',
        payment_method: "",
        orderStatus: {
            name: "processing"
        },
        user: {
            username: '',
        },
        voucher: {
            id: ''
        },
        get order_details() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: parseFloat(item.price),
                    quantity: parseInt(item.qty),
                };
            });
        },
        total_cost() {
            return $scope.cart.amount + (this.payment_method == 'cod' ? 20000 : 0) +
                (!$scope.isObjEmpty($scope.voucherResponse) ? -($scope.cart.amount * $scope.voucherResponse.value / 100) : 0);
        },
        purchase() {
            let order = angular.copy(this);
            order.address = $scope.userPrincipal.address;
            order.user.username = $scope.userPrincipal.username;
            if (!$scope.isObjEmpty($scope.voucherResponse)) {
                order.voucher.id = $scope.voucherResponse.id;
            }
            order.total = $scope.order.total_cost();
            console.log(order);
            if ($scope.cart.count === 0) {
                alert("Error creating order or your cart is empty! Please try again!");
            } else {
                if (order.payment_method == "paypal") {
                    // document.getElementById('purchase-form').submit();
                    console.log('paypal boi');
                    $http.post('/pay', order).then(res => {
                        console.log(res.data.returned_url);
                        $scope.cart.clear();
                        location.href = res.data.returned_url;
                    }).catch(err => {
                        alert("Error creating order or your cart is empty! Please try again!");
                        console.log(err);
                    });
                } else if (order.payment_method == "cod") {
                    order.total += 20000;
                    console.log('cod boi');
                    $http.post('/rest/orders', order).then(res => {
                        alert("Order successfully created");
                        $scope.cart.clear();
                        location.href = "/order/list";
                    }).catch(err => {
                        alert("Error creating order or your cart is empty! Please try again!");
                        console.log(err);
                    });
                }
            }
        }
    };


}