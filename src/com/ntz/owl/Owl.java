package com.ntz.owl;

import java.io.BufferedReader;
import java.io.FileReader;

public class Owl {

	public static void main(String[] args) {
		System.out.println(args[0]);
		Neighbors neig = new Neighbors();
		String[] parts;
		String line;

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			line=br.readLine();
			

			while (line != null) 
			{
				if((!line.equals("null")) && !(line.contains("#")) )
				{
					neig.addId(line);
					System.out.println(line);
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


	}

	

}


