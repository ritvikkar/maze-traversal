import java.util.*;

/**
 * An interface for a graph vertex. 
 * Assumes the adjacency list model of storing edges.
 * @author Amy Csizmar Dalal and Aditya Vaze and Ritvik Kar
 * CS 201, Winter 2016
 * date: 08 March 2018
 */
public class Vertex implements VertexInt{
    
    private boolean visited; //visited marker
    private VertexInt pre; //previous vertex
    private ArrayList<VertexInt> neighbors; //list of neighbours. Adjacency List
    private int row; //row number
    private int col; //column number
    
    public Vertex() //default constructor
    {
        this.visited=false; 
        this.pre=null;
        this.neighbors=new ArrayList();
        this.row=0;
        this.col=0;
    }
    
    public Vertex(int row, int col) //parametrized constructor
    {
        this.visited=false;
        this.pre=null;
        this.neighbors=new ArrayList();
        this.row=row;
        this.col=col;
    }
	/**
	 * Returns whether this vertex has been visited on the latest graph traversal.
	 */
	public boolean isVisited()
    {
        return visited;
    }

	/**
	 * Sets whether this vertex has been visited (true) or resets it (false).
	 */
	public void setVisited(boolean visited)
    {
        this.visited=visited;
    }

    /**
     * Returns the predecessor (the vertex visited prior to this one) to this vertex.
     */
    public VertexInt getPredecessor()
    {
        return pre;
    }

    /**
     * Sets the predecessor for this vertex.
     */
    public void setPredecessor(VertexInt p)
    {
        this.pre=p;
    }

	/**
	 * Returns the x-coordinate of this vertex in the maze.
	 */
	public int getRow()
    {
        return row;
    }

	/**
	 * Returns the y-coordinate of this vertex in the maze.
	 */
	public int getCol()
    {
        return col;
    }

	/**
	 * Returns a list of the neighbors of this node.
	 */
	public ArrayList<VertexInt> getNeighbors()
    {
        return neighbors;
    }
	
	/**
	 * Adds a new neighbor to the list of neighbors for this vertex
	 * @param v	The new neighbor (vertex)
	 */
	public void addNeighbor(VertexInt v)
    {
        neighbors.add(v);
    }
	
	/**
	 * Compares two vertices to see if they are equal. Two vertices are equal if their
	 * row and column values are identical.
	 * @param v		The vertex to which to compare this vertex.
	 * @return 		true if the 2 vertices are equal, false otherwise
	 */
	public boolean equals(VertexInt v)
    {
        if(this.row==v.getRow()&&this.col==v.getCol())
            return true;
        else
            return false;
    }
	
	/** 
	 * Returns true if the vertex v is a neighbor of this vertex, and false otherwise.
	 */
	public boolean isNeighbor(VertexInt v)
    {
        for(int i=0;i<neighbors.size();i++) //checks if neighbours by comparing vertex to adjacency list
        {
            if(v.equals(neighbors.get(i)))
                return true;
        }
            return false;
    }
	
	/**
	 * Returns the vertex as a (row, col) string.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
    {
        return "("+row+", "+col+")";
    }
	
	/**
	 * Prints out a list of the neighbors of this node
	 */
	public void printNeighborList()
    {
        for(int i=0;i<neighbors.size();i++)
        {
            System.out.print(neighbors.get(i).toString()+" ");
        }
    }
	
}
