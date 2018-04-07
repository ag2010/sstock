<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商家管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商家管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
           <select name="status" id="status" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value="1">启用</option>
               <option value="0">禁用</option>
           </select>
       </span>
		<%-- <span class="select-box" style="width: 100px;border: hidden;">
       <y:select id="roleName" name="roleName" codeGroup="" selectedValue=""
				 cssClass="select" headerKey="" headerValue="角色">
	   </y:select>
       </span> --%>
		<input type="text" class="input-text" style="width:250px" placeholder="商家名称" id="shopName" name="shopName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">
	 <a href="javascript:;" onclick="shops_exportExcel()" class="btn btn-primary radius"><!-- <i class="Hui-iconfont">&#xe600;</i> --> 导出</a>
	 <a href="javascript:;" onclick="shops_importExcel()" class="btn btn-primary radius"><!-- <i class="Hui-iconfont">&#xe600;</i> --> 导入</a>
	 <a href="javascript:;" onclick="shops_add('添加商家','${context_root}/shops/addshops.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
	 </span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<!-- <th style="width: 7%;height: 20px;"><input  onclick="allSelect()" type="checkbox"name="allSel" value=""/></th> -->
				<th width="5%">序号</th>
				<th width="5%">商家名称</th>
				<th width="5%">联系人</th>
				<th width="6%">座机电话</th>
				<th width="6%">手机</th>
				<th width="6%">邮箱</th>
				<th width="15%">地址</th>
				<th width="5%">状态</th>
				<th width="8%">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<%-- <div id="imp_container" style="display:none">	
		<form id="import-form" name="import-form" action="${context_root}/shops/impShopsList.action" method="post"  enctype="multipart/form-data"> >  
           
            <input type="file" id="file" name="file" value="选择文件"/>
            <input type="submit" value="导入">
        </form> 
         <a href="javascript:;" onclick="getExcelModel()" >模板下载</a> 
    </div> --%>
</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){
    var aoColumns = [
	/* {
		"sDefaultContent": "复选",
		"bSortable" : false,
		"sClass": "text-c",
		"bSearchable": false,
		"mRender": function(data, type, row) {
			return "<input class= \"userName\" type=\"checkbox\"name=\"checkName\" value="+row.name+"></input>";
		}
	}, */
    {
		 "mData": "no",
	     "bSortable" : false,
	     "sClass": "text-c",
	     "mRender": function(data, type, row) {
            if (row.no != null) {
                return row.no;
            } else {
                return "";
            }
	     }
	}, 
    {
        "mData": "name",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
        	if(row.name != null){
            	return row.name;
            }else{
            	return "";
            }
        }
    },
    {
        "mData": "contacts",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
        	if(row.contacts != null){
            	return row.contacts;
            }else{
            	return "";
            }
        }
    },
    {
        "mData": "tel",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
        	if(row.tel != null){
            	return row.tel;
            }else{
            	return "";
            }
        }
    },
    {
        "mData": "phone",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
        	if(row.phone != null){
            	return row.phone;
            }else{
            	return "";
            }
        }
    },
    {
    	"mData": "email",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
        	if(row.email != null){
            	return row.email;
            }else{
            	return "";
            }
        }
    },
    
    {
        "mData": "address",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data,type,row){
        	if(row.address != null){
            	return row.address;
            }else{
            	return "";
            }
        }
    },
    {
        "sDefaultContent": "状态",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.status == '1') {
                return "<span class=\"label label-success radius\">已启用</span>";
            } else {
                return "<span class=\"label label-defaunt radius\">已停用</span>";
            }
        }
    },
    {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"shops_edit('编辑','${context_root}/shops/updateshops.action?id=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">编辑</a>";
           //删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"shops_del(this,\'" + row.id + "\')\" class=\"ml-5\" style=\"text-decoration:none\">删除</a>";
        	/*//修改密码
        	var toPassword = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','${context_root}/system/toChangePwd.action?userId=" + row.userId + "','600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,\'" + row.userId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";*/
        	return  statusTools(row) + "&nbsp;&nbsp;" + toEdit + "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/shops/shopsList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.status == '1') {
        return "<a style=\"text-decoration:none\" onClick=\"shops_stop(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"停用\" class=\"label label-defaunt radius\">禁用</a>";
    } else {
        return "<a style=\"text-decoration:none\" onClick=\"shops_start(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"启用\" class=\"label label-success radius\">启用</a>";
    }
}

function query() {
    var status = $("#status option:selected").val();
    var name = $("#shopName").val().replace(/\s+/g,"");
    
     
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/shops/shopsList.action?status="+status+"&name="+name);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*商家管理信息-导出*/
function shops_exportExcel(){
    var status = $("#status option:selected").val();
    var shopName = $("#shopName").val();
    var jsonObject = '{\"status\":\"' + status + '\",\"shopName\":\"' + shopName + '\"}';
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = "${context_root}/shops/expShopsList.action?jsonObject="+encodeURIComponent(jsonObject);   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);   
    }catch(e){ 

    }  
}


/*商家管理信息-导入*/
function shops_importExcel(){
	var url = encodeURI("${context_root}/shops/toImpPage.action?impUrl="+("/shops/impShopsList.action")+"&molUrl="+("/shops/getImpExcelModel.action"));
	layer.open({
		  type: 2,
		  shadeClose: true,
		  shade: 0.8,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['400px', '90%'], //宽高
		  content: url
		}); 
}


/* function allSelect(){
    $("input[name='checkName']").attr("checked","true");
} */
/*用户-添加*/
function shops_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*商家-编辑*/
function shops_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,w,h){
	layer_show(title,url,w,h);	
}



/*商家-停用*/
function shops_stop(obj,id){
	parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/shops/changeShopsStatus.action?id=" + id+"&status=0", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="shops_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用" class="label label-success radius">启用</a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
					$(obj).remove();                                 
					parent.layer.msg('已停用!',{icon: 5,time:1000});
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;
		
	});
}

/*商家-启用*/
function shops_start(obj,id){
	parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/shops/changeShopsStatus.action?id=" + id+"&status=1", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="shops_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none" class="label label-defaunt radius">禁用</a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					parent.layer.msg('已启用!', {icon: 6,time:1000});
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;
		
	});
}

/*商家-删除*/
function shops_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/shops/delshopsById.action?id=" + id, 
				type:'post',
				async:true ,
				cache:false ,
				dataType:"json",
				success:function(data){
					if(data.s == true){
						$(obj).parents("tr").remove();
						parent.layer.msg('已删除!',{icon:1,time:1000});
						window.location.reload();
						//loadData() ;
					}else{
						parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
					}
				},
				
			}) ;
	});
}
</script> 
</body>
</html>