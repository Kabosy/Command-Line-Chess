package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public class Roi extends Piece implements Rockers {

	public boolean verificationEchecFaite = false;
	Set<Position> enEchec = new HashSet<Position>();

	public Roi(Position position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public boolean contraintes(Plateau plateau, Position pos) {
		return ((Math.abs(pos.getX() - this.getPosition().getX()) <= 1
				&& Math.abs(pos.getY() - this.getPosition().getY()) <= 1 && !estOccuperParUnAllier(pos, plateau))
				|| (plateau.canBigRock(getJoueur()) && wantToBigRock(pos))
				|| (plateau.canLittleRock(getJoueur()) && wantToLittleRock(pos)));
	}

	public boolean isPosEchec(Plateau plat, Position pos) {
		if (!verificationEchecFaite) {
			Position p;
			for (int lign = 0; lign < plat.getSize(); lign++) {
				for (int col = 0; col < plat.getSize(); col++) {
					p = new Position(lign, col);
					if (plat.getPieceAt(p) != null && plat.getPieceAt(p).getJoueur() != this.getJoueur()) {
						enEchec.addAll(plat.getPieceAt(p).deplacementsPossibles(plat));
					}
				}
			}
		}
		verificationEchecFaite =true;
		return enEchec.contains(pos);
	}

	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
		Set<Position> positions = new HashSet<Position>();

		for (int y = 0; y < plateau.plateau.length; y++) {
			for (int x = 0; x < plateau.plateau[0].length; x++) {
				Position p = new Position(x, y);
				if (contraintes(plateau, p)) {
					positions.add(p);
				}
			}
		}

		return positions;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "R" + PieceColor.RESET.getColor();
	}

	public int getScore() {
		return 20;
	}

	public boolean wantToBigRock(Position to) {
		return to.getX() == 2 && to.getY() == getJoueur().getStart();
	}

	public boolean wantToLittleRock(Position to) {
		return to.getX() == 6 && to.getY() == getJoueur().getStart();
	}

}
