//MALADIES
let maladies=[];
var mals="";

function loadMaladies(){
	var url = "http://localhost:8080/SanteWebClient/Maladies";//use any url that have json data  
	   var request;  
	   
	  
	   if(window.XMLHttpRequest){    
	    request=new XMLHttpRequest();//for Chrome, mozilla etc  
	   }    
	   else if(window.ActiveXObject){    
	    request=new ActiveXObject("Microsoft.XMLHTTP");//for IE only  
	   }    
	   request.onreadystatechange  = function(){  
	      if (request.readyState == 4  )  
	      {  
	        var jsonObj = JSON.parse(request.responseText);//JSON.parse() returns JSON object  
	       
	        for(var j=0;j<jsonObj.donnees.length;j++){
	        	
	        	maladies.push(jsonObj.donnees[j]);
	        	mals+="-"+jsonObj.donnees[j].id;
	        }
	        mals=mals.substring(1);
	        maladies.push({id:0,designation:"Non malade"})
	      }  
	   }  
	   request.open("GET", url, true);  
	   request.send();  
}


function resultAnalyse()  
{  
   var url = "http://localhost:8080/SanteWebClient/Hello?id="+idPrel+"&mals="+mals;
   //use any url that have json data  
   var request;  
     
   if(window.XMLHttpRequest){    
    request=new XMLHttpRequest();//for Chrome, mozilla etc  
   }    
   else if(window.ActiveXObject){    
    request=new ActiveXObject("Microsoft.XMLHTTP");//for IE only  
   }    
   request.onreadystatechange  = function(){  
      if (request.readyState == 4  )  
      {  
        var jsonObj = JSON.parse(request.responseText);//JSON.parse() returns JSON object  
        if(jsonObj.status=="success"){
        	document.getElementById("analyseResult").innerHTML="<h1>Resultats</h1><br><ul>";
        	for(var i=0;i<jsonObj.donnees.length;i++){
        		document.getElementById("analyseResult").innerHTML+="<li>"+maladies[i].designation+":"+jsonObj.donnees[i].pourcentage+"%</li>";
        	}
        	document.getElementById("analyseResult").innerHTML+="</ul>";
        	
        }else{
        	console.log(jsonObj.status)
        }
        
        
      }  
   }  
   request.open("GET", url, true);  
   request.send();  
}  
		


function updateAnalyse(analyse)  
{  
   var url = "http://localhost:8080/SanteWebClient/PrelevementModif?id="+idPrel;
   url+="&glycemie="+analyse.data[0]+"&hemoglobine="+analyse.data[1]+"&GB="+analyse.data[2]+"&acides="+analyse.data[3]+"&chol="+analyse.data[4]+"";
   //use any url that have json data  
   var request;  
     
   if(window.XMLHttpRequest){    
    request=new XMLHttpRequest();//for Chrome, mozilla etc  
   }    
   else if(window.ActiveXObject){    
    request=new ActiveXObject("Microsoft.XMLHTTP");//for IE only  
   }    
   request.onreadystatechange  = function(){  
      if (request.readyState == 4  )  
      {  
        var jsonObj = JSON.parse(request.responseText);//JSON.parse() returns JSON object  
        if(jsonObj.status=="success"){
        	console.log("ok")
        }else{
        	console.log(jsonObj.status)
        }
        
        
      }  
   }  
   request.open("GET", url, true);  
   request.send();  
}  
		

function loadAnalyse(chart)  
{  
   var url = "http://localhost:8080/SanteWebClient/PrelevementController?id="+idPrel;//use any url that have json data  
   var request;  
     
   if(window.XMLHttpRequest){    
    request=new XMLHttpRequest();//for Chrome, mozilla etc  
   }    
   else if(window.ActiveXObject){    
    request=new ActiveXObject("Microsoft.XMLHTTP");//for IE only  
   }    
   request.onreadystatechange  = function(){  
      if (request.readyState == 4  )  
      {  
        var jsonObj = JSON.parse(request.responseText);//JSON.parse() returns JSON object  
       
        for(var i=0;i<jsonObj.donnees.length;i++){
        	
        	var color="rgba(200,0,0,0.2)";
        	var data=[jsonObj.donnees[i].glycemie,jsonObj.donnees[i].hemoglobine,jsonObj.donnees[i].gloublesBlanches,jsonObj.donnees[i].acides,jsonObj.donnees[i].cholesterol];
        	var label=jsonObj.donnees[i].designation;
        	
        	addData(chart,label,color,data);
        	
        }
        
      }  
   }  
   request.open("GET", url, true);  
   request.send();  
}  

function addData(chart, label, color, data) {
	  chart.data.datasets.push({
	    label: label,
	    backgroundColor: color,
	    data: data
	  });
	  chart.update();
	}

var ctx = document.getElementById('myChart').getContext('2d');
var marksData = {
    labels: ["Glycemie","Hemoglobine","Globules Blanches","Acides","Cholesterol"],
    datasets: []
  };
console.log(marksData.datasets);
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'radar',
    // The data for our dataset
    data: marksData,
    options:{
      dragData: true,
      dragDataRound:0,
      onDragEnd: function(e,datasetIndex,index,value){
        
        
        //code mapiditra anle donnee anaty base 



        console.log(datasetIndex,index,value)
        console.log(marksData.datasets[datasetIndex].data)
        updateAnalyse(marksData.datasets[datasetIndex])
      },
      scale: {
        ticks: {
          max: 100,
          min: 0,
          stepSize: 10
        },
        gridLines: {
          display: false
        },
        responsive: true,
        maintainAspectRatio: false
      }
    }
});

function analyser(){
	resultAnalyse();
}

loadAnalyse(chart);
loadMaladies();