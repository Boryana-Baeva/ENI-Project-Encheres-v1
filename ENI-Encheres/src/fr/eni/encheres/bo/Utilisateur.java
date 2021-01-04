
package fr.eni.encheres.bo;
import java.io.Serializable;
	
	public class Utilisateur implements Serializable {​​
		
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
		
	// Constructeur vide
		public Utilisateur() {​​
		}​​
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String code_postal,
	String ville, String password, int credit, boolean administrateur) {​​
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
	}​​ // Getters & Setters
	public int getId() {​​
	return id;
	}​​
	public void setId(int id) {​​
	this.id = id;
	}​​
	public String getPseudo() {​​
	return pseudo;
	}​​
	public void setPseudo(String pseudo) {​​
	this.pseudo = pseudo;
	}​​
	public String getNom() {​​
	return nom;
	}​​
	public void setNom(String nom) {​​
	this.nom = nom;
	}​​
	public String getPrenom() {​​
	return prenom;
	}​​
	public void setPrenom(String prenom) {​​
	this.prenom = prenom;
	}​​
	public String getEmail() {​​
	return email;
	}​​
	public void setEmail(String email) {​​
	this.email = email;
	}​​
	public String getTelephone() {​​
	return telephone;
	}​​
	public void setTelephone(String telephone) {​​
	this.telephone = telephone;
	}​​
	public String getCode_postal() {​​
	return code_postal;
	}​​
	public void setCode_postal(String code_postal) {​​
	this.code_postal = code_postal;
	}​​
	public String getVille() {​​
	return ville;
	}​​
	public void setVille(String ville) {​​
	this.ville = ville;
	}​​
	public String getPassword() {​​
	return password;
	}​​
	public void setPassword(String password) {​​
	this.password = password;
	}​​
	public int getCredit() {​​
	return credit;
	}​​
	public void setCredit(int credit) {​​
	this.credit = credit;
	}​​
	public boolean isAdministrateur() {​​
	return administrateur;
	}​​
	public void setAdministrateur(boolean administrateur) {​​
	this.administrateur = administrateur;
	}​​
	}​​


}
