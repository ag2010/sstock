<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-stores-modify">
        <input type="hidden" class="input-text" id="id" name="id" value="${stores.id}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>仓库名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${stores.storeName}" placeholder="" id="storeName" name="storeName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>仓库地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${stores.address}" placeholder="" id="address" name="address">
            </div>
        </div>
        <div class="row cl">
           <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
           <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <span class="select-box" style="width: 120px;">
		          <select name="status" id="status" class="select" autocomplete="off">
		              <option value="">状态</option>
		              <option value="1" <c:if test="${stores.status == 1}">selected="selected"</c:if>>启用</option>
		              <option value="0" <c:if test="${stores.status == 0}">selected="selected"</c:if>>禁用</option>
		          </select>
                </span>
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
		$("#form-stores-modify").validate({
			rules: {
				storeName: {
		            required: true,
		            isSpace: true,
		        },
		        address: {
		            required: true,
		            isSpace: true,
		        },
		        status: {
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
		        	url: "${context_root}/stock/savestores.action",
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