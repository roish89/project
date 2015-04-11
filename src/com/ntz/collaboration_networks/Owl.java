package com.ntz.collaboration_networks;
import com.ntz.app.Application;

import java.io.BufferedReader;
import java.io.FileReader;

public class Owl {

	public static void main(String[] args) {
		Neighbors neig = new Neighbors();
		String[] parts;
		String line;
		BuildMatrix matrix;

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			line=br.readLine();

			while (line != null) 
			{
				if((!line.equals("null")) && !(line.contains("#")) )
				{
					neig.parserToHash(line);
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
	
		matrix= new BuildMatrix(neig.counter);
		//System.out.println(neig.counter);
		//---------------------------------------------------------------

		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
		{

			line=br.readLine();

			while (line != null) 
			{
				if((!line.equals("null")) && !(line.contains("#")) )
				{
					neig.parserToVal(line);
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
		
		neig.WriteNeihbors();
		matrix.convertHashToMatrix(neig.hashX);
	//	matrix.writeMatrixToFile();
	//	Application.main(matrix.getMatrix());
		
		//matrix.printListNeihbors(hashX);
	}







}


