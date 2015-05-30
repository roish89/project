package com.ntz.enron_email_network;

import java.io.BufferedReader;
import java.io.FileReader;
import com.ntz.build_matrix.*;
import com.ntz.app.*;



public class EnronEmailMain {

	public static void main(String[] args) {
		
		
		Neighbors neig = new Neighbors();
		BuildMatrix matrix = new BuildMatrix();
	
			String line;
			

			try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\roi\\Desktop\\project\\Email-Enron.txt"))) 
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

			try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\roi\\Desktop\\project\\Email-Enron.txt"))) 
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
			Application.main(neig.getHash());
			//matrix.getHash(neig.getHash());
			//matrix.symmetricalTest();
			

		}
}
