package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.ScriptDAO;
import com.model.ScriptForm;
import com.opensymphony.xwork2.ActionSupport;
import com.tools.MyPagination;
import com.tools.StringUtils;

public class ScriptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ScriptDAO scriptDAO = null;
    private StringUtils su=new StringUtils();
    MyPagination pagination=null;
    
   List<ScriptForm> scriptFormList ; 
   String result;
    
    public ScriptAction() {
        this.scriptDAO = new ScriptDAO();
    }
    
    public String execute() throws Exception {
  
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String action = request.getParameter("action");
    	
		if("scriptQuery".equals(action)){
			request.setAttribute("scripList",scriptDAO.query(null));	
			this.setScriptFormList(scriptDAO.query(null));
			return "scriptQuery"; 		 
		}else if("scriptAdd".equals(action)){
			ScriptForm scriptForm=new ScriptForm();
			
			if(!"".equals(request.getParameter("wishMan"))){
				scriptForm.setWishMan(su.StringtoSql(request.getParameter("wishMan")));
	    		if(!"".equals(request.getParameter("wellWisher"))){
	    			scriptForm.setWellWisher(su.StringtoSql(request.getParameter("wellWisher"))); 
	    			if(!"".equals(request.getParameter("content"))){
	    				scriptForm.setContent(su.StringtoSql(request.getParameter("content")));
	    				scriptForm.setColor(Integer.parseInt(request.getParameter("color")));
	    				scriptForm.setFace(Integer.parseInt(request.getParameter("face")));
	    				String rtn=scriptDAO.insert(scriptForm);					
	    		    	request.setAttribute("returnValue",rtn);
	    			}else{
	    				request.setAttribute("returnValue","Please enter your wish message.");	
	    			}
	    		}else{
	    			//String rtn=scriptDAO.insert(scriptForm);					
	    			request.setAttribute("returnValue","Please enter wisher");
	    		}
	     	}else{
	    		request.setAttribute("returnValue","wish man");
	    	}
			return "scriptAdd";			
		}else if("addHoldout".equals(action)){
			int id=Integer.parseInt(request.getParameter("scriptId"));		
	    	String hits=scriptDAO.holdoutAdd(id);							
	    	request.setAttribute("hits", hits);
			return "holdoutAdd";		
		}else if("scriptList".equals(action)){
			//StringUtils su=new StringUtils();
	    	String strPage=(String)request.getParameter("Page");
	    	String f = request.getParameter("f"); 
	    	String key = request.getParameter("key"); 
	    	String condition="";
	    	if(("".equals(f) ||null==f) || ("all".equals(f) && "".equals(key))){
	    		condition="ORDER BY sendTime DESC";
	    	}else if("all".equals(f) && !"".equals(key)){
	    		condition="WHERE wishMan like '%"+key+"%' OR wellWisher like '%"+key+"%' OR content like '%"+key+"%' ORDER BY sendTime DESC";
	    	}else{
	    		condition="WHERE "+f+" like '%"+key+"%' ORDER BY sendTime DESC";
	    	}
	    	int Page=1;
	    	List list=null;
	    	if(strPage==null){
	    		 pagination=new MyPagination();
	    		list=scriptDAO.query(condition);					
	    		int pagesize=5;										
	    		list=pagination.getInitPage(list,Page,pagesize);	
	    		request.getSession().setAttribute("pagination",pagination);
	    		this.setScriptFormList(list);
	    	}else{
	    		pagination=(MyPagination)request.getSession().getAttribute("pagination");
	    		Page=pagination.getPage(strPage);
	    		list=pagination.getAppointPage(Page);				
	    	}
	    	request.setAttribute("scripList",list);				
	    	request.setAttribute("Page",Page);						
	    	request.setAttribute("f", f);
	    	request.setAttribute("key", key);
			return "scriptList";		
		}else if("scrollScript".equals(action)){
			//request.setAttribute("scrollScript",scriptDAO.queryTop());
			this.setScriptFormList(scriptDAO.queryTop());
			return "scrollScript";		
		}else{
			return "error";
		}
    }

	public List<ScriptForm> getScriptFormList() {
		return scriptFormList;
	}

	public void setScriptFormList(List<ScriptForm> scriptFormList) {
		this.scriptFormList = scriptFormList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
