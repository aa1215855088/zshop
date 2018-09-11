<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>backend</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>

</head>
<script>

    $(function () {
        $("#doSearch").unbind("click");
        $("#doSearch").bind("click", function () {
            $("#pageNum").val(1)
            $("#findSysuserForm").submit();
        })
        $("#role").val(${findSysuser.role.id});
        $("#status").val(${findSysuser.isValid});
        $("#addSysuserForm").bootstrapValidator({
            feedbackIcons: {        //提示图标
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: "用户姓名不能为空"
                        }
                    }
                },
                loginName: {
                    validators: {
                        notEmpty: {
                            message: "登录账户不能为空"
                        },
                        remote: {
                            url: "${pageContext.request.contextPath}/backend/sysuser/checkLoginName"
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: "登录密码不能为空"
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: "手机号码不能为空"
                        },
                        regexp: {
                            regexp: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
                            message: "请输入正确的手机号码"
                        }
                    }
                },
                email: {
                    validators: {
                        message: "",
                        notEmpty: {
                            message: "邮箱不能为空"
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message: "邮箱格式不正确"
                        }
                    }
                }
            }
        });
        $("#updateSysuserForm").bootstrapValidator({
            feedbackIcons: {        //提示图标
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: "用户名不能为空"
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: "手机号码不能为空"
                        },
                        regexp: {
                            regexp: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
                            message: "请输入正确的手机号码"
                        }
                    }
                },
                email: {
                    validators: {
                        message: "",
                        notEmpty: {
                            message: "邮箱不能为空"
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                            message: "邮箱格式不正确"
                        }
                    }
                }
            }
        });
        $("#paginator").bootstrapPaginator({
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
                $("#findSysuserForm").submit();
            }
        })
    });

    function showSysuser(id) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/backend/sysuser/showSysuser",
            data: {
                id: id
            },
            dataType: "json",
            success: function (result) {
                $("#MargerStaffId").val(result.data.id);
                $("#MargerStaffname").val(result.data.name);
                $("#MargerLoginName").val(result.data.loginName);
                $("#MargerPhone").val(result.data.phone);
                $("#MargerAdrees").val(result.data.email);
                $("#MargerRole").val(result.data.role.id);
            }
        })
    }

    function updateSysuser() {
        var serialize = $("#updateSysuserForm").serialize();
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/backend/sysuser/updateSysuser",
            data: serialize,
            success: function () {
                layer.msg("修改成功", {
                    icon: 1,
                    time: 2000
                }, function () {
                    $("#findSysuserForm").submit();
                })
            }
        })
    }

    function updateValid(id, isValid, obj) {

        layer.confirm("确定要" + $(obj).val() + "此用户吗?", {
            title: "提示",
            icon: 4
        }, function (e) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/sysuser/updateValid",
                data: {
                    id: id,
                    isValid: isValid == 1 ? 0 : 1
                },
                dataTyee: "json",
                success: function (result) {
                    layer.msg(result.message, {
                        icon: result.status == 1 ? 1 : 2,
                        time: 1000
                    }, function () {
                        $("#findSysuserForm").submit();
                    });
                }
            })
            layer.close(e)
        })

    }
</script>
<body>
<!-- 系统用户管理 -->
<div class="panel panel-default" id="adminSet">
    <div class="panel-heading">
        <h3 class="panel-title">系统用户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showmargersearch">
            <form id="findSysuserForm" class="form-inline"
                  action="${pageContext.request.contextPath}/backend/sysuser/find">
                <div class="form-group">
                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                    <label for="userName">姓名:</label>
                    <input type="text" class="form-control" name="name" value="${findSysuser.name}" id="userName"
                           placeholder="请输入姓名">
                </div>
                <div class="form-group">
                    <label for="loginName">帐号:</label>
                    <input type="text" class="form-control" name="loginName" value="${findSysuser.loginName}"
                           id="loginName"
                           placeholder="请输入帐号">
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="text" class="form-control" name="phone" value="${findSysuser.phone}" id="phone"
                           placeholder="请输入电话">
                </div>
                <div class="form-group">
                    <label for="role">角色</label>
                    <select class="form-control" name="role.id" id="role">
                        <option value="-1">全部</option>
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">状态</label>
                    <select class="form-control" name="isValid" id="status">
                        <option value="-1">全部</option>
                        <option value="1">---有效---</option>
                        <option value="0">---无效---</option>
                    </select>
                </div>
                <input type="button" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <input type="button" value="添加系统用户" class="btn btn-primary" id="doAddManger">
        <div class="show-list text-center" style="position: relative; top: 10px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">注册时间</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="sysuser">
                    <tr>
                        <td>${sysuser.id}</td>
                        <td>${sysuser.name}</td>
                        <td>${sysuser.loginName}</td>
                        <td>${sysuser.phone}</td>
                        <td>${sysuser.email}</td>
                        <td>${sysuser.isValid==1?"有效":"无效"}</td>
                        <td><fmt:formatDate value="${sysuser.createDate}" type="DATE"/></td>
                        <td>${sysuser.role.name}</td>
                        <td class="text-center">
                            <input onclick="showSysuser(${sysuser.id})" type="button"
                                   class="btn btn-warning btn-sm doMangerModify" value="修改">
                            <c:if test="${sysuser.isValid==1}">
                                <input onclick="updateValid(${sysuser.id},${sysuser.isValid},this)" type="button"
                                       class="btn btn-danger btn-sm doMangerDisable" value="禁用">
                            </c:if>
                            <c:if test="${sysuser.isValid==0}">
                                <input onclick="updateValid(${sysuser.id},${sysuser.isValid},this)" type="button"
                                       class="btn btn-success btn-sm doMangerDisable" value="启动">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="paginator"></ul>
        </div>
    </div>
</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="myMangerUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form id="addSysuserForm" action="${pageContext.request.contextPath}/backend/sysuser/add" method="post"
              class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加系统用户</h4>
                </div>
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <div class="modal-body text-center">
                    <div class="form-group text-right">
                        <label for="marger-username" class="col-sm-4 control-label">用户姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="name" id="marger-username">
                        </div>
                    </div>
                    <br>
                    <div class="form-group text-right">
                        <label for="marger-loginName" class="col-sm-4 control-label">登录帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="loginName" id="marger-loginName">
                        </div>
                    </div>
                    <br>
                    <div class="form-group text-right">
                        <label for="marger-password" class="col-sm-4 control-label">登录密码：</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" name="password" id="marger-password">
                        </div>
                    </div>
                    <br>
                    <div class="form-group text-right">
                        <label for="marger-phone" class="col-sm-4 control-label">联系电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="phone" id="marger-phone">
                        </div>
                    </div>
                    <br>
                    <div class="form-group text-right">
                        <label for="marger-adrees" class="col-sm-4 control-label">联系邮箱：</label>
                        <div class="col-sm-4">
                            <input type="text" name="email" class="form-control" id="marger-email">
                        </div>
                    </div>
                    <br>
                    <div class="form-group text-right">
                        <label for="role"
                               class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                        <div class=" col-sm-4">
                            <select class="form-control" name="role.id">
                                <option>请选择</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal" type="button">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加系统用户 end -->

<!-- 修改系统用户 start -->
<div class="modal fade" tabindex="-1" id="myModal-Manger">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form id="updateSysuserForm" method="post"
              class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">系统用户修改</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="MargerUsername" class="col-sm-4 control-label">用户编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="id" id="MargerStaffId" readonly="readonly">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerUsername" class="col-sm-4 control-label">用户姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="name" id="MargerStaffname">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerLoginName" class="col-sm-4 control-label">登录帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="loginName" id="MargerLoginName"
                                   readonly="readonly">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerPhone" class="col-sm-4 control-label">联系电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="phone" id="MargerPhone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerAdrees" class="col-sm-4 control-label">联系邮箱：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="email" id="MargerAdrees">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                        <div class=" col-sm-4">
                            <select class="form-control" id="MargerRole" name="role.id">
                                <option>请选择</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button id="update" onclick="updateSysuser()" type="button" class="btn btn-primary doMargerModal">
                        修改
                    </button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改系统用户 end -->

</body>

</html>