package com.ntz.dfs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
 
public class DirectedConnectivityDfs
{
    private Stack<Integer> stack;
    public static void main(Hashtable<Integer,Hashtable<Integer,Integer>> hash)
    {
    	System.out.println(hash.size());
        int number_of_nodes, source;
        Scanner scanner = null;
 	try
        {
	   
            number_of_nodes = hash.size();
 
	    int adjacency_matrix[][] = new int[number_of_nodes+1][number_of_nodes+1];
	    
	    Hashtable<Integer,Integer> a;
		for (int i = 0; i <number_of_nodes ; i++)
		{
			a=hash.get(i);
			adjacency_matrix[i][i]=a.size();
			Enumeration items = a.keys();
			while(items.hasMoreElements()){
				adjacency_matrix[i+1][(int)items.nextElement()+1]=1;
				
			}
				
		}
	/*	
		for (int i = 1; i <=number_of_nodes; i++)
		{
		       for (int j = 1; j <= number_of_nodes; j++)
		       {
	                 System.out.print(adjacency_matrix[i][j] +" ");
		       }
		       System.out.println(" ");
		}
 */
            DirectedConnectivityDfs directedConnectivity= new DirectedConnectivityDfs();
            directedConnectivity.dfs(adjacency_matrix, 1);	
 
        }catch(InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input format");
        }	
       
    }	
 
    public DirectedConnectivityDfs() 
    {
        stack = new Stack<Integer>();
    }
 
    public void dfs(int adjacency_matrix[][], int source)
    {
        int number_of_nodes = adjacency_matrix[source].length - 1;
        int visited[] = new int[number_of_nodes + 1];		
        int element = source;		
        int i = source;	
        visited[source] = 1;		
        stack.push(source);
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = element;	
	    while (i <= number_of_nodes)
	    {
     	        if (adjacency_matrix[element][i] == 1 && visited[i] == 0)
	        {
                    stack.push(i);
                    visited[i] = 1;
                    element = i;
                    i = 1;
	            continue;
                }
                i++;
	    }
            stack.pop();	
        }
        boolean connected = false;
 
        for (int vertex = 1; vertex <= number_of_nodes; vertex++)
        {
            if (visited[vertex] == 1) 
            {
                connected = true;
            }
            else
            {
                connected = false;
                break;
            }
        }
 
        if (connected)
        {
            System.out.println("The graph is connected");
        }else
        {
            System.out.println("The graph is disconnected");
        }
    }
 
    
}
