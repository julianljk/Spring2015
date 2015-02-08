import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Depth-First Search (DFS)
 * 
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {
	
	/**
	 * INSTANCE VARIABLES AVAILABLE
	 * 	protected Maze maze; //this is 
		protected int cost = 0;
		protected int noOfNodesExpanded = 0;
		protected int maxDepthSearched = 0;
		protected int maxSizeOfFrontier = 0;
	 */
	
	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public DepthFirstSearcher(Maze maze) //maze is coming from the main, which got it from IO
	{
		super(maze); //this.maze = maze. 
	}

	/**
	 * Main depth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD

		// CLOSED list (Explored list) is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been expanded.
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()]; //Explored list
		// Stack implementing the Frontier list
		LinkedList<State> stack = new LinkedList<State>(); // frontier list
		//Push the starting node onto the stack
		State startState = new State (maze.getPlayerSquare(), null, 0, 0);
		stack.push(startState);
		//Temp State place holder
		State tempState = null;
		//Temp arrayList placeHolder
		ArrayList <State> tempList = null;
		//Temp Iterator
		Iterator <State> myIterator = null;
		//Temp Iterator state
		State tempItrState = null;
		while (!stack.isEmpty()) 
		{
			//remove from frontier
			tempState = stack.pop();
			
			//mark as expanded in the closed list
			closed[tempState.getX()][tempState.getY()] = true;
			noOfNodesExpanded++;
			if (tempState.isGoal(maze))
			{
				while(tempState.getParent() != null)
				{
					tempState = tempState.getParent();
					maze.setOneSquare(tempState.getSquare(), '.');
					cost++;
				}
				maze.setOneSquare(tempState.getSquare(), 'H');
				return true;
			}
			else 
			{
				tempList = tempState.getSuccessors(closed, maze);
				myIterator = tempList.iterator();
				
				while (myIterator.hasNext())
				{
					tempItrState = myIterator.next();
					if (closed [tempItrState.getX()][tempItrState.getY()] == false) //checks if the node has already been expanded.
					{
						stack.push(tempItrState);
					}
				}
			}
		}
		return false;
	}
}
