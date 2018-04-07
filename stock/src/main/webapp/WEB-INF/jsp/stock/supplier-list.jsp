<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="供应商管理"/>
<style type="text/css">
input#file.btn.btn-success.radius{
  width:200px;
  margin-left:2px;
  
}
input#file{
color: 5EB95E;
 font-size: 14px;
}

div#model{
margin-top: 35px;
}
a#xia{
margin-left:10px;
}
input#imp{
margin-left:10px;
}

.file {
	    position: relative;
	    display: inline-block;
	    background: #D0EEFF;
	    border: 1px solid #99D3F5;
	    border-radius: 4px;
	    padding: 4px 12px;
	    overflow: hidden;
	    color: #1E88C7;
	    text-decoration: none;
	    text-indent: 0; 
	    line-height: 27px;
	    width: 60px;
	    float: left;
	    height:27px;
	    margin: 0 12px;
	}
	.file input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	}
	.file:hover {
	    background: #AADFFD;
	    border-color: #78C3F3;
	    color: #004974;
	    text-decoration: none;
	}
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存管理 <span class="c-gray en">&gt;</span> 供应商管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	<div class="text-c">                                                                                      <%-- onclick="exportExcel('导出','${context_root}/system/toUserAdd.action','','610')"  --%>
	    <span class="l"><a href="javascript:;" onclick="importExcel( )" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导入</a></span>
	    <span class="l" style="padding-left: 10px"><a href="javascript:;" onclick="exportExcel()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导出</a></span>
	    <span class="l" style="padding-left: 10px"><a href="javascript:;" onclick="supplier_add('添加供货商','${context_root}/stock/toSupplierAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加供货商</a></span>
		<span class="select-box" style="width: 120px;">
           <select name="status" id="statusSelect" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value="1">启用</option>
               <option value="0">禁用</option>
           </select>
       </span>

		<input type="text" class="input-text" style="width:250px" placeholder="供应商名称" id="userName"  name="name">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div>
<%-- 	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l"><a href="javascript:;" onclick="user_add('导入','${context_root}/system/toUserAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导入</a></span>
	 <span class="l" style="padding-left: 10px"><a href="javascript:;" onclick="user_resetPWD('导出','${context_root}/system/toUserAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 导出</a></span>
	 <span class="l" style="padding-left: 10px"><a href="javascript:;" onclick="supplier_add('添加供货商','${context_root}/stock/toSupplierAdd.action','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加供货商</a></span>
	</div> --%>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">序号</th>
				<th width="10%">供货商名称</th>
				<th width="15%">地址</th>
				<th width="10%">电话</th>
				<th width="10%">手机</th>
				<th width="10%">邮箱</th>
				<th width="5%">状态</th>
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
        "mData": "address",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.address != null) {
                return row.address;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "tel",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.tel != null) {
                return row.tel;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "phone",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.phone != null) {
                return row.phone;
            } else {
                return "";
            }
		}
    },
    {
        "mData": "email",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.email != null) {
                return row.email;
            } else {
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
            } else if(row.status=='0'){
                return "<span class=\"label label-defaunt radius\">已禁用</span>";
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
            var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"supplier_edit('编辑','${context_root}/stock/toSupplierModify.action?Id=" + row.id + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\">编辑</a>";

        	//删除  statusTools(row)
        	var toDelete = "<a title=\"删除\" href=\"javascript:;\" onclick=\"supplier_del(this,\'" + row.id + "\')\" class=\"ml-5\" style=\"text-decoration:none\">删除</a>";
        	return   statusTools(row)+"&nbsp;&nbsp;"+toEdit + "&nbsp;&nbsp;" + toDelete ;
        }
    },
    ];
    var url = "${context_root}/stock/supplierList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
});

 function statusTools(row) {
    if (row.status == '1') {
        return "<a  class=\"label label-success radius\" style=\"text-decoration:none\" onClick=\"user_stop(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"停用\" class=\"label label-defaunt radius\">禁用</a>";
    } else if(row.status=='0') {
        return "<a class=\"label label-success radius\" style=\"text-decoration:none\" onClick=\"user_start(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"启用\" class=\"label label-success radius\">启用</a>";
    }
}

function query() {
    var status = $("#statusSelect option:selected").val();
    var name=$("#userName").val();
        //alert(status+"       "+name);
    pageTable.fnSettings().sAjaxSource = "${context_root}/stock/supplierList.action?status=" + status+"&name="+name;
    pageTable.fnClearTable(0);
    pageTable.fnDraw();
}


/*用户-添加*/
 function supplier_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*用户-编辑*/
 function supplier_edit(title,url,w,h){
	layer_show(title,url,w,h);
}


/*供货商-停用*/
function user_stop(obj,id){
	parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/stock/changeSupplierStatus.action?Id=" + id +"&status=0",
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){                                                                                       
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a class=\"label label-success radius\" style="text-decoration:none" onClick="user_start(this,'+"'"+id+"'"+')" href="javascript:;" title="启用">启用</a>');
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

/*供货商-启用*/
 function user_start(obj,id){
	parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			url:"${context_root}/stock/changeSupplierStatus.action?Id=" + id+"&status=1",
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){
				if(data.s == true){
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="user_stop(this,'+"'"+id+"'"+')" href="javascript:;" title="停用" style="text-decoration:none">禁用</a>');
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

/*供货商-删除*/
 function supplier_del(obj,id){
	parent.layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
		$.ajax({
			    url:"${context_root}/stock/deleteSupplierById.action?Id=" + id,
				type:'post',
				async:false ,
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


 /*导出信息*/
 function exportExcel() {
	 var status = $("#statusSelect option:selected").val();
	 var name=$("#userName").val();
	   // var jsonObject = '{\"status\":\"' + status + '\",\"name\":\"' + name+ '\"}';
     try{
         var elemIF = document.createElement("iframe");
         elemIF.src = "${context_root}/stock/exportSupplierList.action?status=" + status+"&name="+name;
         elemIF.style.display = "none";
         document.body.appendChild(elemIF);
     }catch(e){

     }
 }
 /*导入信息  */
/*   function importExcel(){
		layer.open({
			  type: 1,
			  title:"导入信息",
			  skin: 'layui-layer-lan', //加上边框
			  area: ['420px', '240px'], //宽高
			  content: $('#imp_container')
			 
		})
	} 
 $("#import-form").submit(function(e){
	  if($("#file").val()==null||$("#file").val()==""){
		  parent.layer.alert("请选择导入文件！" , {icon: 2,title:"提示"});
		  return false;
	  }
	}); */
 
	/* function importExcel(){
		
		$("#imp_container").css("display", "block");
		$("img").attr("width","180");
		$("#import-form").attr("action","${context_root}/stock/importSupplierList.action");
		$("#downMol").attr("href","${context_root}/stock/importSupplierModel.action");
		
		$("#import-form").submit(function(e){
			  if($("#file").val()==null||$("#file").val()==""){
				  parent.layer.alert("请选择导入文件！" , {icon: 2,title:"提示"});
				  return false;
			  }
			});
	}
	  */
	function importExcel(){ 
		var url='${context_root}/stock/importRequest.action?impUrl='+encodeURIComponent('/stock/importSupplierList.action')+'&molUrl='+encodeURIComponent('/stock/importSupplierModel.action')
		layer.open({
			  type: 2,
			  title: '导入',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['380px', '50%'],
			  content: url
			});
	}


</script>
</body>
</html>