package joueurs;

public class GestionJoueur {
	private final Joueur LEJOUEUR;
	private final String PSEUDO;
	private int score;

	public GestionJoueur(Joueur unJoueur, String unPseudo) {
		this.LEJOUEUR = unJoueur;
		this.PSEUDO = unPseudo;
		this.score = 0;
	}
	/**
	 * renvoie le score du joueur
	 * @return le score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * permet de modifier le score du joueur
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * permet de savoir si il s'agit du joueur1, du joueur2, ou de l'IA
	 * @return une instance de l'enumeration joueur
	 */
	public Joueur getLEJOUEUR() {
		return LEJOUEUR;
	}
	/**
	 * permet de recuperer le pseudo du joueur
	 * @return le pseudo
	 */
	public String getPSEUDO() {
		return PSEUDO;
	}

}
