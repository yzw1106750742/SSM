<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript">
function getJosn(){
alert(111);
$.post("getJson.action",null,function(data){
     alert("name"+data[0].username+"id"+data[0].userid+"pwd"+data[0].userpwd);
   }, "json");
}

</script>
  </head>
  
  <body>
  <form action="UserInfo.action?" method="post" id="form1">
   <input type="text" name="username" ></br>
   <input type="submit">
   <input type="button" value="json" onclick="getJosn();">
  <a href="getDownload.action?filename=可爱的吃手手.jpg">下载可爱的吃手手</a></br>
   </form>
   <form id="form2" action="upload.action" method="post" enctype="multipart/form-data">
  选择图片1 <input type="file" name="file1">
   选择图片2<input type ="file" name="file2">
   <input type="text" name="testid">
   <input type="submit" value="上传">
   </form>
  </body>
</html>
