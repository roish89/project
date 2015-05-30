package com.ntz.collaboration_networks;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;

public class ParserToNewFile {
	Writer writer,Map;
	public int newID=0,newIDMap=0;
	Hashtable<Integer,Integer> hashID = new Hashtable<Integer,Integer>();
	Hashtable<Integer,Integer> hashMap = new Hashtable<Integer,Integer>();
	String valueY;
	String valueX;

	public ParserToNewFile()
	{
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\roi\\Desktop\\project\\aaa\\newFile.txt"), "utf-8"));
			Map=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\roi\\Desktop\\project\\aaa\\HashMap.txt"), "utf-8"));
		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}
	}
	
	
	public void parserToAddID(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		//writerTofile(x,y);
		addIdToHashMap(Integer.parseInt(x),Integer.parseInt(y));
		addIdToHash(Integer.parseInt(x),Integer.parseInt(y));

	}
	
	public void addIdToHash(int idX,int idY)
	{
		
		if(isThereKey(idX)==false)
		{		
			hashID.put(idX, newID);
			newID++;
		}

	}
	public void addIdToHashMap(int idX,int idY)
	{
		
		if(isThereKey(idX)==false)
		{		
			hashMap.put(newIDMap,idX);
			newIDMap++;
		}

	}
	
	
	public boolean isThereKey(int id)
	{

		if(hashID.containsKey(id))
		{
			return true;
		}

		return false;
	}

	public void printHashID()
	{ 
		for (int i = 0; i < hashMap.size(); i++) 
		{
			writerToHashMap(Integer.toString(i)+" "+hashMap.get(i).toString() );
			writerToHashMap("\n");
		}

	
		
	}
	
	
	public void parserToNewFile(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		//writerTofile(x,y);

		createNewFile(Integer.parseInt(x),Integer.parseInt(y));

	}
	
	public void createNewFile(int idX,int idY)
	{
		//System.out.println(hashID.get(idX));
				valueY=hashID.get(idY).toString();
				valueX=hashID.get(idX).toString();
				writerTofile(valueX+" "+valueY);
				writerTofile("\n");

	}
	
	public void writerTofile(String x)
	{
		try 
		{
			writer.write(x);
			writer.flush();
		} 
		catch(Exception e)
		{
			System.err.println("error: writerTofile ");
		}
	}
	public void writerToHashMap(String x)
	{
		try 
		{
			Map.write(x);
			Map.flush();
		} 
		catch(Exception e)
		{
			System.err.println("error: writerTofile ");
		}
	}



}
