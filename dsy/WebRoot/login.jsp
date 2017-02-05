<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>电商平台安全管理系统</title>
	<link href="<%=request.getContextPath()%>/H-ui/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/jquery/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/bootstrap/js/bootstrap.js"></script>
	<style type="text/css">
h4{
		font-size: 17.5px;
	    display: block;
        -webkit-margin-before: 1.33em;
        -webkit-margin-after: 1.33em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
        text-rendering: optimizelegibility;
        font-family: inherit;
        font-weight: bold;
        line-height: 20px;
		}
.title{
	left: 0px;
	text-align: center;
    margin: 70px auto 50px;
    border-bottom: 1px solid #eeeeee;
    color: #b1b1b1;
		}
      #pjax-container{
          width: 320px;
          height: 300px;
          margin: auto;
      }

	</style>
</head>
<body>
<div class="container" style="width: 320px;margin-top: 150px;">
	<div class="login-page"></div>
    <div class="logo"></div>
    <h4 class="title">
    <span>
     电商平台<b>·</b>安全管理系统
      </span>
    	
    </h4>
    <div id="pjax-container">
      <div class="sign-in">
        <form class="bs-example bs-example-form" name="form1" action="logon.html" method="post" id="pagerForm" style="width: 300px;">
		<div class="row">

			<div  style="float: left; margin-left: 20px;">
				<div class="input-group">
					<span class="input-group-addon" style="background-color: #ffffff;">
					   <span class="glyphicon glyphicon-user"></span>
					</span>
					<input id="TxtUserName" name="TxtUserName" type="text" maxLength=20 class="form-control" style="width: 240px;" placeholder="请输入用户名">
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 --><br>

			<div  style="float: left; position: relative; margin-top: 15px;margin-left: 20px;">
				<div class="input-group">
					<span class= "input-group-addon" style="background-color: #ffffff;">
					 <span class="glyphicon glyphicon-lock">
					 	
					 </span>
					</span>
					<input id="TxtPassword" name="TxtPassword" type="password" class="form-control" style="width: 240px;" placeholder="请输入密码">
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
			<div>
			</div>
			<br/>
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${msg}
		</div>
		
		<br/>
		<div class="row" style="font: left;">
			<button id="IbtnEnter" type="submit" class="btn btn-info btn-block" style="margin-left: 20px;  width: 280px;color: #ffffff;">登录</button>
		</div>
	</form>
     </div>
     </div>
</body>
</html>
