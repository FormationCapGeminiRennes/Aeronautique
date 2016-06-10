package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeronautique.Avion;
import aeronautique.Pilote;

public class PiloteDAO extends DAO<Pilote> {

	private static final String TABLE = "Pilote";
	private static final String CLE_PRIMAIRE = "numPil";

	
	@Override
	public boolean create(Pilote pilote) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" (nompil, adr, sal) VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, pilote.getNomPil());
			pst.setString(2, pilote.getAdr());
			pst.setInt(3, pilote.getSalaire());
			pst.executeUpdate();
			
			//mettre à jour l'identifiant
			pilote.setNumPil(Connexion.getMaxId(CLE_PRIMAIRE, TABLE));
			
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		
		return succes;
	}

	@Override
	public boolean delete(Pilote pilote) {
		boolean succes = true;
		try {
			int id = pilote.getNumPil();
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
	public boolean update(Pilote obj) {
		boolean succes=true;

		String nom =obj.getNomPil();
		String loc =obj.getAdr();
		int capacite = obj.getSalaire();
		int id = obj.getNumPil();

		try {
			String requete = "UPDATE "+TABLE+" SET nomPil = ?, Adr = ?, sal = ? WHERE "+CLE_PRIMAIRE+"= ?";
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
	public Pilote find(int id) {
		Pilote pilote = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString("nomPil");
			String adr = rs.getString("adr");
			int salaire = rs.getInt("sal");
			pilote = new Pilote (id, nom, adr, salaire);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pilote;
	}

	public void afficheSelectEtoilePilote() {
		System.out.println("--- Pilote non utilisé ---");
		String clauseWhere = CLE_PRIMAIRE+" NOT IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Pilote", clauseWhere);

		System.out.println("--- Pilote contraint par Vol --- ");
		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
		Connexion.afficheSelectEtoile("Pilote", clauseWhere);
		
	}

	
	
}
