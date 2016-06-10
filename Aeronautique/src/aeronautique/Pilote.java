package aeronautique;

public class Pilote {

	int numPil;
	String nomPil;
	String adr;
	int salaire;
	public int getNumPil() {
		return numPil;
	}
	public void setNumPil(int numPil) {
		this.numPil = numPil;
	}
	public String getNomPil() {
		return nomPil;
	}
	public void setNomPil(String nomPil) {
		this.nomPil = nomPil;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	public Pilote(int numPil, String nomPil, String adr, int salaire) {
		super();
		this.numPil = numPil;
		this.nomPil = nomPil;
		this.adr = adr;
		this.salaire = salaire;
	}
	
	@Override
	public String toString() {
		return "Pilote [numPil=" + numPil + ", nomPil=" + nomPil + ", adr=" + adr + ", salaire=" + salaire + "]";
	}
	
	
	
}
