package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import aeronautique.Avion;

public class AvionDAO extends DAO<Avion> {

	private static Map<Integer, Avion> lesAvionsConnus = new HashMap<Integer, Avion>();

	private static final String TABLE = "Avion";
	private static final String CLE_PRIMAIRE = "numav";

	@Override
	public boolean create(Avion av) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" (nomav, loc, capacite) VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, av.getNom());
			pst.setString(2, av.getLoc());
			pst.setInt(3, av.getCapacite());
			pst.executeUpdate();

			//mettre à jour l'identifiant
			av.setNumero(Connexion.getMaxId(CLE_PRIMAIRE, TABLE));

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Avion av) {
		boolean succes = true;
		try {
			int id = av.getNumero();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Avion obj) {
		boolean succes=true;

		String nom =obj.getNom();
		String loc =obj.getLoc();
		int capacite = obj.getCapacite();
		int id = obj.getNumero();

		try {
			String requete = "UPDATE "+TABLE+" SET nomav = ?, loc = ?, capacite = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,loc) ; 
			pst.setInt(3, capacite) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Avion find(int id) {
		Avion avion = null;
		if (lesAvionsConnus.containsKey(id)) {
			avion=lesAvionsConnus.get(id);
		}
		else {
			try {
				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString("nomav");
				String loc = rs.getString("loc");
				int capacite = rs.getInt("capacite");
				avion = new Avion (id, nom, loc, capacite);
				lesAvionsConnus.put(id, avion);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return avion;
	}

	public void afficheSelectEtoileAvion() {
		System.out.println("--- Avion non utilisé ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Avion", clauseWhere);

		System.out.println("--- Avion contraint par Vol --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Avion", clauseWhere);

	}



}
