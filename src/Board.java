import java.util.Arrays;

public class Board {
	
	private final int DIMENSION;
	private final int BLANK_FIELD_INDEX;
	
	private int[] blocks;
	
	private int emptyBlockIndex;
	
	public Board(int[][] blocks) {
		DIMENSION = blocks.length;
		
		this.blocks = new int[DIMENSION * DIMENSION];

		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				
				int index = i * DIMENSION + j; 
				
				this.blocks[index] = blocks[i][j];
				
				if (this.blocks[index] == 0) {
					emptyBlockIndex = index;
				}
			}
		}
		
		BLANK_FIELD_INDEX = this.blocks.length - 1;
	}

	public int dimension() {
		return DIMENSION;
	}

	public int hamming() {
		int result = 0;
		
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] != 0 && blocks[i] != i + 1) {
				result++;
			}
		}
		
		return result;
	}

	public int manhattan() {
		int result = 0;
		
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] != 0 && blocks[i] != i + 1) {
				result += getManhattanDistance(i, blocks[i] - 1);
			}
		}
		
		return result;
	}
	
	private int getManhattanDistance(int fromPosition, int toPosition) {
		int absLength = Math.abs(fromPosition - toPosition);
		return absLength / DIMENSION + absLength % DIMENSION;
	}

	public boolean isGoal() {
		boolean result = true;
		
		if (emptyBlockIndex != BLANK_FIELD_INDEX) {
			
			result = false;
			
		} else {
			
			for (int i = 0, max = blocks.length - 1; i < max; i++) {
				if (blocks[i] != i + 1) {
					result = false;
					break;
				}
			}
		}
		
		return result;
	}

	public Board twin() {
		return null;
	}

	public boolean equals(Object y) {
		if (this == y) {
			return true;
		} else if (y == null || !(y instanceof Board)) {
			return false;
		} else {
			Board that = (Board) y;
			return Arrays.equals(this.blocks, that.blocks);
		}
	}

	public Iterable<Board> neighbors() {
		return null;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (int i = 0; i < blocks.length; i++) {
			builder.append(blocks[i] == 0 ? "    " : String.format("%4d", blocks[i]));
			
			if (i % DIMENSION == DIMENSION - 1) {
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		int[][] blocks = new int[][]{
			new int[]{8, 1, 3},
			new int[]{4, 0, 2},
			new int[]{7, 6, 5}
		};
		
		int[][] otherBlocks = new int[][]{
			new int[]{8, 1, 3},
			new int[]{4, 0, 2},
			new int[]{7, 6, 5}
		};
		
		Board board = new Board(blocks);
		Board otherBoard = new Board(otherBlocks);
		
		assert(board.equals(board));
		assert(board.equals(otherBoard));
		assert(!board.equals(null));
		assert(!board.equals(new Object()));
		
		assert(board.dimension() == 3);
		
		assert(board.hamming() == 5);
		
		assert(board.manhattan() == 10);
		
		System.out.println("Tests passed");
		System.out.println(board.toString());
	}
}
