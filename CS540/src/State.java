import java.util.ArrayList;
import java.util.List;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the DFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            closed[i][j] is true if (i,j) is already expanded
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] closed, Maze maze) //edit the gValue
	{
		// FILL THIS METHOD
		// step 1: get the coordinates of this state
		int i = getX();
		int j = getY();

		ArrayList <State> expandState = new ArrayList <State>();


		//left
		if (stateChecker(i, j - 1, maze, closed))
		{
			Square mySquare = new Square(i, j - 1);
			State rightState = new State(mySquare,this, gValue + 1, depth + 1);
			expandState.add(rightState);
		}
		//down
		if (stateChecker(i + 1, j, maze, closed))
		{
			Square mySquare = new Square(i + 1, j);
			State leftState = new State(mySquare,this, gValue + 1, depth + 1);
			expandState.add(leftState);
		}
		//right
		if (stateChecker(i, j + 1, maze, closed))
		{
			Square mySquare = new Square(i, j + 1);
			State downState = new State(mySquare,this, gValue + 1, depth + 1);
			expandState.add(downState);
		}
		//up
		if (stateChecker(i - 1, j, maze, closed))
		{
			Square mySquare = new Square(i - 1, j);
			State upState = new State(mySquare,this, gValue + 1, depth + 1);
			expandState.add(upState);
		}

		return expandState;

		//step 2:
		// TODO check all four neighbors (up, right, down, left)
		// TODO return all unvisited neighbors
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the DFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() 
	{
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
	private boolean stateChecker (int i, int j, Maze maze, boolean[][] closed)
	{
		if (maze.getSquareValue(i, j) != '%')
		{
			return true;
		}
		return false;

	}

}
