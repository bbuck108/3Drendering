package components;

import java.util.ArrayList;

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
			data.add(new ArrayList<Double>(columns));
		}
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
	
	// Implement plus, minus, scalar times, inverse, determinate, more stuff
	//Here...
	
	/** Computes a Matrix times a matrix
	 * returns null if invalid multiplication */
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
		else return null;
	}
	
	
	public void print() {
		for(int i = 0 ; i < rows() ; i ++){
			for(int j  = 0 ; j < columns() ; j++){
				System.out.print(get(i, j) + " ");
			}
			System.out.print('\n');
		}
	}
}
