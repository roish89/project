package com.ntz.algorithms;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.ntz.amg.AMG;
import com.ntz.amg.Grid;
import com.ntz.data_structure.HierarchyGrids;
import com.ntz.data_structure.SparseVector;
import com.ntz.utils.Diagnostic;
import com.ntz.utils.Utils;

/**
 * This class performs V-Cycle
 */
public class Cycle {

	private int numOfCycles = 1; //By default

	HierarchyGrids hierarchyGrids;

	Relaxation relaxation = new Relaxation();
	AMG amg = new AMG();

	public Cycle(){
		numOfCycles = Integer.parseInt(Utils.getAppProperties().getProperty("numOfCycles"));
		Diagnostic.numOfCycles = numOfCycles;
		this.hierarchyGrids = HierarchyGrids.getInstance();
	}

	public void perform(int num){
		Diagnostic.startCycleWatch();
		numOfCycles=num;
		for(int i=0;i<numOfCycles;i++) {
			  vcycle();
			if(i<numOfCycles-1){//TODO: Cycle: Re-factor this block
				Grid temp = new Grid(hierarchyGrids.getGrid(0).A);
				temp.v = hierarchyGrids.getGrid(0).v;
				temp.f = new SparseVector(hierarchyGrids.getGrid(0).v.size());
				hierarchyGrids.clear();
				hierarchyGrids.addGrid(temp);
			}
			
		}
		
		Diagnostic.afterNorm = hierarchyGrids.getFinestGrid().v.norm();
		Diagnostic.endCycleWatch();
	}

	public void vcycle(){
		boolean moreWork =true;
		
		for(int i=0;i<5; i++) {//moreWork
			double r=0;	// compute |r|^2
			Grid mGrid = hierarchyGrids.getGrid(i);
			mGrid.residual = mGrid.f.minus(mGrid.A.times(mGrid.v));//compute residual r = f - Av	
			
			for (int j = 0; j < mGrid.residual.size(); j++) {				
				r+=Math.pow(mGrid.residual.get(j), 2) ;
			}
			
			System.out.println("in restriction "+i+":  Residual: "+r);
			relaxation.relax(mGrid, 2); //relaxation
			
			amg.start(mGrid);
			
			
			mGrid.printIn();
			Grid mGrid2 = new Grid(mGrid.A2h);
			//mGrid2.printIn();
			mGrid2.f = mGrid.restrict(mGrid.residual);//restrict;
			mGrid2.v = new SparseVector(mGrid2.f.size());

			moreWork = !mGrid.hasOnlyC();
			hierarchyGrids.addGrid(mGrid2);

		}
		
		for(int i=hierarchyGrids.size()-2; i>=0; i--) {
			Grid mGrid = hierarchyGrids.getGrid(i);
			mGrid.v = mGrid.v.plus(mGrid.interpolate(hierarchyGrids.getGrid(i+1).v));//correction
			relaxation.relax(mGrid, 2); //relaxation
		}
	}
}
