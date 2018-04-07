<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="仓库管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存管理 <span class="c-gray en">&gt;</span> 库存预警 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
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
		<input type="text" class="input-text" style="width:250px" placeholder="商品名称|商品条码" id="codeOrName" name="codeOrName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">               
	 <a href="javascript:;" onclick="storeWarn_exportExcel()" class="btn btn-primary radius"><!-- <i class="Hui-iconfont">&#xe600;</i> --> 导出</a>
	 </span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<!-- <th style="width: 7%;height: 20px;"><input  onclick="allSelect()" type="checkbox"name="allSel" value=""/></th> -->
				<th width="5%">序号</th>
				<th width="5%">商品名称</th>
				<th width="8%">商品条码</th>
				<th width="5%">供货商</th>
				<th width="5%">单位</th>
				<th width="5%">分类</th>
				<th width="5%">库存上限</th>
				<th width="5%">库存下限</th>
				<th width="5%">库存</th>
				<th width="5%">备注</th>
				<!-- <th width="8%">过保日期</th> -->
			</tr>
		</thead>
	</table>
	</div>
</div>
<script type="text/javascript">
var pageTable;
$(document).ready(function(){
    var aoColumns = [
    {
		"mData": "no",
		"bSortable" : false,
		"sClass": "text-c",
		"mRender":function(data, type, row){
			if (row.no != null) {
                return row.no;
            } else {
                return "";
            }
		}
	},
	{
        "mData": "productName",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.productName != null) {
                return row.productName;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "barCode",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.barCode != null) {
                return row.barCode;
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
			if (row.name != null) {
                return row.name;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "unit",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.unit != null) {
                return row.unit;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "categoryName",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.categoryName != null) {
                return row.categoryName;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "stockTop",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.stockTop != null) {
                return row.stockTop;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "stockDown",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.stockDown != null) {
                return row.stockDown;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "stock",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.stock != null) {
                return row.stock;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "remark",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.stock > row.stockTop) {
                return "<span class='c-green'>库存充足</span>";
            }if ((row.stock <= row.stockTop)&&(row.stock >= row.stockDown)) { 
                return "<span class='c-black'>正常</span>";
            }if (row.stock < row.stockDown) {
                return "<span class='c-red'>库存不足</span>";
            } else {
                return "";
            }
		}
    }
   /*  {
        "mData": "expiryDate",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.expiryDate != null) {
                return formatDate(row.expiryDate,"yyyy-MM-dd hh:mm:ss");
            } else {
                return "";
            }
		}
    }, */
    
    /* {
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
    }, */
    /* {
        "sDefaultContent": "操作",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            //编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"stores_edit('编辑','${context_root}/stock/updatestores.action?storesId=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">编辑</a>";
           //删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"stores_del(this,\'" + row.id + "\')\" class=\"ml-5\" style=\"text-decoration:none\">删除</a>";
        	/*
        	/* var toPassword = "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','${context_root}/system/toChangePwd.action?userId=" + row.userId + "','600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,\'" + row.userId + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";*/
        	//return  statusTools(row) + "&nbsp;&nbsp;" + toEdit + "&nbsp;&nbsp;" + toDelete; */
        //}
    //}, 
    ];
    var url = "${context_root}/stock/storewarnList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

function statusTools(row) {
    if (row.status == '1') {
        return "<a style=\"text-decoration:none\" onClick=\"stores_stop(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"停用\" class=\"label label-defaunt radius\">禁用</a>";
    }else{
        return "<a style=\"text-decoration:none\" onClick=\"stores_start(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"启用\" class=\"label label-success radius\">启用</a>";
    }
}

function query() {
    var status = $("#status option:selected").val();
    var codeOrName = $("#codeOrName").val().replace(/\s+/g,"");
    
     
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/stock/storewarnList.action?status="+status+"&codeOrName="+codeOrName);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*库存信息导出*/
function storeWarn_exportExcel(){
	 var status = $("#status option:selected").val();
	 var codeOrName = $("#codeOrName").val();
	 var jsonObject = '{\"status\":\"' + status + '\",\"codeOrName\":\"' + codeOrName + '\"}';
	 try{ 
	        var elemIF = document.createElement("iframe");   
	        elemIF.src = "${context_root}/stock/expStoreWarnList.action?jsonObject="+encodeURIComponent(jsonObject);  
	        elemIF.style.display = "none";   
	        document.body.appendChild(elemIF);   
	    }catch(e){ 

	    }  
}


/*仓库-添加*/
function stores_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*仓库-编辑*/
function stores_edit(title,url,w,h){
	layer_show(title,url,w,h);
}

/*密码-修改*/
function change_password(title,url,w,h){
	layer_show(title,url,w,h);	
}




/*商家-停用*/
function stores_stop(obj,id){
	parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/stock/changeStoresStatus.action?id=" + id+"&status=0", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="stores_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用" class="label label-success radius">启用</a>');
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
function stores_start(obj,id){
	parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/stock/changeStoresStatus.action?id=" + id+"&status=1", 
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="stores_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none" class="label label-defaunt radius">禁用</a>');
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
function stores_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/stock/delstoresById.action?id=" + id, 
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