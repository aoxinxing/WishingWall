package com.tools;

import java.util.ArrayList;
import java.util.List;

import com.model.ScriptForm;

public class MyPagination {
	public List<ScriptForm> list=null;
	private int recordCount=0;
	private int pagesize=0;
	private int maxPage=0;
	
	public List<ScriptForm> getInitPage(List<ScriptForm> list,int Page,int pagesize){
		List<ScriptForm> newList=new ArrayList<ScriptForm>();
		this.list=list;
		recordCount=list.size();
		this.pagesize=pagesize;
		this.maxPage=getMaxPage();
		try{
		for(int i=(Page-1)*pagesize;i<=Page*pagesize-1;i++){
			try{
				if(i>=recordCount){break;}
			}catch(Exception e){}
			newList.add((ScriptForm)list.get(i));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return newList;
	}
	
	public List<ScriptForm> getAppointPage(int Page){
		List<ScriptForm> newList=new ArrayList<ScriptForm>();
		try{
			for(int i=(Page-1)*pagesize;i<=Page*pagesize-1;i++){
				try{
					if(i>=recordCount){break;}
				}catch(Exception e){}
				newList.add((ScriptForm)list.get(i));
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return newList;
	}
	
	public int getMaxPage(){
		int maxPage=(recordCount%pagesize==0)?(recordCount/pagesize):(recordCount/pagesize+1);
		return maxPage;
	}
	
	public int getRecordSize(){
		return recordCount;
	}
	
	public int getPage(String str){
		System.out.println("STR:"+str+"&&&&"+recordCount);
		if(str==null){
			str="0";
		}
		int Page=Integer.parseInt(str);
		if(Page<1){
			Page=1;
		}else{
			if(((Page-1)*pagesize+1)>recordCount){
				Page=maxPage;
			}
		}
		return Page;
	}
	
	public String printCtrl(int Page,String url,String para){
		String strHtml="<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr> <td height='24' align='right'>Current Page：["+Page+"/"+maxPage+"]&nbsp;";
		try{
		if(Page>1){
			strHtml=strHtml+"<a href='"+url+"&Page=1"+para+"'>First</a>　";
			strHtml=strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>Previous</a>";
		}
		if(Page<maxPage){
			strHtml=strHtml+"<a href='"+url+"&Page="+(Page+1)+para+"'>Next</a>　<a href='"+url+"&Page="+maxPage+para+"'>Last&nbsp;</a>";
		}
		strHtml=strHtml+"</td> </tr>	</table>";
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return strHtml;
	}	
}
