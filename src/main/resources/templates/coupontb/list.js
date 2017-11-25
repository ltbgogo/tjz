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
                location.href = actionPath + "/coupontb/showDetail?couponId=" + coupon.id;
            }
        }
    });

    /**
     * 加载券
     */
    function loadCoupons() {
        page > 0 && layer.load(1, {shade: [0.1, '#fff'], time: 1000});
        $.get(actionPath + "/coupontb/getCoupons?page=" + page, location.search.substr(1), function(r) {
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
     * 滚动加载
     */
    $(window).scroll(function () {
        if (page < totalPages && $common.isOnPageBottom()) {
            loadCoupons();
        }
    });
});