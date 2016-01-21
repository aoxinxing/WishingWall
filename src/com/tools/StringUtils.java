package com.tools;

public class StringUtils {
/*	public String toGBK(String strvalue) {
		try {
			if (strvalue == null) {			
				return "";				
			} else {
				strvalue = new String(strvalue.getBytes("ISO-8859-1"), "GBK");
				return strvalue;		
			}
		} catch (Exception e) {
			return "";
		}
	}

	public String toUTF8(String strvalue) {
		try {
			if (strvalue == null) {			
				return "";					
			} else {
				strvalue = new String(strvalue.getBytes("ISO-8859-1"), "UTF-8");
				return strvalue;	
			}
		} catch (Exception e) {
			return "";
		}
	}
*/

	public String StringtoSql(String str) {
		if (str == null) {				
			return "";					
		} else {
			try {
				str = str.trim().replace('\'', (char) 32);
			} catch (Exception e) {
				return "";
			}
		}
		return str;
	}
}
