import java.util.ArrayList;
import java.util.Collections;
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
	
	private MinPQ<SearchNode> queueInitial;
	private MinPQ<SearchNode> queueAlternative;

	private List<Board> solution;
	
	private int moves = -1;
	
	private boolean solvable;
	
	public Solver(Board initial) {
		queueInitial = new MinPQ<>();
		queueAlternative = new MinPQ<>();
		
		SearchNode initialNode = new SearchNode(initial, null);
		queueInitial.insert(initialNode);
		
		SearchNode alternativeNode = new SearchNode(initial.twin(), null);
		queueAlternative.insert(alternativeNode);
		
		SearchNode mpNodeInitial = queueInitial.delMin();
		SearchNode mpNodeAlternative = queueAlternative.delMin();
		
		boolean processInitial = true;
		boolean goalReached = false;
		
		if (mpNodeInitial.board.isGoal()) {
			goalReached = true;
			processInitial = false;
		} else if (mpNodeAlternative.board.isGoal()) {
			goalReached = true;
			processInitial = true;
		}
		
		while (!goalReached) {

			if (processInitial) {
				mpNodeInitial = performAStarSearchIteration(mpNodeInitial, queueInitial);
				goalReached = mpNodeInitial.board.isGoal();
			} else {
				mpNodeAlternative = performAStarSearchIteration(mpNodeAlternative, queueAlternative);
				goalReached = mpNodeAlternative.board.isGoal();
			}

			processInitial = !processInitial;
		}
		
		solvable = !processInitial;
		
		solution = solvable ? getSolution(solvable ? mpNodeInitial : mpNodeAlternative) : null;
		
		moves = solvable ? solution.size() - 1 : -1;
	}
	
	private List<Board> getSolution(SearchNode searchNode) {
		List<Board> result = new ArrayList<>();
		while (searchNode != null) {
			result.add(searchNode.board);
			searchNode = searchNode.previous;
		}
		
		Collections.reverse(result);
		return result;
	}

	private SearchNode performAStarSearchIteration(SearchNode currentNode, MinPQ<SearchNode> queue) {
		for (Board board : currentNode.board.neighbors()) {
			if (currentNode.previous == null || !currentNode.previous.board.equals(board)) {
				queue.insert(new SearchNode(board, currentNode));
			}
		}
		
		return queue.delMin();
	}
	
	public boolean isSolvable() {
		return solvable;
	}

	public int moves() {
		return moves;
	}

	public Iterable<Board> solution() {
		return solution;
	}
}
