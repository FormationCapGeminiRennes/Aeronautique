package aeronautique;

public class Avion {

	private int numero;
	private String nom;
	private String loc;
	private int capacite;
	
	public Avion(int numero, String nom, String loc, int capacite) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.loc = loc;
		this.capacite = capacite;
	}

	@Override
	public String toString() {
		return "Avion [numero=" + numero + ", nom=" + nom + ", loc=" + loc + ", capacite=" + capacite + "]";
	}

	
	
	
	
}
