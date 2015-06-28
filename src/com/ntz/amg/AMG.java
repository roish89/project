package com.ntz.amg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ntz.amg.GridNode.NodeType;
import com.ntz.data_structure.SparseMatrix;
import com.ntz.data_structure.SparseVector;
import com.ntz.utils.Diagnostic;
import com.ntz.utils.Utils;
import com.ntz.utils.Diagnostic.LogLevel;

public class AMG {

	public static final double TH = 0.2;

	private void strongInfluenceCalculation(GridNode[] nodes, SparseMatrix A) {

		for(GridNode gn : nodes){

			SparseVector neighbors = A.getRow(gn.id);
			//maximum
			double max = Double.MIN_VALUE;
			Iterator<Integer> itr = neighbors.Iterator();
			while(itr.hasNext()){		
				int i = itr.next();
				if(i != gn.id){
					if(max < Math.abs(neighbors.get(i)))
						max = Math.abs(neighbors.get(i));

				}
			}

			itr = neighbors.Iterator();
			while(itr.hasNext())
			{
				int j = itr.next();			

				if(j != gn.id)
				{
					if(Math.abs(neighbors.get(j)) > TH*max) 
					{
						gn.S.put(j, nodes[j]);	// Xj strong impact on Xi
						nodes[j].St.put(gn.id, nodes[gn.id]);
					} 
					else 
					{
						if(neighbors.get(j) != 0)
							gn.St.put(j, nodes[j]);	//Xi strong impact on Xj	
					}

				}
			}


		}


	}

	/**
	 * Classify grids points to be C or F
	 * @param grid
	 * @return
	 */
	public int classifyGrid(Grid grid)
	{
		GridNode[] nodes = grid.nodes;
		SparseMatrix A = grid.A;
		GridNode gn = null;
		int numOfC = 0;	

		strongInfluenceCalculation(nodes, A);

		while((gn = getMax(nodes)) != null)
		{
			gn.type = NodeType.C;
			gn.order = numOfC++;



			SparseVector neighbors = A.getRow(gn.id);
			Iterator<Integer> itr = neighbors.Iterator();
			while(itr.hasNext()){
				Integer i = itr.next();
				if(i != gn.id && nodes[i].type == NodeType.UNASSIGN){
					nodes[i].type = NodeType.F;
					nodes[i].lamda = Double.MIN_VALUE;
				}
			}

			itr = neighbors.Iterator();

			while(itr.hasNext()){		//for each neighbors of i
				Integer i = itr.next();
				if(i != gn.id){
					//					Iterator<Integer> itr2 = neighbors.Iterator();
					Iterator<Integer> itr2 =  A.getRow(i).Iterator();
					while(itr2.hasNext()){
						Integer n = itr2.next();
						if(i != n && nodes[n].type == NodeType.UNASSIGN)
							nodes[n].lamda++;
					}
				}
			}
		}
		

		classify(A,nodes);
		Diagnostic.log("Before Second Pass Number Of C's: " + numOfC);
		//Second pass
		numOfC = secondPass(nodes, A, numOfC);

		Diagnostic.log("After Second Pass Number Of C's: " + numOfC);
		return numOfC;
	}

	/**
	 * Second pass on the grid to make sure there is no F-F strong connections
	 * @param nodes
	 * @param A
	 * @param numOfC
	 * @return
	 */
	public int secondPass(GridNode[] nodes, SparseMatrix A, int numOfC){
		for(GridNode sgn : nodes){
			if(sgn.type != NodeType.C){
				ArrayList<Integer> Cit = new ArrayList<Integer>();
				SparseVector neighbors = A.getRow(sgn.id);
				Iterator<Integer> itr = neighbors.Iterator();
				while(itr.hasNext()){
					Integer j= itr.next();
					
					if(j != sgn.id && nodes[j].type != NodeType.C && sgn.St.containsKey(j)) {
						Set<Integer> keysInJ = new HashSet<Integer>(nodes[j].Ci.keySet());   
						Set<Integer> keysInI = new HashSet<Integer>(sgn.Ci.keySet());

						boolean isChanged = keysInI.retainAll(keysInJ);
						if(keysInI.size() == 0){ //if the cutting of them = empty group  
							Cit.add(j);
						}	
					}
				}
				if(Cit.size() > 1){ 
					sgn.type = NodeType.C;
					sgn.order = numOfC++;
				}
				else if(Cit.size() == 1){ 
					nodes[Cit.get(0)].type = NodeType.C;
					nodes[Cit.get(0)].order = numOfC++;
				} 
			}
		}
		return numOfC;
	}

	/**
	 * Retrieve next point in grid with maximum lamda
	 * @param nodes
	 * @return
	 */
	public GridNode getMax(GridNode[] nodes){
		double max = Double.MIN_VALUE;
		GridNode res = null;
		for(GridNode gn : nodes){
			if(gn.lamda > max){
				max = gn.lamda;
				res = gn;
			}				
		}
		if(res != null) res.lamda = Double.MIN_VALUE;
		return res;
	}
	/**
	 * Classify for each grid point.
	 * @param Ni
	 * @param nodes
	 */
	public void classify(SparseMatrix A, GridNode[] nodes){
		for(GridNode gn : nodes)
			classify(gn, A.getRow(gn.id), nodes);
	}

	/**
	 * Classify for each grid point.
	 * @param Ni
	 * @param nodes
	 */
	public void classify(SparseVector Ni, GridNode[] nodes){
		for(GridNode gn : nodes)
			classify(gn, Ni, nodes);
	}

	/**
	 * Classify for each point the grid its neighbors for
	 *  
	 * 		Ci - Coarse interpolarity set ( only c points ) 
	 * 		Dis - Strong dependent set ( only f points )
	 * 		Diw - Weak dependent set ( c & f points)
	 * 
	 * @param gn
	 * @param Ni
	 * @param nodes
	 */
	public void classify(GridNode gn, SparseVector Ni, GridNode[] nodes){
		//maximum
		double max = Double.MIN_VALUE;
		Iterator<Integer> itr = Ni.Iterator();
		while(itr.hasNext()){
			int i = itr.next();
			if(i != gn.id)
				if(max < Math.abs(Ni.get(i)))
					max = Math.abs(Ni.get(i)); 
		}
		itr = Ni.Iterator();

		while(itr.hasNext())
		{
			int j = itr.next();

			if(j != gn.id)
			{


				if(Math.abs(Ni.get(j)) >= TH*max) 
				{
					//nodes[gn.id].S.put(j, nodes[j]);
					if(nodes[j].type == NodeType.C){ // Coarse interpolary set
						gn.Ci.put(j, nodes[j]);

					}
					else {// F Points strongly connected
						gn.Dis.put(j, nodes[j]);
					}
				}
				else{ // F/C Points weakly connected 
					gn.Diw.put(j, nodes[j]);
				}
	
			}

			gn.Dependence.put(j,Math.abs(Ni.get(j)));
		}
		
		for (int i = 0; i < nodes.length; i++) {
			Set<Integer> keysCi = new HashSet<Integer>(nodes[i].Ci.keySet());   
			Set<Integer> keysDis = new HashSet<Integer>(nodes[i].Dis.keySet());
			keysDis.retainAll(keysCi);
			
			Iterator<Integer> itr2=keysDis.iterator();
			while(itr2.hasNext()){
				int j = itr2.next();
				nodes[i].Dis.remove(j);
			}
			
		}
		
		
		
		
	}

	public double computeWeight(GridNode gn, SparseMatrix A){
		double sum = 0;
		for(int j : gn.Ci.keySet())
			sum += computeWeight2pt(gn, j, A);

		return sum;
	}

	public double computeWeight2pt(GridNode gn, int j, SparseMatrix A){
		double aii = A.get(gn.id, gn.id);
		double Dw = 0;
		for(int n : gn.Diw.keySet())   //calculation the denominator
		{
			Dw += A.get(gn.id,n);

		}
		double denominator = aii + Dw;
		double Ds = 0;

		for(int m : gn.Dis.keySet()){  //For each F points that strong impact on i	
			double Dsnomirator = A.get(gn.id,m)*A.get(m,j);
			double Dsdenomirator = 0;
			for(int k : gn.Ci.keySet()){
				Dsdenomirator += A.get(m,k);
			}
			if(Dsdenomirator == 0){
				Dsdenomirator = -1;
				System.err.println("Dsdenomirator is zero");
				Diagnostic.log("Dsdenomirator is zero",LogLevel.ERROR);
			}

			Ds += Dsnomirator/Dsdenomirator;
		}

		double numerator = A.get(gn.id,j) + Ds;   //Aij+Ds
		if(denominator==0)
		{
			denominator=1;
			System.err.println("denominator is zero");
		}
		return (numerator / denominator) * -1;
	}

	public SparseMatrix buildInterpolation(GridNode[] nodes, SparseMatrix A, int Nc) {//
		SparseMatrix I = new SparseMatrix(A.dimensions()[0],Nc,true);
		int i=0;
		for(GridNode gn : nodes){		
			if(gn.type == NodeType.C){
				I.put(gn.id,gn.order, 1);
			}
			else
				for(int j : gn.Ci.keySet()){
					
					I.put(gn.id,nodes[j].order ,computeWeight2pt(gn, j, A));
					
				}
			if(gn.type.equals(NodeType.UNASSIGN)){
					i++;
					//I.put(gn.id,gn.order, 1);
				//	gn.type=NodeType.C;
			}
		}
		//System.err.println("Ther are:"+i +" "+"NODE UNASSIGN");
		return I;
	}

	public SparseMatrix buildRestriction(SparseMatrix Inter){ 
		return Inter.transpose(true);
	}

	public SparseMatrix buildA2h(SparseMatrix Rest, SparseMatrix A, SparseMatrix Inter){
		return (Rest.multiply(A)).multiply(Inter);
	}

	/**
	 * Perform 2 grid schema
	 * @param A
	 * @return
	 */
	public void start(Grid g){
		Diagnostic.startClassifyGridWatch();
		int numOfC = classifyGrid(g);
		Diagnostic.endClassifyGridWatch();
		classify(g.A, g.nodes);
		Diagnostic.log("For A: " + g.A.size() + "x" + g.A.size());

		//Utils.hasZeroRows(g.A);


		g.Interpolation = buildInterpolation(g.nodes, g.A, numOfC);

		//System.out.println(g.Interpolation.N+"X"+g.Interpolation.M);
		//System.err.println("Number of zero rows in interpolation operator: " + g.Interpolation.isZeroRows());

		g.Restriction = buildRestriction(g.Interpolation);

		Diagnostic.startRelaxWatch();
		g.A2h = buildA2h(g.Restriction, g.A, g.Interpolation);
		
		
		Diagnostic.endRelaxWatch();

	}
}
