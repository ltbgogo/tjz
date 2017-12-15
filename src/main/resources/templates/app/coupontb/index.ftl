<#include "app/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 页头 -->
<div id="id_header">
    <!-- 搜索框 -->
    <form id="id_search" action="${app.actionPath}/coupontb/showList" method="get">
        <span class="cls_icon fa fa-search"></span>
        <input placeholder="输入商品名或黏贴淘宝标题" name="goodsName" class="cls_box">
        <button class="cls_btn">搜券</button>
    </form>
    <!-- 清除浮动 -->
    <div class="cls_clear"></div>
    <div id="id_guid">
        <span class="fa fa-hand-o-right"></span>
        <span class="cls_text">淘宝/天猫查券详细教程</span>
    </div>
</div>

<!--  分类 -->
<div id="id_quick_entries">
    <a class="cls_quick_entry" v-for="entry in quickEntries" v-bind:href="'${app.actionPath}/app/coupontb/showList?categoryId=' + entry.id">
        <span>
            <img :src="'${app.dynamicResPath}/coupontb/entry/' + entry.id.replace('coupontb', '') + '.png'">
            <span class="cls_text">{{entry.name}}</span>
        </span>
    </a>
</div>

<!-- 券列表 -->
<div id="id_coupon_list">
    <div v-for="coupon in coupons" class="cls_coupon" v-on:click="gotoDetail(coupon)">
        <div class="cls_image_area">
            <img v-bind:src="coupon.imagePath">
        </div>
        <div class="cls_info_area">
            <div class="cls_info_title">{{coupon.goodsName}}</div>
            <div class="cls_info_oldPrice">淘宝价 &yen;{{coupon.oldPrice / 100}}</div>
            <div class="cls_info_bottom">
                <mark class="cls_label_buyPrice">券后价</mark>
                <span class="cls_buyPrice">&yen;{{coupon.buyPrice / 100}}</span>
                <mark class="cls_label_couponPrice">{{coupon.couponPrice / 100}}元券</mark>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "app/inc/_footer.ftl">