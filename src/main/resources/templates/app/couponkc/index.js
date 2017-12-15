
$(function () {
    /**
     * 全局变量
     */
    var page = 0, totalPages = 1;
    var categoryId = $common.getUrlParam("categoryId", "couponkckfc");

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
            //当前劵类别
            currentEntry: null,
            //弹出层券
            layerCoupon: null
        },
        methods: {
            //显示指定类型券
            showIndex: function (entry) {
                location.href = actionPath + "/app/couponkc/showIndex?categoryId=" + entry.id;
            },
            //显示优惠券
            showCouponLayer: function(coupon) {
                vue.layerCoupon = coupon;
                layer.open({
                    title: "使用请直接出示此页",
                    type: 1,
                    skin: 'cls_couponLayer', //样式类名
                    closeBtn: 1, //不显示关闭按钮
                    anim: 2,
                    offset: '50px',
                    area: '260px',
                    shadeClose: true, //开启遮罩关闭
                    content: $('#id_couponLayer')
                });
            }
        }
    });

    /**
     * 加载快速入口
     */
    $.get(actionPath + "/app/couponkc/getQuickEntries", function(r) {
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
        $.get(actionPath + "/app/couponkc/getCoupons?page=" + page, {categoryId: categoryId}, function(r) {
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
    new IScroll('#id_navbar', { scrollX: true, scrollY: false, mouseWheel: false });
});

/**
 * 事件 - 手机触屏
 */
(function () {
    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, isPassive() ? {
        capture: false,
        passive: false
    } : false);

    function isPassive() {
        var supportsPassiveOption = false;
        try {
            addEventListener("test", null, Object.defineProperty({}, 'passive', {
                get: function () {
                    supportsPassiveOption = true;
                }
            }));
        } catch (e) {
        }
        return supportsPassiveOption;
    }
});
