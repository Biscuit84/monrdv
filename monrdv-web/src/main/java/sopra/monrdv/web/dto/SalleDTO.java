package sopra.monrdv.web.dto;

import sopra.formation.model.Salle;

public class SalleDTO {

	private Long id;
	private String nom;
	private Integer capacite;
	private Boolean videoProjecteur;
	private String rue;
	private String complement;
	private String codePostal;
	private String ville;

	public SalleDTO() {
		super();
	}

	public SalleDTO(Salle salle) {
		super();
		this.id = salle.getId();
		this.nom = salle.getNom();
		this.capacite = salle.getCapacite();
		this.videoProjecteur = salle.getVideoProjecteur();
		if (salle.getAdr() != null) {
			this.rue = salle.getAdr().getRue();
			this.complement = salle.getAdr().getComplement();
			this.codePostal = salle.getAdr().getCodePostal();
			this.ville = salle.getAdr().getVille();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Boolean getVideoProjecteur() {
		return videoProjecteur;
	}

	public void setVideoProjecteur(Boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
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
