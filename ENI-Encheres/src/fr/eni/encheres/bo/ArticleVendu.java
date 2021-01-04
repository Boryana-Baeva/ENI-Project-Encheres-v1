package fr.eni.encheres.bo;

import java.io.Serializable;
import java.sql.Date;

public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date debutEncheres;
	private Date finEncheres;
	private int prixInitial;
	private int prixVente;
	private Utilisateur id;
	private int noCategorie;

	// CONSTRUCTEURS

	public ArticleVendu() {
		super();
	}

	public ArticleVendu(String nomArticle, String description, Date debutEncheres, Date finEncheres, int prixInitial,
			int prixVente, Utilisateur id, int noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEncheres = debutEncheres;
		this.finEncheres = finEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.id = id;
		this.noCategorie = noCategorie;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, Date debutEncheres, Date finEncheres,
			int prixInitial, int prixVente, Utilisateur id, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEncheres = debutEncheres;
		this.finEncheres = finEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.id = id;
		this.noCategorie = noCategorie;
	}

	// GETTERS AND SETTERS

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDebutEncheres() {
		return debutEncheres;
	}

	public void setDebutEncheres(Date debutEncheres) {
		this.debutEncheres = debutEncheres;
	}

	public Date getFinEncheres() {
		return finEncheres;
	}

	public void setFinEncheres(Date finEncheres) {
		this.finEncheres = finEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Utilisateur getId() {
		return id;
	}

	public void setId(Utilisateur id) {
		this.id = id;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

}
