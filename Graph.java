import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * Creates the graph using the Vertex class. Adds edges between vertices and prints the graph.
 * @author Aditya Vaze and Ritvik Kar
 * CS 201, Winter 2016
 * date: 08 March 2018
 */

public class Graph implements GraphInt
{
    /** The list of vertices */
    private ArrayList<VertexInt> vertices;

    /** The number of vertices, currently, in the graph */
    private int numVertices;
    
    /**
     * Constructor for the Graph class
     */
    public Graph()
    {
        vertices = new ArrayList();
        numVertices=0;
    }
    
    /**
	 * Adds a vertex to the list of vertices
	 * @param row	The x-position of this vertex in the maze
	 * @param col	The y-position of this vertex in the maze	
	 */
	public ArrayList<VertexInt> getVertices()
    {
        return vertices;
    }

	/**
	 * Inserts a vertex in specified position.
	 * @param row	The x-position of this vertex in the maze	
	 * @param col	The y-position of this vertex in the maze	
	 */
	public void insertVertex(int row, int col)
    {
        
        VertexInt v = new Vertex(row, col);
        vertices.add(v); //adds vertex to the Vertix ArrayList
        numVertices++;
    }
	
	/**
	 * Adds an edge between two vertices
	 * @param v1 	The first vertex
	 * @param v2	The second vertex
	 */
	public void insertEdge(VertexInt v1, VertexInt v2)
    {
        v1.addNeighbor(v2); //inserts V2 as Neighbour of V1
    }
 
	/**
	 * Displays the graph contents by printing out each vertex and its neighbor list.
	 */
	public void printGraph()
    {
        for(int i=0;i<vertices.size();i++)
        {
            System.out.print(vertices.get(i).toString()+": ");
            vertices.get(i).printNeighborList();
            System.out.println();
        }
    }
	
	/**
	 * Conducts a breadth-first search of a graph, starting at the upper left corner of
	 * the maze (0, 0) and continuing to the bottom right corner of the maze. Prunes all 
     * of the visited nodes that are NOT on the shortest path from (0, 0) to the bottom
     * right corner of the maze.
	 * @return	A string listing the vertices visited, in order, on the path from (0, 0) 
     *          to the end of the maze, or an empty string if no path is found.
	 */
	public String breadthFirstTraversal()
    {
        ArrayBlockingQueue<VertexInt> q = new ArrayBlockingQueue(vertices.size());//queue to travese through Maze
        ArrayList<VertexInt> neighbors; //list of neighbours
        String path=""; //final path
        
        VertexInt curr=vertices.get(0); //first vertex
        q.offer(curr); //add first vertex to queue
        curr.setVisited(true); //set it to visited
        VertexInt end=vertices.get(vertices.size()-1); //final vertex to look for
        while(q.peek()!=null && !(q.peek().equals(end))) //while the queue is not empty or the first element is the end
        {
            curr=q.peek(); //first element of queue
            neighbors=curr.getNeighbors(); // gets all of its neighbours
            for(int i=0;i<neighbors.size();i++) //adds the neighbours to queue
            {
                if(!neighbors.get(i).isVisited()) //if they are not visited
                {
                    q.offer(neighbors.get(i));
                    neighbors.get(i).setVisited(true); //set to visited
                    neighbors.get(i).setPredecessor(curr);  //current node is the previous for the next step
                }
            }
            q.poll(); //dequeue the first element
        }
        curr=end; //start from end
        
        while(curr.getPredecessor()!=null) //prune through the prev till we reach the first element
        {
            path=curr.toString()+" "+path; //add to path
            curr=curr.getPredecessor();
        }
        
        if(!end.isVisited()) //if the end is not visited then return empty string, i.e. path not found.
            return "";
        
        for(int i=0;i<vertices.size();i++) //mark the entire maze unvisited to avoid confusion on multiple traversals
        {
            vertices.get(i).setVisited(false);
        }
        return vertices.get(0).toString()+" "+path; //return path
    }

}