<#include "app/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/detail.css">

<!-- 商品图片 -->
<div id="id_header">
    <img v-bind:src="coupon.detailImagePath" onerror='this.src="//img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png" />
</div>

<!-- 商品标题 -->
<div id="id_title">
    <p class="cls_first">{{category.name}} | {{coupon.startTime}}</p>
    <p class="cls_title">{{coupon.couponName}}</p>
    <p class="cls_tip">{{coupon.tip}}</p>
</div>

<!-- 优惠简介 -->
<div id="id_summary">
    <div class="cls_title">优惠简介</div>
    <div class="cls_content" v-html="coupon.summary"></div>
</div>

<!-- 活动规则 -->
<div id="id_rules">
    <div class="cls_title">活动规则</div>
    <div class="cls_content" v-html="coupon.rules"></div>
</div>

<!-- 直达链接 -->
<div id="id_link">
    <a v-bind:href="coupon.link">直达链接&gt;</a>
</div>

<script type="text/javascript" src="${app.pageResPath}/detail.js"></script>
<#include "app/inc/_footer.ftl">