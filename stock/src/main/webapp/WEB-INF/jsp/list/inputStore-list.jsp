<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="货流管理"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 货流管理 <span class="c-gray en">&gt;</span> 入库单资料<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="row cl text-c">
		<div class="row col-xs-4 col-sm-4 " style="text-align:left"> 
				<shiro:hasAnyRoles name="管理员,入库员">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			<a href="javascript:;" onclick="show_dialog('新增入库单','${context_root}/list/toInputStoreAdd.action','','600')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增入库单</a>
		  		</shiro:hasAnyRoles>
		  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" onclick="exportExcel()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导出</a>
		</div> 
		<div class="row col-xs-1 col-sm-1 ">
			<y:select id="status" name="status" codeGroup="[{'id':'1','value':'审核中'},{'id':'2','value':'已完成'}]" selectedValue=""
				cssClass="select" headerKey="" headerValue="请选择">
			</y:select> 
		</div>
		<div class="row col-xs-7 col-sm-7 ">
			<input type="text" class="input-text" style="width:150px" placeholder="入库单号" id="id" name="id">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			派单日期：
			<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})" id="beginTime" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M-%d'})" id="endTime" class="input-text Wdate" style="width:120px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</div>
	</div>
	  
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">序号</th>
				<th width="10%">入库单号</th>
				<th width="10%">派单时间</th>
				<th width="5%">状态</th>
				<th width="10%">采购单号</th> 
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
        "mData": "id",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.id != null) {
                return row.id;
            } else {
                return "";
            }
        }
    },
    {
        "mData": "inputStoreTime",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.inputStoreTime != null) {
            	return formatDate(row.inputStoreTime,"yyyy-MM-dd hh:mm:ss"); 
            } else {
                return "";
            }
        }
    },
    {
        "mData": "status",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender": function(data, type, row) {
            if (row.status =="1") {
                return "审核中";
            } else if(row.status =="2") {
                return "完成";
            }else{
            	 return "";
            }
        }
    },
    {
        "mData": "procurementId",
        "bSortable" : false,
        "sClass": "text-c", 
        "mRender": function(data, type, row) { 
        	return "<a   href=\"javascript:;\" onclick=\"show_dialog('查看采购单','${context_root}/list/toProcurementHandle.action?handleType=look&id=" + row.procurementId + "','','600')\" class=\"ml-5\" style=\"text-decoration:none\">"+row.procurementId+"</a>";
        } 
    },
    {
        "mData": "remark",
        "bSortable" : false,
        "sClass": "text-c", 
        "mRender": function(data, type, row) {
            if (row.remark != null) {
                return row.remark;
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
        	//查看
            var html = "<a title=\"查看入库单\" href=\"javascript:;\" onclick=\"show_dialog('查看入库单','${context_root}/list/toInputStoreHandle.action?handleType=look&id=" + row.id + "','','600')\" class=\"ml-5\" style=\"text-decoration:none\">查看</a>";
        	//审核
        	if(row.status==1){
        		html=html+"&nbsp;&nbsp;" + "<shiro:hasAnyRoles name='管理员,库存主管'><a title=\"审核入库单\" href=\"javascript:;\" onclick=\"show_dialog('审核入库单','${context_root}/list/toInputStoreHandle.action?handleType=check&id=" + row.id + "','','600')\" class=\"ml-5\" style=\"text-decoration:none\">审核</a></shiro:hasAnyRoles>";;
        	} 
        	
        	return html;
        }
    }

    ];
    var url = "${context_root}/list/inputStoreList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
     
});

function query() {
    var id = $("#id").val();
    var status = $("#status").val();
    var beginTime = $("#beginTime").val();
    var endTime = $("#endTime").val();
     
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/list/inputStoreList.action?id=" + id+"&status="+status+"&beginTime="+beginTime+"&endTime="+endTime);
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


function exportExcel() {
	 var id = $("#id").val();
	 var status = $("#status").val();
	 var beginTime = $("#beginTime").val();
	 var endTime = $("#endTime").val();
	      
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = "${context_root}/list/expInputStoreList.action?id=" + id+"&status="+status+"&beginTime="+beginTime+"&endTime="+endTime;   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);   
    }catch(e){ 

    }  
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
						loadData() ;
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