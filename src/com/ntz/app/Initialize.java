package com.ntz.app;

import com.ntz.amg.Grid;
import com.ntz.data_structure.AHCGraph;
import com.ntz.data_structure.HierarchyGrids;
import com.ntz.data_structure.SparseMatrix;
import com.ntz.data_structure.SparseVector;
import com.ntz.utils.Diagnostic;

/**
 * This initializer prepare the components of AMG algorithm
 * @author Noam Tzumie
 *
 */
public class Initialize {
	
	private AHCGraph ahcGraph;
	
	public Initialize(AHCGraph ahcGraph) {
		this.ahcGraph = ahcGraph;
	}

	public void perform() {
		
		HierarchyGrids hierarchyGrids = HierarchyGrids.getInstance();
	
		SparseMatrix S = ahcGraph.getMatrix();
		//System.out.println(S);   ///////////////////////////////////
		Grid grid = new Grid(S);
		//grid.print();
		int N = S.size();
	
		grid.v = new SparseVector(N);
		grid.f = new SparseVector(N);
		
		

		System.out.println("Guess vector:");
		for(int i=1; i<=N; i++)
		{
			double  val = (Math.sin(i*32*Math.PI/N) + Math.sin(i*6*Math.PI/N) + Math.sin(i*Math.PI/N)) * 1.0/3;
			grid.v.put(i-1, val);
			System.out.println(val);
		}

		hierarchyGrids.addGrid(grid);
		
		Diagnostic.numOfNodes = N;
		Diagnostic.beforeNorm = grid.getNorm();
	}

}
