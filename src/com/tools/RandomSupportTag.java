package com.tools;

import java.util.Random;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class RandomSupportTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private int n=0;
	private int base=0;
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getBase() {
		return base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	
	public int doStartTag() throws JspException{
		
		JspWriter out=pageContext.getOut();
		try{
			Random random = new Random();
			int num=random.nextInt(n);
			out.print(num+base); //output a random number
		}catch(Exception e){
			System.out.println("Error occurs: "+e.getMessage());
		}
		
		return (SKIP_BODY);
	}
}
