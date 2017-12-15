
$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //券列表
            coupons: [],
            totalPages: 1
        },
        methods: {
            //转到详情页
            gotoDetail: function (coupon) {
                location.href = actionPath + "/app/couponcx/showDetail?couponId=" + coupon.id;
            },
            //删除券
            deleteCoupons: function () {
                $.post(actionPath + "/adm/couponcx/deleteCoupons", $("#id_form").serialize(), function () {
                   location.reload(true);
                });
            }
        }
    });

    /**
     * 加载券
     */
    function loadCoupons() {
        $.get(actionPath + "/adm/couponcx/getCoupons?page=" + $common.getUrlParam("page", 0), function(r) {
            vue.totalPages = r.data.totalPages;
            vue.coupons = r.data.content;
        }, "json");
    }
    loadCoupons();
});
