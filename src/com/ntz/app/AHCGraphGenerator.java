package com.ntz.app;

import com.ntz.data_structure.AHCGraph;
import com.ntz.utils.Utils;

public class AHCGraphGenerator {

	public static AHCGraph generateLaplaciasize1D(int size) {
		AHCGraph graph = new AHCGraph(size);
		double h= Math.pow(size, 2);
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
		//--------------------------------- i do
			/*for(int i=0;i<size;i++)
			{
				for (int j = 0; j <size; j++) 
				{
					System.out.print(graph.getEdge(i, j)+" ");
				}
				System.out.println("");
			}*/
			
		//---------------------------------
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
