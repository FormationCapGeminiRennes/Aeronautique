package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import aeronautique.Avion;
import aeronautique.Vol;

public class VolDAO extends DAO<Vol> {

	private static final String CLE_PRIMAIRE = "numVol";
	private static final String TABLE = "VOL";

	@Override
	public boolean create(Vol obj) {

		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" (numAv, numPil, hDep, hArr, villeDep, villeArr) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, obj.getNumAv());
			pst.setInt(2, obj.getNumPil());
			Timestamp ds = new Timestamp(obj.gethDep().getTimeInMillis());
			pst.setTimestamp(3, ds);
			ds = new Timestamp(obj.gethArr().getTimeInMillis());
			pst.setTimestamp(4, ds);
			pst.setString(5, obj.getVilleDep());
			pst.setString(6, obj.getVilleArr());
			pst.executeUpdate() ;

			obj.setNumVol(Connexion.getMaxId(CLE_PRIMAIRE, TABLE)); // Mise à jour de l'id.

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Vol obj) {
		boolean succes=true;
		int id = obj.getNumVol();
		try {
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}

	@Override
	public boolean update(Vol obj) {
		boolean succes=true;

		int numPil= obj.getNumPil();
		int numAv= obj.getNumAv();
		String vDep = obj.getVilleDep();
		String vArr = obj.getVilleArr();
		GregorianCalendar hDep = obj.gethDep();
		GregorianCalendar hArr = obj.gethArr();
		
		try {
			String requete = "UPDATE "+TABLE+" SET numPil = ?, numAv = ?, vDep = ?, vArr = ?, hDep = ?, hArr = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1,numPil) ; 
			pst.setInt(2,numAv) ; 
			pst.setString(3, vDep) ;
			pst.setString(4, vArr) ;
			pst.setTimestamp(5, new Timestamp(hDep.getTimeInMillis())) ;
			pst.setTimestamp(6, new Timestamp(hArr.getTimeInMillis())) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Vol find(int id) {
		String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
		ResultSet rs = Connexion.executeQuery(requete);
		Vol vol= null;
		try {
			rs.next();
			int numAv=rs.getInt("numAv");
			Avion avion = (new AvionDAO()).find(numAv); // à utiliser
			int numPil=rs.getInt("numPil"); // TODO idem avec Pilote
			GregorianCalendar hDep = new GregorianCalendar();
			hDep.setTimeInMillis(rs.getTimestamp("hdep").getTime());
			GregorianCalendar hArr = new GregorianCalendar();
			hArr.setTimeInMillis(rs.getTimestamp("harr").getTime());
			String villeDep=rs.getString("villeDep");;
			String villeArr=rs.getString("villeArr");;
			vol=new Vol(numAv, numPil, hDep, hArr, villeDep, villeArr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vol;
	}
	


}
