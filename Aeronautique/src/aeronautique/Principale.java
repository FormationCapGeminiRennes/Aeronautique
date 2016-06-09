package aeronautique;

import dao.AvionDAO;
import dao.Connexion;

public class Principale {

	public static void main(String[] args) {
		Connexion.afficheSelectEtoile("Avion");
		// Seul l'id compte
		//Avion avion = new Avion(-1, "airbus 320", "Caen", 350);
		//(new AvionDAO()).delete(avion);
		/*(new AvionDAO()).create(avion);
		System.out.println(avion);*/
		Avion avion=(new AvionDAO()).find(5);
		System.out.println(avion);
		Connexion.afficheSelectEtoile("Avion");		
		Connexion.afficheSelectEtoile("Vol");
		Connexion.fermer();
	}

}
