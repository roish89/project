package com.ntz.social_networks;

import com.ntz.app.Application;
import com.ntz.build_matrix.*;
import com.ntz.dfs.DirectedConnectivityDfs;

import java.io.BufferedReader;
import java.io.FileReader;

public class SocialMain  {

	public static void main(String[] args) {
		Neighbors neig = new Neighbors();
		String[] parts;
		String line;
		BuildMatrix matrix=new BuildMatrix();
		
	
		//BuildMatrix matrix;

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			line=br.readLine();

			while (line != null) 
			{
				if((!line.equals("null")) && !(line.contains("#")) )
				{
					//System.out.println(line);
					neig.parserToAddID(line);
				}

				line=br.readLine();
				if(line==null)
					break;

			}

			br.close();

		}

		catch(Exception e)
		{
			System.out.println("error main");
		}
	
		//neig.printHash();
		//System.out.println(neig.counter);
		//---------------------------------------------------------------

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			line=br.readLine();

			while (line != null) 
			{
				if((!line.equals("null")) && !(line.contains("#")) )
				{
					neig.parserToAddFriend(line);
				}

				line=br.readLine();
				if(line==null)
					break;

			}

			br.close();

		}

		catch(Exception e)
		{
			System.out.println("error");
		}

		//neig.printFriendsOfX();
		//DirectedConnectivityDfs.main(neig.getHash());
		
		Application.main(neig.getHash());
		//matrix.getHash(neig.getHash());
		

	}
	}





