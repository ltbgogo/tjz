
var $common = {};

/**
 * 是否滚动到了底部
 */
$common.isOnPageBottom = function () {
    return this.getScrollTop() + this.getClientHeight() == this.getScrollHeight();
}

/**
 * 获取滚动条距离顶部高度
 */
$common.getScrollTop = function() {
    var scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    } else if (document.body) {
        scrollTop = document.body.scrollTop;
    }
    return scrollTop;
}

/**
 * 获取当前可视范围的高度
 */
$common.getClientHeight = function() {
    var clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight,
            document.documentElement.clientHeight);
    } else {
        clientHeight = Math.max(document.body.clientHeight,
            document.documentElement.clientHeight);
    }
    return clientHeight;
}

/**
 * 获取文档完整的高度
 */
$common.getScrollHeight = function() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}

/**
 * 获取URL参数
 */
$common.getURLParam = function(name, defaultValue) {
    var regexp = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var result = window.location["search"].substr(1).match(regexp);
    return result ? decodeURIComponent(result[2]) : defaultValue;
};

/**
 * 后加载项
 */
$(function(){
    $.scrollUp();
});

