$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //优惠券
            coupon: {},
            //优惠券类别
            category: {}
        },
        methods: {}
    });

    /**
     * 获取订单详情
     */
    $.get(actionPath + "/app/couponwm/getDetail?couponId=" + $common.getUrlParam("couponId"), function(r) {
        vue.coupon = r.data.coupon;
        vue.category = r.data.category;
    }, "json");
});

