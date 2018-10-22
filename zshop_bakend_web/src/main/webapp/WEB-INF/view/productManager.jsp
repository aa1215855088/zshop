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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script>
        $(function () {

            //使用bootstrapValidator进行表单验证
            $("#addProductForm").bootstrapValidator({
                feedbackIcons: {        //提示图标
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        validators: {
                            notEmpty: {        // 非空校验+提示信息
                                message: '商品名称不能为空'
                            },
                            remote: {
                                url: "${pageContext.request.contextPath}/backend/product/checkName"
                            }
                        }
                    },
                    price: {
                        validators: {
                            notEmpty: {        // 非空校验+提示信息
                                message: '商品价格不能为空'
                            },
                            regexp: {
                                regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                                message: "请输入正确的商品价格"
                            }
                        }
                    },
                    image: {
                        validators: {
                            notEmpty: {
                                message: "请选择图片"
                            }
                        }
                    },
                    productTypeId: {
                        validators: {
                            notEmpty: {
                                message: "请选择商品类型"
                            }
                        }
                    }
                }
            });
            $("#updateForm").bootstrapValidator({
                feedbackIcons: {        //提示图标
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        validators: {
                            notEmpty: {        // 非空校验+提示信息
                                message: '商品名称不能为空'
                            }
                        }
                    },
                    price: {
                        validators: {
                            notEmpty: {        // 非空校验+提示信息
                                message: '商品价格不能为空'
                            },
                            regexp: {
                                regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                                message: "请输入正确的商品价格"
                            }
                        }
                    },
                    productTypeId: {
                        validators: {
                            notEmpty: {
                                message: "请选择商品类型"
                            }
                        }
                    }
                }
            });
            //上传图像预览
            $('#product-image').on('change', function () {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change', function () {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
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
                pageUrl: function (type, page, current) {
                    if (current == page) {
                        return "javascript:void(0)";
                    } else {
                        return "${pageContext.request.contextPath}/backend/product/findAll?pageNum=" + page;
                    }
                }
            })
        });

        function showProduct(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/backend/product/showProduct",
                data: {
                    id: id
                },
                dataType: "json",
                success: function (result) {
                    if (result.status == 1) {
                        $("#pro-num").val(result.data.id);
                        $("#pro-name").val(result.data.name);
                        $("#pro-price").val(result.data.price);
                        $("#img2").prop('src', result.data.image);
                        $("#pro_productType").val(result.data.productType.typeId);
                    }
                }
            })
        }

        function delProduct(id, name, obj) {
            layer.confirm("确定要删除(" + name + ")吗", {
                icon: 3,
                title: "提示"
            }, function (e) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/backend/product/delProduct",
                    data: {
                        id: id
                    },
                    dataType: "json",
                    success: function (result) {
                        $(obj).parent().parent().empty()
                        layer.msg(result.message)
                    }
                })
                layer.close(e)
            })

        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">商品管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">商品</th>
                    <th class="text-center">价格</th>
                    <th class="text-center">产品类型</th>
                    <th class="text-center">信息</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:if test="${empty pageInfo.list}">没有商品</c:if>
                <c:forEach items="${pageInfo.list}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.productType.typeName}</td>
                        <td>${product.info}</td>
                        <td class="text-center">
                            <input onclick="showProduct(${product.id})" type="button"
                                   class="btn btn-warning btn-sm doProModify" value="修改">
                            <input onclick="delProduct(${product.id},'${product.name}',this)" type="button"
                                   class="btn btn-warning btn-sm doProDelete" value="删除">
                        </td>
                    </tr>
                </c:forEach>
                <%-- <tr>
                     <td>1</td>
                     <td>平板</td>
                     <td>2999</td>
                     <td>电子产品</td>
                     <td>有效商品</td>
                     <td class="text-center">
                         <input type="button" class="btn btn-warning btn-sm doProModify" value="修改">
                         <input type="button" class="btn btn-warning btn-sm doProDelete" value="删除">
                     </td>
                 </tr>--%>
                </tbody>
            </table>
            <ul id="paginator"></ul>
        </div>
    </div>
</div>

<!-- 添加商品 start -->
<div class="modal fade" tabindex="-1" id="Product">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form id="addProductForm" action="${pageContext.request.contextPath}/backend/product/add" method="post"
              enctype="multipart/form-data" class="form-horizontal">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品</h4>
                </div>
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" name="name" class="form-control" id="product-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" name="price" class="form-control" id="product-price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="image" id="product-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select name="productTypeId" class="form-control">
                                    <option value="">请选择</option>
                                    <c:forEach items="${productTypes}" var="productType">
                                        <option value="${productType.typeId}">${productType.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加商品 end -->

<!-- 修改商品 start -->
<div class="modal fade" tabindex="-1" id="myProduct">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/backend/product/updateProduct" method="post"
              class="form-horizontal" id="updateForm" enctype="multipart/form-data">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品</h4>
                </div>
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                            <div class="col-sm-8">
                                <input type="text" name="id" class="form-control" id="pro-num" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" name="name" class="form-control" id="pro-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" name="price" class="form-control" id="pro-price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    更新图片 <input type="file" name="image" id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select name="productTypeId" id="pro_productType" class="form-control">
                                    <c:forEach items="${productTypes}" var="productType">
                                        <option value="${productType.typeId}">${productType.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary updatePro">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
    <!-- 修改商品 end -->

</body>
<script>

</script>
</html>