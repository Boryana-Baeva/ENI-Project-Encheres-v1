package fr.eni.encheres.bo;

public enum EtatVente {
	CREE("vente créée"),
	EN_COURS("en cours"),
	ANNULE("annulée"),
	ENCHERES_TERMINEES("enchères terminées"),
	RETRAIT_EFFECTUE("retrait effectué");
	
	private final String message; 
	
	EtatVente(String message)
	{
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}

