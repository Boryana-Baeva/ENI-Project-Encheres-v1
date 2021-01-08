package fr.eni.encheres.bo;

import java.io.Serializable;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String libelle;

	// Constructor

	/**
	 * @param id
	 * @param libelle
	 */
	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	/**
	 * 
	 */
	public Categorie() {
	}

	/**
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	// Getters And Setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	// toString

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", libelle=" + libelle + "]";
	}
}
