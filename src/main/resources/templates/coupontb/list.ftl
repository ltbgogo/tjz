<#include "inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/list.css">

<!-- 券列表 -->
<ul id="id_list">
    <li v-for="(coupon, index) in coupons" v-bind:class="index % 2 == 0 ? 'cls_left' : 'cls_right'" v-on:click="gotoDetail(coupon)">
        <img v-bind:src="coupon.imagePath"/>
        <div class="cls_title">{{coupon.goodsName}}</div>
        <div class="cls_price">
            <span class="cls_coupon_price">{{coupon.couponPrice / 100}}元券</span>
            <span class="cls_buyPrice">
                券后价
                <b class="number-font">&yen;{{coupon.buyPrice / 100}}</b>
            </span>
        </div>
    </li>
</ul>

<script type="text/javascript" src="${app.pageResPath}/list.js"></script>
<#include "inc/_footer.ftl">