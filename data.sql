use.Sante;

db.Maladies.insertOne({id:1,designation:"hypertension",tension:80,glycemie:30,bcm:24,fer:15,magnesium:10});
db.Maladies.insertOne({id:2,designation:"dysplenie",tension:70,glycemie:20,bcm:10,fer:5,magnesium:20});
db.Maladies.insertOne({id:3,designation:"hypothermie",tension:10,glycemie:15,bcm:20,fer:14,magnesium:6});


db.Analyse.insertOne({id:1,designation:"A1",tension:20,glycemie:30,bcm:17,fer:22,magnesium:33});
db.Analyse.insertOne({id:2,designation:"A2",tension:34,glycemie:27,bcm:23,fer:10,magnesium:26});
db.Analyse.insertOne({id:3,designation:"A3",tension:48,glycemie:10,bcm:18,fer:17,magnesium:2});

db.ProportionsMaladies.insertOne({id:1,designation:"hypertension",tension:3,glycemie:2,bcm:1.5,fer:3,magnesium:1});
db.ProportionsMaladies.insertOne({id:2,designation:"dysplenie",tension:2,glycemie:2.5,bcm:3,fer:2,magnesium:3});
db.ProportionsMaladies.insertOne({id:3,designation:"hypothermie",tension:1,glycemie:2,bcm:2,fer:1.5,magnesium:2.5});

