
public class PercolationUF implements IPercolate{

	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		myOpenCount = 0;
		finder.initialize(size * size + 2);
		myFinder = finder;
		VTOP = size*size;
		VBOTTOM = size * size+1;
	}
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (isOpen(row, col)) return;
		myGrid[row][col] = true;
		if (row == 0) {
			myFinder.union(toInt(row, col), VTOP);
		}
		if (row == myGrid.length-1) {
			myFinder.union(toInt(row, col), VBOTTOM);
		}
		if (inBounds(row-1, col) && myGrid[row-1][col] == true) {
			myFinder.union(toInt(row, col), toInt(row-1, col));
		}
		if (inBounds(row+1, col) && myGrid[row+1][col] == true) {
			myFinder.union(toInt(row, col), toInt(row+1, col));
		}
		if (inBounds(row, col+1) && myGrid[row][col+1] == true) {
			myFinder.union(toInt(row, col), toInt(row, col+1));
		}
		if (inBounds(row, col-1) && myGrid[row][col-1] == true) {
			myFinder.union(toInt(row, col), toInt(row, col-1));
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(toInt(row, col), VTOP);
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	/**
	 * Determine if (row,col) is valid for given grid
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
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
