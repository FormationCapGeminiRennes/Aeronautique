package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Connexion {

	private static Connection connect = null;

	private static final String ID = "AlainB";
	private static final String MDP = "toto";

	/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static Connection getInstance() {
		if (connect==null) {
			try { 

				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser(ID);
				ds.setPassword(MDP);
				ds.setServerName("localhost\\SQLEXPRESS");
				ds.setDatabaseName("aeronautique");
				connect = ds.getConnection();
			}
			catch (SQLException e){
				System.out.println("Echec de la tentative de connexion : " + e.getMessage() + e.getStackTrace()) ;
			}
		}
		return connect;
	}

	public static ResultSet executeQuery(String requete){
		Statement st = null ;
		ResultSet rs = null;
		System.out.println("requete = "+requete);
		try{
			st = getInstance().createStatement() ;
			rs = st.executeQuery(requete) ;
		}catch(SQLException e){
			System.out.println("Echec de la tentative d'exécution de requete : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return rs;
	}

	/**
	 * Une méthode statique qui permet de faire une mise à jour d'une table (INSERT / UPDATE / DELETE)
	 * @param requete
	 * @return
	 */
	public static boolean executeUpdate(String requete){
		boolean succes = true;
		System.out.println(requete);
		Statement st = null ;
		try{
			st = getInstance().createStatement() ;
			st.executeUpdate(requete) ;
		}catch(SQLException e){
			succes = false;
			System.out.println("Echec de la tentative d'exécution de Mise à Jour : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return succes;
	}

	/**
	 * Fermeture de la connexion au SGBD SQL ServerExpress
	 */
	public static void fermer()
	{
		try
		{
			getInstance().close();
			System.out.println("deconnexion ok");
		}
		catch (SQLException e)
		{
			connect=null;
			System.out.println("echec de la fermeture");
		}
	}

	/**
	 * Requête qui permet de voir le contenu d'une table
	 * Attention à ne pas perdre la première ligne en testant la table vide
	 * @param table
	 */
	public static void afficheSelectEtoile(String table){
		try{
			ResultSet res = Connexion.executeQuery("SELECT * FROM "+table) ;
			boolean hasNext =res.next(); 
			if (!hasNext) {System.out.println("table vide");}
			else {
				while(hasNext){
					System.out.print("|");
					ResultSetMetaData rsmd = res.getMetaData();
					int taille = rsmd.getColumnCount();
					for (int j = 1; j <= taille; j++) {
						switch (rsmd.getColumnType(j)) {
						case Types.VARCHAR:
							System.out.print(res.getString(j)+"|");							
							break;
						case Types.DATE:
							System.out.print(res.getDate(j)+"|");							
							break;
						case Types.INTEGER:
							System.out.print(res.getInt(j)+"|");							
							break;
						default:
							break;
						}
					} 
					System.out.println();
					hasNext = res.next();
				}
			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
	}

	/**
	 * Requête qui permet de récupérer le plus grand id de la table, a priori celui qui vient d'être affecté
	 * à une ligne créée via identity.
	 * @param cle
	 * @param table
	 * @return
	 */
	protected static int getMaxId(String cle, String table) {
		String requete = "SELECT MAX("+cle+")as max FROM "+table;
		ResultSet rs = Connexion.executeQuery(requete);
		int id= -1;
		try {
			rs.next();
			id = rs.getInt("max");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}


	
}
