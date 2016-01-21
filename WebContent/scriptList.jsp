<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String f="";
String key="";
if(request.getAttribute("f")!=null){
	f=request.getAttribute("f").toString();
	key=request.getAttribute("key").toString();
}
%>
<jsp:useBean id="pagination" class="com.tools.MyPagination" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>心之语许愿墙_字条列表</title>
<link href="css/index.css" rel="stylesheet"/>
<script language="javascript" src="js/AjaxRequest.js"></script>
</head>

<body>
<div id="header"><img src="images/banner.jpg" width="932" height="112" /></div>
 <form id="form1" name="form1" method="post" action="script?action=scriptList">
<div  id="navigation"> 
 <ul>
	 <li><img src="images/home_ico.gif" width="15" height="17" /></li>
	 <li><a href="script?action=scriptQuery">返回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
	 <li>请选择查询条件：</li>
 	 <li><select name="f" class="wenbenkuang" id="f">
	        <option value="all" selected="selected">全部</option>
	        <option value="wishMan">祝福对象</option>
	        <option value="wellWisher">祝福者</option>
	        <option value="content">字条内容</option>
	</select></li>
   	<li> 关键字：</li>
   	<li><input name="key" type="text" id="key" size="40" class="navigation_input"/>&nbsp;</li>
    <li><input type="image" src="images/btn_search.gif" class="noborder"/></li>
 </ul>
</div>
</form>
<!--开始显示字条信息-->
<jsp:include page="scrollScrip.jsp"/>
<div id="main" style="padding-top:5px;">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#3F873B">
      <tr>
        <td height="27" align="center" bgcolor="#D9EE9F">字条编号</td>
        <td align="center" bgcolor="#D9EE9F">祝福对象</td>
        <td align="center" bgcolor="#D9EE9F">祝福者</td>
        <td align="center" bgcolor="#D9EE9F">字条内容</td>
        <td align="center" bgcolor="#D9EE9F">发送时间</td>
        <td align="center" bgcolor="#D9EE9F">人气</td>
      </tr>
	<s:iterator value="scriptFormList" >
      <tr>
        <td height="27" bgcolor="#E8F3D1">&nbsp;
        <s:property value="id" /></td>
        <td bgcolor="#E8F3D1">&nbsp;
        <s:property value="wishMan" /></td>
        <td bgcolor="#E8F3D1">&nbsp;
        <s:property value="wellWisher" /></td>
        <td bgcolor="#E8F3D1">&nbsp;
        <s:property value="content" /></td>
        <td bgcolor="#E8F3D1">&nbsp;
        <s:property value="sendTime" /></td>
        <td bgcolor="#E8F3D1">&nbsp;
        <s:property value="hits" /></td>
      </tr>
	</s:iterator>
  </table>
  <%=pagination.printCtrl(Integer.parseInt(request.getAttribute("Page").toString()),"script?action=scriptList","&f="+f+"&key="+key)%> 
</div>
<!--显示字条信息结束-->
<jsp:include page="copyright.jsp"/>
</body>
</html>