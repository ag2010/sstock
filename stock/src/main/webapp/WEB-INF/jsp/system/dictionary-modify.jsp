<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
        <input type="hidden" class="input-text" value="${dictionary.dictionaryId}" placeholder="" id="dictionaryId"
               name="dictionaryId">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" autocomplete="off" value="${dictionary.name}" placeholder=""
                       id="name" name="name">
            </div>
        </div>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">英文名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" autocomplete="off" value="${dictionary.nameEn}"  placeholder="" id="nameEn" name="nameEn">
            </div>
        </div>--%>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">编号：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${dictionary.no}" placeholder="" id="no" name="no">
            </div>
        </div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${dictionary.sort}" placeholder="" id="sort" name="sort">
            </div>
        </div>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>父级：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" readonly="readonly" class="input-text" value="${dictionary.name}" placeholder="" name="">
                <input type="hidden" class="input-text" value="${dictionary.dictionaryId}" placeholder="" name="parentId">
            </div>
        </div>--%>
        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">值：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${dictionary.value}" placeholder="" name="value">
            </div>
        </div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>类型编码：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${dictionary.typeCode}" placeholder="" name="typeCode">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>是否启用：</label>
            <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <select name="status">
                    <option value="1" <c:if test="${dictionary.status == 1}">selected="selected"</c:if>>启用</option>
                    <option value="0" <c:if test="${dictionary.status == 0}">selected="selected"</c:if>>不启用</option>
                </select>
            </div>
        </div>


        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-6">
                <textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符"
                          datatype="*10-100" dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
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
    $("#form-user-add").validate({
        rules: {
            name: {
                required: true,
                isSpace: true,
            },
            sort: {
                required: true,
                isSpace: true,
            },
            typeCode: {
                required: true,
                isSpace: true,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            $.ajax({
                url: "${context_root}/system/saveDictionary.action",
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