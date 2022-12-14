app.controller("voucher-ctrl", function ($scope, $http, $filter) {
    $scope.list_vouchers = [];
    $scope.detail_vouchers = [];
    $scope.form = {};
    $scope.form_new = {};
    $scope.user = {};
    $scope.voucherLoading = true;

    $scope.initialize = function () {
        $http.get(`/rest/vouchers`).then(resp => {
            $scope.list_vouchers = resp.data.filter(v => v.isDeleted === false);
        }).catch(error => {
            console.log("Error", error);
        }).finally(function () {
            $scope.voucherLoading = false;
        });
    };

    $scope.initialize();

    $scope.edit = function (voucher) {
        voucher.startDate = new Date(voucher.startDate);
        voucher.endDate = new Date(voucher.endDate);
        $scope.form = angular.copy(voucher);
        $(".nav-tabs a:eq(1)").tab('show');
    };

    $scope.reset = function () {
        $scope.form = {};
        $scope.form_new = {};
    };

    $scope.create = function () {
        let voucher = angular.copy($scope.form_new);
        if (voucher.isDeleted == null) {
            voucher.isDeleted = false;
        }
        if (voucher.isUsed == null) {
            voucher.isUsed = false;
        }
        $http.get(`/rest/vouchers/code/${voucher.code}`).then(resp => {
            if (resp.status === 404) {
                $http.post(`/rest/vouchers`, voucher).then(resp => {
                    $scope.list_vouchers.push(resp.data);
                    $scope.reset();
                    // $scope.initialize();
                    alert('Tạo voucher mới thành công');
                }).catch(error => {
                    alert('Lỗi khi tạo voucher : ' + error);
                    console.log("Error", error);
                });
            } else {
                alert('Mã code đã tồn tại');
            }
        }).catch((error) => {
            $http.post(`/rest/vouchers`, voucher).then(resp => {
                $scope.list_vouchers.push(resp.data);
                $scope.reset();
                // $scope.initialize();
                alert('Tạo voucher mới thành công');
            }).catch(error => {
                alert('Lỗi khi tạo voucher : ' + error);
                console.log("Error", error);
            });
        });
    };

    $scope.delete = function (voucher) {
        let check = confirm(`Bạn có chắn chắc muốn xóa voucher này không ${voucher.code}`);
        if (check) {
            $http.delete(`/rest/vouchers/${voucher.id}`).then(resp => {
                let index = $scope.list_vouchers.findIndex(p => p.code == voucher.code);
                $scope.list_vouchers.splice(index, 1);
                $scope.reset();
                // $scope.initialize();
                alert('Xóa voucher thành công');
            }).catch(error => {
                alert('Lỗi khi xóa voucher : ' + error);
                console.log("Error", error);
            });
        }
    };

    $scope.update = function () {
        let item = angular.copy($scope.form);
        item.isDeleted = false;

        let check = confirm(`Bạn có chắc chắn cập nhật voucher này không ?`);
        if (check && $scope.checkDateStart(item.startDate)) {
            $http.put(`/rest/vouchers/${item.id}`, item).then(resp => {
                let index = $scope.list_vouchers.findIndex(v => v.id == item.id);
                $scope.list_vouchers[index] = item;
                // $scope.initialize();
                $scope.reset();
                alert("Cập nhật thành công ");
            }).catch(error => {
                alert("Lỗi khi cập nhật voucher: " + error.message);
                console.log("Error", error);
            });
        }


    };

    $scope.checkDateStart = function () {
        let todayDate = new Date();
        if ($scope.form_new.startDate >= todayDate.setHours(0,0,0,0)){
            return true;
        }else if($scope.form_new.startDate === undefined){
            return true;
        }
        else{
            return false;
        }
    }

    $scope.checkDateEnd = function (){
        if ($scope.form_new.startDate < $scope.form_new.endDate){
            return true;
        }else if($scope.form_new.endDate === undefined){
            return true;
        }else{
            return false;
        }
    }

    $scope.checkDateEndEdit = function (){
        if ($scope.form.startDate < $scope.form.endDate){
            return true;
        }else if($scope.form.endDate === undefined){
            return true;
        }else{
            return false;
        }
    }

    $scope.checkExistCode = function () {
        let tmp = $scope.list_vouchers.find((v)=>v.code === $scope.form_new.code)
        if(tmp){
            return true;
        }
        return false;
    }

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            let start = this.page * this.size;
            return $scope.list_vouchers.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.list_vouchers.length / this.size);
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

});