<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>

<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${context_root}/js/Hselect.js"></script> 
<body class="pos-r">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品分类 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pos-a" style="width:250px;left:0;height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
	<ul id="menuTree" class="ztree">
	</ul> 
</div>

<article class="page-container">
	<form action="" method="post" class="form form-horizontal" id="form-menu-save" style="margin-left:250px;" >
	    <input type="hidden" value="" id="categoryId" name="categoryId">
	    <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>目录：</label>
			<div class="formControls col-xs-8 col-sm-4">
				 <!-- <select id="parentId" name="parentId" ></select> --> 
				 	<input id="parentName" name="parentName"  type="text" readonly value="" class="input-text" onclick="showMenu(); return false;"/>
					<input id="parentId"  name="parentId"  type="hidden" /> 
					<ul id="hiddenTree" class="ztree" style="display:none;background-color: white;">
					</ul> 
			</div> 
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="名称" id="categoryName" name="categoryName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-4">
				 <y:select id="status" name="status" codeGroup="[{'id':'1','value':'启用'},{'id':'0','value':'弃用'}]" selectedValue=""
					cssClass="select" headerKey="" headerValue="请选择">
				</y:select>
			</div>
		</div> 
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">排序：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="sort" name="sort">
			</div>
		</div> 
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="" placeholder="" id="remark" name="remark">
			</div>
		</div> 
		 
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;添加&nbsp;&nbsp;"  >
			</div>
		</div>
	</form>
</article>

<SCRIPT type="text/javascript">
	var setting = {
			view: {
				selectedMulti: false
			},
			edit: {
				enable: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeRemove: beforeRemove,
				onClick: onClick
			}
		};

	var setting1 = {
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

	function showRemoveBtn(treeId, treeNode) {
		return true;
	}
	
	function showRenameBtn(treeId, treeNode) {
		return false;
	}

	//删除菜单
	function beforeRemove(treeId, treeNode) {
		parent.layer.confirm("确认删除菜单【 " + treeNode.name + "】吗？",function(index){
			$.ajax({
					url:"${context_root}/product/delProductCategory.action" ,
					data : {'categoryId':treeNode.id,'parentId':treeNode.pId} ,
					type:'post',
					async:true ,
					cache:false ,
					dataType:"json",
					success:function(data){
						if(data.s == true){
							parent.layer.msg("删除成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
							    location.replace(location.href);
							});
						}else{
							parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
						}
					},
			});
		});
		return false;
	}
	function resetForAdd(){
		$("#categoryId").val("");
		$("#parentId").val("");
		$("#categoryName").val("");
		$("#status").val("");
		$("#sort").val("");
		$("#remark").val(""); 
	}
	
	
	//单击菜单触发
	function onClick(e,treeId, treeNode) {
		if(treeId=="menuTree"){
			var zTree = $.fn.zTree.getZTreeObj("menuTree");
			zTree.expandNode(treeNode);
			$("#categoryId").val(treeNode.id);
			$("#parentId").val(treeNode.pId);
			$("#parentName").val(treeNode.pName);
			$("#categoryName").val(treeNode.name);
			$("#status").val(treeNode.status);
			$("#sort").val(treeNode.sort);
			$("#remark").val(treeNode.remark); 
			$("#save").val("修改");
		}else{
			var zTree = $.fn.zTree.getZTreeObj("hiddenTree");
			
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
			$("#parentName").attr("value", v);
			$("#parentId").attr("value", w);
			hideMenu();
		} 
	}
	
	function resetid(){
		//alert(111+$("#categoryId").val());
		$("#categoryId").val("");
	}
	
	function checkid(){
		//alert(222+$("#categoryId").val());
		if($("#categoryId").val()==""){ 
			parent.layer.alert("请选择要修改的节点！" , {icon: 2,title:"系统提示"});
			return false;
		}
	}
	
	
	$("#form-menu-save").validate({
		rules:{
			parentId:{
				required:true,
			},
			parentName:{
				required:true,
			}, 
			categoryName:{
				isSpace:true,
				required:true
			}, 
			status:{
				isSpace:true,
				required:true
			},
			sort:{
				isSpace:true 
			},
			remark:{
				isSpace:true 
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){ 
				var zTree = $.fn.zTree.getZTreeObj("menuTree");
				if(zTree.getNodes()!=null){
					for(var a=0;a<zTree.getNodes().length;a++){
						if(zTree.getNodes()[a].name==$("#categoryName").val()&&zTree.getNodes()[a].id!=$("#categoryId").val()){
							parent.layer.alert("节点重名，保存失败！" , {icon: 2,title:"系统提示"});
							return false;}
						if(zTree.getNodes()[a].children!=null){
							for(var b=0;b<zTree.getNodes()[a].children.length;b++){
								if(zTree.getNodes()[a].children[b].name==$("#categoryName").val()&&zTree.getNodes()[a].children[b].id!=$("#categoryId").val()){
									parent.layer.alert("节点重名，保存失败！" , {icon: 2,title:"系统提示"});
									return false;}
							}
						}
					}
				}
				 
			
			$.ajax({
				url:"${context_root}/product/saveProductCategory.action", 
				type:'post',
				async:true ,
				cache:false ,
				data:$(form).serialize(),
				dataType:"json",
				success:function(data){ 
					if(data.s == true){
						index = parent.layer.getFrameIndex(window.name);
						parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
							location.replace(location.href);
						}) ;
					}else{
						parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
					}
				},
			});
		}
	});
	
	$(document).ready(function(){ 
		var t = $("#menuTree");
		var ht = $("#hiddenTree");
		$.post("${context_root}/product/productCategoryList.action", function(data){
			t = $.fn.zTree.init(t, setting, data);
			t.expandAll(true);
			ht = $.fn.zTree.init(ht, setting1, data);
			
			//initSelect(data);
		}); 
	});
	
	/* function initSelect(data){

		var arr = new Array(); 
	    $(data).each(function(index,element){

	        var ijs = {};

	        ijs.id = element.id

	        ijs.text = element.name

	        ijs.upId = element.pid

	        arr.push(ijs)

	          
	    });

	    $("#parentId").Hselect({

	        data:arr,

	        height:"29px",

	        border:"#ccc solid 1px",

	    });

	} */
	
	function showMenu() {
		var parentName = $("#parentName")[0]; 
		$("#hiddenTree").css({left:parentName.offsetLeft + "px", top:parentName.offsetTop+30 + "px",zIndex: 100,position: "absolute"}).slideDown("fast");
		var zTree = $.fn.zTree.getZTreeObj("hiddenTree");
		zTree.expandAll(true);
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#hiddenTree").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "hiddenTree" || $(event.target).parents("#hiddenTree").length>0)) {
			hideMenu();
		}
	}
	 
	 
	
</SCRIPT>
</body>
</html>