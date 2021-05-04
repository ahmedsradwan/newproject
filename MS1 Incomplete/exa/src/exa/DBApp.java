package exa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class DBApp implements DBAppInterface, Serializable{

	FileWriter metadata;
	Vector<String> tableNames = new Vector<String>();
	Vector<String> colName = new Vector<String>();
	Vector<String> colNameDataType = new Vector<String>();	
	Table table1 ;
	static Vector<Table> tables = new Vector<Table>();
	
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void createTable(String tableName, String clusteringKey, Hashtable<String, String> colNameType,
			Hashtable<String, String> colNameMin, Hashtable<String, String> colNameMax) throws DBAppException, IOException, ClassNotFoundException {
	
		table1 = new Table(tableName,false,colNameType,colNameMin,colNameMax);
		table1.DeserializeTable();
		tableNames.addElement(tableName);
		tables.addElement(table1);
		
		//writer
		//metadata = new FileWriter("C:/Users/fuc u/eclipse-workspace/exa/src/resources/metadata.csv",true);
		metadata = new FileWriter("C:/Users/fuc u/eclipse-workspace/exa/src/resources/metadata.csv");
		metadata.write("Table Name");
		metadata.write(",");
		metadata.write("Column Name");
		metadata.write(",");
		metadata.write("Column Type");
		metadata.write(",");
		metadata.write("Clustering Key");
		metadata.write(",");
		metadata.write("Min");
		metadata.write(",");
		metadata.write("Max");
		metadata.write(",");
		metadata.write("\n");
		//
		    Set<String> colNameTypeKeys = colNameType.keySet();
		    Iterator<String> colNameTypeItr = colNameTypeKeys.iterator();	 
		    Set<String> colNameMinKeys = colNameMin.keySet();
		    Iterator<String> colNameMinItr = colNameMinKeys.iterator();	 
		    Set<String> colNameMaxKeys = colNameMax.keySet();
		    Iterator<String> colNameMaxItr = colNameMaxKeys.iterator();	 
		    Enumeration enu = colNameType.keys();
			//
		    
		    while (colNameTypeItr.hasNext() && enu.hasMoreElements()) {      
		    	
		    	String colNameTypeStr = colNameTypeItr.next();
			    String colNameMaxinStr = colNameMaxItr.next();
			    String colNameMinStr = colNameMinItr.next();
			    if(enu.nextElement() == clusteringKey){
	            	metadata.write(tableName);
				    metadata.write(",");
					metadata.write(colNameTypeStr);
					colName.add(colNameTypeStr);
					metadata.write(",");
					metadata.write(colNameType.get(colNameTypeStr));
					colNameDataType.add(colNameType.get(colNameTypeStr));
					metadata.write(",");
					metadata.write("true");
					metadata.write(",");
					//metadata.write(colNameMinStr);
					//metadata.write(",");
					metadata.write(colNameMin.get(colNameMinStr));
					metadata.write(",");
					//metadata.write(colNameMaxinStr);
					//metadata.write(",");
					metadata.write(colNameMax.get(colNameMaxinStr));
					metadata.write(",");	
					metadata.write("\n");
				}
	            else {
	            	metadata.write(tableName);
	 			    metadata.write(",");
	 				metadata.write(colNameTypeStr);
	 				colName.add(colNameTypeStr);
	 				metadata.write(",");
	 				metadata.write(colNameType.get(colNameTypeStr));
					colNameDataType.add(colNameType.get(colNameTypeStr));
	 				metadata.write(",");
	 				metadata.write("false");
	 				metadata.write(",");
	 				//metadata.write(colNameMinStr);
	 				//metadata.write(",");
	 				metadata.write(colNameMin.get(colNameMinStr));
	 				metadata.write(",");
	 				//metadata.write(colNameMaxinStr);
	 				//metadata.write(",");
	 				metadata.write(colNameMax.get(colNameMaxinStr));
	 				metadata.write(",");	
	 				metadata.write("\n");
	            }   
		    }	
		    
		    
		//
		   /* Set<String> colNameMinKeys = colNameMin.keySet();
		    Iterator<String> colNameMinItr = colNameMinKeys.iterator();	 
		    while (colNameMinItr.hasNext()) {      
		       String colNameMinStr = colNameMinItr.next();
		        metadata.write(tableName);
			    metadata.write(",");
				metadata.write(colNameMinStr);
				metadata.write(",");
				metadata.write(colNameMin.get(colNameMinStr));
				metadata.write(",");
				metadata.write(clusteringKey);
				metadata.write(",");
				metadata.write("\n");
		    }	*/
			//
		 /*   Set<String> colNameMaxKeys = colNameMax.keySet();
		    Iterator<String> colNameMaxItr = colNameMaxKeys.iterator();	 
		    while (colNameMaxItr.hasNext()) {      
		       String colNameMaxinStr = colNameMaxItr.next();
		        metadata.write(tableName);
			    metadata.write(",");
				metadata.write(colNameMaxinStr);
				metadata.write(",");
				metadata.write(colNameMax.get(colNameMaxinStr));
				metadata.write(",");	
				metadata.write(clusteringKey);
				metadata.write(",");
				metadata.write("\n");
		    }	*/
		//

		metadata.flush();
		metadata.close();
		table1.SerializeTable();
		
		
		//Reader
		/*String path = "C:/Users/fuc u/eclipse-workspace/exa/src/resources/metadata.csv";
		String line = "";
		
		File file = new File(path);
		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			scan.useDelimiter(",\n");
			System.out.println(scan.next());
		}*/
		/*String path = "C:/Users/fuc u/eclipse-workspace/exa/src/resources/metadata.csv";
		String line = "";
		
		File file = new File(path);
		Scanner scan = new Scanner(file);
		//Set<String> colNameValueKeys = colNameValue.keySet();
		while (scan.hasNext()) {
			//scan.useDelimiter(",\n");
			if(scan.hasNext("ID"))
			System.out.println("OKAY");
			break;
		}
		//Set<String> keysOfHashtable = colNameValue.keySet();
		/*Iterator<String> colNameTypeItr1 = colNameTypeKeys.iterator();	 
		Vector<String> colNameReceived = new Vector<String>();
		 Vector<String> colNameTypeReceived = new Vector<String>();
		 System.out.print(tableNames);
		    
	    if(tableNames.contains(tableName)) {
	    	
	    	  while (colNameTypeItr1.hasNext()) {      
	    		  
	    		  String colNameTypeStr2 = colNameTypeItr1.next();
				  colNameReceived.add(colNameTypeStr2);
				//  System.out.print(colNameReceived);
				  String colNameTypeStr3 = colNameType.get(colNameTypeStr2);
				  colNameTypeReceived.add(colNameTypeStr3);
				  //System.out.print(colNameTypeReceived);
				 
				  
	    	  }	
	    	  int ssa = 0;
	    	  String tr = null;
	    	  for(int i = 0; i< colNameReceived.size(); i++) {
		    	  if(colName.contains(colNameReceived.get(i)))
		    		  tr= (colNameReceived.get(i));
		    		  ssa  = colName.indexOf(tr);
		    		  System.out.print(ssa + tr );
		    		  if(colNameDataType.get(ssa).equals(colNameTypeReceived.get(ssa))){
			    		  System.out.print("Ahmed");
			    	  } 
		    	  }   
	    	  System.out.print(tableNames + hf);
	  	    
		}*/
		//System.out.print(tables.get().getTableName());
		
		//System.out.print(colNameDataType);
	//	System.out.print(p);
  	    
	}

	@Override
	public void createIndex(String tableName, String[] columnNames) throws DBAppException {
		// TODO Auto-generated method stub
		
	}
	
public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException, IOException, ClassNotFoundException {
		
		
	    Set<String> keysOfHashtable = colNameValue.keySet();
		Iterator<String> colNameTypeItr1 = keysOfHashtable.iterator();	 
		Vector<String> colNameReceived = new Vector<String>();
		Vector<Object> colNameTypeReceived = new Vector<Object>();
		Vector<Object> acceptedInputs = new Vector<Object>();
		Vector<String> acceptedValues = new Vector<String>();
		 
		 Tuple tuples = new Tuple();
			
	//	 System.out.print(tableNames);
		
		/* Enumeration keys = table1.getColNameType().keys(); 
		 while(keys.hasMoreElements())
			 System.out.print(keys.nextElement());*/
	    if(tableNames.contains(tableName)){	
	    	  while (colNameTypeItr1.hasNext()){      
	    		  
				  String colNameTypeStr2 = colNameTypeItr1.next();
				  colNameReceived.add(colNameTypeStr2);
				//  System.out.print(colNameReceived);
				  Object colNameTypeStr3 = (Object) colNameValue.get(colNameTypeStr2);
				  colNameTypeReceived.add(colNameTypeStr3);
				//  System.out.print(colNameTypeReceived);
				  //System.out.print(colName);
				//  System.out.print(colNameDataType);	 
				 
	    	  }	
	    	  int neededIndex = 0;
	    	  int counter = 0;
	    	  for(int i = 0; i< colNameReceived.size(); i++) {
	    		  if(table1.getColNameType().containsKey(colNameReceived.get(i))) {
	    			  // if(colName.contains(colNameReceived.get(i)))
	  		    	  neededIndex  = colName.indexOf(colNameReceived.get(i));
		    		  if(neededIndex != -1) {
		    			  if(colNameDataType.get(neededIndex).equals((Object)(colNameTypeReceived.get(i)).getClass().getName())){
		    				  acceptedInputs.add(colNameTypeReceived.get(i));
		    				  acceptedValues.add(colNameReceived.get(i));			    	  
		    			  }
		    			  else
		    				  System.out.print("Invalid datatype or column Name");				    	  
		    		  }	
	    		  }	 
	    	 }
	    	  if(!(acceptedInputs.isEmpty())  && !(acceptedValues.isEmpty())) {
	    		//  System.out.print(acceptedValues);
	    		//  System.out.print(acceptedInputs);
	    		 // System.out.print(colName);

			      counter = table1.getColNameType().size();
			    //  System.out.print(counter);

				//  tuples.insertIntoTuple(colName, acceptedValues, acceptedInputs, tuples, counter, p, tableName, tables);
			      for(int j = 0; j <tables.size(); j++)
			      tables.get(j).insetIntoTable(acceptedValues, acceptedInputs,tableName, tables);
			      
	    	  }
	    }
	    else
	    System.out.print("Table does not exist");
	
	}
	/*public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException, IOException, ClassNotFoundException {
		
		
	    Set<String> keysOfHashtable = colNameValue.keySet();
		Iterator<String> colNameTypeItr1 = keysOfHashtable.iterator();	 
		Vector<String> colNameReceived = new Vector<String>();
		Vector<Object> colNameTypeReceived = new Vector<Object>();
		Vector<Object> acceptedInputs = new Vector<Object>();
		Vector<String> acceptedValues = new Vector<String>();
		 
		 Tuple tuples = new Tuple();
			
	//	 System.out.print(tableNames);
		
		/* Enumeration keys = table1.getColNameType().keys(); 
		 while(keys.hasMoreElements())
			 System.out.print(keys.nextElement());*/
		/* for(int i = 0; i < tables.size(); i++) {	
				if(tables.get(i).getTableName().equals(tableName)) {
					String colNameTypeStr2 = colNameTypeItr1.next();
					  colNameReceived.add(colNameTypeStr2);
					  Object colNameTypeStr3 = (Object) colNameValue.get(colNameTypeStr2);
					  colNameTypeReceived.add(colNameTypeStr3);
					  if(tables.get(i).getColNameType().containsKey(colNameReceived.get(i))) {
		    			  // if(colName.contains(colNameReceived.get(i)))
		  		    	  neededIndex  = colName.indexOf(colNameReceived.get(i));
			    		  if(neededIndex != -1) {
			    			  if(colNameDataType.get(neededIndex).equals((Object)(colNameTypeReceived.get(i)).getClass().getName())){
			    				  acceptedInputs.add(colNameTypeReceived.get(i));
			    				  acceptedValues.add(colNameReceived.get(i));			    	  
			    			  }
			    			  else
			    				  System.out.print("Invalid datatype or column Name");				    	  
			    		  }	
				}*/
					
		/* if(tableNames.contains(tableName)){	
	    	  while (colNameTypeItr1.hasNext()){      
	    		  
				  String colNameTypeStr2 = colNameTypeItr1.next();
				  colNameReceived.add(colNameTypeStr2);
				//  System.out.print(colNameReceived);
				  Object colNameTypeStr3 = (Object) colNameValue.get(colNameTypeStr2);
				  colNameTypeReceived.add(colNameTypeStr3);
				//  System.out.print(colNameTypeReceived);
				  //System.out.print(colName);
				//  System.out.print(colNameDataType);	 
				  
				 
	    	  }	
	    	  int neededIndex = 0;
	    	  for(int i = 0; i< tables.size(); i++) {
	    		  if(tables.get(i).getColNameType().containsKey(colNameReceived.get(i))) {
	    			  // if(colName.contains(colNameReceived.get(i)))
	  		    	  neededIndex  = colName.indexOf(colNameReceived.get(i));
		    		  if(neededIndex != -1) {
		    			  if(colNameDataType.get(neededIndex).equals((Object)(colNameTypeReceived.get(i)).getClass().getName())){
		    				  acceptedInputs.add(colNameTypeReceived.get(i));
		    				  acceptedValues.add(colNameReceived.get(i));			    	  
		    			  }
		    			  else
		    				  System.out.print("Invalid datatype or column Name");				    	  
		    		  }	
	    		  }	 
	    	 }
	    	  for(int i = 0; i< tables.size(); i++) {
		  	    	
		    	  if(!(acceptedInputs.isEmpty())  && !(acceptedValues.isEmpty())) {
		    		//  System.out.print(acceptedValues);
		    		//  System.out.print(acceptedInputs);
		    		  System.out.print(tables.get(i).getTableName());
	
					//  tuples.insertIntoTuple(colName, acceptedValues, acceptedInputs, tuples, counter, p, tableName, tables);
				      tables.get(i).insetIntoTable(acceptedValues, acceptedInputs, tableName, tables);
		    	  } 
	    	  }
	    }
	    else
	    System.out.print("Table does not exist");
	
	}*/

	@Override
	public void updateTable(String tableName, String clusteringKeyValue, Hashtable<String, Object> columnNameValue) throws DBAppException, IOException, ClassNotFoundException {
		
		
		Set<String> keysOfHashtable = columnNameValue.keySet();
		Iterator<String> colNameTypeItr1 = keysOfHashtable.iterator();	 
		Vector<String> updatingClusteringKeyName = new Vector<String>();
		Vector<String> updatingcolums = new Vector<String>();
		Vector<Object> updatingClusteringKey = new Vector<Object>();
		Vector<Object> updatingValues = new Vector<Object>();
		while (colNameTypeItr1.hasNext()){      
   		  
			  String colNameTypeStr2 = colNameTypeItr1.next();
			  updatingcolums.add(colNameTypeStr2);
			  if(colNameTypeStr2.equals(clusteringKeyValue)){
				  updatingClusteringKeyName.add(colNameTypeStr2);
				  //System.out.print(updatingClusteringKey.get(0));
				  updatingClusteringKey.add(columnNameValue.get(updatingClusteringKeyName.get(0)));
			//	  System.out.print(updatingClusteringKey);
			  }
			  
			  Object colNameTypeStr3 = (Object) columnNameValue.get(colNameTypeStr2);
			  updatingValues.add(colNameTypeStr3);
			 // System.out.print(updatingcolums +"sdasd");
			  //System.out.print(columnNameValue.get(updatingClusteringKey.get(0)));
			  //System.out.print(updatingClusteringKey.get(0));		
		 }	  

		 for(int i =0; i <tables.size(); i++) {
			if(tables.get(i).getTableName().equals(tableName)){
				Table table1 = tables.get(i);
				table1.UpdateTheTable(updatingcolums, tableName, updatingClusteringKey, updatingValues, tables );
			}
			System.out.println("updated");
		}
	}

	@Override
	public void deleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws DBAppException, IOException, ClassNotFoundException {
		
		Set<String> keysOfHashtable = columnNameValue.keySet();
		Iterator<String> colNameTypeItr1 = keysOfHashtable.iterator();	 
		Vector<String> colNameReceived = new Vector<String>();
		Vector<Object> colNameTypeReceived = new Vector<Object>();
		Vector<Object> acceptedInputs = new Vector<Object>();
		Vector<String> acceptedValues = new Vector<String>();
		 while (colNameTypeItr1.hasNext()){      
   		  
			  String colNameTypeStr2 = colNameTypeItr1.next();
			  colNameReceived.add(colNameTypeStr2);
			  Object colNameTypeStr3 = (Object) columnNameValue.get(colNameTypeStr2);
			  colNameTypeReceived.add(colNameTypeStr3);
			  //System.out.print(colNameReceived);
			//  System.out.print(colNameTypeReceived);
				
		 }	  
		for(int i =0; i <tables.size(); i++) {
			if(tables.get(i).getTableName().equals(tableName)) {
				Table table1 = tables.get(i);
				table1.DeleteFromTable(tableName, colNameReceived, colNameTypeReceived  );
			}
			System.out.println("deleted");
		}
	}

	@Override
	public Iterator selectFromTable(SQLTerm[] sqlTerms, String[] arrayOperators) throws DBAppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]) throws IOException, DBAppException, ClassNotFoundException {
		
		Hashtable<String,String> dsa = new Hashtable();
		dsa.put("ID", "java.lang.Integer");
		dsa.put("Name", "java.lang.String");
		dsa.put("Specialization", "java.lang.String");
	//	dsa.put("Park", "java.lang.String");
		
		Hashtable<String,String> sd = new Hashtable();
		sd.put("ID", "0");
		sd.put("Name", "A");
		sd.put("Specialization", "AA");		
		//sd.put("Park", "AA");		
		
		Hashtable<String,String> sed = new Hashtable();
		sed.put("ID", "10000");
		sed.put("Name", "ZZZZZZZZZZZZZZ");
		sed.put("Specialization", "XZZZZZZZZ");
		//sed.put("Park", "XZZZZZZZZ");
		//
		Hashtable<String,String> dsa1 = new Hashtable();
		dsa1.put("Name", "java.lang.String");
		dsa1.put("Address", "java.lang.Integer");
		dsa1.put("Faculty", "java.lang.String");
		//dsa1.put("Car", "java.lang.String");
			
		Hashtable<String,String> sd1 = new Hashtable();
		sd1.put("Name", "ABBBB");
		sd1.put("Faculty", "A");
		sd1.put("Address", "AA"); 
	//	sd1.put("Car", "AA"); 
				
		Hashtable<String,String> sed1 = new Hashtable();
		sed1.put("Name", "XYZZZZZZZ");
		sed1.put("Faculty", "ZZZZZZZZZZZZZZ");
		sed1.put("Address", "XZZZZZZZZ");
	//	sed1.put("Car", "XZZZZZZZZ");
		//
		
		Hashtable<String,Object> insert1 = new Hashtable();
		insert1.put("Specialization", new String ("BI"));
		insert1.put("Address", new String ("123"));
		insert1.put("ID", new Integer (10));
		insert1.put("Name", new String ("ahmed"));
		
		Hashtable<String,Object> insert2 = new Hashtable();
		insert2.put("Specialization", new String ("BI"));
		insert2.put("Address", new String ("123"));
		insert2.put("ID", new Integer (15));
		insert2.put("Name", new String ("Rana"));
		
		Hashtable<String,Object> delete1 = new Hashtable();	
		delete1.put("ID", 4612337);
		delete1.put("Name", "ahmed");
		delete1.put("Specialization", "BI");
		
		Hashtable<String,Object> update1 = new Hashtable();	
		update1.put("ID", 10);
		update1.put("Name", "ahmed");
		update1.put("Specialization", "BI");
		
		
		DBApp a = new DBApp();
		
		a.createTable("Student", "Name", dsa ,sd , sed );
		
		
		//a.createTable("Uni", "Address", dsa1, sd1, sed1);
		
		//System.out.print(tables);
		
		a.insertIntoTable("Student", insert1);
		a.insertIntoTable("Student", insert2);
		//a.insertIntoTable("Student", insert1);
		//a.insertIntoTable("Student", insert1);
		//a.insertIntoTable("Student", insert1);
		//a.insertIntoTable("Student", insert1);
		//a.insertIntoTable("Student", insert1);
		//a.insertIntoTable("Uni", insert1);
		
		a.updateTable("Student" , "Name" ,update1);
		a.deleteFromTable("Student" ,delete1);
		
		
	}
}
