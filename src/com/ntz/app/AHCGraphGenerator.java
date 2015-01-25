package com.ntz.app;

import com.ntz.data_structure.AHCGraph;
import com.ntz.utils.Utils;

public class AHCGraphGenerator {

	public static AHCGraph generateLaplaciasize1D(int N) {
		int size=N*N;
		AHCGraph graph = new AHCGraph(size);
		double h=1;//Math.pow(size, 2);

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