package com.ntz.collaboration_networks;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

public class BuildMatrix {
	int[][] matrix;
	Writer writer;

	public BuildMatrix(int x)
	{
		matrix= new int[x][x];
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\roi\\Desktop\\project\\aaa\\Matrix.txt"), "utf-8"));

		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}
	}

	public void convertHashToMatrix(Hashtable<Integer,LinkedList<NodeNeighbors>> hashX)
	{
		int keyOfEquation,numOfNeighbors,numOfEquation;
		Enumeration equations = hashX.keys();
		
		while(equations.hasMoreElements())
		{
			
			keyOfEquation=hashX.get(equations.nextElement()).get(1).id;
			numOfNeighbors=hashX.get(keyOfEquation).size()-2;
			numOfEquation=hashX.get(keyOfEquation).get(0).id;
			//System.out.println("keyOfEquations:"+keyOfEquation+" numOfEquations:"+numOfEquation+" numOfNeighbors:"+numOfNeighbors);
			
			
			

			matrix[numOfEquation][numOfEquation]=numOfNeighbors;
			
			for (int i=2; i < numOfNeighbors+2; i++)
			{
				matrix[numOfEquation][hashX.get(hashX.get(keyOfEquation).get(i).id).get(0).id]=1;
			}
			
			numOfEquation++;	  
			
			
			
		}
		

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
	public int[][] getMatrix()
	{
		return matrix;
	}
	
	
}
