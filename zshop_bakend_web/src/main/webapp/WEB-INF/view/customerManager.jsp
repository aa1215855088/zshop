<%@ include file="../../commons/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        $("#customer_isValid").val(${queryCustomer.isValid})
        $("#updateCustomer").bootstrapValidator({
            feedbackIcons: {        //提示图标
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: "客户名称不能为空"
                        }
                    }
                },
                loginName: {
                    validators: {
                        notEmpty: {
                            message: "用户名不能为空"
                        },
                        stringLength: {
                            min: 3,
                            max: 30,
                            message: '用户名长度必须在3到30之间'
                        },
                        remote: {
                            url: "${pageContext.request.contextPath}/backend/customer/checkLoginName"
                        }

                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: "客户号码不能为空"
                        },
                        regexp: {
                            regexp: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
                            message: "请输入正确的手机号码"
                        }
                    }
                },
                address: {
                    validators: {
                        notEmpty: {
                            message: "地址不能为空"
                        }
                    }
                }
            }
        });

        $("#doSearch").unbind("click");
        $("#doSearch").bind("click", function () {
            $("#bbq").val(1);
        });
        $("#pagination").bootstrapPaginator({
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
                $("#doSearchForm").submit();
            }
        })
    });

    function updateIsValid(id, isValid, name, obj) {
        layer.confirm("确定要" + $(obj).val() + "(" + name + ")客户吗", {
            icon: 4,
            title: "提示"
        }, function (e) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/customer/updateValid",
                data: {
                    id: id,
                    isValid: isValid == 1 ? 0 : 1
                },
                dataType: "json",
                success: function (result) {
                    if (result.status == 1) {
                        layer.msg(result.message, {
                            icon: result.status == 1 ? 1 : 2,
                            time: 1000
                        }, function () {
                            window.location = "${pageContext.request.contextPath}/backend/customer/findAll?pageNum=" +
                            ${pageInfo.pageNum}
                        })
                    }
                }
            })
            layer.close(e)
        })


    };

    function showCustomer(id) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/backend/customer/showCustomer",
            data: {
                id: id
            },
            dataType: "json",
            success: function (result) {
                $("#id").val(result.data.id);
                $("#name").val(result.data.name);
                $("#loginName").val(result.data.loginName);
                $("#phone").val(result.data.phone);
                $("#address").val(result.data.address);
            }
        })
    }
</script>
<body>
<div class="panel panel-default" id="userInfo" id="homeSet">
    <div class="panel-heading">
        <h3 class="panel-title">客户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showusersearch">
            <form action="${pageContext.request.contextPath}/backend/customer/find" id="doSearchForm"
                  class="form-inline"
                  method="post">
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                <input type="hidden" name="bbq" id="bbq">
                <div class="form-group">
                    <label for="customer_name">姓名:</label>
                    <input type="text" class="form-control" value="${queryCustomer.name}" id="customer_name" name="name"
                           placeholder="请输入姓名"
                           size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_loginName">帐号:</label>
                    <input type="text" class="form-control" value="${queryCustomer.loginName}"
                           id="customer_loginName"
                           name="loginName" placeholder="请输入帐号"
                           size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_phone">电话:</label>
                    <input type="text" class="form-control" value="${queryCustomer.phone}" id="customer_phone"
                           name="phone"
                           placeholder="请输入电话"
                           size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_address">地址:</label>
                    <input type="text" class="form-control" value="${queryCustomer.address}" id="customer_address"
                           name="address" placeholder="请输入地址">
                </div>
                <div class="form-group">
                    <label for="customer_isValid">状态:</label>
                    <select class="form-control" id="customer_isValid" name="isValid">
                        <option value="-1">全部</option>
                        <option value="1">---有效---</option>
                        <option value="0">---无效---</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>

        <div class="show-list text-center" style="position: relative;top: 30px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.loginName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address}</td>
                        <td>${customer.isValid==1?'有效':"无效"}</td>
                        <td class="text-center">
                            <input onclick="showCustomer(${customer.id})" type="button"
                                   class="btn btn-warning btn-sm doModify" value="修改">
                            <c:if test="${customer.isValid==1}">
                                <input onclick="updateIsValid(${customer.id},${customer.isValid},'${customer.name}',this)"
                                       type="button"
                                       class="btn btn-danger btn-sm doDisable" value="禁用">
                            </c:if>
                            <c:if test="${customer.isValid==0}">
                                <input onclick="updateIsValid(${customer.id},${customer.isValid},'${customer.name}',this)"
                                       type="button"
                                       class="btn btn-success btn-sm doDisable" value="启动">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 修改客户信息 start -->
<div class="modal fade" tabindex="-1" id="myModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/backend/customer/updateCustomer" type="post"
              id="updateCustomer" class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改客户</h4>
                </div>
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="id" class="col-sm-4 control-label">编号：</label>
                        <div class="col-sm-4">
                            <input type="text" name="id" class="form-control" id="id" readonly>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="name" class="col-sm-4 control-label">姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" name="name" class="form-control" id="name">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="loginName" class="col-sm-4 control-label">帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" name="loginName" class="form-control" id="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="phone" class="col-sm-4 control-label">电话：</label>
                        <div class="col-sm-4">
                            <input type="text" name="phone" class="form-control" id="phone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="address" class="col-sm-4 control-label">地址：</label>
                        <div class="col-sm-4">
                            <input type="text" name="address" class="form-control" id="address">
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改客户信息 end -->
</body>

</html>