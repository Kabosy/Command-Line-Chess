package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public class Dame extends Piece{
	
	public Dame(Position position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
		Tour uneTour = new Tour(getPosition(), getJoueur());
		Fou unFou = new Fou(getPosition(), getJoueur());
		Set<Position> res = new HashSet<Position>();
		res.add(this.getPosition());
		res.addAll(uneTour.deplacementsPossibles(plateau));
		res.addAll(unFou.deplacementsPossibles(plateau));
		
		return res;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "D" + PieceColor.RESET.getColor();
	}

	@Override
	public boolean contraintes(Plateau plateau, Position pos) {
		Tour uneTour = new Tour(getPosition(), getJoueur());
		Fou unFou = new Fou(getPosition(), getJoueur());
		
		return uneTour.contraintes(plateau, pos) || unFou.contraintes(plateau, pos);
	}

	@Override
	public int getScore() {
		return 9;
	}

}
