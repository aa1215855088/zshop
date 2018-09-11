<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>

<body>
    <div class="navbar navbar-default clear-bottom">
        <div class="container">
            <!-- logo start -->
            <div class="navbar-header">
                <a class="navbar-brand logo-style" href="http://edu.51cto.com/center/lec/info/index?user_id=12392007">
                        <img class="brand-img" src="${pageContext.request.contextPath}/images/com-logo1.png" alt="logo" height="70">
                    </a>
            </div>
            <!-- logo end -->
            <!-- navbar start -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="main.html">商城主页</a>
                    </li>
                    <li>
                        <a href="myOrders.html">我的订单</a>
                    </li>
                    <li>
                        <a href="cart.jsp">购物车</a>
                    </li>
                    <li class="active">
                        <a href="center.jsp">会员中心</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                    </li>
                    <li>
                        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                    </li>
                    <li class="userName">
                        您好：user！
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg" height="33" />
                                <span class="caret"></span>
                            </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                        <i class="glyphicon glyphicon-cog"></i>修改密码
                                    </a>
                            </li>
                            <li>
                                <a href="#">
                                        <i class="glyphicon glyphicon-off"></i> 退出
                                    </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- navbar end -->
        </div>
    </div>
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>基本资料</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="name" class="col-md-2  col-sm-2 control-label">用户姓名:</label>
                <div class="col-md-8 col-sm-10">
                    <input type="text" class="form-control" id="name" placeholder="用户姓名" readonly="readonly" value="${user.name}">
                </div>
            </div>
            <div class="form-group">
                <label for="loginName" class="col-md-2 col-sm-2 control-label">登陆账号:</label>
                <div class="col-md-8  col-sm-10">
                    <input type="text" class="form-control" id="loginName" placeholder="登陆账号" readonly="readonly" value="${user.loginName}">
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-md-2  col-sm-2 control-label">联系电话:</label>
                <div class="col-md-8 col-sm-10">
                    <input type="text" class="form-control" id="phone" placeholder="联系电话" value="${user.phone}">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-md-2   col-sm-2  control-label">联系地址:</label>
                <div class="col-md-8 col-sm-10">
                    <input type="text" class="form-control" id="address" placeholder="联系电话" value="${user.address}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning">确认修改</button>
                </div>
            </div>
        </form>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>修改头像</h3>
                </div>
            </div>
        </div>
        <form action="" class="form-horizontal">
            <div class="form-group">
                <label for="address" class="col-md-2   col-sm-2  control-label">选择头像:</label>
                <div class="col-md-10 col-sm-10">
                    <img src="${pageContext.request.contextPath}/images/add.png" id="showImg" class="togeImg" onclick="openFile()" alt="" width="100" height="100">
                    <input id="file" type="file" style="display: none;" name="imgName" />
                    <script>
                    function openFile() {
                        $("#file").click();
                    }
                    $('#file').change(function() {
                        $("#showImg").attr("src", window.URL.createObjectURL(this.files[0]));
                    });
                    </script>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-warning">确认修改</button>
                </div>
            </div>
        </form>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        版权所有：南京网博
    </div>
    <!-- footers end -->
    <!-- 修改密码模态框 -->
    <div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <form action="" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">原密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">重复密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="submit" class="btn btn-warning">修&nbsp;&nbsp;改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- 登录模态框 -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">登&nbsp;陆</h4>
                </div>
                <form action="" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录帐号：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">验证码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                            <div class="col-sm-2 input-height">
                                1234
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- 注册模态框 -->
    <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">会员注册</h4>
                </div>
                <form action="" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户姓名:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登陆账号:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录密码:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系电话:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系地址:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="submit" class="btn btn-warning">注&nbsp;&nbsp;册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

</html>