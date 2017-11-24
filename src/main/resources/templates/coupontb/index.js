$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //快速入口
            quickEntries: [],
            //券列表
            coupons: []
        },
        methods: {}
    });

    /**
     * 加载券
     */
    function loadCoupons() {
        layer.load(1, {shade: [0.1, '#fff'], time: 1000});
        $.get(actionPath + "/coupontb/getCoupons", function(r) {
            $.each(r.data.content, function(r) {
                vue.coupons.push(this);
            });
            layer.closeAll('loading');
        }, "json");
    }
    loadCoupons();

    /**
     * 加载快速入口
     */
    $.get(actionPath + "/coupontb/getQuickEntries", function(r) {
        vue.quickEntries = r.data;
    }, "json");
});