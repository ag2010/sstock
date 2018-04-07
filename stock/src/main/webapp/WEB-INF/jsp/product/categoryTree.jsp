<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/uiloader/static/h-ui/css/H-ui.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
      type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/uiloader/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/uiloader/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<body>
<div class="dataTable">


    <ul id="menuTree" class="ztree" style="height: 320px;width: 218px;margin-top: 0px"></ul>
    <input class="btn btn-secondary-outline radius size-M" id="perms" type="button" value="完成" style="width: 230px;">
    <input class="btn btn-secondary-outline radius size-M" id="cancel" type="button" value="取消" style="width: 230px;">
    <script type="text/javascript">
        var index = parent.layer.getFrameIndex(window.name);


        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true,
                chkStyle: "radio",  //单选框
                radioType: "all"   //对所有节点设置单选
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
        };
        var zTree;
        $(document).ready(function () {
            var zTree = $("#menuTree");
            var tree = '${zTreeNodes}';
            var zTreeNodes = eval(tree);
            zTree = $.fn.zTree.init(zTree, setting, zTreeNodes);

            $("#perms").bind("click", function () {
                var nodes = zTree.getCheckedNodes();
                var tmpNode;
                var perms = "";
                var permsName = "";

                for (var i = 0; i < nodes.length; i++) {
                    tmpNode = nodes[i];
                    if (i != nodes.length - 1) {
                        perms += tmpNode.id + ",";
                        permsName += tmpNode.name + ",";
                    } else {
                        perms += tmpNode.id;
                        permsName += tmpNode.name;
                    }
                }
                parent.$("#categoryId").val(perms);
                parent.$("#categoryName").val(permsName);
                parent.layer.close(index);
            });

            $("#cancel").bind("click", function () {
                parent.layer.close(index);
            });
        });
    </script>
</body>
</html>