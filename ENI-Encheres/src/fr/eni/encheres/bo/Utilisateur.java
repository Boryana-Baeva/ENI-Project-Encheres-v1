package fr.eni.encheres.bo;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String code_postal;
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
    
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String code_postal,
			String ville, String password, int credit, boolean administrateur, ArticleVendu[] articlesVendus, ArticleVendu[] articlesAchetes, Enchere[] encheres ) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.code_postal = code_postal;
		this.ville = ville;
		this.password = password;
		this.credit = credit;
		this.administrateur = administrateur;
	}

    
	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone,
			String code_postal, String ville, String password, int credit, boolean administrateur, ArticleVendu[] articlesVendus, ArticleVendu[] articlesAchetes, Enchere[] encheres) {
		this(pseudo, nom, prenom, email, telephone, code_postal, ville, password, credit, administrateur, articlesVendus, articlesAchetes, encheres );
		this.id = id;
		
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
	
	public String getCode_postal() {
		return code_postal;
	}
	
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
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

	@Override
    public String toString() {
    	return super.toString();
    }
}
