package exa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;



public class Table implements Serializable {
	
	static String tableName;
	static Boolean clusteringKey;
	Hashtable<String, String> colNameType;
	Hashtable<String, String> colNameMax;
	Hashtable<String, String> colNameMin;
	static Vector<Pages> pageStorage = new Vector<Pages>();
	static String path;
	
	

	public Table(String tableName, Boolean clusteringKey, Hashtable<String, String> colNameType,
			Hashtable<String, String> colNameMax, Hashtable<String, String> colNameMin) throws IOException {

		this.tableName = tableName;
		this.clusteringKey = clusteringKey;
		this.colNameType = colNameType;
		this.colNameMax = colNameMax;
		this.colNameMin = colNameMin;
		this.path = tableName + "_";
		this.SerializeTable();
	
	}

	public String getPath() {
		return path;
	}

	public String getTableName() {
		return tableName;
	}


	


	public Boolean getClusteringKey() {
		return clusteringKey;
	}


	

	public Vector<String> getcolKeyValues(){
		
		Set<String> keysOfHashtable = this.colNameType.keySet();
		Iterator<String> colNameTypeItr1 = keysOfHashtable.iterator();
		Vector<String> colKeyValues = new Vector<String>();
		while (colNameTypeItr1.hasNext()){      
    		  
			  String colNameTypeStr2 = colNameTypeItr1.next();
			  colKeyValues.add(colNameTypeStr2);
		}
		return colKeyValues;
	}
	public Hashtable<String, String> getColNameType() {
		return colNameType;
	}


	


	public Hashtable<String, String> getColNameMax() {
		return colNameMax;
	}




	public Hashtable<String, String> getColNameMin() {
		return colNameMin;
	}


	


	public static Vector<Pages> getPageStorage() {
		return pageStorage;
	}


	
	
	public void insetIntoTable(Vector<String> acceptedValues, Vector<Object> acceptedInputs,String tableName, Vector<Table> tables) throws IOException, ClassNotFoundException {
		
		
		this.DeserializeTable();
		Tuple tuple1 = new Tuple();
		tuple1.insertIntoTuple(acceptedValues, acceptedInputs, tableName,tables);
		Pages page1 = new Pages(0, path);
		page1.insertIntoPages(tuple1, page1, tableName, tables);
	}
	
	public void SerializeTable() throws IOException{		
	
		File file1 = new File(path +  tableName +".class");
		
		if(!file1.exists()) {
			file1.createNewFile();
		}
		
		FileOutputStream out = new FileOutputStream(file1);
		ObjectOutputStream out1 = new ObjectOutputStream(out);
		System.out.println(path + tableName + " " +"Object has been serialized"); 
		out1.writeObject(this);
		out1.close();
		out.close();
	}
	
	public Table DeserializeTable() throws IOException, ClassNotFoundException {
		
		File f = new File(this.getPath() + this.tableName +".class");	
	
		if(!f.exists()) {
		
			f.createNewFile();
		}
		
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Table  table1 = (Table)ois.readObject();
		System.out.println(path + tableName + " " +"Object has been Deserialized");
		//System.out.println(table1 + "           "+" check meee ");	
		ois.close();
		fis.close();
		return table1;	
	}
	
	public void UpdateTheTable(Vector<String> updatingcolums, String tableName2, Vector<Object> updatingClusteringKey, Vector<Object> updatingValues, Vector<Table> tables) throws IOException, ClassNotFoundException {
		Pages page1;
		FileInputStream fis;
		ObjectInputStream ois;
		for(int j = 0; j < pageStorage.size(); j++) {
			
			fis = new FileInputStream(new File(this.getPath() + pageStorage.get(j).getPageNumber() +".class"));
			ois = new ObjectInputStream(fis);
			//page1 = (Pages) ois.readObject();
			
			page1 = Table.getPageStorage().get(0);
			
			System.out.println("sadasd" +page1.tupleStorage.get(0));
			System.out.println(page1.tupleStorage.get(0).tuples +" HIIIII");
			System.out.println(page1.tupleStorage.get(1).tuples +" HIIIII");

			for(int i = 0; i < pageStorage.size(); i++) {
				
					if(page1.checkClusteringKey( tableName2,tables, updatingClusteringKey, updatingValues, (page1.tupleStorage.get(i).tuples)) == true) {
					
						//insert_into_table(strTableName, htblColNameValue);
						
						page1.removeTupleFromPage(page1.tupleStorage.get(i));
						insetIntoTable( updatingcolums, updatingValues, tableName2 ,tables);
						System.out.println("Updating");
						System.out.println(page1.tupleStorage.toString());
					//	page1.bubbleSort(tables, "ID", tableName2);
						System.out.println(page1.tupleStorage.get(0).tuples +" BYEEEEE");
						System.out.println(page1.tupleStorage.get(1).tuples +" BYEEEEE");
					}
					else{
						System.out.print("tuple not found");
						return;
					}
			}
		}
		return;
	}
	public void DeleteFromTable(String tableName2, Vector<String> colNameReceived, Vector<Object> deletedVlaues) throws IOException, ClassNotFoundException {

		Pages page1;
		FileInputStream fis;
		ObjectInputStream ois;
		for(int j = 0; j < pageStorage.size(); j++) {
			
			fis = new FileInputStream(new File(this.getPath() + pageStorage.get(j).getPageNumber() +".class"));
			ois = new ObjectInputStream(fis);
			//page1 = (Pages) ois.readObject();
			
			page1 = Table.getPageStorage().get(0);
			
			System.out.println("sadasd" +page1.tupleStorage.get(0));
			

			for(int i = 0; i < pageStorage.size(); i++) {
				
				if(page1.checkEquality(deletedVlaues, (page1.tupleStorage.get(i).tuples)) == true) {
					
					page1.removeTupleFromPage(page1.tupleStorage.get(i));
					System.out.println("DELETED");
					System.out.println(page1.tupleStorage.toString());
					
				}
					
			}	
			ois.close();
			fis.close();
		}
		
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		Hashtable<String,String> sd = new Hashtable();
		sd.put("ID", "java.lang.Integer");
		sd.put("Name", "java.lang.String");
		sd.put("Specialization", "java.lang.String");
		
		Hashtable<String,String> dsa = new Hashtable();
		dsa.put("ID", "java.lang.Integer");
		dsa.put("Name", "java.lang.String");
		dsa.put("Specialization", "java.lang.String");
		
		Hashtable<String,String> sed = new Hashtable();
		sed.put("ID", "java.lang.Integer");
		sed.put("Name", "java.lang.String");
		sed.put("Specialization", "java.lang.String");
		
		Table x = new Table("Student",true,sd,dsa,sed);
		System.out.println(x.getTableName());
		System.out.println(x);
		x.DeserializeTable();
		System.out.println(x);
		
	}

	

}
