/**
 * Main Application.
 * 
 * You do not need to change this class.
 */
public class HW1 {

	/**
	 * @param args
	 *            [0]: file name, [1]: astar (a-star), dfs (depth first search)
	 */
	public static void main(String[] args) {
		Maze maze = IO.readInputFile(args[0]); //returns an instance of maze

		Searcher searcher;

		if (args[1].equals("astar"))
			searcher = new AStarSearcher(maze); //pass in an instance of maze
		else
			searcher = new DepthFirstSearcher(maze);

		if (searcher.search()) {
			IO.printOutput(searcher.getModifiedMaze(), searcher.getCost(),
					searcher.getNoOfNodesExpanded());
		} else {
			System.out.println("No Solution");
		}
	}
}
