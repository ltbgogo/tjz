
$(function () {
    /**
     * 全局变量
     */
    var page = 0, totalPages = 1;

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //券列表
            coupons: []
        },
        methods: {
            //转到详情页
            gotoDetail: function (coupon) {
                location.href = actionPath + "/app/couponcx/showDetail?couponId=" + coupon.id;
            }
        }
    });

    /**
     * 加载券
     */
    function loadCoupons() {
        $.get(actionPath + "/app/couponcx/getCoupons?page=" + page, function(r) {
            page = r.data.number + 1;
            totalPages = r.data.totalPages;
            vue.coupons = r.data.content;
        }, "json");
    }
    loadCoupons();
});
