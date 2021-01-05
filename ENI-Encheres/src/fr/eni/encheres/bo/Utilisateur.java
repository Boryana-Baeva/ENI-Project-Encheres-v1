package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.Arrays;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String password; 
	private int credit;
    private boolean administrateur;
    private ArticleVendu[] articlesVendus = null;
    private ArticleVendu[] articlesAchetes = null;
    private Enchere[] encheres = null;
    
    
    
    // Constructeur 
    public Utilisateur() {
		
	}
    
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal,
			String ville, String password, int credit, boolean administrateur, ArticleVendu[] articlesVendus, ArticleVendu[] articlesAchetes, Enchere[] encheres ) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
		this.credit = credit;
		this.administrateur = administrateur;
	}

    
	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String password, int credit, boolean administrateur, ArticleVendu[] articlesVendus, ArticleVendu[] articlesAchetes, Enchere[] encheres) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password, credit, administrateur, articlesVendus, articlesAchetes, encheres );
		this.id = id;
		
	}

	public Retrait getLieuRetraitParDefaut()
	{
		return new Retrait(this.rue, this.codePostal, this.ville);
	}
	
	// Getters & Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return codePostal;
	}
	
	public void setCode_postal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public boolean isAdministrateur() {
		return administrateur;
	}
	
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
    
	
    public ArticleVendu[] getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(ArticleVendu[] articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

	public ArticleVendu[] getArticlesAchetes() {
		return articlesAchetes;
	}

	public void setArticlesAchetes(ArticleVendu[] articlesAchetes) {
		this.articlesAchetes = articlesAchetes;
	}

	public Enchere[] getEncheres() {
		return encheres;
	}

	public void setEnchere(Enchere[] encheres) {
		this.encheres = encheres;
	}

	/**
	 * Méthode ToString 
	 */
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville="
				+ ville + ", password=" + password + ", credit=" + credit + ", administrateur=" + administrateur
				+ ", articlesVendus=" + Arrays.toString(articlesVendus) + ", articlesAchetes="
				+ Arrays.toString(articlesAchetes) + ", encheres=" + Arrays.toString(encheres) + "]";
	}

	
}
