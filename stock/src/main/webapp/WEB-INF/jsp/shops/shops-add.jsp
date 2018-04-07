<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-shops-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商家名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系人：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" autocomplete="off" value="" placeholder="" id="contacts"
                       name="contacts">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>座机：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" autocomplete="off" value="" placeholder="" id="tel"
                       name="tel">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
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
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <textarea id="address" name="address" cols="50" rows="4"  class="textarea"></textarea>
                <!-- <input type="text" class="input-text" value="" placeholder="" id="address" name="address"> -->
            </div>
        </div>
        <div class="row cl">
           <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
           <div class="formControls col-xs-7 col-sm-4 skin-minimal">
                <span class="select-box" style="width: 120px;">
		          <select name="status" id="status" class="select" autocomplete="off">
		              <!-- <option value="">状态</option> -->
		              <option value="1">启用</option>
		              <option value="0">禁用</option>
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
		$("#form-shops-add").validate({
			rules: {
		        name: {
		            required: true,
		            isSpace: true,
		        },
		        contacts: {
		            required: true,
		            isSpace: true,
		        },
		        tel: {
		            required: true,
		            isSpace: true,
		        },
		        phone: {
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
		        	url: "${context_root}/shops/saveshops.action",
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