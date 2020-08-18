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
    <canvas id = "myChart" height="400" width="800">
      
    </canvas>
    <br/>
    <br/>
    <button onclick="getPourcentage()"  >
    	Voir pourcentages
    </button>
    
    
    <script type="text/javascript">
		var idAnalyse=<%out.println(request.getParameter("id"));%>;
    </script>
    
    
  
    <script src="analyse.js"></script>
    
</body>
</html>