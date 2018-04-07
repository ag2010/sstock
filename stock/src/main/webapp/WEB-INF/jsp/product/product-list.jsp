<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品资料<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="row cl text-c"> 
		<div class="row col-xs-4 col-sm-4"> 
		  		<a href="javascript:;" onclick="show_dialog('新增商品','${context_root}/product/toProductHandle.action?handleType=add&productId=','','600')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增商品</a>
		  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" onclick="exportExcel()" class="btn btn-primary radius">导出</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" onclick="importExcel()" class="btn btn-primary radius">导入</a>
			 
		</div>
		<div class="row col-xs-2 col-sm-2">
			<y:select id="status" name="status" codeGroup="[{'id':'1','value':'启用'},{'id':'0','value':'弃用'}]"  selectedValue="${product.status}"
				cssClass="select" headerKey="" headerValue="请选择">
			</y:select>
		</div>
		<div class="row col-xs-6 col-sm-6">
			<input id="categoryName" class="input-text" name="categoryName" placeholder="全部分类" type="text" style="width:250px" onclick="callBack('分类','${context_root}/product/productCategoryTree.action','230','438')">
			<input type="hidden" id="categoryId" name="categoryId">
			<input type="text" class="input-text" style="width:250px" placeholder="内部编码/条码/名称" id="codeOrName" name="codeOrName">
			<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		
		</div> 
 	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">序号</th>
				<th width="10%">内部编码</th>
				<th width="10%">商品名称</th>
				<th width="10%">条码 </th>
				<th width="10%">分类</th>
				<th width="10%">库存</th>
				<th width="5%">单位</th>
				<th width="10%">售价（元）</th>
				<th width="10%">进价（元）</th> 
				<th width="10%">产地</th>
				<th width="10%">操作</th>
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
        "mRender": function(data, type, row) {
            if (row.no != null) {
                return row.no;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "code",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.code != null) {
                return row.code;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "productName",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
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
        "mRender": function(data, type, row) {
            if (row.barCode != null) {
                return row.barCode;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "categoryName",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.categoryName != null) {
                return row.categoryName;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "stock",
        "bSortable" : false,
        "sClass": "text-c",  
        "mRender": function(data, type, row) {
            if (row.stock != null) {
                return row.stock;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "unit",
        "bSortable" : false,
        "sClass": "text-c", 
        "mRender": function(data, type, row) {
            if (row.unit != null) {
                return row.unit;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "salePrice",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.salePrice != null) {
                return row.salePrice;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "purchasePrice",
        "bSortable" : false,
        "sClass": "text-c",  
        "mRender": function(data, type, row) {
            if (row.purchasePrice != null) {
                return row.purchasePrice;
            } else {
                return "";
            }
        }
    },  
    {
        "mData": "origin",
        "bSortable" : false,
        "sClass": "text-c",  
        "mRender": function(data, type, row) {
            if (row.origin != null) {
                return row.origin;
            } else {
                return "";
            }
        }
    },
    {
        "sDefaultContent": "编辑",
        "bSortable" : false,
        "sClass": "td-manage text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
        	//编辑
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"show_dialog('编辑商品','${context_root}/product/toProductHandle.action?handleType=edit&productId=" + row.productId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">编辑</a>";
        	//删除
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"product_del(this,\'" + row.productId + "\')\" class=\"ml-5\" style=\"text-decoration:none\">删除</a>";
        	//授权
        	var toDetail = "<a title=\"查看\" href=\"javascript:;\" onclick=\"show_dialog('查看商品','${context_root}/product/toProductHandle.action?handleType=look&productId=" + row.productId + "','','406')\" class=\"ml-5\" style=\"text-decoration:none\">查看</a>";
        	return toDetail  + "&nbsp;&nbsp;" + toEdit + "&nbsp;&nbsp;" + toDelete;
        }
    },
    ];
    var url = "${context_root}/product/productList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
    
    
 /*    $("#import-form").form("submit",{
		url: "${context_root}/product/impProductList.action",
		onSubmit: function(){alert(111);
			return $(this).form('validate');
		},
		success: function(data){
			var dataObj=$.parseJSON(data);
			$.messager.alert('提示', dataObj["msg"] ,'info');
			 if(dataObj["flag"]==true){
				$("#import-dialog").dialog("close");
				$("#datagrid").datagrid("reload");
				$("#datagrid").datagrid('unselectAll');
			}
		}
	}); */
});

function callBack(title,url,w,h){
    layer_show(title,url,w,h);
}

function query() { 
	var status = $("#status").val();
    var codeOrName = $("#codeOrName").val();
    var categoryId = $("#categoryId").val(); 

    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/product/productList.action?codeOrName=" + codeOrName + "&categoryId=" + categoryId + "&status=" + status);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


function exportExcel() {
    var status = $("#status").val();
    var categoryId = $("#categoryId").val();
    var codeOrName = $("#codeOrName").val();
    var jsonObject = '{\"status\":\"' + status + '\",\"categoryId\":\"' + categoryId + '\",\"codeOrName\":\"' + codeOrName + '\"}';
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = "${context_root}/product/expProductList.action?jsonObject="+encodeURIComponent(jsonObject);   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);   
    }catch(e){ 

    }  
}



function importExcel(){ 
	var url='${context_root}/product/toImpPage.action?impUrl='+encodeURIComponent('/product/impProductList.action')+'&molUrl='+encodeURIComponent('/product/getImpExcelModel.action')
	layer.open({
		  type: 2,
		  title: '导入',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['380px', '50%'],
		  content: url
		});
}




/*角色-添加*/
function show_dialog(title,url,w,h){
	layer_show(title,url,w,h);
}
 

/*角色-删除*/
function product_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/product/delProduct.action?productId=" + id, 
				type:'post',
				async:true ,
				cache:false ,
				dataType:"json",
				success:function(data){
					if(data.s == true){
						$(obj).parents("tr").remove();
						parent.layer.msg('已删除!',{icon:1,time:1000});
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