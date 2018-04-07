<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%> 
<ys:contentHeader/>
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
	label.form-label.col-xs-4.col-sm-4{
	   width:110px;
	  
	}
	span.select-box{
	width:150px;}
</style>
<body>
<article  >
	<form action="" method="post" class="form form-horizontal" id="form-inputStore-add">
		<div class="row cl">   
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4">出库单号</label>
				<div class="row col-xs-7 col-sm-7 ">
					<input type="text" readonly="readonly" class="input-text"  value="${outStoreNo}" placeholder=""  id="id"  name="id"> 
				</div>
			</div>
		 <div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 类型 </label>
				<div class="row col-xs-7 col-sm-7 ">
					<input type="text" class="input-text" value="报废单" placeholder=""  id="type" name="type"> 
				</div>
			</div>
		<%-- 	<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 订货商家名称 </label>
				<div class="row col-xs-7 col-sm-7 ">
				<y:select id="shopId" name="shopId" codeGroup="${shops}" selectedValue=""
						cssClass="select" headerKey="" headerValue="请选择">
					</y:select>  
				</div>
			</div> --%> 
		</div>
		<div class="row cl">   
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 仓库编号 </label>
				<div class="row col-xs-7 col-sm-7 ">
					 <y:select id="storeId" name="storeId" codeGroup="${proStore}" selectedValue=""
						cssClass="select" headerKey="" headerValue="请选择">
					</y:select>  
				</div>
			</div>
			<div class="row col-xs-4 col-sm-4  input_bar"> 
				<label class="form-label col-xs-4 col-sm-4"> 备注</label>
				<div class="row col-xs-7 col-sm-7 ">  
	           <input type="text" class="input-text" value=" " placeholder="" id="remark" name="remark"> 

				</div>
			</div>  
		</div>
		<div class="mt-20"> 
			<input type="button" class="btn btn-primary radius" value="添加列" id="addRow"/>
			<table class="table table-border table-bordered table-hover table-bg table-sort" id="orderTab">
				<thead>
					<tr class="text-c">
						<th width="5%">序号</th>
						<th width="10%">商品名称</th>
						<th width="10%">请求量</th> 
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
</article>
  
<script type="text/javascript"> 


	var tabIndex=0;
	var unitdata=eval('${proUnit}');
	var namedata=eval('${proName}');
	var pageTable;
	$(document).ready(function(){
		$("#addRow").click(function(){
			tabIndex++;
			var id1='no'+tabIndex;
			var id2='barCode'+tabIndex;
			var id3='number'+tabIndex;
			var id4='remark'+tabIndex;
			
			var tr=document.createElement('tr');
			tr.id=tabIndex;
			tr.innerHTML = " <tr id='"+tabIndex+"'>"+
								"<td><input class='input-text' type='text' id="+id1+" name="+id1+" disabled='disabled' value='"+tabIndex+"'></td>"+
								"<td>"+getSelStr(id2,namedata)+"</td>"+
								"<td><input class='input-text' type='text' id="+id3+" name="+id3+"></td>"+ 
								"<td><input class='input-text' type='text' id="+id4+" name="+id4+"></td>"+
								"<td><input class='btn btn-primary radius' type='button' value='删除' onclick='deleteRow(this)'></td>"+
							"</tr>";
			$("tbody")[0].appendChild(tr);
		});
	})
 
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
     //alert(r);
     //alert(unescape(r[2]));
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
		url:"${context_root}/list/saveOutStore.action", 
		type:'post',
		async:true ,
		cache:false ,
		data:{
			"id":$("#id").val(),
			"type":$("#type").val(),
			//"shopId":$("#shopId").val(), 
			"storeId":$("#storeId").val(),  
			"remark":$("#remark").val(), 
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

$("#form-inputStore-add").validate({
    rules: {
        id: {required: true, isSpace: true,}, 
        actorName: {required: true, isSpace: true,},
        storeId: {required: true, isSpace: true,}, 
        shopId: {required: true, isSpace: true,},
        //type: {required: true, isSpace: true,}
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
		
 
</script> 
</body>
</html>