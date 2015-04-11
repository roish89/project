package com.ntz.social_networks;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import com.ntz.collaboration_networks.NodeNeighbors;

public class Neighbors {

	Hashtable<Integer,Hashtable<Integer,Integer>> hashSocial;
	Hashtable<Integer,Integer> hashFriends;
	
	
	public Neighbors()
	{
		
		 hashSocial =new Hashtable<Integer,Hashtable<Integer,Integer>>();
		
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
			//hashFriends.put(idX,idX);
			hashSocial.put(idX, hashFriends);
		}
		
		if(isThereY(idY)==false)
		{
			hashFriends=new Hashtable<Integer,Integer>();
			//hashFriends.put(idY,idY);
			hashSocial.put(idY, hashFriends);
		}

	}
	
	
	public void parserToAddFriend(String line)
	{
		String x,y,temp;
		String[] parts;
		parts =line.split("\\s+");
		x=parts[0];
		y=parts[1];

		
		//writerTofile(x,y);

		addFriend(Integer.parseInt(x),Integer.parseInt(y));
	}
	
	public void addFriend(int idX,int idY)
	{
		if(isThereX(idX))
		{
			if(hashSocial.get(idX).containsKey(idY)==false)
			{
				hashSocial.get(idX).put(idY, idY);
			}
		}
		
		if(isThereY(idY))
		{
			if(hashSocial.get(idY).containsKey(idX)==false)
			{
				hashSocial.get(idY).put(idX, idX);
			}
		}
		
	}
	
	
	public void printHash()
	{ 
		Enumeration items = hashSocial.keys();
		while(items.hasMoreElements())
			System.out.println(items.nextElement());
	}
	
	public boolean isThereX(int idX)
	{

		if(hashSocial.containsKey(idX))
		{
			return true;
		}

		return false;
	}
	
	public boolean isThereY(int idY)
	{

		if(hashSocial.containsKey(idY))
		{
			return true;
		}

		return false;
	}
	
	public void printFriendsOfX()
	{
		Hashtable<Integer,Integer> a;
		a=hashSocial.get(0);
		Enumeration items = a.keys();
		while(items.hasMoreElements())
			System.out.println(items.nextElement());
	}
	
	public Hashtable<Integer,Hashtable<Integer,Integer>> getHash()
	{
		return hashSocial;
	}
	
}
