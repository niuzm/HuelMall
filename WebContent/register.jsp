<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head></head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<!-- 引入表单校验插件jquery插件 -->
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
	 float:left; */
	
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}

.error {
	color: red;
}
</style>
<!-- 使用ajax和 validate来进行表单校验-->
<script type="text/javascript">
$(function(){
	$("#img1").click(function(){
		$(this).prop("src","${ pageContext.request.contextPath }/CheckImgServlet?time="+new Date().getTime());
	});
});
	//自定义校验规则
	//自定义校验规则
	$.validator
			.addMethod(
					//规则的名称
					"checkUsername",
					//校验的方法		
					function(value, emement, params) {
						//定义一个标志
						flag = true;
						//value：输入的内容
						//element：被校验的元素对象
						//params：规则对应的参数值
						//目的：对输入的username进行ajax校验
						$.ajax({
									"async" : false,
									"data" : {
										"username" : value
									},
									"dataType" : "json",
									"success" : function(data) {
										flag = data.isExist;
									},
									"type" : "POST",
									"url" : "${pageContext.request.contextPath}/user/checkUsername.action"
								});
						//返回false代表该校验器不通过
						return !flag;
					});
	$(function() {
		$("#myForm").validate({
			rules : {
				"username" : {
					"required" : true,
					"checkUsername" : "ccc"
				},
				"password" : {
					"required" : true,
					"rangelength" : [ 6, 12 ]
				},
				"repassword" : {
					"required" : true,
					"rangelength" : [ 6, 12 ],
					"equalTo" : "#password"
				},
				"email" : {
					"required" : true,
					"email" : true
				},
				"name" : {
					"required" : true
				},
				"date" : {
					"dateISO" : true
				},
				"sex" : {
					"required" : true
				}
			},
			messages : {
				"username" : {
					"required" : "用户名不能为空",
					"checkUsername" : "用户名已经存在"
				},
				"password" : {
					"required" : "密码不能为空",
					"rangelength" : "密码必须是6到12位"
				},
				"repassword" : {
					"required" : "确认密码不能为空",
					"rangelength" : "确认密码必须是6到12位",
					"equalTo" : "俩次密码不一致"
				},
				"email" : {
					"required" : "邮箱不能为空",
					"email" : "邮箱格式不正确"
				},
				"name" : {
					"required" : "姓名不能为空"
				},
				"date" : {
					"dateISO" : "日期格式不正确"
				}
			}
		});
	});
</script>
</head>
<body>


	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">

			<div class="col-md-2"></div>




			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form id="myForm" class="form-horizontal" style="margin-top: 5px;"
					action="${ pageContext.request.contextPath }/user/register.action"
					method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="repassword"
								name="repassword" placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								name="name" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="sex1" value="male"> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="sex2" value="female"> 女
							</label> <label class="error" for="sex" style="display: none">您没有第三种选择</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="date">
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="code">

						</div>
						<div class="col-sm-3">
							<img id="img1"
								src="${pageContext.request.contextPath}/CheckImgServlet" />
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							&nbsp;&nbsp;&nbsp;<input type="button" width="100" value="返回主页"
								onclick="goback()"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>



	<div style="margin-top: 50px;">
		<img src="./image/footer.jpg" width="100%" height="78" alt="我们的优势"
			title="我们的优势" />
	</div>

	<div style="text-align: center; margin-top: 5px;">
		<ul class="list-inline">
			<li><a>关于我们</a></li>
			<li><a>联系我们</a></li>
			<li><a>招贤纳士</a></li>
			<li><a>法律声明</a></li>
			<li><a>友情链接</a></li>
			<li><a target="_blank">支付方式</a></li>
			<li><a target="_blank">配送方式</a></li>
			<li><a>服务声明</a></li>
			<li><a>广告声明</a></li>
		</ul>
	</div>
	<div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
		Copyright &copy; 2005-2016 传智商城 版权所有</div>

</body>
<script type="text/javascript">
	//返回主页js
	function goback() {
		window.location.href = "${pageContext.request.contextPath}/";
	}
</script>
</html>




