<#include "adm/inc/_header.ftl">
<link rel="stylesheet" href="${app.pageResPath}/edit.css">

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" id="id_form">
                <input type="hidden" name="id" v-model="coupon.id">
                <!-- 券类别 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">券类别</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="category.id" v-model="coupon.categoryId" >
                            <option v-for="category in categories" v-bind:value="category.id" >{{category.name}}</option>
                        </select>
                    </div>
                </div>
                <!-- 券名称 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">券名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="couponName" v-model="coupon.couponName" placeholder="券名称">
                    </div>
                </div>
                <!-- 提示 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">提示</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="tip" v-model="coupon.tip" placeholder="提示">
                    </div>
                </div>
                <!-- 券链接 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">券链接</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="link" v-model="coupon.link" placeholder="券链接">
                    </div>
                </div>
                <!-- 阅读人数 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">阅读人数</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="pageviews" v-model="coupon.pageviews" placeholder="阅读人数">
                    </div>
                </div>
                <!-- 缩略图片路径 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">缩略图片路径</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="thumbImagePath" v-model="coupon.thumbImagePath" placeholder="缩略图片路径">
                    </div>
                </div>
                <!-- 详情图片路径 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">详情图片路径</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="detailImagePath" v-model="coupon.detailImagePath" placeholder="详情图片路径">
                    </div>
                </div>
                <!-- 开始日期 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">开始日期</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="startTime" v-model="coupon.startTime" placeholder="开始日期">
                    </div>
                </div>
                <!-- 优惠简介 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">优惠简介</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="6" name="summary" v-model="coupon.summary" placeholder="优惠简介"></textarea>
                    </div>
                </div>
                <!-- 活动规则 -->
                <div class="form-group">
                    <label  class="col-sm-2 control-label">活动规则</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="6" name="rules" v-model="coupon.rules" placeholder="活动规则"></textarea>
                    </div>
                </div>
                <!-- 是否删除 -->
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="isDeleted" v-model="coupon.isDeleted"> 是否删除
                            </label>
                        </div>
                    </div>
                </div>
                <!-- 操作按钮 -->
                <div id="id_actions">
                    <a href="${app.actionPath}/adm/couponwm/showIndex" type="button" v-on:click="sendEdit();" class="btn btn-default btn-primary">返回</a>
                    <button type="button" v-on:click="sendEdit();" class="btn btn-default btn-primary">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="${app.pageResPath}/edit.js"></script>
<#include "adm/inc/_footer.ftl">