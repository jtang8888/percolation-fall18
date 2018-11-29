import java.util.*;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
	}
	
	@Override
	protected void dfs (int row, int col) {
		myGrid[row][col] = FULL;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(toInt(row, col));
		while (!q.isEmpty()) {
			int current = q.remove();
			int r = toCoord(current)[0];
			int c = toCoord(current)[1];
			myGrid[r][c] = FULL;
			if (inBounds(r-1, c) && isOpen(r-1,c) && !isFull(r-1,c)) {
				myGrid[r-1][c] = FULL;
				q.add(toInt(r-1,c));
			}
			if (inBounds(r+1, c) && isOpen(r+1,c) && !isFull(r+1,c)) {
				myGrid[r+1][c] = FULL;
				q.add(toInt(r+1,c));
			}
			if (inBounds(r, c+1) && isOpen(r,c+1) && !isFull(r,c+1)) {
				myGrid[r][c+1] = FULL;
				q.add(toInt(r,c+1));
			}
			if (inBounds(r, c-1) && isOpen(r,c-1) && !isFull(r,c-1)) {
				myGrid[r][c-1] = FULL;
				q.add(toInt(r,c-1));
			}
		}
	}
	/**
	 * Returns int associated with given row and column
	 * @param row
	 * @param col
	 * @return integer of form row * size + col
	 */
	protected int toInt(int row, int col) {
		return row * myGrid.length + col; 
	}
	/**
	 * Returns an array of coordinates associated with r
	 * @param r integer to be turned back into coordinates
	 * @return new array, where entry 0 corresponds to row, and entry 1 corresponds to column
	 */
	protected int[] toCoord(int r) {
		return new int[] {r / myGrid.length, r % myGrid.length}; 
	}

}
