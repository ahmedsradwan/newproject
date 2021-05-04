package exa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


public class Pages implements Serializable {

	static int  maxLength =5;
	static int occupiedLength;
	static int pageNumber = 1;
	Vector<Tuple> tupleStorage = new Vector<Tuple>();
	String path;
	
	public Pages(int occupiedLength,String path ) throws IOException, ClassNotFoundException {
			
			//ConfigReader();
			this.tupleStorage = tupleStorage;
			this.path = path;
			this.SerializePage();
		
		}
	
	public String getPath() {
		return path;
	}

	public static int getPageNumber() {
		return pageNumber;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static int ConfigReader() throws IOException {
		
		FileReader reader =  new FileReader("C:/Users/fuc u/eclipse-workspace/exa/src/resources/DBApp.config");
		Properties properties = new Properties();
		properties.load(reader);
	    maxLength = Integer.parseInt(properties.getProperty("MaximumRowsCountinPage"));
	    return maxLength;
	
	}
	public void SerializePage() throws IOException, ClassNotFoundException{
		   
			//String filename = "C:/Users/fuc u/eclipse-workspace/exa/tr.text";
	          
	        // Serialization 
            //Saving of object in a file
        //    FileOutputStream file = new FileOutputStream(filename);
        //    ObjectOutputStream out = new ObjectOutputStream(file);
              
            // Method for serialization of object
           // out.writeObject(serializeVector);
              
         //   out.close();
         //   file.close();
            
			//path = (tables.get(0).gettablename + "_");
		
            File file1 = new File(path + pageNumber + ".class");
            
    		if(!file1.exists()) {
    			file1.delete();
    		}	
    		 FileOutputStream file = new FileOutputStream(file1);
    	     ObjectOutputStream out = new ObjectOutputStream(file);
    		//System.out.println(out.writeObject(this));
            
    	     
    		out.writeObject(this);
    		out.close();
    		file.close();
              
    		 System.out.println(path + pageNumber + " " +"Object has been serialized");
             //System.out.println(serializeVector);
            
            /*FileInputStream file2 = new FileInputStream(filename);
              ObjectInputStream in = new ObjectInputStream(file2);
                  
             // Method for deserialization of object
             pageStorage = (Vector<Pages>)in.readObject();
                  
                in.close();
                file.close();
                  
                System.out.println("Object has been deserialized ");
                System.out.println(pageStorage);
            */
	}
	
	

	public Pages DeserializePage(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
			
			File filename = new File(path + pageNumber + ".class");
				 
			FileInputStream file2 = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file2);
			
			Pages page1 = (Pages) in.readObject();
				
			System.out.println(page1.occupiedLength + "  " + maxLength);
			//System.out.println(page1.tupleStorage.toString()+ " before sending to method");
			   
			if(page1.occupiedLength >= maxLength) {	
				page1 = createNewPage();
				occupiedLength = 0;
				System.out.println("Created new page");
				
			}
			
			in.close();
			file2.close();
			System.out.println(path + pageNumber+ "  " + "Object has been Deserialized");
			//System.out.println(page1 + "    "+ "new here");
			      
			return page1;
		}
	
	public void insertIntoPages(Tuple tuples, Pages page1, String tableName, Vector<Table> tables) throws IOException, ClassNotFoundException {
		
		for(int i = 0; i < tables.size(); i++) {	
			if(tables.get(i).getTableName().equals(tableName)) {
				if(tables.get(i).getPageStorage().isEmpty()) {
					//System.out.println( tables.get(i).getPageStorage().size());
					
			//		System.out.println(page1 + "           "+" check meee ");
					
					page1 = DeserializePage(path);
				//	System.out.println(page1 + "           "+"here ");
					//System.out.println(page1.getPath() + pageNumber + "           "+" Actual path");
					
				//System.out.println(page1);
					
					//System.out.println(page1.tupleStorage.toString());
					page1.tupleStorage.add(tuples);
					occupiedLength++;
					//System.out.println(page1.tupleStorage.toString());
					tables.get(i).getPageStorage().add(page1);
					//System.out.println(tables.get(i).getPageStorage().size());
					
					SerializePage();
				}
				else if(!(tables.get(i).getPageStorage().isEmpty()) && occupiedLength != page1.tupleStorage.size()){
					for( int j = 0; j < tables.get(i).getPageStorage().size(); j++) {
						
						page1 = tables.get(i).getPageStorage().get(i);
						//System.out.println(page1.tupleStorage.toString()+ "existing");
						
						//System.out.println(page1 + "           "+" check meee ");
						System.out.println(tables.get(i).getPageStorage().get(0).getPath() + pageNumber+ ""+" The path");
						
						

						page1 = DeserializePage(tables.get(i).getPageStorage().get(0).getPath());
						//System.out.println(page1.tupleStorage.toString()+ " got from deserial");
						
					//	System.out.println(page1 + "           "+"here ");
						//System.out.println(page1.tupleStorage.toString());
						page1.tupleStorage.add(tuples);
						System.out.println(page1.tupleStorage.toString() +" final");
						
						occupiedLength++;
					//	System.out.println(page1.tupleStorage.toString());
						//System.out.println(tables.get(i).getPageStorage());
						//System.out.println("IM HERERERE");
						SerializePage();
						tables.get(i).SerializeTable();
					}
				}
			}
		}
		//System.out.println(path + "Object has been Deserialized");
         
	/*	for(int i = 0; i < tables.size(); i++) {	
			if(tables.get(i).getTableName().equals(tableName)) {
				if(tables.get(i).getPageStorage().isEmpty()) {
					page1.tupleStorage.add(tuples);
					tables.get(i).getPageStorage().add(page1);
				//	System.out.println(tables.get(i).getPageStorage().get(i).tupleStorage);	
				}
				else {
				//	System.out.println("check");
					System.out.println(tables.get(i).getPageStorage().size());	
					boolean flag = false;
					int j;
					for( j = 0; j < tables.get(i).getPageStorage().size(); j++) {
							if(tables.get(i).getPageStorage().get(j).tupleStorage.size() < maxLength) {
								tables.get(i).getPageStorage().get(j).tupleStorage.add(tuples);
								System.out.println(tables.get(i).getPageStorage().get(j).tupleStorage);	
								System.out.println("check");
								
							}
						//	System.out.println(maxLength + "check me");
							
						//	System.out.println(tables.get(i).getPageStorage().size());
					//		System.out.println(j);
					}
							if (j == tables.get(i).getPageStorage().size()) {
								
								flag = true;
								System.out.println("IM HERE00");
								
									if (flag == true){
										System.out.println("IM HERE");
										
									pageNumber++;
									System.out.println("IM HERE12");
									
									Pages x = new Pages(0);
									System.out.println("IM HERE123");
									
									tables.get(i).getPageStorage().add(x);
									System.out.println("IM HERE1234");
									
									//tables.get(i).getPageStorage().add(x);
									
									System.out.println("IM HERE123456");
									x.insertIntoPages(tuples, x, tableName, tables);	
								}
							}
							/*if (tables.get(i).getPageStorage().get(j).tupleStorage.size() == maxLength){
								pageNumber++;
								Pages x = new Pages(0);
								tables.get(i).getPageStorage().add(x);
								System.out.println("check");
								x.insertIntoPages(tuples,x, tableName, tables);	
							}*/
						//	System.out.println(maxLength);
							//System.out.print(p);
					//} //<1,2>
			/*	}
			}
				
		}*/
	
		/*if(tupleStorage.size() < maxLength) {	
			
			p.tupleStorage.add(tuples);
			System.out.print(tupleStorage);
			System.out.print(p);
		//	System.out.print(maxLength);

		}
		else {
			// table.getvecto.add(x)
			pageNumber++;
			Pages x = new Pages(0);
			x.insertIntoPages(tuples,x, tableName, tables);	
		}*/
	}
	
	/*public static void bubbleSort(Vector<Table> tables,String key, String tablename) {	
		
		Object  x = tables.get(0).getPageStorage().get(0).tupleStorage.get(0).tuples.get(2);
		Object y = tables.get(0).getPageStorage().get(0).tupleStorage.get(1).tuples.get(2);
		
		int as = x.compareTo(y);
		
		System.out.println(Objects.compare(x, y));
		// x < y = - ..... 
		System.out.println(x + " THIS IS x");
		
		//String hs = x.getClass().getName();
		System.out.println(y + " THIS IS y");	
		//System.out.println(( (Comparable<Integer>) x).compareTo((Integer) y) + " checkkkkkkkkkkkkkkk");
		/*boolean sorted = false;
	    int Index;
		for(int j = 0; j < tables.size(); j++)	{
			if(tables.get(j).getTableName().equals(tablename)) {
				Index  = tables.get(j).getcolKeyValues().indexOf("ID");
				Tuple temp = new Tuple();
				Object x;
				Object y;
				
	
				while(!sorted) {
					sorted = true;
					for (int i = 0; i < (tables.get(j).getPageStorage().get(i).maxLength)-1 ; i++) {
						x = tables.get(j).getPageStorage().get(i).tupleStorage.get(i).tuples.get(i);
						y = tables.get(j).getPageStorage().get(i).tupleStorage.get(i).tuples.get(i+1);
						
						//((Integer) x).compareTo((Integer) y);
						
						if (1 = 1 ) {
							temp = a[Index];
							a[Index] = a[i+1];
							a[Index+1] = temp;
							sorted = false;
						}
					}
				}
			}
		}*/
	//}
	
	public boolean checkEquality(Vector<Object> deletedValues, Vector<Object> existingTuples ){
		
		boolean flag = false;
		for(int i = 0; i < deletedValues.size(); i++) {
			if(deletedValues.get(i).equals(existingTuples.get(i))) {
				flag = true;
				System.out.print("equal");
			}
			else {
				flag = false;
			}
		}
		return flag;
	}
	
	public boolean checkClusteringKey(String tableName, Vector<Table> tables, Vector<Object> updatingClusteringKey, Vector<Object> updatingValues, Vector<Object> existingTuples ){
			
			boolean flag = false;
			
			for(int i = 0; i < updatingValues.size(); i++){
				if(updatingClusteringKey.get(0).equals(existingTuples.get(i))) {
					flag = true;
					System.out.print("equal");
					return flag;
				}
				else {
					flag = false;
				}
			}
			return flag;
		}

	public void removeTupleFromPage(Tuple t) throws IOException, ClassNotFoundException {
		if(tupleStorage.contains(t)){
			tupleStorage.remove(t);
			occupiedLength--;
			SerializePage();
		}
	}
	
	public Pages createNewPage() throws ClassNotFoundException, IOException {
		
		pageNumber++;
		Pages page1 = new Pages(0, path);
		return page1;
		
	}
	

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		
		/*Pages page2 = new Pages(0);
		
		//System.out.print(page2.tupleStorage);
		
		Tuple tr = new Tuple();
		Vector<Object> input = new Vector<Object>();
		input.add("1232");
		input.add("5678");
		input.add("9778");
		tr.tuples.add(input);
		
		Tuple sr = new Tuple();
		Vector<Object> input1 = new Vector<Object>();
		input.add("1232");
		input.add("5678");
		input.add("9778");
		sr.tuples.add(input1);
		
		Tuple dr = new Tuple();
		Vector<Object> input2 = new Vector<Object>();
		input.add("1232");
		input.add("5678");
		input.add("9778");
		dr.tuples.add(input2);
		
		page2.insertIntoPages(tr);
		page2.insertIntoPages(sr);
		page2.insertIntoPages(dr);
				
		//System.out.print(tr.tuples.toString());
		

		//System.out.print(page2.tupleStorage);
		*/
	
	}
	
	    	
}