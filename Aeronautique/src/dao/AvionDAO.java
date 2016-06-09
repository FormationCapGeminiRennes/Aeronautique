package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeronautique.Avion;

public class AvionDAO extends DAO<Avion> {

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
	public boolean update(Avion av) {
		// TODO Auto-generated method stub
		String requete = "UPDATE "+TABLE+" SET nomav=?, loc = ?, capacite = ?"+
		"WHERE numav=?";
		return false;
	}

	@Override
	public Avion find(int id) {
		Avion avion = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString("nomav");
			String loc = rs.getString("loc");
			int capacite = rs.getInt("capacite");
			avion = new Avion (id, nom, loc, capacite);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avion;
	}



}
