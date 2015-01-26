package com.ntz.owl;

import java.io.BufferedReader;
import java.io.FileReader;

public class Owl {

	public static void main(String[] args) {
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			String line=br.readLine();


			while (line != null) 
			{
				line=br.readLine();
				if(line==null)
					break;


				if(!line.equals("null"))
				{
					System.out.println(line);
				}


			}




		}

		catch(Exception e)
		{
			System.out.println("error");
		}


	}

}
