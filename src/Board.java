import edu.princeton.cs.algs4.MinPQ;

public class Board {
	
	private final int DIMENSION;
	
	public Board(int[][] blocks) {
		DIMENSION = blocks.length;
	}

	public int dimension() {
		return DIMENSION;
	}

	public int hamming() {
		return 0;
	}

	public int manhattan() {
		return 0;
	}

	public boolean isGoal() {
		return false;
	}

	public Board twin() {
		return null;
	}

	public boolean equals(Object y) {
		return false;
	}

	public Iterable<Board> neighbors() {
		return null;
	}

	public String toString() {
		return null;
	}

}
