package sopra.monRdv.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	@Column(length = 255)
	private String voie;
	@Column(length = 255)
	private String complement;
	@Column(length = 10)
	private String codePostal;
	@Column(length = 255)
	private String ville;

	public Adresse() {
		super();
	}

	public Adresse(String voie, String complement, String codePostal, String ville) {
		super();
		this.voie = voie;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
