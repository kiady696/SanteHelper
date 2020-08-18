use.Sante;

db.Maladies.insertOne({id:1,designation:"hypertension",tension:80,glycemie:30,bcm:24,fer:15,magnesium:10,Tahina:45});
db.Maladies.insertOne({id:2,designation:"dysplenie",tension:70,glycemie:20,bcm:10,fer:5,magnesium:20,Tahina:45});
db.Maladies.insertOne({id:3,designation:"Analyse Rabe" ,tension:100,glycemie:100,bcm:100,fer:100,magnesium:100,Tahina:45});
db.Maladies.insertOne({id:4,designation:"Analyse Rakoto" ,tension:100,glycemie:100,bcm:100,fer:100,magnesium:100,Tahina:45});

db.Maladies.drop();

db.Analyse.insertOne({id:1,designation:"A1",tension:20,glycemie:30,bcm:17,fer:22,magnesium:33,Tahina:14});

db.Analyse.drop();

db.ProportionsMaladies.insertOne({id:1,designation:"hypertension",tension:3,tensionMax:4,glycemie:2,glycemieMax:3,bcm:1.5,bcmMax:2.5,fer:3,ferMax:4,magnesium:1,magnesiumMax:2,Tahina:1});
db.ProportionsMaladies.insertOne({id:2,designation:"dysplenie",tension:2,tensionMax:3,glycemie:2.5,glycemieMax:3.5,bcm:3,bcmMax:4,fer:2,ferMax:3,magnesium:3,magnesiumMax:4,Tahina:1});
db.ProportionsMaladies.insertOne({id:3,designation:"hypothermie",tension:1,tensionMax:2,glycemie:2,glycemieMax:3,bcm:2,bcmMax:3,fer:1.5,ferMax:2.5,magnesium:2.5,magnesiumMax:3.5,Tahina:1});

db.ProportionsMaladies.drop();

db.ProportionsSante.insertOne({id:1,designation:"sain",tension:1,glycemie:1,bcm:1,fer:1,magnesium:1,Tahina:1});

db.ProportionsSante.drop();