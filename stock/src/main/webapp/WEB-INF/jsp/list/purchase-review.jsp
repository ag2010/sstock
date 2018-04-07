<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<ys:contentHeader/>
<style type="text/css">
   .input-text{
   width:205px;
   }
   label.form-label.col-xs-4.col-sm-3{
   width:200px;
   font-size: 15px;
   font-family:inherit;
   }
      label.form-label.col-xs-4.col-sm-3.goods{
   width:200px;

   }
   #one{
   margin-left:105px;
   width:650px;
   }
 div.formControls.col-xs-8.col-sm-4{
   width:150px;
   }
 
   div.formControls.col-xs-8.col-sm-4.good{
   width:100px;
   }
   form#form-user-add.form.form-horizontal{
   width:800px;
   }
   div#review.row.cl{
   width:300px;
   margin-left:18px;
   }
   body{
   width:1200px;
   heght:550px;
   }
</style>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-user-add">
          <input type="hidden"  name="id"  value="${purchase.id}"  id="num">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>订货单号:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${purchase.id}"  id="pnum" name="id">
            </div>
            <label class="form-label col-xs-4 col-sm-3" ><span class="c-red"></span>订货时间:</label>
            <div class="formControls col-xs-8 col-sm-4">  
                <input type="text" class="input-text"   value="<fmt:formatDate value="${purchase.purchaseTime}" pattern="yyyy-MM-dd hh:mm:ss"/>"  id="ptime"
                       name="purchaseTime">
            </div>
        </div>
        
        <div  class="row cl" >
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>订货人员:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text"  value="${purchase.purchaser}"   id="actor"
                       name="purchaser">
            </div>
              <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>订货商家:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text"   id="shopname"  value="${purchase.name}"
                           name="name">
            </div>
        </div>
        
           <div  class="row cl" >
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>电&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp话:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text"  value="${purchase.phone}"   id="phone"
                       name="purchaser">
            </div>
              <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>收货地址:</label>
            <div class="formControls col-xs-8 col-sm-4">
              <input type="text" class="input-text"   id="address"  value="${purchase.address}"  name="address" />
                  <%-- <textarea class="textarea"  rows="2" cols="50"  name="address"  id="address"  value="${purchase.address}"  style="height:40px;"></textarea> --%>
                          
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>状&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp态:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <c:if test="${purchase.status == 0}"><input type="text" class="input-text"  value="已拒绝" name="status"></c:if>
                <c:if test="${purchase.status == 1}"><input type="text" class="input-text"   value="待审核" name="status"></c:if>
                <c:if test="${purchase.status == 2}"><input type="text" class="input-text"   value="配货中" name="status"></c:if>
                <c:if test="${purchase.status == 3}"><input type="text" class="input-text"   value="配送中" name="status"></c:if>
                <c:if test="${purchase.status == 4}"><input type="text" class="input-text"   value="已完成" name="status"></c:if>
                <c:if test="${purchase.status == 9}"><input type="text" class="input-text"   value="已作废" name="status"></c:if>
            
            </div>
             <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>配送单号:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${purchase.deliveryId}"  id="song" name="deliveryId">
            </div>
        </div>
       <div id="bei" class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${purchase.remark}"  id="remark" name="remark">
            </div>
             <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>仓&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp库:</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="总仓" id="stock" name="stock">
            </div>
        </div>
        	<div id="one" class="mt-20">
	           <table class="table table-border table-bordered table-hover table-bg table-sort">
		         <thead>
			<tr class="text-c">
				<th width="5%">序号</th>
				<th width="8%">商品名称</th>
				<th width="8%">条码</th>
				<th width="5%">现有库存</th>
				<th width="5%">请求量</th>
				<th width="5%">单位</th>
				<th width="10%">备注</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${orderlist}"  var="order">
		       <tr class="text-c">
		          <th>${order.no}</th>
		          <th>${order.productName}</th>
		          <th>${order.barCode}</th>
		          <th>${order.stock}</th>
		          <th>${order.number}</th>
		          <th>${order.unit}</th>
		          <th>${order.remark}</th>	       
		       </tr>
		   
		   </c:forEach>
		</tbody>
	</table>
	</div>
	
	  <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3 goods"><span class="c-red"></span>商品总数:</label>
            <div class="formControls col-xs-8 col-sm-4 good">
                <input type="text" class="input-text" value="${count.totalProduct}" id="supname" name="name">
            </div>
            <label id ="shu" class="form-label col-xs-4 col-sm-3 goods" ><span class="c-red"></span>总件数:</label>
            <div class="formControls col-xs-8 col-sm-4 good">
                <input type="text" class="input-text"  value="${count.totalNum}"  id="address"
                       name="address">
            </div>
        </div>
	
	<shiro:hasAnyRoles name="管理员,运营客服主管">
        <div id="review" class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
            <input class="btn btn-primary radius" type="button" id="accept" onClick="haveAccept()" value="&nbsp;&nbsp;通过&nbsp;&nbsp;">
           <!--  <input class="btn btn-primary radius" type="button" id="refuse"  onClick="haveRefuse()" value="&nbsp;&nbsp;拒绝&nbsp;&nbsp;"> -->
            </div>
        </div>
    </shiro:hasAnyRoles>
    </form>
</article>


<script type="text/javascript">
$(function(){
	var status=${purchase.status};
	//alert("status==="+status);
	if(status==0 || status==2 || status==4 || status==9 ){
		$("#review").css("display","none");
	}else{
		$("#review").css("display","block");
	}
});
function haveAccept(){
	var uud=$("#num").val();
		$.ajax({
			url:"${context_root}/list/addDlivery.action?Id=" + uud,
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){                                                                                               
				if(data.s == true){
					parent.layer.msg('审核通过!',{icon: 6,time:1000});
					//window.parent.location.reload();
					setTimeout('window.parent.location.reload()',1200);
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;	
}

function haveRefuse(){
	var uud=$("#num").val();
		$.ajax({
			url:"${context_root}/list/purchaseRefuse.action?Id=" + uud,
			type:'post',
			async:true ,
			cache:false ,
			dataType:"json",
			success:function(data){                                                                                               
				if(data.s == true){
					parent.layer.msg('已拒绝!',{icon: 5,time:1000});
					setTimeout('window.parent.location.reload()',1200);
				}else{
					parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
				}
			},
		}) ;	
}


function formatDateStr(dateStr,a){
	var d = new Date(date);  
	a.val(d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate());  
}


var st="${purchase.purchaseTime}";
var sd=st.substring(11,13);
//alert(sd);
 var tt=$("#ptime").val();
 var td=tt.substring(0,11)+" "+sd+tt.substring(13);
$("#ptime").val(td);
 </script> 
</body>
</html> 