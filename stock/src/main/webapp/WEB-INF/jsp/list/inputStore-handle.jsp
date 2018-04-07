<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ys:contentHeader/>
<style>
    article {
        padding: 15px 15px
    }

    .form-label {
        padding: 0 5px;
        line-height: 4;
    }

    .input_bar {
        padding: 3px 4px;
        margin: 0;
        font-size: 10px;
        margin: 5px 0 0 0 !important;
    }

    .input_bar .input-text {
        width: 150px;
    }

</style>
<body>
<article>
    <form action="" method="post" class="form form-horizontal" id="form-role-add">
        <div class="row cl">
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 入库单 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <input type="text" class="input-text" value="${inputStore.id}" placeholder="" id="id" name="id">
                </div>
            </div>
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 采购单号 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <input type="text" class="input-text" value="${inputStore.procurementId}" placeholder=""
                           id="procurementId" name="procurementId">
                </div>
            </div>
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 仓库 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <y:select id="storeId" name="storeId" codeGroup="${proStore}" selectedValue="${inputStore.storeId}"
                              cssClass="select" headerKey="" headerValue="请选择">
                    </y:select>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 派单时间 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <input type="text" onfocus="WdatePicker()" id="inputStoreTime" name="inputStoreTime"
                           class="input-text Wdate"
                           value="<fmt:formatDate value="${inputStore.inputStoreTime}" pattern="yyyy-MM-dd"/>"
                           style="width:120px;">

                </div>
            </div>
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 状态 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <y:select id="" name="" codeGroup="[{'id':'1','value':'审核中'},{'id':'2','value':'已完成'}]"
                              selectedValue="${inputStore.status}"
                              cssClass="select" headerKey="0" headerValue="请选择">
                    </y:select>
                </div>
            </div>
            <div class="row col-xs-4 col-sm-4  input_bar">
                <label class="form-label col-xs-4 col-sm-4"> 类型 </label>
                <div class="row col-xs-7 col-sm-7 ">
                    <y:select id="type" name="type"
                              codeGroup="[{'id':'1','value':'普通入库单'},{'id':'0','value':'门店退货入库单'}]"
                              selectedValue="${inputStore.type}"
                              cssClass="select" headerKey="" headerValue="请选择">
                    </y:select>
                </div>
            </div>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                <tr class="text-c">
                    <th width="5%">序号</th>
                    <th width="10%">商品分类</th>
                    <th width="10%">采购量</th>
                    <th width="5%">单位</th>
                    <th width="10%">采购价（元/单位）</th>
                    <th width="10%">备注</th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="row cl">
            <br/><br/>
            <div class="row col-xs-9 col-sm-9 input_bar">
                <textarea id="remark" name="remark" placeholder="备注" rows="4"
                          style="width:100%;margin-left: 15px;">${inputStore.remark}</textarea>
            </div>
        </div>
        <shiro:hasAnyRoles name="管理员,库存主管">
            <div class="row col-xs-12 col-sm-12">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input type="hidden" class="input-text" value="2" placeholder="" id="status" name="status">
                    <br/>
                    <input class="btn btn-primary radius" type="submit" id="save" value="&nbsp;&nbsp;通过&nbsp;&nbsp;">
                    <br/><br/>
                </div>
            </div>
        </shiro:hasAnyRoles>
    </form>
</article>


<script type="text/javascript">
    var pageTable;
    $(document).ready(function () {
        var aoColumns = [
            {
                "mData": "no",
                "bSortable": false,
                "sClass": "text-c",
                "mRender": function (data, type, row) {
                    if (row.no != null) {
                        return row.no;
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
                "mData": "unit",
                "bSortable": false,
                "sClass": "text-c",
                "mRender": function (data, type, row) {
                    if (row.unit != null) {
                        return row.unit;
                    } else {
                        return "";
                    }
                }
            },
            {
                "mData": "purchasePrice",
                "bSortable": false,
                "sClass": "text-c",
                "mRender": function (data, type, row) {
                    if (row.purchasePrice != null) {
                        return row.purchasePrice;
                    } else {
                        return "";
                    }
                }
            },
            {
                "mData": "remark",
                "bSortable": false,
                "sClass": "text-c",
                "mRender": function (data, type, row) {
                    if (row.remark != null) {
                        return row.remark;
                    } else {
                        return "";
                    }
                }
            }
        ];
        var url = "${context_root}/list/getInputStoreOrderInfo.action?id=" + GetQueryString('id');
        pageTable = _Datatable_Init(pageTable, aoColumns, url);

        initButton()
    })

    function initButton() {
        var handleType = GetQueryString("handleType");
        var productId = GetQueryString("productId");
        if (handleType == "look") {
            $("#save").css("display", "none");
        } else if (handleType == "check") {
            $("#save").val("通过");
        } else {
            parent.layer.alert("链接参数错误!handleType=" + handleType, {icon: 2, title: "系统提示"});
        }
    }


    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function formatDateStr(dateStr, a) {
        alert(1111);
        var d = new Date(date);
        a.val(d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate());
    }


    $("#form-role-add").validate({
        rules: {},
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var index = parent.layer.load();
            $.ajax({
                url: "${context_root}/list/checkInputStore.action",
                type: 'post',
                async: true,
                cache: false,
                data: $(form).serialize(),
                dataType: "json",
                success: function (data) {
                    parent.layer.close(index);
                    if (data.s == true) {
                        index = parent.layer.getFrameIndex(window.name);
                        parent.layer.msg("保存成功,正在刷新数据请稍后……", {icon: 1, time: 1000, shade: [0.1, '#fff']}, function () {
                            window.parent.location.reload();
                        });
                    } else {
                        parent.layer.alert(data.m, {icon: 2, title: "系统提示"});
                    }
                },
            });
        }
    });
</script>
</body>
</html>