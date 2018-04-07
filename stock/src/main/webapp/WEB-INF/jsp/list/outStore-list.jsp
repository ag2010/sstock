<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="出库单管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 货流管理 <span class="c-gray en">&gt;</span>出库单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		 <span class="l"><a href="javascript:;" onclick="exportExcel()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导出</a></span>
	     <span class="l" style="padding-left: 10px"> 
	     	<shiro:hasAnyRoles name="管理员,出库员"> 
	     		<a href="javascript:;" onclick="outStore_add('报废出货单','${context_root}/list/addOutStore.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 报废出货单</a>
	     	</shiro:hasAnyRoles>
	     </span>
		<span class="select-box" style="width: 120px;">
           <select name="status" id="statusSelect" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value="1">审核中</option>
               <option value="2">完成</option>               
           </select>
       </span>
		
		<input type="text" class="input-text" style="width:250px" placeholder="出库单号/配货单号" id="shopName"  name="name">
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate"  placeholder="开始时间"  style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate"  placeholder="结束时间" style="width:120px;">
		<!-- <input type="text" class="input-text" style="width:250px" placeholder="订货时间段" id="userName"  name="name"> -->
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div>
	<%-- <div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l"><a href="javascript:;" onclick="user_add('导入','${context_root}/system/toUserAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导入</a></span>

	</div> --%>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">序号</th>
					<th width="12%">出库单号</th>
				<th width="12%">派单时间</th>
				<th width="10%">状态</th>
				<th width="10%">类型</th>
				<th width="12%">配货单号</th>
				<th width="10%">仓库</th>
				<th width="10%">备注</th>
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
		"sClass": "text-c"
	},
	{
		"mData": "id",
		"bSortable" : false,
		"sClass": "text-c",
		 "mRender":function(data, type, row){
				if (row.id != null) {
	                return row.id;
	            } else {
	                return "";
	            }
			}
	},
	{
        "sDefaultContent": "派单时间",
        "bSortable" : false,
        "sClass": "text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            return formatDate(row.addTime,"yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        "sDefaultContent": "状态", 
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.status == '1') {
                return "<span class=\"label label-success radius\">审核中</span>";
            } else if(row.status=='2'){
                return "<span class=\"label label-defaunt radius\">完成</span>";
            } 
            
        }
    },  
    {
        "sDefaultContent": "类型",
        "bSortable" : false,
        "sClass": "td-status text-c",
        "bSearchable": false,
        "mRender": function(data, type, row) {
            if (row.type == '1') {
                return "<span class=\"label label-success radius\">正常出库</span>";
            } else{
                return "<span class=\"label label-defaunt radius\">报废</span>";
            } 
            
        }
    },  
    {
    	"mData": "deliveryId",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.deliveryId != null) {
             return  "<a title=\"查看\" href=\"javascript:;\" onclick=\"out_edit('查看','${context_root}/list/toDelivery.action?Id=" + row.deliveryId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">"+row.deliveryId+"</a>";
            } else {
                return "";
            }
		}
    },
    {
        "mData": "storeName",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.storeName != null) {
                return row.storeName;
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
			if (row.remark != null) {
                return row.remark;
            } else {
                return "";
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
            //var toEdit = "<a title=\"审核\" href=\"javascript:;\" onclick=\"delivery_edit('审核','${context_root}/list/toOutStore.action?Id=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">审核</a>";
        	//var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"supplier_del(this,\'" + row.id + "\')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
        	return   statusTools(row)
        }
    },
    ];
    var url = "${context_root}/list/outStoreList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
}); 

 function statusTools(row) {
	 var toEdit = "<shiro:hasAnyRoles name='管理员,库存主管'><a title=\"审核\" href=\"javascript:;\" onclick=\"delivery_edit('审核','${context_root}/list/toOutStore.action?Id=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">审核</a></shiro:hasAnyRoles>";
	 var look = "<a title=\"查看\" href=\"javascript:;\" onclick=\"delivery_edit('查看','${context_root}/list/toOutStore.action?Id=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">查看</a>";

    if (row.status == '1') {
        return  look+"&nbsp;&nbsp;"+toEdit;
    } else if(row.status=='2') {
        return look;
    }
} 

function query() {
    var status = $("#statusSelect option:selected").val();
    var uname=$("#shopName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    pageTable.fnSettings().sAjaxSource = "${context_root}/list/outStoreList.action?allname=" +uname+"&beginTime="+beginTime+"&endTime="+endTime+"&status="+status;
    //pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/list/purchaseList.action?jsonObject=" + jsonObject);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*用户-添加*/
  function outStore_add(title,url,w,h){
	layer_show(title,url,w,h);
}  

/*配货单查看*/
  function out_edit(title,url,w,h){
	layer_show(title,url,w,h);
} 


/*订单-审核*/
 function delivery_edit(title,url,w,h){
	layer_show(title,url,w,h);
}  


function exportExcel() {
	var status = $("#statusSelect option:selected").val();
    var uname=$("#shopName").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
    var jsonObject = '{\"allname\":\"' + uname + '\",\"beginTime\":\"' + beginTime + '\",\"endTime\":\"' + endTime +'\",\"status\":\"' +status+ '\"}';
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = "${context_root}/list/toExportOutStore.action";   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);   
    }catch(e){ 

    }  
}
</script> 
</body>
</html>