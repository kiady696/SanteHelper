package com.ibytecode.business;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class AnalyseMongo {

	
	public double[] getProportionSante() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		FindIterable<Document> row = db.getCollection("ProportionsSante").find();
		//cle,valeur retours
		double[] retour = new double[6];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");
			retour[5] = (double)r.get("Tahina");

			System.out.println("les proportions Sante (une ligne) :"+r);

		}
		mongoClient.close();
		return retour;
	}
	
	public double[] getProportionMaladieVaovao(String idProp) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> table = db.getCollection("ProportionsMaladies");
		double idpropMaladie = Double.parseDouble(idProp);
		FindIterable<Document> row = table.find(new Document("id",idpropMaladie));
		//cle,valeur retours
		double[] retour = new double[11];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("tensionMax");

			retour[2] = (double)r.get("glycemie");
			retour[3] = (double)r.get("glycemieMax");

			retour[4] = (double)r.get("bcm");
			retour[5] = (double)r.get("bcmMax");

			retour[6] = (double)r.get("fer");
			retour[7] = (double)r.get("ferMax");

			retour[8] = (double)r.get("magnesium");
			retour[9] = (double)r.get("magnesiumMax");

			retour[10] = (double)r.get("Tahina");

			System.out.println("tokony mipotra eto ny ligne IRAY propMaladie");

			System.out.println(r);
		}
		mongoClient.close();
		return retour;
	}
	
	
	
	public double[] getProportionMaladie(String idProp) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> table = db.getCollection("ProportionsMaladies");
		double idpropMaladie = Double.parseDouble(idProp);
		FindIterable<Document> row = table.find(new Document("id",idpropMaladie));
		//cle,valeur retours
		double[] retour = new double[6];
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");
			retour[5] = (double)r.get("Tahina");

			System.out.println("tokony mipotra eto ny ligne IRAY propMaladie");

			System.out.println(r);
		}
		mongoClient.close();
		return retour;
	}
	
	public double[] setPropAnalyse(double[] analyse) {
		double diviseur = analyse[5];
		double[] propAnalyse = new double[analyse.length];
		for(int i=0;i<analyse.length;i++) {
			propAnalyse[i] = (analyse[i]/diviseur);
		}
		return propAnalyse;
	}
	
	
	public double getDiffAnalyseMaladieVaovao(double[] propAnalyse,double[] proportionMaladieVaovao) {
		double ret = 0;
		int j = 0;
		for(int i = 0;i<(proportionMaladieVaovao.length-1);i=i+2) {
			double min = proportionMaladieVaovao[i];
			double max = proportionMaladieVaovao[i+1];
			double diff = 0;
			if(propAnalyse[j]<min) {
				diff = min - propAnalyse[j];
			}else if(propAnalyse[j]>=min && propAnalyse[j]<=max) {
				diff = 0;
			}else if(propAnalyse[j]>max) {
				diff = propAnalyse[j]-max;
			}
			j++;
			ret+=diff;
			
		}
		ret = ret / propAnalyse.length;
		
		return ret;
	}
	
	
	public double getDiffAnalyseMaladie(double[] propAnalyse,double[] proportionMaladie) {
		double ret = 0;
		
		for(int i=0;i<propAnalyse.length;i++) {
			double diff = propAnalyse[i]-proportionMaladie[i];
			if(diff<0) {
				ret-=diff;
			}else {
				ret+=diff;
			}
			
			System.out.println(diff);
		}
		ret = ret / propAnalyse.length;
		return ret;
	}
	
	public String[][] getAllIdPropMaladie() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		FindIterable<Document> row = db.getCollection("ProportionsMaladies").find();
		//cle,valeur retours
		Vector<String> lesIds = new Vector<String>();
		for(Document r:row) {
			lesIds.add(""+r.get("id")+"-"+r.get("designation"));
			System.out.println("ID REHETRA: "+r);
			
		}
		String[][] ret = new String[lesIds.size()][2];
		
		for(int j=0;j<lesIds.size();j++) {
			ret[j] = lesIds.get(j).split("-");
			System.out.println("ID RET: "+ret[j][0]);
		}
		
		mongoClient.close();
		return ret;
	}
	
	public String getDifferenceVaovao(String idAnalyse) {
		String[][] lesIdsPropMaladies = this.getAllIdPropMaladie();  //MAMERINA
		double[] propSante = this.getProportionSante();       ///MAMERINA
		double[] analyse = this.getAnalyse(idAnalyse);
		double[] propAnalyse = this.setPropAnalyse(analyse);
		double diffSanteAnalyse = this.getDiffAnalyseMaladie(propAnalyse, propSante);
		String json="[";
		
		for(int i=0;i<lesIdsPropMaladies.length;i++) {
			double[] propMaladie = this.getProportionMaladieVaovao(lesIdsPropMaladies[i][0]);            //MAMERINA
			double diffMaladieAnalyse = this.getDiffAnalyseMaladieVaovao(propAnalyse, propMaladie);
			System.out.println(diffMaladieAnalyse);

			double pourcentage = (diffSanteAnalyse/(diffSanteAnalyse+diffMaladieAnalyse))*100;
			System.out.println(pourcentage);
			String jsonMaladie="{";
			jsonMaladie+="\"id\":"+"\""+lesIdsPropMaladies[i][0]+"\",";
			jsonMaladie += "\"designation\" : "+"\""+lesIdsPropMaladies[i][1]+"\",";
			jsonMaladie += "\"pourcentage\" : "+pourcentage;
			jsonMaladie+="}";
			json+=jsonMaladie;
			if(i!=lesIdsPropMaladies.length-1) {
				json+=",";
			}
		}
		json+="]";
		json="{"
				+ "\"status\":\"succes\","
				+"\"data\":"+json+","+"\"message\":\"yeeae\""+"}";
		return json;
		
		
	}
	
	
	public String getDifference(String idAnalyse) {
		String[][] lesIdsPropMaladies = this.getAllIdPropMaladie();  //MAMERINA
		double[] propSante = this.getProportionSante();       ///MAMERINA
		double[] analyse = this.getAnalyse(idAnalyse);
		double[] propAnalyse = this.setPropAnalyse(analyse);
		double diffSanteAnalyse = this.getDiffAnalyseMaladie(propAnalyse, propSante);
		String json="[";
		
		for(int i=0;i<lesIdsPropMaladies.length;i++) {
			double[] propMaladie = this.getProportionMaladie(lesIdsPropMaladies[i][0]);            //MAMERINA
			double diffMaladieAnalyse = this.getDiffAnalyseMaladie(propAnalyse, propMaladie);
			System.out.println(diffMaladieAnalyse);

			double pourcentage = (diffSanteAnalyse/(diffSanteAnalyse+diffMaladieAnalyse))*100;
			System.out.println(pourcentage);
			String jsonMaladie="{";
			jsonMaladie+="\"id\":"+"\""+lesIdsPropMaladies[i][0]+"\",";
			jsonMaladie += "\"designation\" : "+"\""+lesIdsPropMaladies[i][1]+"\",";
			jsonMaladie += "\"pourcentage\" : "+pourcentage;
			jsonMaladie+="}";
			json+=jsonMaladie;
			if(i!=lesIdsPropMaladies.length-1) {
				json+=",";
			}
		}
		json+="]";
		json="{"
				+ "\"status\":\"succes\","
				+"\"data\":"+json+","+"\"message\":\"yeeae\""+"}";
		return json;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateAnalyse(String designation,double tension,double glycemie,double bcm,double fer,double magnesium,double Tahina) {
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
		mal.append("Tahina", Tahina);

		table.updateOne(new Document("designation",designation), new Document("$set",mal));
		mongoClient.close();
	}
	
	public void createAnalyse(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium,double Tahina) {
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
		ana.append("Tahina", Tahina);
		table.insertOne(ana);
		mongoClient.close();
	}
	
	public void updateMaladie(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium,double Tahina) {
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
		mal.append("Tahina", Tahina);
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
		double[] retour = new double[6];
		try {
		
		MongoDatabase db = mongoClient.getDatabase("test");
		double id = Double.parseDouble(idAnalyse);
		FindIterable<Document> row = db.getCollection("Analyse").find(new Document("id",id));
		//cle,valeur retours
		
		for(Document r:row) {
			retour[0] = (double)r.get("tension");
			retour[1] = (double)r.get("glycemie");
			retour[2] = (double)r.get("bcm");
			retour[3] = (double)r.get("fer");
			retour[4] = (double)r.get("magnesium");
			retour[5] = (double)r.get("Tahina");

			System.out.println("ANALYSE UNE LIGNE: "+r);
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
