<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<div id="header"><img src="images/banner.jpg" width="932" height="112" /></div>
  <form id="form1" name="form1" method="post" action=""> 
<div id="navigation">
  <ul>
  <li>请输入字条编号：</li><li><input type="text" name="keyID" id="keyID" class="navigation_input" />&nbsp;&nbsp;</li>
  <li><input type="image" src="images/btn_search.gif" class="noborder" onclick="searchScrip(this.form.keyID);return false;"/>&nbsp;&nbsp;&nbsp;&nbsp;</li>  
  <li><img src="images/addScript_ico.gif" width="12" height="18" /></li><li>&nbsp;<a href="#" onclick="loadScripAdd_window()">贴字条</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><img src="images/listScript_ico.gif" width="12" height="17" /></li><li>&nbsp;<a href="script?action=scriptList">字条列表</a></li>
  </ul>
</div>
  </form>  
<script >
function searchScrip(n){
	value=n.value;
	if(value!=""){
		if(document.getElementById("scrip"+value)){
			Show(value,'shadeDiv');
		}else{
			alert("does not exist!");n.focus();
		}
	}else{
		alert("enter!");n.focus();
	}
}
</script>
