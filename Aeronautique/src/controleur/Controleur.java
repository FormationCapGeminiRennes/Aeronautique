package controleur;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import aeronautique.Avion;
import aeronautique.Pilote;
import aeronautique.Vol;
import dao.AvionDAO;
import dao.Connexion;
import dao.PiloteDAO;
import dao.VolDAO;
import ihm.Menu;

public class Controleur {

	// Liste des constantes privées permettant d'enchainer les menus.
	// Menu principal
	private static final int MENU_AJOUT_VOL=0;
	private static final int MENU_AJOUT_PILOTE=1;
	private static final int MENU_AJOUT_AVION=2;
	private static final int MENU_SUPPR_VOL=3;
	private static final int MENU_SUPPR_PILOTE=4;
	private static final int MENU_SUPPR_AVION=5;
	private static final int MENU_PRINCIPAL=6;
	private static final int MENU_AJOUT_VRAIMENT_VOL = 7;
	private static final int MENU_AFFICHER_VOL = 8;
	private static final int MENU_AJOUT_VRAIMENT_AVION = 9;
	private static final int MENU_AFFICHER_AVION = 10;
	private static final int MENU_AJOUT_VRAIMENT_PILOTE = 11;
	private static final int MENU_AFFICHER_PILOTE = 12;
	private static final int MENU_SUPPR_VRAIMENT_VOL = 13;
	private static final int MENU_SUPPR_VRAIMENT_AVION = 14;
	private static final int MENU_SUPPR_VRAIMENT_PILOTE = 15;

	public Controleur() {
		this.sgbdJava();
	}

	private int getChoix(int menuAAfficher){
		int rep;
		switch (menuAAfficher) {
		case MENU_PRINCIPAL:
			rep = gererMenuPpl(); 			
			break;
		case MENU_AJOUT_VOL:
			rep = gererAjoutVol(); 			
			break;
		case MENU_AJOUT_AVION:
			rep = gererAjoutAvion(); 			
			break;
		case MENU_AJOUT_PILOTE:
			rep = gererAjoutPilote(); 			
			break;
		case MENU_SUPPR_VOL:
			rep = gererSupprVol(); 			
			break;
		case MENU_SUPPR_AVION:
			rep = gererSupprAvion(); 			
			break;
		case MENU_SUPPR_PILOTE:
			rep = gererSupprPilote(); 			
			break;
		default:
			rep=-1;
			break;
		} 
		return rep;
	}

	/**
	 * La méthode jouer gère les enchainements des menus.
	 * Les modifications des joueurs (le score) sont effectuées par le métier,
	 * ce qui est l'essentiel de son travail avec le changement d'identité.
	 */
	public void sgbdJava(){
		boolean fini=false;
		int choix = this.getChoix(Controleur.MENU_PRINCIPAL);
		int menuPrecedent=choix;
		while (!fini) {
			switch (choix) {
			case -1 :
				fini = true;
				break;
				// Les cas de base ou on appelle simplement le menu demandé
			case Controleur.MENU_PRINCIPAL :
			case Controleur.MENU_AJOUT_AVION :
			case Controleur.MENU_AJOUT_PILOTE :
			case Controleur.MENU_AJOUT_VOL :
			case Controleur.MENU_SUPPR_AVION :
			case Controleur.MENU_SUPPR_PILOTE :
			case Controleur.MENU_SUPPR_VOL :
				menuPrecedent=choix;
				choix = this.getChoix(choix);
				break;

				// Les cas où on ne change rien, on ne fait que de l'affichage
			case Controleur.MENU_AFFICHER_AVION :
				(new AvionDAO()).afficheSelectEtoileAvion(); 
				choix = menuPrecedent;
				break;
			case Controleur.MENU_AFFICHER_PILOTE :
				(new PiloteDAO()).afficheSelectEtoilePilote();
				choix = menuPrecedent;
				break;
			case Controleur.MENU_AFFICHER_VOL :
				Connexion.afficheSelectEtoile("Vol", null);
				choix = menuPrecedent;
				break;

				// Les cas où on demande des informations pour l'ajout
			case Controleur.MENU_AJOUT_VRAIMENT_AVION :
				this.effectuerAjoutAvion();
				choix = menuPrecedent;
				break;
			case Controleur.MENU_AJOUT_VRAIMENT_VOL :
				this.effectuerAjoutVol();
				choix = menuPrecedent;
				break;
			case Controleur.MENU_AJOUT_VRAIMENT_PILOTE :
				this.effectuerAjoutPilote();
				choix = menuPrecedent;
				break;

				// Les cas où on demande une clé pour suppression
			case Controleur.MENU_SUPPR_VRAIMENT_AVION :
				this.effectuerSupprAvion();
				choix = menuPrecedent;
				break;
			case Controleur.MENU_SUPPR_VRAIMENT_VOL :
				this.effectuerSupprVol();
				choix = menuPrecedent;
				break;
			case Controleur.MENU_SUPPR_VRAIMENT_PILOTE :
				this.effectuerSupprPilote();
				choix = menuPrecedent;
				break;
			default:
				// Code inaccessible selon nos vérifications
				Menu.afficheMsg(" ## Ré-essayez");
				break;
			}
		}
		Menu.afficheMsg("Au revoir");
	}


	private void effectuerSupprPilote() {
		(new PiloteDAO()).afficheSelectEtoilePilote();
		Menu.afficheMsg("Quel numéro de pilote supprimer ?");		
		Pilote av = new Pilote(Menu.lireInt(),null, null, -1);
		(new PiloteDAO()).delete(av);
	}

	private void effectuerSupprVol() {
		Connexion.afficheSelectEtoile("Vol", null);
		Menu.afficheMsg("Quel numéro de vol supprimer ?");		
		Vol av = new Vol(Menu.lireInt(),0,null, null, null,null);
		(new VolDAO()).delete(av);
	}

	private void effectuerSupprAvion() {
		(new AvionDAO()).afficheSelectEtoileAvion();
		Menu.afficheMsg("Quel numéro d'avion supprimer ?");		
		Avion av = new Avion(Menu.lireInt(),null, null, -1);
		(new AvionDAO()).delete(av);

	}

	private void effectuerAjoutPilote() {
		Menu.afficheMsg("Quel est le nom du pilote ?");
		String nom = Menu.lireString();
		Menu.afficheMsg("Quelle est son adresse ?");
		String adr = Menu.lireString();
		Menu.afficheMsg("Quelle est son salaire ?");
		int salaire = Menu.lireInt();
		Pilote pilote = new Pilote(-1, nom, adr, salaire);
		(new PiloteDAO()).create(pilote);

	}

	private GregorianCalendar lireDate(String msg){
		Menu.afficheMsg("date heure "+msg);		
		String dateActuelle=""; 
		Menu.afficheMsg("quelle année "+msg+" : "+dateActuelle);
		int annee = Menu.lireInt();
		dateActuelle+=annee+"/";
		Menu.afficheMsg("quelle mois "+msg+" : "+dateActuelle);
		int mois = Menu.lireInt();
		dateActuelle+=mois+"/";
		Menu.afficheMsg("quelle jour "+msg+" : "+dateActuelle);
		int jour = Menu.lireInt();
		dateActuelle+=jour+" ";
		Menu.afficheMsg("quelle heure "+msg+" : "+dateActuelle);
		int heure = Menu.lireInt();
		dateActuelle+=heure+":";
		Menu.afficheMsg("quelle minute "+msg+" : "+dateActuelle);
		int minute = Menu.lireInt();
		dateActuelle+=minute+":";
		Menu.afficheMsg("quelle seconde "+msg+" : "+dateActuelle);
		int seconde = Menu.lireInt();
		dateActuelle+=seconde;
		Menu.afficheMsg("date heure "+msg+" "+dateActuelle);		
		return new GregorianCalendar(annee, mois-1, jour, heure, minute, seconde);
	}

	private void effectuerAjoutVol() {
		(new PiloteDAO()).afficheSelectEtoilePilote();
		Menu.afficheMsg("POur ce nouveau Vol, quel id de pilote ?");
		int numPil= Menu.lireInt();

		(new AvionDAO()).afficheSelectEtoileAvion();
		Menu.afficheMsg("Quel id d'avion ?");
		int numAv= Menu.lireInt();

		Menu.afficheMsg("Quelle ville de départ ?");
		String vDep = Menu.lireString();
		Menu.afficheMsg("Quelle ville d'arrivée ?");
		String vArr = Menu.lireString();
		GregorianCalendar hDep = lireDate("DEPART");
		GregorianCalendar hArr = lireDate("DEPART");		
		Vol v = new Vol(numPil, numAv, hDep, hArr, vDep, vArr);
		(new VolDAO()).create(v);
	}

	private void effectuerAjoutAvion() {
		Menu.afficheMsg("Quel est le nom de l'avion ?");
		String nom = Menu.lireString();
		Menu.afficheMsg("Quelle est sa localisation?");
		String loc = Menu.lireString();
		Menu.afficheMsg("Quelle est sa capacité ?");
		int capacite = Menu.lireInt();
		Avion av = new Avion(-1, nom, loc, capacite);
		(new AvionDAO()).create(av);

	}

	private int gererSupprPilote() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un Pilote");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Pilote");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_PILOTE;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_PILOTE;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererSupprAvion() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un Avion");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Avion");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_AVION;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_AVION;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererSupprVol() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un Vol");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Vol");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_VOL;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_VOL;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererAjoutPilote() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un Pilote");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Pilote");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_PILOTE;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_PILOTE;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererAjoutAvion() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un Avion");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Avion");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_AVION;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_AVION;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererAjoutVol() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un Vol");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Vol");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_VOL;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_VOL;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererMenuPpl() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajout Vol");
		leMenu.add("Ajout Avion");
		leMenu.add("Ajout Pilote");
		leMenu.add("Supprimer Vol");
		leMenu.add("Supprimer Avion");
		leMenu.add("Supprimer Pilote");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VOL;
			break;
		case 1:
			rep=MENU_AJOUT_AVION;
			break;
		case 2:
			rep=MENU_AJOUT_PILOTE;
			break;
		case 3:
			rep=MENU_SUPPR_VOL;
			break;
		case 4:
			rep=MENU_SUPPR_AVION;
			break;
		case 5:
			rep=MENU_SUPPR_PILOTE;
			break;

		default:
			rep=-1;
			break;
		}
		return rep;
	}

}
