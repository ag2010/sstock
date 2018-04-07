<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<body>
<article class="page-container">
    <div class="mt-20">
            <table class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                <tr class="text-c">
                    <th width="6%">入库单编号</th>
                    <th width="10%">商品类别</th>
                    <th width="10%">入库量</th>
                    <th width="6%">时间</th>
                </tr>
                </thead>
            </table>
    </div>
</article>


<script type="text/javascript">
var pageTable;
$(document).ready(function () {
    var aoColumns = [
         {
            "mData": "inputId",
            "bSortable": false,
            "sClass": "text-c",
            "mRender": function (data, type, row) {
                if (row.inputId != null) {
                    return row.inputId;
                } else {
                    return "";
                }
            }
        },
        {
            "mData": "productName",
            "bSortable": false,
            "sClass": "text-c",
            "mRender": function (data, type, row) {
                if (row.productName != null) {
                    return row.productName;
                } else {
                    return "";
                }
            }
        },
        {
            "mData": "number",
            "bSortable": false,
            "sClass": "text-c",
            "mRender": function (data, type, row) {
                if (row.number != null) {
                    return row.number;
                } else {
                    return "";
                }
            }
        },
       
        {
            "mData": "inputTime",
            "bSortable": false,
            "sClass": "text-c",
            "mRender": function (data, type, row) {
                if (row.inputTime != null) {  
                    return formatDate(row.inputTime,"yyyy-MM-dd hh:mm:ss");
                } else {
                    return "";
                }
            }
        },
        
        
    ];
    var url = "${context_root}/stock/getStoreInfo.action?barCode=" + GetQueryString('barCode');
    pageTable = _Datatable_Init(pageTable, aoColumns, url);

})


    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }




 
</script>
</body>
</html>