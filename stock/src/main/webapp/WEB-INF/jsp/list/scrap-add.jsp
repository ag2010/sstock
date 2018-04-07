<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%> 
<ys:contentHeader/>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript" src="${context_root}/js/Hselect.js"></script> 
<style>
	article{
		padding: 15px 15px
	}
	.form-label{
		padding:0 5px;
		line-height: 4;
	}
	.input_bar{  
		padding:  3px 4px; 
		margin: 0 ; 
		font-size: 10px;
		margin:5px 0 0 0 !important;  
	} 
	.input_bar .input-text{
		width: 150px;
	}
	  
	 
</style>
<body>
<article  >
	<form action="" method="post" class="form form-horizontal" id="form-scrap-add">
		<div class="row cl">   
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 报废单号 </label>
				<div class="row col-xs-7 col-sm-7 ">
					<input type="text" readonly="readonly" class="input-text" value="${scrapNo}" placeholder="" id="id" name="id"> 
					<input type="hidden"  value="1"  id="status" name="status"> 
				</div>
			</div>
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 采购单号 </label>
				<div class="row col-xs-7 col-sm-7 ">
					<select id="procurementId" name="procurementId"></select>
					<!-- <input type="text" class="input-text" value="" placeholder="" id="procurementId" name="procurementId">  -->
				</div>
			</div>
			
		</div>
		<%-- <div class="row cl">    
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 状态 </label>
				<div class="row col-xs-7 col-sm-7 ">
					 <y:select id="status" name="status" codeGroup="[{'id':'1','value':'审核中'},{'id':'2','value':'已完成'}]" selectedValue=""
						cssClass="select" headerKey="" headerValue="请选择">
					</y:select> 
				</div>
			</div> 
		</div> --%>
		<div class="mt-20"> 
			<input type="button" class="btn btn-primary radius" value="添加列" id="addRow"/>
			<table class="table table-border table-bordered table-hover table-bg table-sort" id="orderTab">
				<thead>
					<tr class="text-c">
						<th width="5%">序号</th>
						<th width="10%">商品分类</th>
						<th width="10%">报废量</th>
						<th width="10%">单位</th>
						<th width="8%">采购价（元/单位）</th> 
						<th width="10%">备注</th>  
						<th width="5%">删除</th>  
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div> 
	 
		<div class="row col-xs-12 col-sm-12"> 
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3"> 
				<br />
				<input class="btn btn-primary radius"   type="submit"   id="save" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<br /><br />
			</div>
		</div>
	</form>
	
	<ul id="categoryHiddenTree" forInput="" class="ztree" style="display:none;background-color: white; border: solid 1px rgb(150,150,150);">
	</ul>
</article>
  
<script type="text/javascript"> 


	var tabIndex=0;
	var unitdata=eval('${proUnit}');
	var typedata=eval('${proType}');
	var pageTable;
	
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
	
	$(document).ready(function(){  
		$.post("${context_root}/list/getPassedProcurementList.action?type=scrap", function(data){ 
			initSelect(data);
		}); 
		
		$.post("${context_root}/product/productCategoryList.action", function(data){ 
			var ht = $("#categoryHiddenTree");
			ht = $.fn.zTree.init(ht, setting, data);  
			ht.expandAll(true); 
		});
		
		$("#addRow").click(function(){
			tabIndex++;
			var id1='no'+tabIndex;
			var id2='categoryId'+tabIndex;
			var id3='number'+tabIndex;
			var id4='unit'+tabIndex;
			var id5='price'+tabIndex; 
			var id6='remark'+tabIndex;
			
			var tr=document.createElement('tr');
			tr.id=tabIndex;
			tr.innerHTML = " <tr id='"+tabIndex+"'>"+
								"<td><input class='input-text' type='text' id="+id1+" name="+id1+" disabled='disabled' value='"+tabIndex+"'></td>"+
								"<td><input type='hidden' id="+id2+" name="+id2+"><input showfor="+id2+" class='input-text' type='text' readonly onclick='showMenu(this)'></td>"+
								"<td><input class='input-text' type='text' id="+id3+" name="+id3+"></td>"+ 
								"<td>"+getSelStr(id4,unitdata)+"</td>"+
								"<td><input class='input-text' type='text' id="+id5+" name="+id5+"></td>"+
								"<td><input class='input-text' type='text' id="+id6+" name="+id6+" value=' '></td>"+ 
								"<td><input class='btn btn-primary radius' type='button' value='删除' onclick='deleteRow(this)'></td>"+
							"</tr>";
			$("tbody")[0].appendChild(tr);
		});
	})
	
 function initSelect(data){

		var arr = new Array(); 
	    $(data).each(function(index,element){

	        var ijs = {};

	        ijs.id = element.id

	        ijs.text = element.name

	        ijs.upId = element.pid

	        arr.push(ijs)

	          
	    });

	    $("#procurementId").Hselect({

	        data:arr,

	        height:"29px",
	        width:"160px",
	        border:"#ccc solid 1px",

	    });

	}
 
function getSelStr(id,data){ 
		var Str="<select id='"+id+"' name='"+id+"' class='input-text'>"
		for(var a=0;a<data.length;a++){
			Str+="<option value ='"+data[a].codeid+"'>"+data[a].codevalue+"</option>";
		} 
		Str+="</select>"
		return Str;
	}
 
function	deleteRow(a){ 
	a.parentNode.parentNode.remove(); 
}

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
 

function submitData(){ 
	var orderStr='[';
	var trs=$("tbody tr"); 
	if(trs.length<1){
		parent.layer.alert("请添加列！" , {icon: 2,title:"系统提示"});
		return false;
	}
	for(i=0;i<trs.length;i++){ 
		orderStr+='{';
		orderStr+='"index":"'+trs[i].id+'",';
		for(j=0;j<$(trs[i]).find('td').length-1;j++){   
			var ele=$($(trs[i]).find('td')[j]).children()[0];
			orderStr+='"'+ele.id+'":"'+ele.value+'",';
			}
		orderStr=orderStr.substr(0,orderStr.length-1);
		orderStr+='},'; 
		} 
	orderStr=orderStr.substr(0,orderStr.length-1);
	orderStr+=']'; 
	
	var index = parent.layer.load();
	 $.ajax({
		url:"${context_root}/list/addScrapInfo.action", 
		type:'post',
		async:true ,
		cache:false ,
		data:{
			"id":$("#id").val(),
			"procurementId":$("#procurementId").val(),  
			"status":$("#status").val(), 
			"orders":orderStr
		},
		dataType:"json",
		success:function(data){
			parent.layer.close(index);
			if(data.s == true){
				index = parent.layer.getFrameIndex(window.name);
				parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 1000,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				}) ;
			}else{
				parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
			}
		},
	}); 
}
	
$("#form-scrap-add").validate({
    rules: {
        id: {required: true, isSpace: true,}, 
        procurementId: {required: true, isSpace: true,}, 
        status: {required: true, isSpace: true,} 
    },
    onkeyup: false,
    focusCleanup: true,
    success: "valid",
    submitHandler: function (form) {
    	var inputs=$("tbody tr input");
    	for(var a=0;a<inputs.length;a++ ){
    		if(inputs[a].value==""){ 
    			parent.layer.alert("请将添加列的信息填写完整！", {icon: 2,title:"提示"});
    			return false;
    		}
    	}
    	submitData();
    }
});

function showMenu(a) {  
	var ultree=$("#categoryHiddenTree");
	/*a.parentElement.append(ultree[0]); */
	$(a.parentElement).append(ultree[0]);
	ultree.css({left:getElemPos(a).x + "px", top:getElemPos(a).y+30 + "px",zIndex: 100,position: "absolute"}).slideDown("fast");
	ultree.attr("forInput",$(a).attr("showfor"));
	$("body").bind("mousedown",onBodyDown(ultree[0].id))  
}
function hideMenu(ultreeId) {
	$("#"+ultreeId).fadeOut("fast");
	$("#"+ultreeId).attr("forInput","");
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
function onClick(e,treeId, treeNode) { debugger
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		nodes = zTree.getSelectedNodes(),
		v = nodes[0].name;
		w = nodes[0].id;  
		var ultree=$("#categoryHiddenTree");
		var inputid=ultree.attr("forInput");
		$("#"+inputid).attr("value", w);
		$("[showfor='"+inputid+"']").attr("value", v);
		
		hideMenu(treeId); 
}

function getElemPos(obj){
    var pos = {"top":0, "left":0};
     if (obj.offsetParent){
       while (obj.offsetParent){
         pos.top += obj.offsetTop;
         pos.left += obj.offsetLeft;
         obj = obj.offsetParent;
       }
     }else if(obj.x){
       pos.left += obj.x;
     }else if(obj.x){
       pos.top += obj.y;
     }
     return {x:pos.left, y:pos.top};
}
 
</script> 
</body>
</html>