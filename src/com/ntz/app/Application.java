package com.ntz.app;

import java.util.concurrent.TimeUnit;

import com.ntz.algorithms.Clustering;
import com.ntz.algorithms.Cycle;
import com.ntz.data_structure.AHCGraph;
import com.ntz.utils.Diagnostic;

public class Application {

	public static void main(String[] args){
		Diagnostic.startAppWatch();
		//Convert general graph to AHCGraph

		long startTime = System.nanoTime();    


		String fileName = "resources/MI7";

		int N=3;
		int numOfVCycle=10;




		//Convert general graph to AHCGraph
		//		AHCGraph ahcGraph = AHCGraphGenerator.generateFromCSV(fileName + ".csv");//.generateFromFile("resources/r-graph.stp");//generateFromImage("resources/img.txt");
		AHCGraph ahcGraph = AHCGraphGenerator.generateLaplaciasize1D(N);
		//		System.out.println(ahcGraph.getMatrix());
		//--------------------------------------------------------
	
		
		//Initialize graph data for AMG
		Initialize initializer = new Initialize(ahcGraph);
		initializer.perform();

		//Perform AMG
		Cycle vCycle = new Cycle();
		vCycle.perform(numOfVCycle);//the parameter is the number of VCycle

		//Perform clustering
		//Clustering clustering = new Clustering();
		//clustering.perform();

		//************************************************//
		//**		For Clustering visualization use:   **//
		//**		http://app.raw.densitydesign.org/   **// 
		//************************************************//

		Diagnostic.endAppWatch();
		Diagnostic.print();
		long estimatedTime = System.nanoTime() - startTime;
		double seconds = (double)estimatedTime / 1000000000.0;

		System.out.println("time of V-Cycle "+numOfVCycle+": "+seconds);
	}

}




//--------------------------------------------------------
/*
 * int size=N*N;
 * for(int i=0;i<size;i++)
		{
			for (int j = 0; j <size; j++) 
			{
				System.out.print(ahcGraph.getEdge(i, j)+" ");
			}
			System.out.println("");
		}*/
//--------------------------------------------------------