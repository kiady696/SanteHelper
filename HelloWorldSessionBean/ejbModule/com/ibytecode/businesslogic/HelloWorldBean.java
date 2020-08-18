package com.ibytecode.businesslogic;

import org.bson.Document;

import com.ibytecode.business.AnalyseMongo;
import com.ibytecode.business.HelloWorld;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Iterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;




/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@LocalBean
public class HelloWorldBean implements HelloWorld {


	
	
    /**
     * Default constructor. 
     */
    public HelloWorldBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String sayHello() {
		return "Hello World !!!";
	}
    
    public String getDifference(String idAnalyse) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	try {
    		analyse.getDifference(idAnalyse);
    		return "{\"status\":\"success\",\"message\":\"getDifference success\"}";
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "{\"status\":\"success\",\"message\":\"getDifference success\"}";
    	}
    }
    
    
    
    
    
    public String updateAnalyse(String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	try {
    		analyse.updateAnalyse(designation, tension, glycemie, bcm, fer, magnesium);
    		return "{\"status\":\"success\",\"message\":\"updateAnalyse success\"}";
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "{\"status\":\"success\",\"message\":\"create Analyse fail\"}";
    	}
    	
    }
    
    public String createAnalyse(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	try {
    		analyse.createAnalyse(id, designation, tension, glycemie, bcm, fer, magnesium);
    		return "{\"status\":\"success\",\"message\":\"create Analyse success\"}";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "{\"status\":\"success\",\"message\":\"create Analyse failed\"}";
    	}
    }
    
    public String updateMaladie(int id, String designation,double tension,double glycemie,double bcm,double fer,double magnesium) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	try {
    		analyse.updateMaladie(id,designation, tension, glycemie, bcm, fer, magnesium);
    		return "{\"status\":\"success\",\"message\":\"update successful\"}";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "{\"status\":\"success\",\"message\":\"update failed\"}";
    	}
    	
    }
    public Object[]getSomeDataMongo()
    {
    	AnalyseMongo analyse = new AnalyseMongo();
    	Object[]retour=analyse.getSomeDataFromMongo();
    	return retour;
    }
    public String getChart() {
    	AnalyseMongo analyse = new AnalyseMongo();
    	return analyse.getChart();
    }
    public Object getOneDataFromMongo(String idMaladie) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	return analyse.getOneDataFromMongo(idMaladie);
    }
    public String getOneChart(String idMaladie) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	return analyse.getOneChart(idMaladie);
    }
    public String selectOne(int id,String nomTable) {
    	AnalyseMongo analyse = new AnalyseMongo();
    	return analyse.selectOne(id, nomTable);
    }
    




}
