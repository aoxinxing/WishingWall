<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<script>
timer = window.setInterval("getScrollScrip()",10000); 
window.onload=function(){
	 getScrollScrip();		//当页面载入后调用Ajax获取最新的10条字条信息
}
function getScrollScrip(){
	var loader1=new net.AjaxRequest("script?action=scrollScript&nocache="+new Date().getTime(),deal_getScrollScrip,onerror,"GET");
}
//Ajax的回调函数
function deal_getScrollScrip(){
	document.getElementById("scrollScriptContent").innerHTML=this.req.responseText;
}
</script>
<div id="scrollScrip">最新10条字条：
  <marquee direction="left" scrollamount="2" width="90%" height="30" onMouseMove="this.stop()" onMouseOut="this.start()">
  <span id="scrollScriptContent">正在获取字条内容......</span>
  </marquee>
</div>