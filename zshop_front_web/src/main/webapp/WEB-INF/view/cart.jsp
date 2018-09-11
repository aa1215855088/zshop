<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>我的购物车</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>

</head>
<script>

    $(function () {
        $("#all").click(function () {
            if ($("#all").prop("checked") == true) {
                $("input:checkbox[name='id']").prop("checked", true);
            } else {
                $("input:checkbox[name='id']").prop("checked", false);
            }
        });
    })

    function clearCart() {
        layer.confirm("确定要清空购物车吗？", {
            icon: 3
        }, function (e) {
            window.location = "${pageContext.request.contextPath}/zshop/clearCart"

            layer.close(e)
        })
    }

    function clearCartById(id, name) {
        layer.confirm("确定要删除" + name + "?", {
            icon: 3
        }, function (e) {
            window.location = "${pageContext.request.contextPath}/zshop/clearCart?id=" + id
            layer.close(e)
        })
    }

    function clearCartByIds() {
        var ids = $("input:checkbox[name='id']").serialize();

        layer.confirm("确定要删除选定项吗?", {
            icon: 3
        }, function (e) {
            if (ids == "" || ids == null) {
                layer.msg("选择你要删除的商品！")
            } else {
                window.location = "${pageContext.request.contextPath}/zshop/clearCartByIds?" + ids
                layer.close(e)

            }
        })
    }
</script>
<body>
<div class="navbar navbar-default clear-bottom">
    <div class="container">
        <!-- logo start -->
        <div class="navbar-header">
            <a class="navbar-brand logo-style" href="http://edu.51cto.com/center/lec/info/index?user_id=12392007">
                <img class="brand-img" src="${pageContext.request.contextPath}/images/com-logo1.png" alt="logo"
                     height="70">
            </a>
        </div>
        <!-- logo end -->
        <!-- navbar start -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${pageContext.request.contextPath}/zshop/home">商城主页</a>
                </li>
                <li>
                    <a href="myOrders.html">我的订单</a>
                </li>
                <li class="active">
                    <a href="cart.jsp">购物车</a>
                </li>
                <li class="dropdown">
                    <a href="center.jsp">会员中心</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="userInfo">
                <c:choose>
                    <c:when test="${empty user}">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="userName">
                            您好：${user.name}！
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg"
                                     height="30"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                        <i class="glyphicon glyphicon-cog"></i>修改密码
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/zshop/logout">
                                        <i class="glyphicon glyphicon-off"></i> 退出
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>

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
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th>
                <input type="checkbox" id="all">
            </th>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品数量</th>
            <th>商品总价</th>
            <th>操作</th>
        </tr>
        <c:choose>
            <c:when test="${buyerCart==null}">
                <tr>
                    <td> 购物车里没有商品!</td>
                </tr>
                <tr>
                    <td colspan="7" align="right">
                        <button class="btn btn-warning margin-right-15" type="button"> 继续购物</button>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${buyerCart.items}" var="buyerItem">
                    <tr>
                        <td>
                            <input type="checkbox" name="id" value="${buyerItem.product.id}">
                        </td>
                        <td>${buyerItem.product.id}</td>
                        <td>${buyerItem.product.name}</td>
                        <td><img
                                src="${pageContext.request.contextPath}/zshop/showImage?path=${buyerItem.product.image}"
                                alt="" width="60" height="60">
                        </td>
                        <td>
                            <input type="text" value="${buyerItem.amount}" size="5">
                        </td>
                        <td>${buyerItem.priceSum}</td>
                        <td>
                            <button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"
                                                                                aria-hidden="true"></span>修改
                            </button>
                            <button class="btn btn-danger" type="button"
                                    onclick="clearCartById(${buyerItem.product.id},'${buyerItem.product.name}')">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </td>
                    </tr>

                </c:forEach>
                <tr>
                    <td colspan="7" align="right">
                        <button onclick="clearCartByIds()" class="btn btn-warning margin-right-15" type="button">
                            删除选中项
                        </button>
                        <button onclick="clearCart()" class="btn btn-warning  margin-right-15" type="button"> 清空购物车
                        </button>
                        <button onclick="window.history.back(-1)" class="btn btn-warning margin-right-15" type="button">
                            继续购物
                        </button>
                        <a href="order.html">
                            <button class="btn btn-warning " type="button"> 结算</button>
                        </a>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="7" align="right" class="foot-msg">
                总计： <b><span>${buyerCart.totalPrice}</span></b>
            </td>
        </tr>
    </table>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>