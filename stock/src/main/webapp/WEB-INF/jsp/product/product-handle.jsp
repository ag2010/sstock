<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<style>
    .input_bar {
        border: 1px solid rgb(169, 161, 168);
        border-radius: 4px;
        padding: 3px 4px;
        line-height: 2;
        margin: 0 20px 0 0 !important;
        color: rgb(122, 122, 122);
        font-size: 10px;
    }

    .input_bar > label {
        padding: 0 5px 0 0;
    }

    .input_bar > div {
        padding: 0;
    }

    .input_bar > input {
        box-sizing: border-box;
        border: none;
        /* border: solid 1px #ddd; */
        font-size: 14px;
        height: 31px;
        line-height: 1.42857;
        padding: 4px;
        transition: all .2s linear 0s;
        width: 130px;
    }

    .input_bar > textarea {
        border: none;
    }

    input:focus {
        outline: none;
    }

    input:hover {
        outline: none;
    }

    .row {
        margin: 0;
    }

    .select-box {
        padding: 0;
        width: 100px;
    }

    .alignCenter {
        text-align: center;
    }
     
    
</style>
<body>
<article>
    <form action="" method="post" class="form form-horizontal" id="form-role-add">
        <div class="row cl">
            <div class="row col-xs-3 col-sm-3 alignCenter">
                <input type="hidden" value="${product.productId}" name="productId" id="productId">
                <img src="${product.img}" width="80px" height="80px" class="image" id="image">
                <input type="file" class="picture" id="picture" accept="image/*" name="picture"
                       onchange="changImg(event)">
            </div>

            <div class="row col-xs-9 col-sm-9">
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>是否启用：
                        <y:select id="status" name="status"
                                  codeGroup="[{'id':'1','value':'启用'},{'id':'0','value':'弃用'}]"
                                  selectedValue="${product.status}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select>
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>内部编码：
                        <input type="text" value="${product.code}" placeholder="" id="code" name="code">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>条码：
                        <input type="text" value="${product.barCode}" placeholder="" id="barCode" name="barCode">
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>品名：
                        <input type="text" value="${product.productName}" placeholder="" id="productName"
                               name="productName">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>单位：
                        <y:select id="unit" name="unit" codeGroup="${proUnit}" selectedValue="${product.unit}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select>
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>保质期：
                        <input type="text"  id="expiryDate" name="expiryDate" value="${product.expiryDate}" >

                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>产地：
                        <%-- <y:select id="origin" name="origin" codeGroup="${proArea}" selectedValue="${product.origin}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select> --%>
                        <input id="origin" name="origin"  type="text" readonly value="${product.origin}" class="input-text" onclick="showMenu(this); return false;"/>
						<ul id="originHiddenTree" forInput="origin" class="ztree" style="display:none;background-color: white;">
						</ul>
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>规格：
                        <input type="text" value="${product.spec}" placeholder="" id="spec" name="spec">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>储存方法：
                        <y:select id="storageCondition" name="storageCondition"
                                  codeGroup="[{'id':'1','value':'常温 '},{'id':'0','value':'冷藏'}]"
                                  selectedValue="${product.storageCondition}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select>
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>分类：
                        <%-- <y:select id="categoryId" name="categoryId" codeGroup="${proType}"
                                  selectedValue="${product.categoryId}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select> --%> 
					 	<input id="categoryName" type="text" readonly value="${product.categoryName}" class="input-text" onclick="showMenu(this); return false;"/>
						<input id="categoryId"  name="categoryId"  type="hidden" value="${product.categoryId}"/> 
						<ul id="categoryHiddenTree" forInput="categoryName" class="ztree" style="display:none;background-color: white;">
						</ul> 
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>等级：
                        <y:select id="level" name="level"
                                  codeGroup="[{'id':'1','value':'一等品 '},{'id':'2','value':'二等品'},{'id':'0','value':'不良品'}]"
                                  selectedValue="${product.level}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select>
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>特征：
                        <input type="text" value="${product.characteristic}" placeholder="" id="characteristic"
                               name="characteristic">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>售价：
                        <input type="text" value="${product.salePrice}" placeholder="" id="salePrice" name="salePrice">
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>进价：
                        <input type="text" value="${product.purchasePrice}" placeholder="" id="purchasePrice"
                               name="purchasePrice">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>库存：
                        <input type="text" value="${product.stock}"  readonly  placeholder="" id="stock" name="stock">
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>供货商：
                        <y:select id="supplierId" name="supplierId" codeGroup="${proSupplier}"
                                  selectedValue="${product.supplierId}"
                                  cssClass="select" headerKey="" headerValue="请选择">
                        </y:select>
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>库存上限：
                        <input type="text" value="${product.stockTop}" placeholder="" id="stockTop" name="stockTop">
                    </div>
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>库存下限：
                        <input type="text" value="${product.stockDown}" placeholder="" id="stockDown" name="stockDown">
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-5 col-sm-5 input_bar">
                        <span class="c-red">*</span>仓库：
                        <y:select id="storesId" name="storesId"
                                  codeGroup=""
                                  selectedValue="${product.storesId}"
                                  cssClass="select" headerKey="c4f51326bbc24d2f80199c1af2161c56" headerValue="总库">
                        </y:select>
                    </div> 
                </div>

                <div class="row col-xs-12 col-sm-12">
                    <div class="row col-xs-9 col-sm-9 input_bar">
                        <textarea id="remark" name="remark" placeholder="备注" rows="4"
                                  style="width:100%;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;">${product.remark}</textarea>
                    </div>
                </div>
                <div class="row col-xs-12 col-sm-12">
                    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                        <br/>
                        <input class="btn btn-primary radius" type="submit" id="save"
                               value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                        <br/><br/>
                    </div>
                </div>

            </div>

        </div>

    </form>
</article>


<script type="text/javascript">
	var setting = {
			view: {
				selectedMulti: false
			},
			edit: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: { 
				onClick: onClick
			}
		};

    $(function () { 
		$.post("${context_root}/product/productCategoryList.action", function(data){ 
			var ht = $("#categoryHiddenTree");
			ht = $.fn.zTree.init(ht, setting, data);  
			ht.expandAll(true); 
		});
		
		$.post("${context_root}/system/getDictionaryListByType.action?type=area", function(data){ 
			var ht = $("#originHiddenTree");
			ht = $.fn.zTree.init(ht, setting, data);  
			ht.expandAll(true); 
		});
		
		var handleType = GetQueryString("handleType");
        var productId = GetQueryString("productId");
        if (handleType == "add") {
            $("#save").val("添加");
            $("#stock").val("0");
            var num = Math.random().toString();
            num = num.substr(num.length - 13, num.length);
            $("#barCode").val(num);
        } else if (handleType == "edit") {
            $("#save").val("修改");
        } else if (handleType == "look") {
            $("#save").css("display", "none");
            $(window).mousedown(function(event){
            	event.preventDefault();
              });
            $(":file").css("display","none"); 
        } else {
            parent.layer.alert("链接参数错误!handleType=" + handleType, {icon: 2, title: "系统提示"});
        }
         
        $("select").hover(function(){
            $(this).attr("title",$(this).find("option:selected").html()); 
        },function(){ 
        });
		 
    })

    function getProInfo(productId) {
        $.ajax({
            url: "${context_root}/product/querySelOptions.action",
            type: 'post',
            async: true,
            cache: false,
            data: {'productId': productId},
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

    //校验上传文件是否为图片格式
    function changImg(e){debugger
    	  if($("#picture")[0].files[0].size>1204000){
			  parent.layer.alert("图片不能超过1M!", {icon: 2, title: "提示"});
			  $("#picture")[0].value="";
			  return;
		  }  
        for (var i = 0; i < e.target.files.length; i++) {
            var file = e.target.files.item(i);
            if (!(/^image\/.*$/i.test(file.type))) {
                continue; //不是图片 就跳出这一次循环
            }
            //实例化FileReader API
            var freader = new FileReader();
            freader.readAsDataURL(file);
            freader.onload = function(e) {
                $("#image").attr("src",e.target.result);
            }
        }
 
    }


    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function formatDateStr(dateStr, a) { 
        var d = new Date(date);
        a.val(d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate());
    }

    
    function showMenu(a) { 
    	if(GetQueryString("handleType")=="look"){return false;}
    	
    	var ultree=$("[forInput='"+a.id+"']")
    	ultree.css({left:a.offsetLeft + "px", top:a.offsetTop+30 + "px",zIndex: 100,position: "absolute"}).slideDown("fast");
		$("body").bind("mousedown",onBodyDown(ultree[0].id))  
	}
	function hideMenu(ultreeId) {
		$("#"+ultreeId).fadeOut("fast");
		$("body").unbind("mousedown",onBodyDown(ultreeId) );   
	}
	  
	function onBodyDown(ultreeId)
	{
		return function(event)
		{
			if (!(event.target.id == ultreeId || $(event.target).parents("#"+ultreeId).length>0)) {
				hideMenu(ultreeId);
			}
		}
	}
	
	//单击菜单触发
	function onClick(e,treeId, treeNode) { 
			 
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			nodes = zTree.getSelectedNodes(),
			v = "";
			w = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				w += nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (w.length > 0 ) w = w.substring(0, w.length-1);  
			if(treeId=="categoryHiddenTree"){
				$("#categoryName").attr("value", v);
				$("#categoryId").attr("value", w);
			}else{
				$("#origin").attr("value", v);
			}
			
			hideMenu(treeId); 
	}
    
    

    $("#form-role-add").validate({
        rules: {
            status: {required: true, isSpace: true,},
            //picture: {required: true, isSpace: true,},
            code: {required: true, isSpace: true,},
            barCode: {required: true, isSpace: true,},
            productName: {required: true},
            unit: {required: true, isSpace: true,},
            expiryDate: {required: true},
            origin: {required: true, isSpace: true,},
            spec: {required: true, isSpace: true,},
            storageCondition: {required: true, isSpace: true,},
            categoryId: {required: true, isSpace: true,},
            level: {required: true, isSpace: true,},
            characteristic: {required: true, isSpace: true,},
            salePrice: {required: true, isSpace: true, number:true},
            purchasePrice: {required: true, isSpace: true, number:true},
            stock: {required: true, isSpace: true,},
            supplierId: {required: true, isSpace: true,},
            stockTop: {required: true, isSpace: true, number:true},
            stockDown: {required: true, isSpace: true, number:true}
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            var formData = new FormData($('#form-role-add')[0]);
            $.ajax({
                url: "${context_root}/product/saveProduct.action",
                type: 'post',
                async: true,
                cache: false,
                data:formData,
                mimeType: "multipart/form-data", 
                contentType: false,
                processData: false,
                success: function (data) {
                    var data = JSON.parse(data);
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