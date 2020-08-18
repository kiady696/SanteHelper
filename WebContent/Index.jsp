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
    <div>
        Température: <input type="text" id="Température (°C)"><br>
        Taux de globlule Rouge: <input type="text" id="Taux de globlule Rouge"><br>
        Taux de globule blanc: <input type="text" id="Taux de globule blanc"><br>
        Sucre: <input type="text" id="Sucre"><br>
        Lipide: <input type="text" id="Lipide"><br>
        Glucide: <input type="text" id="Glucide"><br>
        Taux oxygène sanguin: <input type="text" id="Taux oxygène sanguin"><br>
        Tension arterielle: <input type="text" id="Tension arterielle"><br>
        Battement de couer (bpm): <input type="text" id="Battement de couer (bpm)"><br>
        Poids: <input type="text" id="Poids"><br>
    </div>
    <button onclick="add()">Add data</button>
    <script src="test.js"></script>
</body>
</html>