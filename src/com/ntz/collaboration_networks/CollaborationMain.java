package com.ntz.collaboration_networks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;

import com.ntz.app.Application;
import com.ntz.build_matrix.BuildMatrix;
import com.ntz.dfs.DirectedConnectivityDfs;

public class CollaborationMain
{

	public static void main(String[] args) 
	{
		ParserToNewFile newFile = new ParserToNewFile();
		Neighbors neig = new Neighbors();
		BuildMatrix matrix=new BuildMatrix();
			String line;
			

			try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
			{

				line=br.readLine();

				while (line != null) 
				{
					if((!line.equals("null")) && !(line.contains("#")) )
					{
						newFile.parserToAddID(line);
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
			newFile.printHashID();
			
			
			try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
			{

				line=br.readLine();

				while (line != null) 
				{
					if((!line.equals("null")) && !(line.contains("#")) )
					{
						newFile.parserToNewFile(line);
					}

					line=br.readLine();
					if(line==null)
						break;

				}

				br.close();

			}

			catch(Exception e)
			{
				System.out.println("error main bbb");
			}
			
			
			
	//-----------------------------------------------------------------------------------------
			
			
			
			try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\roi\\Desktop\\project\\aaa\\newFile.txt"))) 
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

			try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\roi\\Desktop\\project\\aaa\\newFile.txt"))) 
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
			DirectedConnectivityDfs.main(neig.getHash());
			//Application.main(neig.getHash());
			//matrix.getHash(neig.getHash());
			//matrix.symmetricalTest();
			

		}
	}
	
	


