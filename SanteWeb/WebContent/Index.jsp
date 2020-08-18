<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>chart</title>
</head>
<body>
    <script src="chart.js"></script>
    <script src="chartjs-plugin-draggable.min.js"></script>
    <canvas id = "myChart" height="400" width="800">   </canvas>
    
    <h2>Add Analyse</h2>
    <div>
    	<form action="CreateAnalyse" method="GET">
	        Patient N�: <input type="text" name="id"><br>
	        Designation Analyse: <input type="text" name="designation"><br>
	        
	        <input type="submit" value="Add Analyse"></input>
        </form>
    
    
    </div>
    
    <h2>Add maladie</h2>
    <div>
        Battements de coeur/Minutes: <input type="text" id="Température (°C)"><br>
        Glycemie: <input type="text" id="Taux de globlule Rouge"><br>
        Fer: <input type="text" id="Taux de globule blanc"><br>
       	Magnesium: <input type="text" id="Sucre"><br>
        Tension: <input type="text" id="Lipide"><br>
        
    </div>
    <button onclick="add()">Add data</button>
    <script src="test.js"></script>
</body>
</html>