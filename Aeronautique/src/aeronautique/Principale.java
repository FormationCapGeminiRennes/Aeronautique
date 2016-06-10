package aeronautique;

import controleur.Controleur;
import dao.AvionDAO;
import dao.Connexion;

public class Principale {

	public static void main(String[] args) {
		
		
		
		new Controleur();		
		
		/*
		Connexion.afficheSelectEtoile("Avion",null);
		// Seul l'id compte
		//Avion avion = new Avion(-1, "airbus 320", "Caen", 350);
		//(new AvionDAO()).delete(avion);
		//(new AvionDAO()).create(avion);
		System.out.println(avion);
		Avion avion=(new AvionDAO()).find(5);
		System.out.println(avion);
		//Connexion.afficheSelectEtoile("Avion");		
		//Connexion.afficheSelectEtoile("Vol");
		System.out.println(Connexion.getLesIds("numav", "Avion", null));
		System.out.println(Connexion.getLesIds("numav", "Vol", null));
		(new AvionDAO()).afficheSelectEtoileAvion();
		*/
		Connexion.fermer();
	}

}
