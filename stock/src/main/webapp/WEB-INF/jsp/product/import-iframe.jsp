<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<ys:contentHeader title="商品管理"/>
<style>
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
	<div id="imp_container" > 
		<br/> 
		&nbsp;&nbsp;&nbsp;<a id="downMol" href=" "  class="btn btn-primary radius">模板下载</a> 
		<br/> <br/> 
		<form id="import-form" name="import-form" action="" method="post"  enctype="multipart/form-data"> 
           	<div  class="file">选择文件
			     &nbsp;&nbsp;&nbsp;<input type="file" id="file" name="file" value="选择文件"/>
			</div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-success radius" value="导入">
        </form>
        <br/>  
	</div>
</body>
<script type="text/javascript">  
$(document).ready(function(){ 
	var impUrl=GetQueryString("impUrl");
	var molUrl=GetQueryString("molUrl"); $("img").attr("width","180");
	$("#import-form").attr("action","${context_root}"+impUrl);
	$("#downMol").attr("href","${context_root}"+molUrl);
	
	$("#import-form").submit(function(e){
		  if($("#file").val()==null||$("#file").val()==""){
			  parent.layer.alert("请选择导入文件！" , {icon: 2,title:"提示"});
			  return false;
		  }
		});
	
	$('#file').change(function(){
		var a=this.value.split('.');
		if(a[a.length-1]!="xls"){
			parent.layer.alert("请选择.xls文件！" , {icon: 2,title:"提示"});
			this.value="";
		}
	});
})
 
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
</script>
</html>