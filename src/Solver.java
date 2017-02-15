import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
	private class SearchNode implements Comparable<SearchNode> {
		
		private Board board;
		private int moves;
		private SearchNode previous;

		@Override
		public int compareTo(SearchNode o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	private MinPQ<SearchNode> queue;

	public Solver(Board initial) {
		queue = new MinPQ<>();
	}

	public boolean isSolvable() {
		return false;
	}

	public int moves() {
		return 0;
	}

	public Iterable<Board> solution() {
		return null;
	}

}
