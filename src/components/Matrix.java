package components;

import java.util.ArrayList;

import static util.Util.power;

/**
 * General n by m matrix
 * @author Connor Lehmacher and Benjamin Buck
 */
public class Matrix {
	/** The main ArrayList contains the rows */
	private ArrayList<ArrayList<Double>> data = new ArrayList<>();
	
	/** Creates a Matrix with some amount of rows and columns */
	public Matrix(int rows, int columns) {
		for(int i = 0 ; i < rows ; i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();
			for(int j = 0 ; j < columns ; j++) {
				tmp.add(0.0);
			}
			data.add(tmp);
		}
	}
	
	
	/** one by one matrix --May be broken-- */
	public Matrix() {
		data.add(new ArrayList<Double>(1));
	}
	
	/** Sets a value in a matrix */
	public void set(int row, int column, double value) {
		if(row >= 0 && column >= 0 && row < rows() && column < columns()) {
			ArrayList<Double> newRow = data.get(row);
			newRow.set(column, value);
			data.set(row, newRow);
		}
		else{
			System.err.println(row + " " + column + " are not in the correct bounds");
		}
		
	}
	
	/** Gets a value from a matrix */
	public double get(int row, int column) {
		if(row >= 0 && column >= 0 && row < rows() && column < columns()) {
			return data.get(row).get(column);
		}
		else{
			System.err.println(row + " " + column + " are not in the correct bounds");
			return(0.0);
		}
	}
	
	public int rows() {
		return data.size();
	}
	
	public int columns() {
		return data.get(0).size();
	}
	
	/** Computes the combination of two matrices next to each other merged */
	public Matrix combineWith(Matrix m) {
		if(rows() == m.rows()) {
			Matrix answer = new Matrix(rows(), columns() + m.columns());
			int r = rows();
			int c = columns();
			int mc = m.columns();
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < (c + mc) ; j++) {
					if(j < c) {
						answer.set(i, j, get(i, j));
					}
					else{
						answer.set(i, j, m.get(i, j - c));
					}
				}
			}
			return answer;
		}
		else{
			System.err.println("row numbers not equal");
			return null;
		}
	}
	
	/** Computes a small matrix based on the entire matrix */
	public Matrix subMatrix(int rowStart, int columnStart, int rowEnd, int columnEnd) {
		if(0 <= rowStart && rowStart <= rowEnd && 0 <= columnStart && columnStart <= columnEnd 
				&& 0 <= rowEnd && rowEnd < rows() && 0 <= columnEnd && columnEnd < columns()) {
			Matrix answer = new Matrix(rowEnd - rowStart + 1, columnEnd - columnStart + 1);
			for(int i = rowStart ; i < rowEnd + 1 ; i++) {
				for(int j = columnStart ; j < columnEnd + 1 ; j++) {
					final int currentNewRow = i - rowStart;
					final int currentNewColumn = j - columnStart;
					answer.set(currentNewRow, currentNewColumn, get(i, j));
				}
			}
			return answer;
		}
		else{
			System.err.println("Bad range");
			return null;
		}
	}
	
	/** Computes the sum of to matrices */
	public Matrix plus(Matrix m) {
		if(rows() == m.rows() && columns() == m.columns()) {
			Matrix answer = new Matrix(rows(), columns());
			int r = rows();
			int c = columns();
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < c ; j++){
					answer.set(i, j, get(i, j) + m.get(i, j));
				}
			}
			return answer;
		}
		else{
			System.err.println("Invalid dimensions");
			return null;
		}
	}
	
	// Implement plus, minus, scalar times, inverse, determinate, more stuff
	//Here...
	
	/** Computes a Matrix times a matrix
	 * returns null if invalid multiplication 
	 * this first in order then m*/
	public Matrix times(Matrix m) {
		if(columns() == m.rows()) {
			Matrix answer = new Matrix(rows(), m.columns());
			double tmpValue = 0;
			int r = rows();
			int mc = m.columns();
			int c = columns();
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < mc ; j++){
					for(int k = 0 ; k < c ; k ++){
						tmpValue += (get(i, k) * m.get(k, j));
					}
					answer.set(i, j, tmpValue);
					tmpValue = 0;
				}
			}
			return answer;
		}
		else{
			System.err.println("Invalid dimensions");
			return null;
		}
	}
	
	/** multiplies a matrix times a scaler */
	public Matrix times(double x) {
		Matrix answer = new Matrix(rows(), columns());
		int r = rows();
		int c = columns();
		for(int i = 0 ; i < r ; i++) {
			for(int j = 0 ; j < c ; j++) {
				answer.set(i, j, get(i, j) * x);
			}
		}
		return answer;
	}
	
	/** computes the determinate of a n by n matrix */
	public double determinant() {
		if(rows() == columns()) {
			//2 by 2 Matrix
			if(rows() == 2) {
				return get(0, 0) * get(1, 1) - get(0, 1) *  get(1, 0);
			}
			//1 by 1 matrix
			if(rows() == 1) {
				return get(0, 0);
			}
			//bigger by bigger matrix
			int r  = rows();
			int rless1 = r - 1;
			double det = 0;
			det += get(0, 0) * subMatrix(1, 1, rless1, rless1).determinant();
			for(int i = 1 ; i < rless1 ; i++) {
				det += power(-1, i) * get(0, i) * 
						subMatrix(1, 0, rless1, i - 1).combineWith(subMatrix(1, i + 1, rless1, rless1)).determinant();
			}
			det += power(-1, rless1) * get(0, rless1) * subMatrix(1, 0, rless1, rless1 - 1).determinant();
			return det;
		}
		else{
			System.err.println("Invalid dimensions" + '\n'
					+ "determinate can only be found on n by n matricies" + '\n'
					+ "You put a" + rows() + " " + columns());
			return 0.0;
		}
	}
	
	public Matrix tanspose() {
		Matrix answer = new Matrix(columns(), rows());
		final int nr = columns();
		final int nc = rows();
		for(int i = 0 ; i < nr ; i++) {
			for(int j = 0 ; j < nc ; j++) {
				answer.set(i, j, get(j, i));
			}
		}
		return answer;
	}
	
	public void println() {
		for(int i = 0 ; i < rows() ; i ++){
			for(int j  = 0 ; j < columns() ; j++){
				System.out.print(get(i, j) + " ");
			}
			System.out.print('\n');
		}
	}
}
