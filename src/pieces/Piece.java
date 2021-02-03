package pieces;

import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public abstract class Piece {
	
	private Position position;
	private Joueur joueur;
	protected boolean firstMove = true;
	
	public Piece(Position position, Joueur joueur) {
		this.position = position;
		this.joueur = joueur;
	}
	/**
	 * return la position de la piece
	 * @return la position de la piece
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * return le joueur qui possede cette piece
	 * @return une instance de l'enum joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}
	/**
	 * change la position d'une piece
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * return true si la position est valide sur le plateau pour que la piece se deplace
	 * @param plateau
	 * @param pos
	 * @return true si la piece peut se deplacer, false sinon
	 */
	public abstract boolean contraintes(Plateau plateau, Position pos);
	/**
	 * renvoie un set de position possible pour la piece
	 * @param plateau
	 * @return le set de position possible pour la piece
	 */
	public abstract Set<Position> deplacementsPossibles(Plateau plateau);
	/**
	 * affiche la piece
	 * @return une chaine de charactere qui represente la piece
	 */
	public abstract String draw();
	/**
	 * retourne le score de la piece
	 * @return le score
	 */
	public abstract int getScore();
	
	/**
	 * action effectuee apres le deplacement de la piece
	 * @param plateau
	 */
	public void endAction(Plateau plateau) {};
	
	/**
	 * test si la case donnee en parametre est occupee par une piece alliee ou ennemie
	 * @param pos
	 * @param plat
	 * @return true si la piece est alliee ou false sinon
	 */
	public boolean estOccuperParUnAllier(Position pos, Plateau plat) {
		if(plat.getPieceAt(pos) == null) return false;
		return plat.getPieceAt(pos).getJoueur() == this.joueur ;
	}

}
