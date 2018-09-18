$(function () {
    //用户注册表单验证
    $("#addUserForm").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    }   //非空提
                }
            },
            loginName: {
                validators: {
                    notEmpty: {message: '请输入登录账号'},    //非空提示
                    stringLength: {    //长度限制
                        min: 6,
                        max: 14,
                        message: '登录账号长度必须在6到14之间'
                    },
                    remote: {
                        url: "/user/checkLoginName"
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {message: '请输入密码'},
                    stringLength: {    //长度限制
                        min: 6,
                        max: 15,
                        message: '密码长度必须在6到15之间'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {message: '请输入手机号码'},
                    regexp: {//匹配规则
                        regexp: /^1[34578]\d{9}$/,  //正则表达式
                        message: '请输入正确的手机格式'
                    },
                    remote: {
                        url: "/user/checkPhone"
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '请输入联系地址'
                    }   //非空提
                }
            }
        }
    });
    //密码框验证
    $("#updatePwdForm").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            oldPassword: {
                validators: {
                    notEmpty: {
                        message: '请输入原密码'
                    },
                    remote: {
                        url: "/user/checkPassword"
                    }
                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: '密码长度必须在6到15之间'
                    },
                    different: {  //比较
                        field: 'oldPassword', //需要进行比较的input name值
                        message: '请输入新密码'
                    }
                }
            },
            newPassword2: {
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    },
                    identical: {  //比较是否相同
                        field: 'newPassword',  //需要进行比较的input name值
                        message: '两次密码不一致'
                    }
                }
            }
        }
    });
});

function center() {

    $.ajax({
        type: "post",
        url: "/zshop/Authentication",
        success: function (data) {
            if (data.status == 1) {
                window.location = "/zshop/center"
            } else if (data.status == 2) {
                layer.msg(data.message, {
                    time: 2000,
                    icon: 7
                })
                $("#loginModal").modal("show");
            }
        }
    })

};

function myOrders() {
    $.ajax({
        type: "post",
        url: "/zshop/Authentication",
        success: function (data) {
            if (data.status == 1) {
                window.location = "/zshop/myOrders"
            } else if (data.status == 2) {
                layer.msg(data.message, {
                    time: 2000,
                    icon: 7
                })
                $("#loginModal").modal("show");
            }
        }
    })

};


//结算
function order() {
    var ids = $("input:checkbox[name='id']").serialize();


    if (ids == "" || ids == null) {
        layer.msg("请选择你需要结算的商品！")
    } else {
        $.ajax({
            type: "post",
            url: "/zshop/Authentication",
            success: function (data) {
                if (data.status == 1) {
                    window.location = "/zshop/order?" + ids
                } else if (data.status == 2) {
                    layer.msg(data.message, {
                        time: 2000,
                        icon: 7
                    })
                    $("#loginModal").modal("show");
                }
            }
        })

    }
};

//登录
function login() {
    $.ajax({
        type: "post",
        url: "/zshop/login",
        data: {
            username: $("#username").val(),
            password: $("#password").val()
        },
        beforeSend: function () {
            loading = layer.load("登录中.......");
        },
        success: function (data) {
            if (data.status == 1) {
                //刷新
                location.reload();

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
    });
}