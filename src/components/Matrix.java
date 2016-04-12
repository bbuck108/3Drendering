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
	
	public Matrix() {
		data.add(new ArrayList<Double>(1));
	}
	
	/** Sets a value in a matrix */
	public void set(int row, int column, double value) {
		ArrayList<Double> newRow = data.get(row);
		newRow.set(column, value);
		data.set(row, newRow);
	}
	
	/** Gets a value from a matrix */
	public double get(int row, int column) {
		return data.get(row).get(column);
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
				for(int j = 0 ; j < c ; j++) {
					answer.set(i, j, get(i, j));
				}
				for(int j = c ; j < mc ; j++) {
					answer.set(i, j, m.get(i, j - c));
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
	public double determinate() {
		double det = 0;
		if(rows() == columns()) {
			int r = rows();
			for(int i = 0 ; i < r ; i++) {
				//TODO
			}
			return det;
		}
		else{
			System.err.println("Invalid dimensions"
					+ '\n'
					+ "determinate can only be found on n by n matricies");
			return 0.0;
		}
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
