let maladie=[];

		
function updateMaladie(index,analyse)  
{  
   var url = "http://localhost:8080/SanteWeb/ModifMaladie?id="+index+1;
   url+="&designation="+analyse.label+"&tension="+analyse.data[4]+"&fer="+analyse.data[1]+"&glycemie="+analyse.data[2]+"&magnesium="+analyse.data[3]+"&bcm="+analyse.data[0]+"";
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

function load(chart)  
{  
   var url = "http://localhost:8080/SanteWeb/Kiady";//use any url that have json data  
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
       
        for(var i=0;i<jsonObj.data.length;i++){
        	
        	var color="rgba(66,0,0,0.2)";
        	var data=[jsonObj.data[i].bcm,jsonObj.data[i].fer,jsonObj.data[i].glycemie,jsonObj.data[i].magnesium,jsonObj.data[i].tension];
        	var label=jsonObj.data[i].designation;
        	
        	addData(chart,label,color,data)
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
    labels: ["Battements de coeur/ Minutes","Fer","Glycemie","Magnesium","Tension"],
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
        
        updateMaladie(index,marksData.datasets[datasetIndex]);
        //code mapiditra anle donnee anaty base 



        console.log(datasetIndex,index,value)

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

function add() {
 var note = [];
 marksData.labels.forEach(element => {

     var lab = document.getElementById(element).value;
    note.push(lab);
 });
    marksData.datasets.push(
        {
            label:"haja",
            backgroundColor: "rgba(106,187,216,0.2)",
            data: note
        }
    )
 //   chart.data.push(marksData);
    chart.update();
}
load(chart)