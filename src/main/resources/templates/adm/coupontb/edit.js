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
                $.post(actionPath + "/adm/coupontb/edit", $("#id_form").serialize(), function (r) {
                    location.href = $common.getUrlParam("jumpUrl", actionPath + "/adm/coupontb/showIndex");
                }, "json");
            }
        }
    });

    /**
     * 获取类别列表
     */
    $.get(actionPath + "/adm/category/getCategories?parentId=coupontb", function (r) {
        vue.categories = r.data;
        /**
         * 获取订单详情
         */
        if ($common.getUrlParam("id")) {
            $.get(actionPath + "/adm/coupontb/getCoupon?id=" + $common.getUrlParam("id"), function(r) {
                vue.coupon = r.data;
            }, "json");
        }
    }, "json")
});

