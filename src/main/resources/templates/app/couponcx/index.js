
$(function () {
    /**
     * 全局变量
     */
    var page = 0, totalPages = 1;
    var categoryId = $common.getUrlParam("categoryId", "couponcxdddc");

    /**
     * 初始化vue
     */
    var vue = new Vue({
        el: '#id_app',
        data: {
            //快速入口
            quickEntries: [],
            //券列表
            coupons: [],
            //当前劵
            currentEntry: null
        },
        methods: {
            //转到详情页
            gotoDetail: function (coupon) {
                location.href = actionPath + "/app/couponcx/showDetail?couponId=" + coupon.id;
            },
            //显示指定类型券
            showIndex: function (entry) {
                location.href = actionPath + "/app/couponcx/showIndex?categoryId=" + entry.id;
            }
        }
    });

    /**
     * 加载快速入口
     */
    $.get(actionPath + "/app/couponcx/getQuickEntries", function(r) {
        $.each(r.data, function() {
            if (this.id == categoryId) {
                vue.currentEntry = this;
            }
        });
        vue.quickEntries = r.data;
    }, "json");

    /**
     * 加载券
     */
    function loadCoupons() {
        page > 0 && layer.load(1, {shade: [0.1, '#fff'], time: 1000});
        $.get(actionPath + "/app/couponcx/getCoupons?page=" + page, {categoryId: categoryId}, function(r) {
            $.each(r.data.content, function(r) {
                vue.coupons.push(this);
            });
            page = r.data.number + 1;
            totalPages = r.data.totalPages;
            layer.closeAll('loading');
        }, "json");
    }
    loadCoupons();

    /**
     * 初始化横向滚动条
     */
    new IScroll('#id_navbar', { scrollX: true, scrollY: false, mouseWheel: false, click: true});
});

