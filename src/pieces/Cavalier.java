package pieces;

import java.util.HashSet;
import java.util.Set;

import coordonnee.Position;
import joueurs.Joueur;

public class Cavalier extends Piece{
	
	public Cavalier(Position position, Joueur joueur) {
		super(position, joueur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<Position> deplacementsPossibles(Plateau plateau) {
		Set<Position> res = new HashSet<Position>();
		res.add(this.getPosition());
		
		Position pos1 = new Position(this.getPosition().getX()+1, this.getPosition().getY()+2);
		Position pos2 = new Position(this.getPosition().getX()+2, this.getPosition().getY()+1);
		Position pos3 = new Position(this.getPosition().getX()+2, this.getPosition().getY()-1);
		Position pos4 = new Position(this.getPosition().getX()+1, this.getPosition().getY()-2);
		Position pos5 = new Position(this.getPosition().getX()-1, this.getPosition().getY()-2);
		Position pos6 = new Position(this.getPosition().getX()-2, this.getPosition().getY()-1);
		Position pos7 = new Position(this.getPosition().getX()-2, this.getPosition().getY()+1);
		Position pos8 = new Position(this.getPosition().getX()-1, this.getPosition().getY()+2);
		
		if(plateau.checkPosition(pos1) && !this.estOccuperParUnAllier(pos1, plateau)) {res.add(pos1);}
		if(plateau.checkPosition(pos2) && !this.estOccuperParUnAllier(pos2, plateau)) {res.add(pos2);}
		if(plateau.checkPosition(pos3) && !this.estOccuperParUnAllier(pos3, plateau)) {res.add(pos3);}
		if(plateau.checkPosition(pos4) && !this.estOccuperParUnAllier(pos4, plateau)) {res.add(pos4);}
		if(plateau.checkPosition(pos5) && !this.estOccuperParUnAllier(pos5, plateau)) {res.add(pos5);}
		if(plateau.checkPosition(pos6) && !this.estOccuperParUnAllier(pos6, plateau)) {res.add(pos6);}
		if(plateau.checkPosition(pos7) && !this.estOccuperParUnAllier(pos7, plateau)) {res.add(pos7);}
		if(plateau.checkPosition(pos8) && !this.estOccuperParUnAllier(pos8, plateau)) {res.add(pos8);}
		
		return res;
	}

	@Override
	public String draw() {
		return this.getJoueur().getPlayerColor() + "C" + PieceColor.RESET.getColor();
	}

	@Override
	public boolean contraintes(Plateau plateau, Position pos) {
		//vérification de l'appartenance au plaetau de la position pos
				if((pos.getX() < plateau.getSize()) && (pos.getY() < plateau.getSize()) && !this.estOccuperParUnAllier(pos, plateau)) {
					if(((pos.getX() == this.getPosition().getX()+1) && (pos.getY() == this.getPosition().getY()+2))
							|| ((pos.getX() == this.getPosition().getX()+2) && (pos.getY() == this.getPosition().getY()+1))
							|| ((pos.getX() == this.getPosition().getX()-1) && (pos.getY() == this.getPosition().getY()+2))
							|| ((pos.getX() == this.getPosition().getX()-2) && (pos.getY() == this.getPosition().getY()+1))
							|| ((pos.getX() == this.getPosition().getX()+1) && (pos.getY() == this.getPosition().getY()-2))
							|| ((pos.getX() == this.getPosition().getX()+2) && (pos.getY() == this.getPosition().getY()-1))
							|| ((pos.getX() == this.getPosition().getX()-1) && (pos.getY() == this.getPosition().getY()-2))
							|| ((pos.getX() == this.getPosition().getX()-2) && (pos.getY() == this.getPosition().getY()-1))) 
					{
						return true;
					}
				}
		return false;
	}

	@Override
	public int getScore() {
		return 3;
	}

}
