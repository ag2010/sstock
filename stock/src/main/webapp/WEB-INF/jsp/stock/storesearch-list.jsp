<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="仓库管理"/>
<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存管理 <span class="c-gray en">&gt;</span> 仓库查询 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="text-c">
		<span class="select-box" style="width: 120px;">
           <select name="status" id="status" class="select" autocomplete="off">
               <option value="">状态</option>
               <option value="1">启用</option>
               <option value="0">禁用</option>
           </select>
       </span>
	   <span class="select-box" style="width: 120px;border: hidden;">
	       <%-- <y:select id="storesId" name="storesId" codeGroup="${storeName}" selectedValue=""
					cssClass="select" headerKey="" headerValue="仓库">
		   </y:select> --%>
		   <y:select id="storesId" name="storesId" codeGroup="[{'id':'c4f51326bbc24d2f80199c1af2161c56','value':'总仓 '}]" selectedValue=""
					cssClass="select" headerKey="" headerValue="仓库">
		   </y:select>
       </span>
        <input id="categoryName" class="input-text" name="categoryName" placeholder="全部分类" style="width:250px;"  onclick="showMenu();return false;">
	    <input type="hidden" id="categoryId" name="categoryId">
	    <ul id="categoryHiddenTree" class="ztree" style="display:none;background-color: white;"></ul>
		<input type="text" class="input-text" style="width:250px" placeholder="内部编码 |条码|名称" id="codeOrName" name="codeOrName">
		<button type="button" class="btn btn-success radius" onclick="query()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">
	 <a href="javascript:;" onclick="stores_exportExcel()" class="btn btn-primary radius"><!-- <i class="Hui-iconfont">&#xe600;</i> --> 导出</a>
	 </span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<!-- <th style="width: 7%;height: 20px;"><input  onclick="allSelect()" type="checkbox"name="allSel" value=""/></th> -->
				<th width="5%">序号</th>
				<th width="5%">内部编码</th>
				<th width="5%">商品名称</th>
				<th width="5%">条码</th>
				<th width="5%">分类</th>
				<th width="5%">仓库</th>
				<th width="10%">库存</th>
				<th width="5%">单位</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<script type="text/javascript">
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
        "mData": "code",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
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
        "mData": "stock",
        "bSortable" : false,
        "sClass": "text-c",
        "mRender":function(data, type, row){
			if (row.stock != null) {                                                                                                     
                return "<a class='label label-warning' href='javascript:void(0);' onclick=\"show_dialog('查看入库单','${context_root}/stock/toStoreInfoHandle.action?barCode=" + row.barCode + "','','600')\">"+row.stock+"</a>";
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
    ];
    var url = "${context_root}/stock/storesearchList.action";
    pageTable = _Datatable_Init(pageTable, aoColumns, url);
    
    
    
    //获取所有商品分类
    $.post("${context_root}/product/productCategoryList.action", function(data){ 
		var ht = $("#categoryHiddenTree");
		ht = $.fn.zTree.init(ht, setting, data); //这里是初始化    data是你后端的数据
		ht.expandAll(true); //展开全部节点
	});
    
});

function statusTools(row) {
    if (row.status == '1') {
        return "<a style=\"text-decoration:none\" onClick=\"stores_stop(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"停用\" class=\"label label-defaunt radius\">禁用</a>";
    }else{
        return "<a style=\"text-decoration:none\" onClick=\"stores_start(this,\'" + row.id + "\')\" href=\"javascript:;\" title=\"启用\" class=\"label label-success radius\">启用</a>";
    }
}


function callBack(title,url,w,h){
    layer_show(title,url,w,h);
}

function query(){
    var categoryId = $("#categoryId").val(); 
    var storesId = $("#storesId option:selected").val();
    var status = $("#status option:selected").val();
    var codeOrName = $("#codeOrName").val().replace(/\s+/g,"");
    
    pageTable.fnSettings().sAjaxSource = encodeURI("${context_root}/stock/storesearchList.action?status=" + status+"&storesId=" + storesId +"&categoryId="+ categoryId +"&codeOrName="+codeOrName);
    pageTable.fnClearTable(0);                                                                 
    pageTable.fnDraw();
}


/*库存信息导出*/
function stores_exportExcel(){
	 var categoryId = $("#categoryId").val();
	 var storesId = $("#storesId option:selected").val();
	 var status = $("#status option:selected").val();
	 var codeOrName = $("#codeOrName").val().replace(/\s+/g,"");
	 var jsonObject = '{\"status\":\"' + status + '\",\"storesId\":\"' + storesId + '\",\"categoryId\":\"'+ categoryId +'"\,\"codeOrName\":\"'+ codeOrName +'"\}';
	 try{ 
	        var elemIF = document.createElement("iframe");  
	        elemIF.src = "${context_root}/stock/expStoresList.action?jsonObject="+encodeURIComponent(jsonObject);   
	        elemIF.style.display = "none";   
	        document.body.appendChild(elemIF);   
	    }catch(e){ 

	    }  
}


/*根据条形码查询入库单*/
function show_dialog(title,url,w,h){
	layer_show(title,url,w,h);
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


//点击搜索框，显示树
function showMenu() {
	var categoryName = $("#categoryName")[0]; 
	$("#categoryHiddenTree").css({left:categoryName.offsetLeft + "px", top:categoryName.offsetTop+30 + "px",zIndex: 100,position: "absolute"}).slideDown("fast");
	var zTree = $.fn.zTree.getZTreeObj("categoryHiddenTree");
	zTree.expandAll(true);
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#categoryHiddenTree").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "categoryHiddenTree" || $(event.target).parents("#categoryHiddenTree").length>0)) {
		hideMenu();
	}
}



//单击菜单触发，商品分类树形结构
function onClick(e,treeId, treeNode){ 
		 
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
		}
		
		hideMenu(treeId); 
}







</script> 
</body>
</html>