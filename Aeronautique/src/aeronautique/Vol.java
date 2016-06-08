package aeronautique;

import java.util.GregorianCalendar;

public class Vol {

	private GregorianCalendar hDep;	
	private GregorianCalendar hArr;
	private int numeroVol;
	private String villeDep;
	private String villeArr;
	public Vol(GregorianCalendar hDep, GregorianCalendar hArr, int numeroVol, String villeDep, String villeArr) {
		super();
		this.hDep = hDep;
		this.hArr = hArr;
		this.numeroVol = numeroVol;
		this.villeDep = villeDep;
		this.villeArr = villeArr;
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
	public int getNumeroVol() {
		return numeroVol;
	}
	public void setNumeroVol(int numeroVol) {
		this.numeroVol = numeroVol;
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
	@Override
	public String toString() {
		return "Vol [hDep=" + hDep + ", hArr=" + hArr + ", numeroVol=" + numeroVol + ", villeDep=" + villeDep
				+ ", villeArr=" + villeArr + "]";
	}
	
	
}
