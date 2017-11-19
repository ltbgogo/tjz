$(function () {

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //快速入口
            quickEntries: []
        },
        methods: {}
    });

    /**
     * 加载快速入口
     */
    $.get(actionPath + "/juantb/getQuickEntries", function(r) {
        vue.quickEntries = r.data;
    }, "json");
});