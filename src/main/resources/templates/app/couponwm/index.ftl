<#include "app/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 导航 -->
<div style="position: relative;">
<div id="id_navbar">
    <div id="scroller">
        <ul>
            <li v-for="(entry,index) in quickEntries" v-bind:class="{active: currentEntry == entry}" >
                <img v-on:click="showIndex(entry);"
                     v-bind:src="'${app.dynamicResPath}/couponwm/entry/' + entry.id.replace('couponwm', '') + '.png'">
                <span class="cls_text">{{entry.name}}</span>
            </li>
        </ul>
    </div>
</div>
</div>

<!-- 列表 -->
<ul id="id_coupon_list">
    <li v-for="coupon in coupons" class="cls_coupon" v-on:click="gotoDetail(coupon)">
        <div class="cls_head">
            <img v-bind:src="coupon.thumbImagePath">
        </div>
        <div class="cls_body">
            <p class="cls_title">{{coupon.couponName}}</p>
            <p class="cls_tip">{{coupon.tip}}</p>
            <div class="cls_bottom">
                <img class="cls_logo" :src="'${app.dynamicResPath}/couponwm/entry/' + currentEntry.id.replace('couponwm', '') + '.png'">
                <span class="cls_name">{{currentEntry.name}}</span>
                <span class="cls_pageviews"> 查看人数：{{coupon.pageviews}}</span>
            </div>
        </div>
    </li>
</ul>

<div id="id_nomore">主人，没更多优惠券了~ </div>

<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "app/inc/_footer.ftl">