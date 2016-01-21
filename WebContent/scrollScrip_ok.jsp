<%@ page contentType="text/html; charset=UTF-8" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="scriptFormList">
	[<span style="color:#990000"><s:property value="wellWisher" /></span>]祝福[<span style="color:#990000"><s:property value="wishMan" /></span>]：<s:property value="content" /> <span style="color:#666666"><s:property value="sendTime" /></span>
</s:iterator>