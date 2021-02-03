package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public class Fou extends Piece {

	public Fou(Position position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
		Set<Position> res = new HashSet<Position>();
		res.addAll(checkDiagonale(plateau, 1, 1));
		res.addAll(checkDiagonale(plateau, -1, 1));
		res.addAll(checkDiagonale(plateau, 1, -1));
		res.addAll(checkDiagonale(plateau, -1, -1));
		return res;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "F" + PieceColor.RESET.getColor();
	}

	@Override
	public boolean contraintes(Plateau plateau, Position pos) {
		return checkDiagonale(plateau, 1, 1).contains(pos)
				|| checkDiagonale(plateau, -1, -1).contains(pos)
				|| checkDiagonale(plateau, -1, 1).contains(pos)
				|| checkDiagonale(plateau, 1, -1).contains(pos);
	}

	@Override
	public int getScore() {
		return 3;
	}
	
	private Set<Position> checkDiagonale(Plateau plateau, int addX, int addY){
		Set<Position> res = new HashSet<Position>();
		int i = 1;
		int coordX = getPosition().getX();
		int coordY = getPosition().getY();
		while(i * addX + coordX < plateau.getSize() 
				&& i * addY + coordY < plateau.getSize()
				&& i * addY + coordY >= 0
				&& i * addX + coordX >= 0
				&& (plateau.getPieceAt(i * addX + coordX, i * addY + coordY) == null)) {
			res.add(new Position(i * addX + coordX, i * addY + coordY));
			i ++;
		}
		if(i * addX + coordX < plateau.getSize() 
				&& i * addY + coordY < plateau.getSize()
				&& i * addY + coordY >= 0
				&& i * addX + coordX >= 0
				&& plateau.getPieceAt(i * addX + coordX, i * addY + coordY).getJoueur() != getJoueur()) res.add(new Position(i * addX + coordX, i * addY + coordY));
		return res;
	}
	
}
