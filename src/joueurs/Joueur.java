package joueurs;


public enum Joueur {
	JOUEUR1 ("\u001B[31m", 7), 
	JOUEUR2 ("\u001B[36m", 0);
	
	private final String color;
	private final int pionGoal;
	
	Joueur(String color, int pionGoal) {
		this.color = color;
		this.pionGoal = pionGoal;
	}
	/**
	 * retourne un code couleur utile pour l'affichage des pieces
	 * @return un code couleur sous forme de chaine de charactere
	 */
	public String getPlayerColor() {
		return color;
	}
	
	public int getPionGoal() {
		return pionGoal;
	}
	
	public int getStart() {
		if(this == Joueur.JOUEUR1) return JOUEUR2.getPionGoal();
		else return JOUEUR1.getPionGoal();
	}
}
