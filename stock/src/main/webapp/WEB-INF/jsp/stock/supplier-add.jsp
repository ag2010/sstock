<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供货商名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="supname" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <textarea class="textarea"  rows="2" cols="36"  name="address"  id="address"  value=""  style="height:60px;"></textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>座机号码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text"  placeholder="" id="tel"
                       name="tel">
            </div>
        </div>
       <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="phone" name="phone">
            </div>
        </div>
       <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="email" name="email">
            </div>
        </div>
       <div class="row cl">  <!--  formControls col-xs-8 col-sm-4"   "formControls col-xs-7 col-sm-4 skin-minimal" -->
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <select name="status">
                    <option value="1" selected="selected">启用</option>
                    <option value="0">禁用</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script type="text/javascript">
   // 只能输入英文
 /*    jQuery.validator.addMethod("english", function (value, element) {
        var chrnum = /^([a-zA-Z]+)$/;
        return this.optional(element) || (chrnum.test(value));
    }, "只能输入字母"); */
    $("#form-user-add").validate({
        rules: {
        	name: {
                required: true,
                minlength: 5,
                isSpace: true,
             /*    remote: {
                    url: "${context_root}/stock/checkNameUnique.action",
                    type: "post",
                    dataType: "text",
                    data: {
                        name: function () {
                            return $.trim($("#supname").val());
                        }
                    },
                    dataFilter: function (data, type) {
                        if (data == "0") return true;
                        else return false;
                    }
                }  */
            },
            address: {
                required: true,
                isSpace: true,
                minlength: 6
            },
            tel: {
                required: true,
                isSpace: true,
                minlength: 6,
               
            },
            phone: {
                required: true,
                isPhone: true
            },
            email: {
                required: true,
                email: true
            },
            roleId: {
                required: true,
            },
        },
     /*    messages: {
            "name": {
                remote: "该用户已经存在"
            }
        }, */
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            $.ajax({
                url: "${context_root}/stock/saveSupplier.action",
                type: 'post',
                async: true,
                cache: false,
                data: $(form).serialize(),
                dataType: "json",
                success: function (data) {
                    parent.layer.close(index);
                    if (data.s == true) {
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("保存成功,正在刷新数据请稍后……", {icon: 1, time: 1000, shade: [0.1, '#fff']}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });
        }
    });
</script>
</body>
</html>