package com.ntz.owl;

import Node2;

public class Neighbors {
	String [] idArr;
	
	public void addId(String id)
	{
		for (int i = 0; i < idArr.length; i++)
		{
			if(!idArr[i].equals(id))
			{
				idArr[idArr.length]=id;
			}
		}
	}
	
	public void printArr()
	{
		for (int i = 0; i < idArr.length; i++)
		{
			System.out.println(idArr[i]);
		}
	}
	
	public void toList(String lineXml)
	{
		String type,symbol="",temp;
		String[] parts;
		parts =lineXml.split(" ");
		type=parts[0];
		parts=type.split("<");
		type=parts[1];
		parts=type.split(">");
		type=parts[0];
		
		if(!(type.equals("tokens")||type.equals("/tokens")))
			{
				parts =lineXml.split(">");
				symbol=parts[1];
				parts =symbol.split("<");
				symbol=parts[0];
				//symbol=symbol.trim();
				//System.out.println(type+":"+symbol);
				symbol=symbol.substring(1, symbol.length()-1);
				list.add(new Node2(symbol,type));
			}
		
			
	}
	
	
	
}
