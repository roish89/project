package com.ntz.collaboration_networks;

import java.util.Enumeration;
import java.util.Hashtable;

public class Neighbors {
	
	Hashtable<Integer,Hashtable<Integer,Integer>> hashCollaboration;
	Hashtable<Integer,Integer> hashFriends;
	
	public Neighbors()
	{
		
		 hashCollaboration =new Hashtable<Integer,Hashtable<Integer,Integer>>();
		
	}
	
	public void parserToAddID(String line)
	{
		
			String x,y,temp;
			String[] parts;
			parts =line.split("\\s+");
			x=parts[0];
			y=parts[1];

			
			//writerTofile(x,y);

			addIdToHash(Integer.parseInt(x),Integer.parseInt(y));
		
	}
	
	
	public void addIdToHash(int idX,int idY)
	{

		if(isThereX(idX)==false)
		{	
			hashFriends=new Hashtable<Integer,Integer>();
			hashCollaboration.put(idX, hashFriends);
		}
		
	}
	
	public void parserToAddFriend(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		addFriend(Integer.parseInt(x),Integer.parseInt(y));
	}
	
	public void addFriend(int idX,int idY)
	{
		if(isThereX(idX))
		{
			if(hashCollaboration.get(idX).containsKey(idY)==false)
			{
				hashCollaboration.get(idX).put(idY, idY);
			}
		}
		
		
	}
	
	
	public void printHash()
	{ 
		Enumeration items = hashCollaboration.keys();
		while(items.hasMoreElements())
			System.out.println(items.nextElement());
	}
	
	public boolean isThereX(int idX)
	{

		if(hashCollaboration.containsKey(idX))
		{
			return true;
		}

		return false;
	}
	
	public boolean isThereY(int idY)
	{

		if(hashCollaboration.containsKey(idY))
		{
			return true;
		}

		return false;
	}
	
	public void printFriendsOfX()
	{
		Hashtable<Integer,Integer> a;
		a=hashCollaboration.get(1);
		Enumeration items = a.keys();
		while(items.hasMoreElements())
			System.out.println(items.nextElement());
	}
	
	public Hashtable<Integer,Hashtable<Integer,Integer>> getHash()
	{
		return hashCollaboration;
	}
	


}
