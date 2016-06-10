package aeronautique;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Vol {

	private int numVol;
	//private Avion avion;
	//private Pilote pilote;
	private int numAv;// Ensuite il faudra passer aux objets
	private int numPil;	// Ensuite il faudra passer aux objets
	
	// Pour les bases de données, on passera par java.sql.Timestamp
	/* Pour le find :
	 * GregorianCalendar hArr = new GregorianCalendar();
	 * hArr.setTimesInMillis (rs.getTimeStamp("harr").getTime());
	 * Pour le create :
	 * Timestamp ts = new Timestamp (vol.gethDep().getTimeInMillis());
	 * pst.setTimestamp(3,ts); 
	 */
	private GregorianCalendar hDep;	
	private GregorianCalendar hArr;
	private String villeDep;
	private String villeArr;
	
	@Override
	public String toString() {
		// TimeStamp de java.sql se rapproche du dateTime de sql Server
		Timestamp hDepTs = new Timestamp(hDep.getTimeInMillis()); 
		Timestamp hArrTs = new Timestamp(hArr.getTimeInMillis()); 
		return "Vol [numVol=" + numVol + ", numAv=" + numAv + ","
				+ "numPil=" + numPil + ", hDep=" + hDepTs + ", hArr="
				+ hArrTs + ", villeDep=" + villeDep + ", villeArr=" +
				villeArr + "]";
	}
	
	public int getNumVol() {
		return numVol;
	}
	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}
	public int getNumAv() {
		return numAv;
	}
	public void setNumAv(int numAv) {
		this.numAv = numAv;
	}
	public int getNumPil() {
		return numPil;
	}
	public void setNumPil(int numPil) {
		this.numPil = numPil;
	}
	public GregorianCalendar gethDep() {
		return hDep;
	}
	public void sethDep(GregorianCalendar hDep) {
		this.hDep = hDep;
	}
	public GregorianCalendar gethArr() {
		return hArr;
	}
	public void sethArr(GregorianCalendar hArr) {
		this.hArr = hArr;
	}
	public String getVilleDep() {
		return villeDep;
	}
	public void setVilleDep(String villeDep) {
		this.villeDep = villeDep;
	}
	public String getVilleArr() {
		return villeArr;
	}
	public void setVilleArr(String villeArr) {
		this.villeArr = villeArr;
	}
	public Vol(int numAv, int numPil, GregorianCalendar hDep, GregorianCalendar hArr, String villeDep,
			String villeArr) {
		super();
		this.numAv = numAv;
		this.numPil = numPil;
		this.hDep = hDep;
		this.hArr = hArr;
		this.villeDep = villeDep;
		this.villeArr = villeArr;
	}
	
	
	
	
}
