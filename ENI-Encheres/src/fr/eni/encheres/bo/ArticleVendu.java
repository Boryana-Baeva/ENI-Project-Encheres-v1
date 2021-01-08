package fr.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private Categorie categorie;
	private Utilisateur vendeur;
	private EtatVente etatVente;
	private Retrait lieuRetrait;

	// CONSTRUCTEURS


	public ArticleVendu() {

	}

	public ArticleVendu(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur vendeur, EtatVente etatVente ,
			Retrait lieuRetrait) {
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.vendeur = vendeur;
		this.etatVente = etatVente;
		this.lieuRetrait = lieuRetrait;
	}
	
	

	public ArticleVendu(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur vendeur) {
		this(nom,description,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,categorie,vendeur,EtatVente.CREE, vendeur.getLieuRetraitParDefaut());
			
	}
	
	

	public ArticleVendu(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, Categorie categorie, Utilisateur vendeur, EtatVente etatVente,
			Retrait lieuRetrait) {
		this(nom, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, categorie, vendeur, etatVente,
				lieuRetrait);
		this.id = id;

	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public EtatVente getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * Méthode ToString 
	 */
	@Override
	public String toString() {
		return "ArticleVendu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", categorie=" + categorie + ", vendeur=" + vendeur + ", etatVente="
				+ etatVente + ", lieuRetrait=" + lieuRetrait + "]";
	}

}
