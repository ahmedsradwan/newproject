package exa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Vector;

public class Tuple implements Serializable{
	
	Vector<Object> tuples = new Vector<Object>();
	
	public Tuple() {

	}

	public Tuple(Vector<Object> tuples) {
		
		this.tuples = tuples;
	}
	
	public void insertIntoTuple(Vector<String> acceptedValues, Vector<Object> acceptedInputs, String tableName, Vector<Table> tables)throws IOException{
		
		int neededIndex = 0;
		int Index = 0;
		
		for(int i = 0; i < tables.size(); i++) {	
			if(tables.get(i).getTableName().equals(tableName)) {
				tuples.setSize(tables.get(i).getColNameType().size());
				for(int j = 0; j < acceptedInputs.size(); j++){	
					neededIndex  = tables.get(i).getcolKeyValues().indexOf(acceptedValues.get(j));
					Index  = tables.get(i).getcolKeyValues().indexOf("ID");
					if(neededIndex != -1) 
							tuples.set(neededIndex, acceptedInputs.get(j));
					}
			}
			System.out.println(tuples);
			System.out.println(Index + " FOOOOR RANAA");
		}
	}
	
	public static void main(String args[]) {

		/*Vector<Object> checker = new Vector<Object>();
		Tuple zeft = new Tuple(checker);
		zeft*/
	}
}