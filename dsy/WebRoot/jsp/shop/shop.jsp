<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/static/public.jsp"%>
    <title>店铺管理</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/shop/js/shop.js"></script>
  </head>
  <body>
   <nav class="breadcrumb"><i class="Hui-iconfont" style="font-size: 18px;">&#xe67f;</i> 首页 
   <span class="c-gray en">&gt;</span> 店铺管理
    <span class="c-gray en">&gt;</span> 店铺管理
    <a class="btn btn-success radius r mr-20" 
    style="line-height:1.6em;margin-top:3px" 
    href="javascript:location.replace(location.href);"
     title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
     </a>
   </nav>
     <div class="pd-20">
    <div id="paramContainer" class="text-c">
    <!-- 部门：
    <span class="select-box inline">
         <select id="dept" name="dept" class="select">
        </select>
    </span> -->
    &nbsp;&nbsp;店铺或者客户名称
    <input id="shopname" type="text" class="input-text" style="width:250px" placeholder="输入店铺名称或者客户名称" id="" name=""> 
    <button name="search" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
    <span class="l">
    <a class="btn btn-warning radius" id="update" href="javascript:;">
    <i class="Hui-iconfont">&#xe60c;</i> 修改</a>
    <a href="javascript:;" onclick="shop_delete()" class="btn btn-danger radius">
    <i class="Hui-iconfont">&#xe6e2;</i> 冻结</a> 
    
    </span> <span class="r">共有数据：<strong id="total"></strong>条</span> </div>
    <div class="mt-20">
        <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox" id="selectAll" name="" value=""></th>
                    <th width="80">店铺名称</th>
                    <th width="80">店主名称</th>
                    <th width="80">类型</th>
                    <th width="80">状态</th>
                    <th width="80">申请时间</th>
                    <th width="80">审批人</th>
                    <th width="80">审批时间</th>
                    <th width="80">商铺说明</th>
                    <th width="120">操作</th>
                </tr>
            </thead>
            <tbody>
              </tbody>
        </table>
    </div>
</div>
</body>
</html>