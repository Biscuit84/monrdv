package sopra.monrdv.web.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import sopra.monRdv.model.Civilite;
import sopra.monRdv.model.Praticien;
import sopra.monRdv.model.Secteur;

public class PraticienDTO {

	
	private Long id;
	private Civilite civilite;
	private String nom;
	private String prenom;
	private String telephone;
	private Secteur secteur;
	private boolean carteVitale;
	private String photo;
	private boolean cheque;
	private boolean espece;
	private Integer dureeCreneau;
	
	
	
	
	

	
	public PraticienDTO(Praticien praticien) {
		this.id = praticien.getId();
		this.civilite = praticien.getCivilite();
		this.nom = praticien.getNom();
		this.prenom = praticien.getPrenom();
		this.telephone = praticien.getTelephone();
		this.secteur = praticien.getSecteur();
		this.carteVitale = praticien.isCarteVitale();
		this.photo = praticien.getPhoto();
		this.cheque = praticien.isCheque();
		this.espece = praticien.isEspece();
		this.dureeCreneau = praticien.getDureeCreneau();
	}
	public PraticienDTO() {}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public boolean isCarteVitale() {
		return carteVitale;
	}
	public void setCarteVitale(boolean carteVitale) {
		this.carteVitale = carteVitale;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public boolean isCheque() {
		return cheque;
	}
	public void setCheque(boolean cheque) {
		this.cheque = cheque;
	}
	public boolean isEspece() {
		return espece;
	}
	public void setEspece(boolean espece) {
		this.espece = espece;
	}
	public Integer getDureeCreneau() {
		return dureeCreneau;
	}
	public void setDureeCreneau(Integer dureeCreneau) {
		this.dureeCreneau = dureeCreneau;
	}
	
	
	
	
}
