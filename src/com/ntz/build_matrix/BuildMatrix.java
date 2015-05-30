package com.ntz.build_matrix;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;

import com.ntz.social_networks.*;

public class BuildMatrix {
	
	Hashtable<Integer,Hashtable<Integer,Integer>> hashSocial;
	int matrix[][];
	Hashtable<Integer,Integer> internalHash;
	Writer writer;
	
	public BuildMatrix()
	{
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\roi\\Desktop\\project\\Email-Enron_Matrix.txt"), "utf-8"));

		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}
	}
	
	public void getHash(Hashtable<Integer,Hashtable<Integer,Integer>> hash)
	{
		hashSocial=hash;
		matrix=new int [hashSocial.size()][hashSocial.size()];
		
		for (int i = 0; i < matrix.length; i++) 
		{
			internalHash=hashSocial.get(i);
			matrix[i][i]=internalHash.size();
			
			Enumeration items = internalHash.keys();
			while(items.hasMoreElements())
			{
				matrix[i][(int)items.nextElement()]=1;
			}
		}
		
		writeMatrixToFile();
	}
	
	public void symmetricalTest()
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix.length; j++) 
			{
				if(matrix[i][j]==1)
					if(matrix[j][i]!=1)
						System.out.println(i+" " +j);
				
		
			}
			
		}
		System.out.println("symmetrical");
	}
	
	public void writeMatrixToFile()
	{
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++) 
			{
				writerTofile(Integer.toString(matrix[i][j])+" ");
				//System.out.print(matrix[i][j]+" ");
			}
			writerTofile("\n");
			//System.out.println("");
		}
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
