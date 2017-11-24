<#include "inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 页头 -->
<div id="id_header">
    <!-- 搜索框 -->
    <div id="id_search">
        <span class="cls_icon fa fa-search"></span>
        <input placeholder="输入商品名或黏贴淘宝标题" class="cls_box">
        <button class="cls_btn">搜券</button>
    </div>
    <!-- 清除浮动 -->
    <div class="cls_clear"></div>
    <div id="id_guid">
        <span class="fa fa-hand-o-right"></span>
        <span class="cls_text">淘宝/天猫查券详细教程</span>
    </div>
</div>

<!--  分类 -->
<div id="id_quick_entries">
    <a class="cls_quick_entry" v-for="entry in quickEntries" href="javascript: void(0);">
        <span>
            <img :src="'${app.dynamicResPath}' + entry.imagePath">
            <span class="cls_text">{{entry.name}}</span>
        </span>
    </a>
</div>

<!-- 券列表 -->
<div id="id_coupon_list">
    <div v-for="conpon in coupons" class="cls_coupon">
        <div class="cls_image_area">
            <img v-bind:src="'${app.dynamicResPath}/' + conpon.imagePath">
        </div>
        <div class="cls_info_area">
            <div class="cls_info_title">{{conpon.title}}</div>
            <div class="cls_info_oldPrice">淘宝价 &yen;{{conpon.oldPrice / 100}}</div>
            <div class="cls_info_bottom">
                <mark class="cls_label_buyPrice">券后价</mark>
                <span class="cls_buyPrice">&yen;{{conpon.buyPrice / 100}}</span>
                <mark class="cls_label_couponPrice">{{conpon.couponPrice / 100}}元券</mark>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "inc/_footer.ftl">