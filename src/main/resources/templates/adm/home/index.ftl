<#include "adm/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/index.css">

<!-- 后台管理入口 -->
<div class="panel panel-default">
    <div class="panel-heading">后台管理入口</div>
    <div class="panel-body">
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/adm/couponcx/showIndex" role="button">淘宝券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/adm/couponcx/showIndex" role="button">出行券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/adm/couponkc/showIndex" role="button">快餐券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/adm/couponwm/showIndex" role="button">外卖券</a>
    </div>
</div>

<!-- App入口 -->
<div class="panel panel-default">
    <div class="panel-heading">App入口</div>
    <div class="panel-body">
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/app/coupontb/showIndex" role="button">淘宝券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/app/couponcx/showIndex" role="button">出行券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/app/couponkc/showIndex" role="button">快餐券</a>
        <a class="btn btn-primary btn-lg" href="${app.actionPath}/app/couponwm/showIndex" role="button">外卖券</a>
    </div>
</div>


<script type="text/javascript" src="${app.pageResPath}/index.js"></script>
<#include "adm/inc/_footer.ftl">