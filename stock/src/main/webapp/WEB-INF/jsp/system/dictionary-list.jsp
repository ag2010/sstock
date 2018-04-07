<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="字典管理"/>

<link rel="stylesheet" href="${context_root}/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<script type="text/javascript" src="${context_root}/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<body class="pos-r">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 字典管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pos-a" style="width:170px;left:0;height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
	<ul id="menuTree" class="ztree">
	</ul>
</div>

<article style="margin-left: 170px" class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="dictionary_add('添加字典','${context_root}/system/toDictionaryAdd.action?dictionaryId=0','','610')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加字典</a></span></div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr class="text-c">
				<th width="15%">字典名称</th>
				<%--<th width="15%">英文名称</th>--%>
				<th width="15%">编号 </th>
				<%--<th width="15%">创建时间</th>--%>
				<th width="15%">状态</th>
				<th width="15%">操作</th>
			</tr>
			</thead>
		</table>
	</div>
</article>

<SCRIPT type="text/javascript">
	var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: false,
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

	function showRemoveBtn(treeId, treeNode) {
		return true;
	}
	
	function showRenameBtn(treeId, treeNode) {
		return false;
	}

	var pageTable;

    var aoColumns = [
        {
            "mData": "name",
            "bSortable" : false,
            "sClass": "text-c"
        },
        /*{
            "mData": "nameEn",
            "bSortable" : false,
            "sClass": "text-c"
        },*/
        {
            "mData": "typeCode",
            "bSortable" : false,
            "sClass": "text-c"
        },
        /*{
            "sDefaultContent": "创建时间",
            "bSortable" : false,
            "sClass": "text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                return formatDate(row.addTime,"yyyy-MM-dd hh:mm:ss");
            }
        },*/
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
            "sDefaultContent": "编辑",
            "bSortable" : false,
            "sClass": "td-manage text-c",
            "bSearchable": false,
            "mRender": function(data, type, row) {
                //编辑
                var toEdit = "<a title=\"编辑\" href=\"javascript:;\" onclick=\"dictionary_edit('编辑','${context_root}/system/toDictionaryModify.action?dictionaryId=" + row.dictionaryId + "','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
                //添加
                var toAdd = "<a title=\"添加\" href=\"javascript:;\" onclick=\"dictionary_add('添加','${context_root}/system/toDictionaryAdd.action?dictionaryId=" + row.dictionaryId + "','','610')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe600;</i></a>";
                return statusTools(row)  + "&nbsp;&nbsp;" + toEdit  + "&nbsp;&nbsp;" + toAdd;
            }
        },
    ];

	//单击字典触发
	function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.expandNode(treeNode);

        pageTable.fnSettings().sAjaxSource = "${context_root}/system/dictionaryInfoDetail.action?dictionaryId=" + treeNode.id;
        pageTable.fnClearTable(0);
        pageTable.fnDraw();

	}

    function statusTools(row) {
        if (row.status == '1') {
            return "<a style=\"text-decoration:none\" onClick=\"dictionary_stop(this,\'" + row.dictionaryId + "\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>";
        } else {
            return "<a style=\"text-decoration:none\" onClick=\"dictionary_start(this,\'" + row.dictionaryId + "\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe615;</i></a>";
        }
    }

    /*用户-编辑*/
    function dictionary_edit(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*字典-添加*/
    function dictionary_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*字典-停用*/
    function dictionary_stop(obj,dictionaryId){
        parent.layer.confirm('确认要停用吗？',{icon: 3, title:'提示'},function(index){
            $.ajax({
                url:"${context_root}/system/changeDictionaryStatus.action?dictionaryId=" + dictionaryId +"&status=0",
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                success:function(data){
                    if(data.s == true){
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="dictionary_start(this,'+"'"+dictionaryId+"'"+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
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

    /*字典-启用*/
    function dictionary_start(obj,dictionaryId){
        parent.layer.confirm('确认要启用吗？',{icon: 3, title:'提示'},function(index){
            $.ajax({
                url:"${context_root}/system/changeDictionaryStatus.action?dictionaryId=" + dictionaryId +"&status=1",
                type:'post',
                async:true ,
                cache:false ,
                dataType:"json",
                success:function(data){
                    if(data.s == true){
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="dictionary_stop(this,'+"'"+dictionaryId+"'"+')" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
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

	$(document).ready(function(){
		var t = $("#menuTree");
		$.post("${context_root}/system/dictionaryList.action", function(data){
			t = $.fn.zTree.init(t, setting, data);
			t.expandAll(false);
		});

        var url = "${context_root}/system/dictionaryInfoDetail.action?parentId=0";
        pageTable = _Datatable_Init(pageTable, aoColumns, url);
	});



    //删除菜单
    function beforeRemove(treeId, treeNode) {
        parent.layer.confirm("确认删除菜单【 " + treeNode.name + "】吗？",function(index){
            $.ajax({
                url:"${context_root}/system/delMenu.action" ,
                data : {'permsId':treeNode.id,'parentId':treeNode.pId} ,
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

    $("#form-menu-save").validate({
        rules:{
            parentId:{
                required:true,
            },
            permsName:{
                isSpace:true,
                required:true
            },
            permsUrl:{
                isSpace:true,
                required:true
            },
            permsLevel:{
                isSpace:true,
                required:true
            },
            permsKey:{
                isSpace:true,
                required:true,
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var index = parent.layer.load();
            $.ajax({
                url:"${context_root}/system/saveMenu.action",
                type:'post',
                async:true ,
                cache:false ,
                data:$(form).serialize(),
                dataType:"json",
                success:function(data){
                    parent.layer.close(index);
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

    $("#permsIcon").bind("click" , function(){
        layer_show("图标选择","${context_root}/system/menuIcon.action",800,500);
    });

    var selectOption = $("#parentId").html();
    function checkOption(permsType)
    {
        var selectHeader = "<option value='0'>一级菜单</option>";
        if(permsType == '0')
        {
            $("#parentId").find("option").remove();
            $("#parentId").append(selectHeader);
        }
        else
        {
            $("#parentId").find("option").remove();
            $("#parentId").append(selectOption);
            $("select option[value='0']").remove();
        }
    }
</SCRIPT>
</body>
</html>