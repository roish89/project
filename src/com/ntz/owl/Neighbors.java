package com.ntz.owl;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;



public class Neighbors {
	String [] idArr;
	LinkedList<NodeNeighbors> list=new LinkedList<NodeNeighbors>();
	Hashtable<Integer,LinkedList<NodeNeighbors>> hashX = new Hashtable<Integer,LinkedList<NodeNeighbors>>(); 
	Hashtable<Integer,Integer> hashY = new Hashtable<Integer,Integer>();
	Writer writer;
	public int counter=0;

	public Neighbors()
	{
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\roi\\Desktop\\project\\aaa\\Neighbors.txt"), "utf-8"));

		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}
	}

	public void addIdToHash(int idX,int idY)
	{

		if(isThereX(idX)==false)
		{		
			list=new LinkedList<NodeNeighbors>();
			list.add(new NodeNeighbors(counter));
			list.add(new NodeNeighbors(idX));
			hashX.put(idX, list);
			counter++;
		}

	}

	public boolean isThereX(int id)
	{

		if(hashX.containsKey(id))
		{
			return true;
		}

		return false;
	}

	public boolean isThereY(int id)
	{

		if(hashY.containsValue(id))
		{
			return true;
		}

		return false;
	}


	public void printHash()
	{ 
		Enumeration items = hashX.keys();
		while(items.hasMoreElements())
			System.out.println(items.nextElement());
	}

	public void parserToHash(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		//writerTofile(x,y);

		addIdToHash(Integer.parseInt(x),Integer.parseInt(y));

	}


	//-------------------------------------------------------- 2


	public void parserToVal(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		//writerTofile(x,y);

		addNeighbors(Integer.parseInt(x),Integer.parseInt(y));

	}
	public void addNeighbors(int idX,int idY)
	{

		if(isThereX(idX)==true)
		{	
			hashX.get(idX).add(new NodeNeighbors(idY));
		}

	}

	public void printListNeihbors()
	{

		for( int key : hashX.keySet() ) {
			for( int i=0; i < hashX.get(key).size(); i++)
			{
				
				System.out.print("=>"+hashX.get(key).get(i).id);
			}
			System.out.println("");
			
		}
		
		
	}
	public void WriteNeihbors()
	{

		for( int key : hashX.keySet() ) {
			for( int i=0; i < hashX.get(key).size(); i++)
			{
				writerTofile("=>"+hashX.get(key).get(i).id);
			
			}
		
			writerTofile("\n");
		}
		
		close();
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

	public void close()
	{
		try 
		{
			writer.close();	
		}
		catch (IOException e) 
		{	
			e.printStackTrace();
		}
	}
}




//------------------------------------------------------------------------------------------------------	









