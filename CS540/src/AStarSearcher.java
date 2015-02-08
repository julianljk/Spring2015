import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {


	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) 
	{
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		// FILL THIS METHOD

		// CLOSED list is a Boolean array that indicates if a state associated with a given position in the maze has already been expanded. 
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		// OPEN list (aka Frontier list)
		PriorityQueue<StateFValuePair> open = new PriorityQueue<StateFValuePair>();
		//Temporary stateFValuePair variable
		StateFValuePair tempPair = null;
		//Temporary arrayList variable
		ArrayList <State> tempList = null;
		//Iterator variable to go through arrayList of states
		Iterator <State> stateIterator = null;
		//temporary state placeholder for iterator
		State tempState = null;
		//ArrayList to hold all the items to remove
		ArrayList <StateFValuePair> removedPairs = new ArrayList <StateFValuePair>();
		// TODO initialize the root state and add
		// to OPEN list
		State rootState = new State(maze.getPlayerSquare(), null, 0,0);
		double rootHeuristic = getHeuristic(maze.getPlayerSquare());
		StateFValuePair rootPair = new StateFValuePair(rootState, rootHeuristic + rootState.getGValue());
		open.add(rootPair);
		closed [rootState.getX()][rootState.getY()] = true;

		while (!open.isEmpty()) 
		{
			if (open.isEmpty())
			{
				return false;
			}
			tempPair = open.poll();
			closed[tempPair.getState().getX()][tempPair.getState().getY()] = true;
			noOfNodesExpanded++;
			//if n is a goal node then return solution
			if (tempPair.getState().isGoal(maze))
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
				tempList = tempPair.getState().getSuccessors(closed, maze);
				stateIterator = tempList.iterator();

				while (stateIterator.hasNext())
				{
					tempState = stateIterator.next();
					if (closed [tempState.getX()][tempState.getY()] == true)
					{
						Iterator <StateFValuePair> queueIterator = open.iterator();
						while (queueIterator.hasNext())
						{
							StateFValuePair tempQueueItem = queueIterator.next();
							if (tempState.getX() == tempQueueItem.getState().getX() && tempState.getY() == tempQueueItem.getState().getY())
							{
								//check if which one has the better g values
								if (tempState.getGValue() < tempQueueItem.getState().getGValue())
								{
									//add into the remove list
									removedPairs.add(tempQueueItem);
									//add the current state into the queue
									StateFValuePair replacedPair = new StateFValuePair(tempState, tempState.getGValue() + getHeuristic(tempState.getSquare()));
									open.add(replacedPair);
								}
							}
						}
					}
					else
					{
						StateFValuePair newNode = new StateFValuePair(tempState, tempState.getGValue() + getHeuristic(tempState.getSquare()));
						open.add(newNode);
					}
					
				}
//				while (stateIterator.hasNext())
//				{
//					tempState = stateIterator.next();
//					Square tempSquare = tempState.getSquare();
//					StateFValuePair expandedNodes = new StateFValuePair(tempState, getHeuristic(tempSquare) + tempState.getGValue());
//
//					Iterator <StateFValuePair> queueIterator = open.iterator();
//					while (queueIterator.hasNext())
//					{
//						StateFValuePair itrTemp = queueIterator.next();
//						//do state checking to see if the g values of any of the states are better
//						if (expandedNodes.getState().getX() == itrTemp.getState().getX() && expandedNodes.getState().getY() == itrTemp.getState().getY())
//						{
//							if (expandedNodes.compareTo(itrTemp) == -1)
//							{
//								open.remove(itrTemp);
//								open.add(expandedNodes);
//							}
//						}
//					}
//					//add them into the frontier
//					open.add(expandedNodes);
//				}
			}

			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded,
			// TODO update the maze if a solution found

			// use open.poll() to extract the minimum stateFValuePair.
			// use open.add(...) to add stateFValue pairs
		}

		// TODO return false if no solution
		return false;
	}
	private double getHeuristic(Square curr)
	{	

		double heuristic = Math.abs(maze.getGoalSquare().X - curr.X) + Math.abs(maze.getGoalSquare().Y - curr.Y); 
		return heuristic;
	}

}
