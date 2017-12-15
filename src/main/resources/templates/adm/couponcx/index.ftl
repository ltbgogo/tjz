<#include "adm/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 操作 -->
<div id="id_actions">
    <a class="btn btn-primary btn-md" href="${app.contextPath}/adm" role="button">首页</a>
    <a class="btn btn-primary btn-md" href="${app.actionPath}/adm/couponcx/showEdit" role="button">添加</a>
    <button class="btn btn-primary btn-md" v-on:click="deleteCoupons();" role="button">删除</button>
</div>

<!-- 列表 -->
<form id="id_form">
    <table id="id_list" class="table table-striped table-hover">
        <tr>
            <th><input type="checkbox"></th>
            <th>编号</th>
            <th>券名称</th>
            <th>提示</th>
            <#--<th>链接</th>-->
            <th>阅览次数</th>
            <#--<th>小图标</th>-->
            <#--<th>大图标</th>-->
            <th>开始时间</th>
            <#--<th>摘要</th>-->
            <#--<th>规则</th>-->
            <th>创建时间</th>
            <th>更新时间</th>
        </tr>
        <tr v-for="coupon in coupons">
            <th><input name="ids" v-bind:value="coupon.id" type="checkbox"></th>
            <td><a v-bind:href="'${app.actionPath}/adm/couponcx/showEdit?id=' + coupon.id + '&jumpUrl=' + $common.generateJumpUrl()">{{coupon.id}}</a></td>
            <td>{{coupon.couponName}}</td>
            <td>{{coupon.tip}}</td>
            <#--<td style="width: 100px;">{{coupon.link}}</td>-->
            <td>{{coupon.pageviews}}</td>
            <#--<td>{{coupon.thumbImagePath}}</td>-->
            <#--<td>{{coupon.detailImagePath}}</td>-->
            <td>{{coupon.startTime}}</td>
            <#--<td>{{coupon.summary}}</td>-->
            <#--<td>{{coupon.rules}}</td>-->
            <td>{{coupon.createDateTime}}</td>
            <td>{{coupon.updateDateTime}}</td>
        </tr>
    </table>
</form>

<!-- 分页 -->
<div id="id_page">
    <v-page href="${app.actionPath}/adm/couponcx/showIndex?" v-bind:total-pages="totalPages"></v-page>
</div>

<script type="text/javascript" src="${app.publicResPath}/adm/js/vue.ext.js"></script>
<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "adm/inc/_footer.ftl">