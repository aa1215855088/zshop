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
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script>
        $(function () {
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
                pageUrl: function (type, page, current) {
                    if (current == page) {
                        return "javascript:void(0)";
                    } else {
                        return "${pageContext.request.contextPath}/backend/productType/findAll?pageNum=" + page;
                    }
                }
            })
        });

        function addProductType() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/productType/add",
                data: {
                    typeName: $("#productTypeName").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.status == 1) {
                        layer.msg(data.message, {icon: 1}, function () {
                            window.location = "${pageContext.request.contextPath}/backend/productType/findAll?pageNum=" +${pageInfo.pageNum};
                        })
                    } else {

                        layer.msg(data.message, {icon: 2})
                    }
                }
            })
        }

        //修改商品类型状态
        function enabledAndDisable(id, obj) {
            layer.confirm("确定" + $(obj).val(), {icon: 3, title: '提示'}, function (e) {
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/backend/productType/updateStatus",
                    data: {
                        id: id,
                        status: $(obj).val() == "启用" ? 1 : 0
                    },
                    dataType: "json",
                    success: function (result) {
                        layer.msg(result.message, {
                            icon: result.status==1?1:2,
                            time: 1000
                        }, function () {
                            if (result.status == 1) {
                                window.location = "${pageContext.request.contextPath}/backend/productType/findAll?pageNum=" +${pageInfo.pageNum};
                            }
                        });
                        layer.close(e)
                    }
                });
            }, function () {

            });
        };

        //显示商品类型
        function showProductType(id) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/productType/findById",
                data: {
                    id: id
                },
                dataType: "json",
                success: function (data) {
                    $("#proTypeNum").val(data.data.typeId);
                    $("#proTypeName").val(data.data.typeName);
                }
            });
        }

        //修改商品类型名称
        function updateProductTypeName() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backend/productType/updateName",
                data: {
                    id: $("#proTypeNum").val(),
                    name: $("#proTypeName").val()
                },
                dataType: "json",
                success: function (result) {
                    if (result.status == 1) {
                        layer.msg("修改成功", {
                            time: 1000,
                            icon: 1
                        }, function () {
                            //重新加载此页面
                            window.location = "${pageContext.request.contextPath}/backend/productType/findAll?pageNum=" +${pageInfo.pageNum};
                        })
                    } else {
                        layer.msg(result.message, {icon: 2})
                    }
                }
            });
        }

        //删除商品类型
        function del(obj, id) {
            layer.confirm("确定要删除吗", {icon: 3, title: "提示"}, function (e) {
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/backend/productType/del",
                    data: {
                        id: id
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.status == 1) {
                            $(obj).parent().parent().empty()
                        }
                        layer.close(e);
                        layer.msg(result.message)
                    }
                });
            }, function () {

            })
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">商品类型管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">类型名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:if test="${empty pageInfo.list}">没有类型</c:if>
                <c:forEach items="${pageInfo.list}" var="productType">
                    <tr>
                        <td>${productType.typeId}</td>
                        <td>${productType.typeName}</td>
                        <td>${productType.typeStatus==1?"启用":"禁用"}</td>
                        <td class="text-center">
                            <input type="button" onclick="showProductType(${productType.typeId})"
                                   class="btn btn-warning btn-sm doProTypeModify" value="修改">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete"
                                   onclick="del(this,${productType.typeId})" value="删除">
                            <c:if test="${productType.typeStatus!=1}">
                                <input type="button" class="btn btn-success btn-sm doProTypeDisable"
                                       onclick="enabledAndDisable(${productType.typeId},this)"
                                       value="启用">
                            </c:if>
                            <c:if test="${productType.typeStatus==1}">
                                <input type="button" class="btn btn-danger btn-sm doProTypeDisable"
                                       onclick="enabledAndDisable(${productType.typeId},this)"
                                       value="禁用">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <%--<tr>
                    <td>1</td>
                    <td>aaaa</td>
                    <td>启用</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm doProTypeModify" value="修改">
                        <input type="button" class="btn btn-warning btn-sm doProTypeDelete" value="删除">
                        <input type="button" class="btn btn-success btn-sm doProDisable" value="启用">
                    </td>
                </tr>--%>
                </tbody>
            </table>
            <%--使用bootrtrap-pagubator分页插件--%>
            <ul id="paginator"></ul>
        </div>
    </div>
</div>

<!-- 添加商品类型 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="productTypeName">
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addProductType" onclick="addProductType()">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 添加商品类型 end -->

<!-- 修改商品类型 start -->
<div class="modal fade" tabindex="-1" id="myProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="proTypeNum" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="proTypeName">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-warning updateProType" onclick="updateProductTypeName()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改商品类型 end -->
</body>

</html>