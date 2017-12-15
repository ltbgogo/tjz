$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //优惠券
            coupon: {}
        },
        methods: {}
    });

    /**
     * 监听 - 一键复制
     */
    $("#id_taoPassword_area .cls_taoPassword_text").click(function () {
        window.getSelection().selectAllChildren(this);
    });

    /**
     * 获取订单详情
     */
    $.get(actionPath + "/app/coupontb/getDetail?couponId=" + $common.getUrlParam("couponId"), function(r) {
        vue.coupon = r.data;
    }, "json");

    /**
     * 剪贴板
     */
    var clipboard = new Clipboard('#id_copy', {
        target: function () {
            return $('#id_copy .cls_wrapper').get(0);
        }
    });
    clipboard.on('success', function (e) {
        layer.msg('复制成功,打开手机淘宝即可使用！', {time: 2000});
    });
    clipboard.on('error', function (e) {
        layer.msg('复制失败,请您手动复制！', {time: 2000});
    });
});

