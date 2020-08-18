package com.ibytecode.business;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class AnalyseMongo {
	
	
	public double[] getProportionSante() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		FindIterable<Document> row = db.getCollection("ProportionsSante").find();
		//cle,valeur retours
		double[] retour = new double[5];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");

		}
		mongoClient.close();
		return retour;
	}
	
	public double[] getProportionMaladie() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		FindIterable<Document> row = db.getCollection("ProportionsMaladies").find();
		//cle,valeur retours
		double[] retour = new double[5];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");

		}
		mongoClient.close();
		return retour;
	}
	
	public double[] getDifference(String idAnalyse) {
		double [] differences = null; //tokony ho 15 (3 maladies)
		double [] moyennes = null; //tokony ho 5 (moyenne des diff pour 3 maladies)
		double [] analyse = this.getAnalyse(idAnalyse);
		//calcul proportion analyse
		double diviseur = analyse[0];
		double[] propAnalyse = new double[analyse.length];
		for(int i=0;i<analyse.length;i++) {
			propAnalyse[i] = (analyse[i]/diviseur);
		}
		//getProportionMaladie
		double[] propMaladies = this.getProportionMaladie();
		//getProportionSante
		double[] propSante = this.getProportionSante();
		for(int i=0;i<propMaladies.length;i++) {
			differences[i] = Math.abs(propAnalyse[i]-propMaladies[i]);
			//atao moyenne
			
		}
		System.out.println("la longueur des différences: "+differences.length);
		
		return differences;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateAnalyse(String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
		System.out.println("niditra update");
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> table = database.getCollection("Analyse");
		Document mal = new Document();
		
		mal.append("tension", tension);
		mal.append("glycemie", glycemie);
		mal.append("bcm", bcm);
		mal.append("fer", fer);
		mal.append("magnesium", magnesium);
		table.updateOne(new Document("designation",designation), new Document("$set",mal));
		mongoClient.close();
	}
	
	public void createAnalyse(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> table = database.getCollection("Analyse");
		Document ana = new Document();
		ana.append("id", id);
		ana.append("designation", designation);
		ana.append("tension", tension);
		ana.append("glycemie", glycemie);
		ana.append("bcm", bcm);
		ana.append("fer", fer);
		ana.append("magnesium", magnesium);
		table.insertOne(ana);
		mongoClient.close();
	}
	
	public void updateMaladie(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
		System.out.println("niditra update");
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> table = database.getCollection("Maladies");
		Document mal = new Document();
		mal.append("designation", designation);
		mal.append("tension", tension);
		mal.append("glycemie", glycemie);
		mal.append("bcm", bcm);
		mal.append("fer", fer);
		mal.append("magnesium", magnesium);
		table.updateOne(new Document("designation",designation), new Document("$set",mal));
		mongoClient.close();
	}
	
	public double[] getDisease(String idMaladie) {  //Fonction maka ny maladie
		System.out.println("les maladies");
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("Sante");
		FindIterable<Document> row = db.getCollection("Maladies").find(new Document("id",new Document("eq",idMaladie)));
		//cle,valeur retours
		double[] retour = new double[5];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");

		}
		mongoClient.close();
		return retour;
	}
	
	public double[] getAnalyse(String idAnalyse) {  //Fonction maka ny maladie
		System.out.println("les maladies");
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		double[] retour = new double[5];
		try {
		
		MongoDatabase db = mongoClient.getDatabase("test");
		FindIterable<Document> row = db.getCollection("Analyse").find(new Document("id",new Document("eq",idAnalyse)));
		//cle,valeur retours
		
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");

		}
	}catch(Exception e) {
		
	}finally {
		mongoClient.close();
	}
		
		return retour;
	}
	
	
	    
	    public Object[] getSomeDataFromMongo() {
	    	MongoClient mongo = new MongoClient( "localhost" , 27017 );
	    	Object[] res = null;
	    	ArrayList al=new ArrayList();
	  	  try {
	  	      // Creating Credentials 
	  	      MongoCredential credential; 
	  	      credential = MongoCredential.createCredential("sampleUser", "myDb", 
	  	         "password".toCharArray()); 
	  	      System.out.println("Connected to the database successfully");  
	  	      
	  	      // Accessing the database 
	  	      MongoDatabase database = mongo.getDatabase("test");  
	  	      
	  	   // Retrieving a collection 
	  	      MongoCollection<Document> collection = database.getCollection("Maladies");
	  	      System.out.println("Collection de MALADIES selected successfully"); 

	  	      // Getting the iterable object 
	  	      FindIterable<Document> iterDoc = collection.find(); 
	  	      int i = 1; 

	  	      // Getting the iterator 
	  	      Iterator it = iterDoc.iterator(); 
	  	      
	  	      
	  	      
	  	      while (it.hasNext()) {  
	  	         Object m=(it.next());  
	  	         System.out.println(m);
	  	         al.add(m);
	  	         i++; 
	  	      }
	  	      res=al.toArray();
	  	      
	  	   }catch(Exception ex) {
	  		   ex.printStackTrace();
	  	   }finally {
	  		   mongo.close();
	  	   }
	  	  return res;
	  	  
	    }
	    
	    public Object getOneDataFromMongo(String idMaladie) {
	    	MongoClient mongo = new MongoClient( "localhost" , 27017 );
	    	Object res = null;
	  	  try {
	  	      // Creating Credentials 
	  	      MongoCredential credential; 
	  	      credential = MongoCredential.createCredential("sampleUser", "myDb", 
	  	         "password".toCharArray()); 
	  	      System.out.println("Connected to the database successfully");  
	  	      
	  	      // Accessing the database 
	  	      MongoDatabase database = mongo.getDatabase("test");  
	  	      
	  	   // Retrieving a collection 
	  	      MongoCollection<Document> collection = database.getCollection("Maladies");
	  	      System.out.println("Collection de MALADIE selected successfully"); 

	  	      // Getting the iterable object 
	  	      int id = Integer.parseInt(idMaladie);
	  	      FindIterable<Document> iterDoc = collection.find(new Document("id",id)); 
	  	      
	  	      int i = 1; 

	  	      // Getting the iterator 
	  	      Iterator it = iterDoc.iterator(); 
	  	      
	  	      
	  	      
	  	      if(it.hasNext()) {  
	  	         Object m=(it.next());  
	  	         System.out.println(m);
	  	         res = m;
	  	        
	  	      }
	  	      
	  	      
	  	   }catch(Exception ex) {
	  		   ex.printStackTrace();
	  	   }finally {
	  		   mongo.close();
	  	   }
	  	  return res;
	  	  
	    }
	    
	    public void insertIntoMongo(Object AnalyseDuChart) {
	    	
	    }
	    
	    public String selectOne(int id,String nomTable) {
	    	String json="[";
	    	MongoClient mongoClient = new MongoClient("localhost",27017);
	    	try {
	    		MongoDatabase database = mongoClient.getDatabase("test");
	    		MongoCollection<Document> collection = database.getCollection(nomTable);
	    		FindIterable<Document> row = collection.find(new Document("id",id));
	    		for(Document r:row) {
	    			json+=r.toJson();
	    			System.out.println(r);
	    		}
	    		json+="]";
	    		json="{"
	    				+ "\"status\":\"succes\","
	    				+"\"data\":"+json+","+"\"message\":\"yeeae\""+"}";
	    		
	    		
	    				
	    		
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		json="{\"status\":\"error\", \"data\":null,\"message\":\"misy diso oo:\""+e.getMessage()+"\"}";
	    	}finally {
	    		mongoClient.close();
	    	}
	    	return json;
	    }
	    
	    public String getOneChart(String idMaladie) {
	    	String json="[";
	    	Object p=this.getOneDataFromMongo(idMaladie);
	    	if(p!=null){
	    		Document doc=(Document)p;
	    		json+=""+doc.toJson();
	    	}
	    	json+="]";
	    	return json;
	    	
	    }
	    public String getChart() {
	    	String json="[";
	    	try {
		    	Object[]p=getSomeDataFromMongo();
		    	for(int i=0;i<p.length;i++) {
		    		Document doc=(Document)p[i];
		    		json+=""+doc.toJson();
		    		if(i!=p.length-1) {
		    			json+=",";
		    		}
		    	}
		    	json+="]";
		    	json="{"
	    				+ "\"status\":\"succes\","
	    				+"\"data\":"+json+","+"\"message\":\"yeeae\""+"}";
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		json="{\"status\":\"error\", \"data\":null,\"message\":\"misy diso oo:\""+e.getMessage()+"\"}";
	    	}
	    	return json;
	    }
	
}
