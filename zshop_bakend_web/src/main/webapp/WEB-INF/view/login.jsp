<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>在线商城-后台管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#login").bind("click", function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/backend/login",
                    data: {
                        "username": $("#username").val(),
                        "password": $("#password").val(),
                        "verifyCode": $("#verifyCode").val()
                    },
                    beforeSend: function () {
                        loading = layer.load("登录中...");
                    },
                    success: function (data) {
                        if (data.status == 1) {
                            window.location = "${pageContext.request.contextPath}/backend/main";
                        } else if (data.status == 2) {
                            layer.msg(data.message, {
                                icon: 7,
                                time: 2000
                            });
                            //刷新验证码
                            refresh();
                        }
                    },
                    error: function () {
                        layer.msg("发生未知错误", {
                            icon: 7,
                            time: 2000
                        })
                    },
                    complete: function () {
                        layer.close(loading);
                    }
                })
            });
            $("#qhyzm").bind("click", function () {
                refresh();
            });
            $("#yzm").bind("click", function () {
                refresh();
            })
        });


        function refresh() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/getVerifyCodeImage",
                success: function (data) {
                    $("#yzm").attr("src", "${pageContext.request.contextPath}/backend/getVerifyCodeImage");
                }
            });
        };
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
</head>
<body>
<!-- 使用自定义css样式 div-signin 完成元素居中-->
<div class="container div-signin">
    <div class="panel panel-primary div-shadow">
        <!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
        <div class="panel-heading">
            <h3>在线商城系统 3.0</h3>
            <span>ZSHOP Manager System</span>
        </div>
        <div class="panel-body">
            <!-- login form start -->
            <form action="${pageContext.request.contextPath}/backend/sysuser/login" class="form-horizontal"
                  method="post">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名：</label>
                    <div class="col-sm-9">
                        <input class="form-control" id="username" type="text" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <div class="col-sm-9">
                        <input class="form-control" id="password" type="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">验证码：</label>
                    <div class="col-sm-4">
                        <input class="form-control" id="verifyCode" name="verifyCode" type="text" placeholder="验证码">
                    </div>

                    <div class="col-sm-2">
                        <!-- 验证码 -->
                        <img id="yzm" class="img-rounded"
                             src="${pageContext.request.contextPath}/backend/getVerifyCodeImage"
                             style="height: 32px; width: 70px;"/>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" id="qhyzm" class="btn btn-link">看不清</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-9 padding-left-0">
                        <div class="col-sm-4">
                            <button type="button" id="login" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-link btn-block">忘记密码？</button>
                        </div>
                    </div>
                </div>
            </form>
            <!-- login form end -->
        </div>
    </div>
</div>
<!-- 页尾 版权声明 -->
<div class="container">
    <div class="col-sm-12 foot-css">
        <p class="text-muted credit">
            Copyright 南京网博 版权所有
        </p>
    </div>
</div>

</body>
</html>
