<#include "app/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 导航 -->
<div style="position: relative;">
<div id="id_navbar">
    <div id="scroller">
        <ul>
            <li v-for="(entry,index) in quickEntries" v-bind:class="{active: currentEntry == entry}">
                <img v-on:click="showIndex(entry);"
                        v-bind:src="'${app.dynamicResPath}/couponkc/entry/' + entry.id.replace('couponkc', '') + '.png'">
                <span class="cls_text">{{entry.name}}</span>
            </li>
        </ul>
    </div>
</div>
</div>

<!-- 列表 -->
<ul id="id_coupon_list">
    <li v-for="coupon in coupons" class="cls_coupon">
        <div class="cls_head">
            <img v-bind:src="coupon.thumbImagePath">
        </div>
        <div class="cls_body">
            <div class="cls_title">
                <img class="cls_logo" :src="'${app.dynamicResPath}/couponkc/entry/' + currentEntry.id.replace('couponkc', '') + '.png'">
                {{coupon.couponName}}
            </div>
            <div class="cls_bottom">
                <span class="cls_price">&yen; {{coupon.couponPrice / 100}}</span>
                <span class="cls_goto" v-on:click="showCouponLayer(coupon);">一键用券</span>
            </div>
        </div>
    </li>
</ul>

<!-- 弹出层 -->
<div class="cls_ahide">
    <div id="id_couponLayer">
        <section v-if="layerCoupon != null">
            <div class="cls_img"><img v-bind:src="layerCoupon.detailImagePath"></div>
            <p class="cls_title">{{layerCoupon.couponName}}</p>
            <p class="cls_price">&yen; <span>{{layerCoupon.couponPrice / 100}}</span></p>
            <p class="cls_time">有效日期至：{{layerCoupon.expireTime}}</p>
        </section>
    </div>
</div>

<div id="id_nomore">主人，没更多优惠券了~ </div>

<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "app/inc/_footer.ftl">