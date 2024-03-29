package com.awesome.excelpp.graph;

import java.util.ArrayList;

import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

/**
 * Contains all shared methods for the pacakge graph
 * @author Team Awesome
 *
 */
public class Utils {
	/**
	 * Checks if the <code>Array</code> contains subsequent <code>Integers</code>.
	 * @param array <code>Array</code> that has to be validated
	 * @throws CellInputException Thrown if the <code>Array</code> does not contain subsequent Integers
	 */
	public static void validate(int[] array) throws CellInputException{
		for(int i = 0; i<array.length-1; i++){
			if(!(array[i] + 1 == array[i+1])){ //check if the array is continuous (e.g. 1,2,3 and not 1,3)
				throw new CellInputException();
			}
		}
	}
	
	/**
	 * Retrieves the names of each column.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>Chart</code>
	 * @param rows Array of the selected rows that will be put into the <code>Chart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Chart</code>
	 * @param headers if true user made headers will be used
	 * @return <code>ArrayList</code> containing all the names
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>Chart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>Chart</code>
	 */
	public static ArrayList<String> getNames(SpreadSheet sheet, int[] rows, int[] columns, boolean headers) throws CellInputException, CellDataException{
		int firstRow; int secondRow; int startInt; int endInt;
		try {
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt =columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e){
			throw new CellInputException();
		}

		ArrayList<String> res = new ArrayList<String>();

		if(headers){ //if user made headers have to be used
			if(endInt-startInt>0 && secondRow-firstRow == 1 && startInt >= 0  && endInt >= 0){ //for safety check if the provided values are possible
				for(int i = 0; i<=endInt-startInt; i++){ 
					if(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent() != null 
							&& ((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent() != ""){ //check if the content of cell can be used as header
						res.add(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent());
					} else {
						throw new CellDataException();
					}
				}
			} else {
				throw new CellInputException();
			}
		} else{
			res = getColumnHeaders(startInt, endInt); //if no user made headers have to be used then the headers of the columns will be used (A, B, C, ...)
		}

		return res;
	}
	
	/**
	 * Retrieves the values of the <code>Cells</code>.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>Chart</code>
	 * @param rows Array of the selected rows that will be put into the <code>Chart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Chart</code>
	 * @return <code>ArrayList</code> containing all the values
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>Chart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>Chart</code>
	 */
	public static ArrayList<Double> getValues(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int secondRow; int startInt; int endInt; Double add;
		try {
			secondRow = rows[rows.length-1];
			startInt = columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e) {
			throw new CellInputException();
		}

		ArrayList<Double> res = new ArrayList<Double>();

		if(endInt-startInt>0 && startInt>=0 && endInt >= 0){ //for safety check if the provided values are possible
		for(int i = 0; i<=endInt-startInt; i++){ //add each value
				try {
					add = Double.parseDouble(((Cell) sheet.getValueAt(secondRow, startInt + i)).toString());
					res.add(add);
				} catch(Exception e) {
					throw new CellDataException();
				}
			}
		} else {
			throw new CellInputException();
		}

		return res;
	}
	
	/**
	 * Retrieves the names of each column (for a x rows by x columns <code>Chart</code>)
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>Chart</code>
	 * @param rows rows Array of the selected rows that will be put into the <code>Chart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Chart</code>
	 * @return <code>ArrayList</code> containing all the names of the columns
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>LineChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>LineChart</code>
	 */
	public static ArrayList<String> getHorizontalNames(SpreadSheet sheet, int[] rows, int[] columns, boolean headers) throws CellInputException, CellDataException{
		int firstRow; int startInt; int endInt;
		try {
			firstRow = rows[0];
			startInt = columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e) {
			throw new CellInputException();
		}

		ArrayList<String> res = new ArrayList<String>();
		if(headers){ //if user made headers have to be used
			if(endInt-startInt>0 && startInt >= 0 && endInt >= 0) { //for safety check if the provided values are possible
				for(int i = 0; i<endInt-startInt; i++){ 
					if(((Cell) sheet.getValueAt(firstRow, startInt + 1 + i)).getContent() != null
							&& ((Cell) sheet.getValueAt(firstRow, startInt + 1 + i)).getContent() != "") { //check if content of cell is suited as a header
						res.add(((Cell) sheet.getValueAt(firstRow, startInt + 1 + i)).getContent());
					} else {
						throw new CellDataException();
					}
				}
			} else {
				throw new CellInputException();
			}
		} else{
			res = getColumnHeaders(startInt, endInt); //if no user made headers have to be used then use column headers (A, B, C, ...)
		}
		return res;
	}
	
	/**
	 * Retrieves the names of each row (for an x rows by x columns <code>Chart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to put into a <code>Chart</code>
	 * @param rows rows Array of the selected rows that will be put into the <code>Chart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Chart</code>
	 * @return <code>ArrayList</code> containing all the names of the columns
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>Chart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>Chart</code>
	 */
	public static ArrayList<String> getVerticalNames(SpreadSheet sheet, int[] rows, int[] columns, boolean headers) throws CellInputException, CellDataException{
		int firstRow; int startInt; int secondRow;
		try{
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt = columns[0];

		} catch(Exception e){
			throw new CellInputException();
		}

		ArrayList<String> res = new ArrayList<String>();
		if(headers){ //if user made headers have to be used
			if(startInt >= 0){
				for(int i = 0; i<secondRow-firstRow; i++){ //for safety check if the provided values are possible
					if(((Cell) sheet.getValueAt(firstRow+1+i, startInt)).getContent() != null
							&& ((Cell) sheet.getValueAt(firstRow+1+i, startInt)).getContent() != ""){ //check if content of Cell is suited as a header
						res.add(((Cell) sheet.getValueAt(firstRow + 1 + i, startInt)).getContent());
					} else{
						throw new CellDataException();
					}
				}
			} else{
				throw new CellInputException();
			}
		} else{
			res = getRowHeaders(firstRow, secondRow); //if no user made headers have to be used then use row headers (1, 2, 3, ...)
		}
		return res;
	}
	
	/**
	 * Retrieves the values of the <code>Cells</code> (for a x rows by x columns <code>Chart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>Chart</code>
	 * @param rows Array of the selected rows that will be put into the <code>Chart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Chart</code>
	 * @return <code>ArrayList</code> containing all the values
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>Chart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>Chart</code>
	 */
	public static ArrayList<Double> getValuesXRowsXColumns(SpreadSheet sheet, int[] rows, int[] columns, boolean headers) throws CellInputException, CellDataException{
		int firstRow; int secondRow; int startInt; int endInt; Double add; int horizontalLength; int verticalLength;
		try{
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt = columns[0];
			endInt = columns[columns.length-1];
			horizontalLength = endInt-startInt;
			verticalLength = secondRow-firstRow;
		} catch(Exception e){
			throw new CellInputException();
		}

		ArrayList<Double> res = new ArrayList<Double>();

		if(headers){ //if user made headers have to be used
		 if(endInt-startInt>0 && startInt>=0 && endInt >= 0){ //for safety check if the provided values are possible
		  for(int i = 0; i<verticalLength; i++){ //for each row
			  for(int i2 = 0; i2<horizontalLength; i2++){ //add the content of each cell in that row
				try{
					add = Double.parseDouble(((Cell) sheet.getValueAt(firstRow+ 1 +i, startInt + 1 + i2)).toString());
					res.add(add);
				} catch(Exception e){
					throw new CellDataException();
			 	}
			  }
			} 
		 } else{
			 throw new CellInputException();
		 }
		} else{ //if no user made headers have to be used
			for(int i = 0; i<=verticalLength; i++){ //for each row
				  for(int i2 = 0; i2<=horizontalLength; i2++){ //add the content of each cell in that row
					try{
						add = Double.parseDouble(((Cell) sheet.getValueAt(firstRow +i, startInt + i2)).toString());
						res.add(add);
					} catch(Exception e){
						throw new CellDataException();
				 	}
				  }
				} 
		}
		
		return res;
	}
	
	/**
	 * returns an <code>arrayList</code> with the column headers of a selection
	 * @param startInt first selected column
	 * @param endInt last selected column
	 * @return <code>arrayList</code> with the column headers
	 */
	public static ArrayList<String> getColumnHeaders(int startInt, int endInt){
		ArrayList<String> res = new ArrayList<String>();
		for(int i = 0; i<=endInt-startInt; i++){
			res.add(Character.toString(Character.toChars(startInt+i+65)[0]));
		}
		return res;
	}
	
	/**
	 * returns an <code>arrayList</code> with the row headers of a selection
	 * @param startInt first selected row
	 * @param endInt last selected row
	 * @return <code>arrayList</code> with the column headers
	 */
	public static ArrayList<String> getRowHeaders(int startInt, int endInt){
		ArrayList<String> res = new ArrayList<String>();
		for(int i = 0; i<=endInt-startInt; i++){
			res.add(Integer.toString(startInt+i+1));
		}
		return res;
	}
}

