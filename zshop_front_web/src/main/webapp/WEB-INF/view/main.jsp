<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>
    <script src="${pageContext.request.contextPath}/js/template.js"></script>
</head>
<script id="info" type="text/html">
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
</script>
<script>
    $(function () {

        $("#login").bind("click", function () {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/zshop/login",
                data: {
                    username: $("#username").val(),
                    password: $("#password").val()
                },
                beforeSend: function () {
                    loading = layer.load("登录中.......");
                },
                success: function (data) {
                    if (data.status == 1) {
                        // $("#loginModal").modal("hide")
                        // //局部刷新
                        // var info = template.get("info")
                        // $("#userInfo").html(info);
                        window.location = location
                    } else if (data.status == 2) {
                        layer.msg(data.message, {
                            time: 2000,
                            icon: 7
                        })
                    }
                },
                error: function () {
                    layer.msg("系统繁忙，请重试!", {
                        icon: 7,
                        time: 2000
                    })
                },
                complete: function () {
                    layer.close(loading);
                }
            })
        })

        $("#queryProduct").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                minPrice: {
                    validators: {
                        regexp: {
                            regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                            message: "请输入正确的格式"
                        },

                    }

                },
                maxPrice: {
                    validators: {
                        regexp: {
                            regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                            message: "请输入正确的格式"
                        },
                    }
                }

            }
        })
        ;

        if (${responseResult.status==3}) {
            layer.msg("没有此类的商品请重新查询", {
                time: 2000
            }, function () {
                window.history.back(-1);
            })
        }
        $("#page").bootstrapPaginator({
            bootstrapMajorVersion: 3,
            currentPage:${pageInfo.pageNum},
            totalPages:${pageInfo.pages},
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "尾页";
                    case "page":
                        return page
                }
            },
            onPageClicked: function (event, origianEvent, type, page) {
                $("#pageNum").val(page);
                window.location = "${pageContext.request.contextPath}/zshop/home?" + $("#queryProduct").serialize();
            }
        });
        $("#dosearch").unbind("click");
        $("#dosearch").bind("click", function () {
            /*  $("#queryProduct").submit();*/

            var bootstrapValidators = $("#queryProduct").data('bootstrapValidator').validate();
            if (bootstrapValidators.isValid()) {
                // todo
                window.location = "${pageContext.request.contextPath}/zshop/home?" + $("#queryProduct").serialize()
            }
        })
    })

    /*加入购物车*/
    function addCart(id) {
        window.location = "${pageContext.request.contextPath}/zshop/addCart?id=" + id
    }
</script>
<body>
<div id="wrapper">
    <!-- navbar start -->
    <div class="navbar navbar-default clear-bottom">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand logo-style" href="http://edu.51cto.com/center/lec/info/index?user_id=12392007">
                    <img class="brand-img" src="${pageContext.request.contextPath}/images/com-logo1.png" alt="logo"
                         height="70">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="#">商城主页</a>
                    </li>
                    <li>
                        <a href="myOrders.html">我的订单</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/zshop/cart">购物车</a>
                    </li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/zshop/center">会员中心</a>
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
        </div>
    </div>
    <!-- navbar end -->
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>商品列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" id="queryProduct" method="post"
                      action="${pageContext.request.contextPath}/zshop/home">
                    <div class="form-group">
                        <label class="control-label">商品:</label>
                        <input type="hidden" id="pageNum" name="pageNum" value="${pageInfo.pageNum}">
                        <input type="text" value="${queryProduct.productName}" name="ProductName" class="form-control"
                               placeholder="商品名称">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">价格:</label>
                        <input id="minPrice" type="text" value="${queryProduct.minPrice}" class="form-control"
                               name="minPrice"
                               placeholder="最低价格"> --
                        <input id="maxPrice" type="text" value="${queryProduct.maxPrice}" class="form-control"
                               name="maxPrice"
                               placeholder="最高价格">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">种类:</label>
                        <select class="form-control input-sm" name="productTypeId">
                            <option value="-1" selected="selected">查询全部</option>
                            <c:forEach items="${findAllProductType}" var="productType">
                                <c:if test="${productType.typeId==queryProduct.productTypeId}">
                                    <option value="${productType.typeId}" selected>${productType.typeName}</option>
                                </c:if>
                                <option value="${productType.typeId}">${productType.typeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <button id="dosearch" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container text-center" id="a">
            <div class="row ">
                <c:forEach items="${pageInfo.list}" var="product">
                    <div class="col-xs-3  hot-item">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="${pageContext.request.contextPath}/zshop/showImage?path=${product.image}"
                                             class="shopImg">
                                    </div>
                                    <h4><a href="">${product.name}</a></h4>
                                    <div class="user clearfix pull-right">
                                        ￥${product.price}
                                    </div>
                                    <div class="desc">${product.info}
                                    </div>
                                    <div onclick="addCart(${product.id})" class="attention pull-right">
                                        加入购物车 <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <ul id="page"></ul>
        </div>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        版权所有：南京网博
    </div>
    <!-- footers end -->
</div>

<!-- 修改密码模态框 start -->
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
<!-- 修改密码模态框 end -->

<!-- 登录模态框 start  -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <!-- 用户名密码登陆 start -->
        <div class="modal-content" id="login-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户名密码登录</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" id="username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" id="password" type="password" placeholder="请输入密码">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" id="login" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                </div>
            </form>
        </div>
        <!-- 用户名密码登陆 end -->
        <!-- 短信快捷登陆 start -->
        <div class="modal-content" id="login-sms" style="display: none;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">短信快捷登陆</h4>
            </div>
            <form class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input class="form-control" id="phone" type="text" placeholder="请输入手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="verificationCode" type="text" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-2">
                            <button class="pass-item-timer" id="send" type="button" onclick="SendVerificationCode()">
                                发送验证码
                            </button>
                            <script>
                                function SendVerificationCode() {
                                    if (!(/^1[34578]\d{9}$/.test($("#phone").val()))) {
                                        layer.msg("请输入正确的手机号码", {
                                            time: 2000,
                                            icon: 7
                                        })
                                    } else {
                                        $.ajax({
                                            type: "post",
                                            url: "${pageContext.request.contextPath}/zshop/SendVerificationCode",
                                            data: {
                                                phone: $("#phone").val()
                                            },
                                            success: function (data) {
                                                if (data.status == 1) {
                                                    var time = 60;
                                                    var t = setInterval(function () {
                                                        time--;
                                                        if (time > 0) {
                                                            $("#send").text("已发送(" + time + ")");
                                                            $('#send').attr("disabled", true);
                                                        } else {
                                                            $("#send").text("重新发送");
                                                            $('#send').attr("disabled", false);
                                                            clearInterval(t);
                                                        }

                                                    }, 1000)
                                                } else if (data.status == 2) {
                                                    layer.msg(data.message, {
                                                        time: 2000,
                                                        icon: 7
                                                    })
                                                } else if (data.status == 3) {
                                                    layer.msg("该手机号未注册!请先注册", {
                                                        time: 2000,
                                                        icon: 7
                                                    });
                                                    $("#zcPhone").val($("#phone").val());
                                                    $("#loginModal").modal("hide");
                                                    $("#registModal").modal("show");
                                                }
                                            },
                                            error: function () {
                                                layer.msg("系统繁忙，请重试!", {
                                                    icon: 7,
                                                    time: 2000
                                                })
                                            }
                                        });
                                    }

                                };

                                function textLogin() {
                                    $.ajax({
                                        type: "post",
                                        url: "${pageContext.request.contextPath}/zshop/textLogin",
                                        data: {
                                            phone: $("#phone").val(),
                                            verificationCode: $("#verificationCode").val(),
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.status == 1) {
                                                window.location = location
                                            } else if (data.status == 2) {
                                                layer.msg(data.message, {
                                                    time: 2000,
                                                    icon: 7
                                                })
                                            }
                                        },
                                        error: function () {
                                            layer.msg("系统繁忙，请重试!", {
                                                icon: 7,
                                                time: 2000
                                            })
                                        }
                                    })
                                }
                            </script>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" onclick="textLogin()" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                </div>
            </form>
        </div>
        <!-- 短信快捷登陆 end -->
    </div>
</div>
<!-- 登录模态框 end  -->

<!-- 注册模态框 start -->
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
                            <input class="form-control" id="zcPhone" type="text">
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
<!-- 注册模态框 end -->
</body>

</html>