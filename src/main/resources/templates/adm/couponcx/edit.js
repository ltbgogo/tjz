$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //优惠券
            coupon: {},
            categories: [],
            categoryId: null
        },
        methods: {
            sendEdit: function () {
                $.post(actionPath + "/adm/couponcx/edit", $("#id_form").serialize(), function (r) {
                    location.href = $common.getUrlParam("jumpUrl", actionPath + "/adm/couponcx/showIndex");
                }, "json");
            }
        }
    });

    /**
     * 获取类别列表
     */
    $.get(actionPath + "/adm/category/getCategories?parentId=couponcx", function (r) {
        vue.categories = r.data;
        /**
         * 获取订单详情
         */
        if ($common.getUrlParam("id")) {
            $.get(actionPath + "/adm/couponcx/getCoupon?id=" + $common.getUrlParam("id"), function(r) {
                vue.coupon = r.data;
            }, "json");
        }
    }, "json")
});

