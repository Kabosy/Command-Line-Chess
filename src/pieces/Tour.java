package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public class Tour extends Piece {

	public Tour(Position position, Joueur joueur) {
		super(position, joueur);
	}


	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
		Set<Position> result = new HashSet<Position>();
		Position posCourante = new Position (0,0);
		for (int lign = 0 ; lign < plateau.getSize();lign++) {
			for (int col =0; col < plateau.getSize();col++) {
				posCourante.setX(lign);
				posCourante.setY(col);
				if(contraintes(plateau, posCourante)) {
					result.add(posCourante);
				}
			}
		}
		
		
		return result;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "T" + PieceColor.RESET.getColor();
	}

	@Override
	public boolean contraintes(Plateau plateau, Position pos) {
		return checkLigne(plateau, 1, 0).contains(pos)
				|| checkLigne(plateau, -1, 0).contains(pos)
				|| checkLigne(plateau, 0, 1).contains(pos)
				|| checkLigne(plateau, 0, -1).contains(pos)
				|| (plateau.canBigRock(getJoueur()) && wantToBigRock(pos))
				|| (plateau.canLittleRock(getJoueur()) && wantToLittleRock(pos));
	}
	
	private Set<Position> checkLigne(Plateau plateau, int addX, int addY){
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
	
	@Override
	public int getScore() {
		return 5;
	}
	
	public boolean wantToBigRock(Position to) {
		return to.getX() == 3 && to.getY() == getJoueur().getStart();
	}
	
	public boolean wantToLittleRock(Position to) {
		return to.getX() == 5 && to.getY() == getJoueur().getStart();
	}
}
