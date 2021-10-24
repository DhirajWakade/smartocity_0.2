package com.allinone.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.allinone.constants.Constants;

public class Util 
{
	
	public static Double getPercentageAmount(Integer percentage,Double amount)
	{
		return (amount*percentage)/100;
	}
	
	public static String toUpperCaseWithSpaceReplace(String str)
	{
		str=str.replace(" ","_");
		return str.toUpperCase();
	}
	
	public static Date covertStringToSQLdate(String strDate)
	{		
		try {
		java.util.Date date=new SimpleDateFormat(Constants.dd_MM_yyyy).parse(strDate); 
		java.sql.Date sqlPackageDate = new java.sql.Date(date.getTime());
	    return sqlPackageDate;
		}
		catch (Exception e) {
			e.printStackTrace();
			 return null;
		}
	}
	
	
	
	
}
