package components;

import java.util.ArrayList;

/**
 * Currently only 3 by 3 matrices!!!!!!!
 * @author Connor Lehmacher and Benjamin Buck
 */
public class Matrix {
	/** The main ArrayList contains the rows */
	private ArrayList<ArrayList<Double>> data = new ArrayList<>();
	
	/** Creates a Matrix with some amount of rows and columns */
	public Matrix(int rows, int columns) {
		for(int i = 1 ; i <= rows ; i++) {
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
	
	// Implemt plus, minus, scalar times, inverse, determinate, more stuff
	
	/** Computes a Matrix times a matrix
	 * returns null if invalid multiplication */
	public Matrix times(Matrix m) {
		if(columns() == m.rows()) {
			//implemnt this!! TODO
			return null;
		}
		else return null;
	}
	
}
