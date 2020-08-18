db.composants.insert({idCompo: "compo_001",nom: "globRouges", tauxNormale: 0.45, unite:"m/ml", ref: true});
db.composants.insert({idCompo: "compo_002",nom: "hemoglobine", tauxNormale: 0.45, unite:"m/ml"});
db.composants.insert({idCompo: "compo_003",nom: "hematocrite", tauxNormale: 0.30, unite:"g/l"});
db.composants.insert({idCompo: "compo_004",nom: "globBlancs", tauxNormale: 0.10, unite:"g/l"});
db.composants.insert({idCompo: "compo_005",nom: "plaquettes", tauxNormale: 0.20, unite:"g/l"});
db.composants.insert({idCompo: "compo_006",nom: "ferritine", tauxNormale: 0.20, unite:"g/l"});
db.composants.insert({idCompo: "compo_007",nom: "gamma GT", tauxNormale: 0.20, unite:"g/l"});

db.users.insert({idUs: "us_001", nom: "Ben", dateNaiss: new Date("2000-03-18"), sexe:"f"});
db.users.insert({idUs: "us_002",nom: "William", dateNaiss: new Date("1990-09-19"), sexe:"m"});
db.users.insert({idUs: "us_003",nom: "Guy", dateNaiss: new Date("1987-01-20"), sexe:"m"});

db.maladies.insert({ 
		idMal: "mal_001",
		libelle: "Alzeihmer",
	 	valeurs: [
				{idCompo: "compo_001", valeur: 0.20, ref: true},
				{idCompo: "compo_002", valeur: 0.30},
				{idCompo: "compo_003", valeur: 0.10},
				{idCompo: "compo_004", valeur: 0.10},
				{idCompo: "compo_005", valeur: 0.10},
				{idCompo: "compo_006", valeur: 0.10},
				{idCompo: "compo_007", valeur: 0.10},
			] });

db.maladies.insert({ 	
		idMal: "mal_002",
		libelle: "Acne",
			valeurs: [
				{idCompo: "compo_001", valeur: 0.90, ref: true},
				{idCompo: "compo_002", valeur: 0.10},
				{idCompo: "compo_003", valeur: 0.25},
				{idCompo: "compo_004", valeur: 0.10},
				{idCompo: "compo_005", valeur: 0.5},
				{idCompo: "compo_006", valeur: 0.3},
				{idCompo: "compo_007", valeur: 0.6},
			]
		}	);

db.maladies.insert({
		idMal: "mal_003",
		libelle: "Asthme",
			valeurs: [
				{idCompo: "compo_001", valeur: 0.30, ref: true},
				{idCompo: "compo_002", valeur: 0.10},
				{idCompo: "compo_003", valeur: 0.30},
				{idCompo: "compo_004", valeur: 0.10},
				{idCompo: "compo_005", valeur: 0},
				{idCompo: "compo_006", valeur: 0.12},
				{idCompo: "compo_007", valeur: 0.19},
			]
		});

db.analyses.insert({
			idAn: "an_001",
			user: {idUs: "us_001", nom: "Ben", dateNaiss: new Date("2000-03-18"), sexe:"f"}, 
			dateAnalyse: new Date("2020-02-14"),
			valCompo: [
				{idCompo: "compo_001", valeur: 20.99},
				{idCompo: "compo_002", valeur: 30.00},
				{idCompo: "compo_003", valeur: 15.00},
				{idCompo: "compo_004", valeur: 10.00},
				{idCompo: "compo_005", valeur: 15.05},
				{idCompo: "compo_006", valeur: 12},
				{idCompo: "compo_007", valeur: 19}
		]});


db.analyses.insert({
			idAn: "an_002",
			user: {idUs: "us_002",nom: "William", dateNaiss: new Date("1990-09-19"), sexe:"m"},
			dateAnalyse: new Date("2020-02-15"),
			valCompo: [
				{idCompo: "compo_001", valeur: 50.99},
				{idCompo: "compo_002", valeur: 50.00},
				{idCompo: "compo_003", valeur: 35.00},
				{idCompo: "compo_004", valeur: -10.00},
				{idCompo: "compo_005", valeur: 7.05},
				{idCompo: "compo_006", valeur: 18},
				{idCompo: "compo_007", valeur: 29}
		]});

db.analyses.insert({
			idAn: "an_003",
			user: {idUs: "us_003",nom: "Guy", dateNaiss: new Date("1987-01-20"), sexe:"m"}, 
			dateAnalyse: new Date("2020-02-16"),
			valCompo: [
				{idCompo: "compo_001",valeur: 15.99},
				{idCompo: "compo_002",valeur: 30.00},
				{idCompo: "compo_003",valeur: 45.00},
				{idCompo: "compo_004",valeur: 25.00},
				{idCompo: "compo_005",valeur: 19.05},
				{idCompo: "compo_006", valeur: 15},
				{idCompo: "compo_007", valeur: 45}
			]
		});
