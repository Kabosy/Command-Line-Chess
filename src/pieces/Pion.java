package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;
import utils.Reader;

public class Pion extends Piece{
	
	public Pion(Position position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public boolean contraintes(Plateau plateau,Position pos) {
		if(pos.getX() == this.getPosition().getX() && !estOccuperParUnAllier(pos, plateau)) {
			if(firstMove) {
				if(this.getJoueur() == Joueur.JOUEUR1) {
					if(pos.getY() - this.getPosition().getY() == 1 || (pos.getY() - this.getPosition().getY() == 2 && plateau.getPieceAt(pos.getX(), pos.getY() - 1) == null)) {
						return plateau.getPieceAt(pos) == null;
					}
					
				}else {
					if(pos.getY() - this.getPosition().getY() == -1 || (pos.getY() - this.getPosition().getY() == -2 && plateau.getPieceAt(pos.getX(), pos.getY() + 1) == null)) {
						return plateau.getPieceAt(pos) == null;
					}
				}
				
			}else {
				if(this.getJoueur() == Joueur.JOUEUR1) {
					if(pos.getY() - this.getPosition().getY() == 1) {
						return plateau.getPieceAt(pos) == null;
					}
				}else {
					if(pos.getY() - this.getPosition().getY() == -1) {
						return plateau.getPieceAt(pos) == null;
					}
				}
			}
		}else if(plateau.getPieceAt(pos) != null && plateau.getPieceAt(pos).getJoueur() != getJoueur()) {
			return Math.abs(pos.getX() - this.getPosition().getX()) == 1
					&&((this.getJoueur() == Joueur.JOUEUR1
						&& pos.getY() - this.getPosition().getY() == 1)
						||(this.getJoueur() == Joueur.JOUEUR2
						&& pos.getY() - this.getPosition().getY() == -1));
					
		}
		return false;
	}

	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
			
			Set<Position> result = new HashSet<Position>();
			for(int i = 0; i < plateau.getSize() ;i++ ) {
				Position posCourante = new Position(this.getPosition().getX(),i);
				if(contraintes(plateau,posCourante)) {
					result.add(posCourante);
				}
			}
			
		return result;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "P" + PieceColor.RESET.getColor();
	}

	@Override
	public int getScore() {
		return 1;
	}
	
	@Override
	public void endAction(Plateau plateau) {
		if(getPosition().getY() == getJoueur().getPionGoal()) {
			plateau.plateau[getPosition().getY()][getPosition().getX()] = null;
			System.out.print("En quel pièce voulez vous transformer le pion? : ");
			PiecesSelectionnable piece = Reader.askPiece();
			Piece toGo = null;
			switch(piece) {
			case DAME:
				toGo = new Dame(getPosition(), getJoueur());
			case CAVALIER:
				toGo = new Cavalier(getPosition(), getJoueur());
			case FOU:
				toGo = new Fou(getPosition(), getJoueur());
			case TOUR:
				toGo = new Tour(getPosition(), getJoueur());
			}
			plateau.plateau[getPosition().getY()][getPosition().getX()] = toGo;
		}
	}

}
