package com.ntz.app;
import java.util.Enumeration;
import java.util.Hashtable;

import com.ntz.collaboration_networks.CollaborationMain;
import com.ntz.data_structure.AHCGraph;
import com.ntz.utils.Utils;

public class AHCGraphGenerator {

	public static AHCGraph generateLaplaciasize1D(Hashtable<Integer,Hashtable<Integer,Integer>> hash) {   //Hashtable<Integer,Hashtable<Integer,Integer>> hash
		
		int size=hash.size();
		AHCGraph graph = new AHCGraph(size);
		double h=1;//Math.pow(size, 2);
		
		Hashtable<Integer,Integer> a;
		for (int i = 0; i < size; i++)
		{
			a=hash.get(i);
			graph.addEdge(i,i, a.size());
			Enumeration items = a.keys();
			while(items.hasMoreElements())
				graph.addEdge(i,(int)items.nextElement(), -1);
		}



		return graph;
	}

	public static AHCGraph generateFromImage(String path){
		return Utils.imageToGraph(path);
	}

	public static AHCGraph generateFromFile(String path){
		AHCGraph graph = Utils.getGraphFromSTPFile(path);
		graph.toMmatrix();
		return graph;
	}

	public static AHCGraph generateFromCSV(String path){
		AHCGraph graph = Utils.getGraphFromCSVFile(path);
		graph.toMmatrix();
		return graph;
	}

}

//-----------------------------------------------------------------------------------------------------------------------------------------------

/*	
 * int size=hash.size();
		AHCGraph graph = new AHCGraph(size);
		double h=Math.pow(size, 2);


		Hashtable<Integer,Integer> a;
		for (int i = 0; i < size; i++)
		{
			a=hash.get(i);
			graph.addEdge(i,i, a.size());
			Enumeration items = a.keys();
			while(items.hasMoreElements())
				graph.addEdge(i,(int)items.nextElement(), -1);
		}
 * 
 * 
 */





/*  for matrix (int[][])
	int size=matrix.length;
		AHCGraph graph = new AHCGraph(size);
		double h=1;//Math.pow(size, 2);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[i][j]!=0)
					graph.addEdge(i, j, matrix[i][j]);
			}
		}


 */
/*
graph.addEdge(0, 0, 4*h);
graph.addEdge(0, 1, -1*h);
graph.addEdge(0, size-1, -1*h);	//i do for circle condition

graph.addEdge(size-1, size-1, 4*h);
graph.addEdge(size-1, size-4, -1*h);
graph.addEdge(size-1, 0, -1*h);	//i do for circle condition

for(int i=1;i<size-1; i++)
{
	graph.addEdge(i, i-1, -1*h);
	graph.addEdge(i, i, 4*h);
	graph.addEdge(i, i+1, -1*h);
}


int left,right;
for(int i=0;i<size; i++)
{
	if((i-N)<0)
	{
		left=Math.abs(i-N);
		left=size-left;
		graph.addEdge(i, left , -1*h);

		if((i+N)>size-1)
		{
			right=i+N;
			right= right-size;
			graph.addEdge(i, right , -1*h);
		}
		else
		{
			right=i+N;
			graph.addEdge(i, right , -1*h);
		}
	}
	else if((i+N)>size-1)
	{
		right=i+N;
		right= right-size;
		graph.addEdge(i, right , -1*h);

		if((i-N)<0)
		{
			left=Math.abs(i-N);
			left=size-left;
			graph.addEdge(i, left , -1*h);
		}
		else
		{
			left=i-N;
			graph.addEdge(i, left , -1*h);
		}
	}
	else
	{
		right=i+N;
		graph.addEdge(i, right , -1*h);
		left=i-N;
		graph.addEdge(i, left , -1*h);
	}
}



return graph;
}
 */
/*
graph.addEdge(0, 0, 2*h);
		graph.addEdge(0, 1, -1*h);
		graph.addEdge(0, size-1, -1*h);	//i do for circle condition

		graph.addEdge(size-1, size-1, 2*h);
		graph.addEdge(size-1, size-2, -1*h);
		graph.addEdge(size-1, 0, -1*h);	//i do for circle condition

		for(int i=1;i<size-1; i++)
		{
			graph.addEdge(i, i-1, -1*h);
			graph.addEdge(i, i, 2*h);
			graph.addEdge(i, i+1, -1*h);
		}
 */