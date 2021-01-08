package fr.eni.encheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArticleVendu article;
	private String rue;
	private String codePostal;
	private String ville;

	// CONSTRUCTEURS

	public Retrait() {
		super();
	}

	
	public Retrait(ArticleVendu article, String rue, String codePostal, String ville) {
		super();
		this.article = article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	// GETTERS AND SETTERS

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
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

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	// METHODE TO STRING
	@Override
	public String toString() {
		return "Retrait [id=" + article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
