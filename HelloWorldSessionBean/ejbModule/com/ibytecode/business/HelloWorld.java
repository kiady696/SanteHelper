package com.ibytecode.business;

import javax.ejb.Remote;

@Remote
public interface HelloWorld {
	public String sayHello();
	public Object[]getSomeDataMongo();
	public String getChart();
	public Object getOneDataFromMongo(String idMaladie);
	public String getOneChart(String idMaladie);
	public String selectOne(int id,String nomTable);
	public String updateMaladie(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium);
	public String createAnalyse(int id,String designation,double tension,double glycemie,double bcm,double fer,double magnesium);
	public String updateAnalyse(String designation,double tension,double glycemie,double bcm,double fer,double magnesium);
	
	public String getDifference(String idAnalyse);
}
