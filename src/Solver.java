import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
	private class SearchNode implements Comparable<SearchNode> {
		
		private Board board;
		private int moves;
		private SearchNode previous;

		public SearchNode(Board board, int moves, SearchNode previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
		}
		
		@Override
		public int compareTo(SearchNode o) {
			return board.hamming() - o.board.hamming();
		}
	}
	
	private MinPQ<SearchNode> queue;

	public Solver(Board initial) {
		queue = new MinPQ<>();
		
		SearchNode initialNode = new SearchNode(initial, 0, null);
		queue.insert(initialNode);
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
