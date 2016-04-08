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
			System.err.println("Invalid Dimentions");
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
			System.err.println("Invalid Dimentions");
			return null;
		}
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
