
public class PercolationDFSFast extends PercolationDFS{

	public PercolationDFSFast(int n) {
		super(n);
	}
	@Override
	protected void updateOnOpen(int row, int col) {
		if (row == 0) {
			dfs(row, col);
			return;
		}
		try {
			if (isFull(row-1, col)) {
				dfs(row, col);
				return;
			}
		} catch (IndexOutOfBoundsException ex) {}
		try {
			if (isFull(row+1, col)) {
				dfs(row, col);
				return;
			}
		} catch (IndexOutOfBoundsException ex) {}
		try {
			if (isFull(row, col-1)) {
				dfs(row, col);
				return;
			}
		} catch (IndexOutOfBoundsException ex) {}
		try {
			if (isFull(row, col+1)) {
				dfs(row, col);
				return;
			}
		} catch (IndexOutOfBoundsException ex) {}
	}

}
