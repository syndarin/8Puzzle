import java.util.List;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	
	private class SearchNode implements Comparable<SearchNode> {
		
		private Board board;
		private int moves;
		private SearchNode previous;

		public SearchNode(Board board, SearchNode previous) {
			this.board = board;
			this.moves = previous != null ? previous.moves + 1 : 1;
			this.previous = previous;
		}
		
		private int getPriority() {
			return board.manhattan() + moves;
		}
		
		@Override
		public int compareTo(SearchNode o) {
			return getPriority() - o.getPriority();
		}
	}
	
	private MinPQ<SearchNode> queue;

	private List<Board> solution;
	
	public Solver(Board initial) {
		queue = new MinPQ<>();
		
		SearchNode initialNode = new SearchNode(initial, null);
		queue.insert(initialNode);
		
		SearchNode mpNode = queue.delMin();
		
		while (!mpNode.board.isGoal()) {
			
			for (Board board : mpNode.board.neighbors()) {
				if (mpNode.previous == null || !mpNode.previous.board.equals(board)) {
					queue.insert(new SearchNode(board, mpNode));
				}
			} 
			
			mpNode = queue.delMin();
		}
		
		do {
			
			System.out.println(mpNode.board.toString());
			
			mpNode = mpNode.previous;
			
		} while (mpNode != null);
	}

	public boolean isSolvable() {
		return false;
	}

	public int moves() {
		return 0;
	}

	public Iterable<Board> solution() {
		return solution;
	}
}
