<#include "inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/detail.css">

<!-- 商品图片 -->
<div id="id_header">
    <img v-bind:src="coupon.imagePath"/>
</div>

<!-- 商品标题 -->
<div id="id_title">{{coupon.goodsName}}</div>

<!-- 商品详情 -->
<div id="id_detail">
    <span class="cls_buyPrice">
        <label>券后价</label>
        <b>&yen;{{coupon.buyPrice / 100}}</b>
    </span>
    <span style="float: right;">
        <span class="cls_couponPrice">
            <mark>券</mark>
            <b>&yen;{{coupon.couponPrice / 100}}</b>
        </span>
        <span class="cls_buyers">
            <i class="fa fa-users"></i>
            <b>{{coupon.buyerCount}}</b>
            人已买
        </span>
    </span>
</div>

<!-- 淘口令 -->
<div id="id_taoPassword_area">
    <div class="cls_taoPassword_text">
        复制框内整段文字，打开「手淘」即可「领取优惠券」并购买￥2RY707LmQOr￥
    </div>
    <div class="cls_tip">复制后打开手淘，立享优惠！</div>
</div>

<!-- 一键复制 -->
<div id="id_copy">
    <div class="cls_wrapper">一键复制</div>
</div>

<script type="text/javascript" src="${app.pageResPath}/detail.js"></script>
<#include "inc/_footer.ftl">