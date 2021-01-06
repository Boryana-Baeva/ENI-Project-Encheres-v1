package fr.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate date;
	private int montant;
	private ArticleVendu article;
	private Utilisateur encherisseur;
	private boolean remporte;
	
	// Constructeurs
	
	public Enchere() {
		
	}
	
	public Enchere(LocalDate date, int montant, ArticleVendu article, Utilisateur encherisseur) {
		this.date = date;
		this.montant = montant;
		this.article = article;
		this.encherisseur = encherisseur;
		this.setRemporte(false);
	}

	public Enchere(int id, LocalDate date, int montant, ArticleVendu article, Utilisateur encherisseur) {
		this(date, montant, article, encherisseur);
		this.id = id;
		this.setRemporte(false);
	}

	// Gettes & Setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getMontant() {
		return montant;
	}
	
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public ArticleVendu getArticle() {
		return article;
	}
	
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}
	
	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}

	public boolean isRemporte() {
		return remporte;
	}

	public void setRemporte(boolean remporte) {
		this.remporte = remporte;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Enchere [id=" + id + ", date=" + date + ", montant=" + montant + ", article=" + article
				+ ", encherisseur=" + encherisseur + "]";
	}
	
}
