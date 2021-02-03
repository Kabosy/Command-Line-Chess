package joueurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import coordonnee.Position;
import pieces.Piece;
import pieces.Plateau;
import pieces.Roi;

public class GestionIA extends GestionJoueur{

	public GestionIA() {
		super(Joueur.JOUEUR2, "Ordinateur");
	}
	
	
	/**
	 * permet a l'IA de choisir une piece dans le plateau
	 * @param plateau
	 * @return une position de piece
	 */
	public Position selectPositionPiece(Plateau plateau) {
		
		Position pos = null;
		boolean correct = false;
		
		
		Map<Position, Position> pions = pieceAttaquants(plateau);
		
		if(!pions.isEmpty()) {
			Piece piece = MeilleurAttaquant(plateau, pions);
			pos = piece.getPosition();
		}
		else {
			
			while(!correct) {
				pos = generatePosition(plateau);
				System.out.println("DEBUG 1 =====");
				if(plateau.getPieceAt(pos) != null) {
					if(plateau.getPieceAt(pos).getJoueur() == this.getLEJOUEUR()) {
						if(plateau.getPieceAt(pos).deplacementsPossibles(plateau) != null && !plateau.getPieceAt(pos).deplacementsPossibles(plateau).isEmpty()) {
							correct = true;
						}
					}
				}
			}
			
		}
		
		
		return pos;
	}
	
	
	private Map<Position, Position> pieceAttaquants(Plateau plateau) {
		Map<Position, Position> list = new HashMap<Position, Position>();
		for(int y = 0; y < plateau.plateau.length; y++) {
			for(int x = 0; x < plateau.plateau[0].length; x++) {
				if(plateau.plateau[x][y] != null && plateau.plateau[x][y].getJoueur() == this.getLEJOUEUR()) {
					Piece piece = plateau.plateau[x][y];
					for(Position pos : piece.deplacementsPossibles(plateau)) {
						if(plateau.getPieceAt(pos) != null && plateau.getPieceAt(pos).getJoueur() != this.getLEJOUEUR()) {
							list.put(piece.getPosition(), pos);
						}
					}
				}
			}
		}
		return list;
	}
	
	private Piece MeilleurAttaquant(Plateau plateau, Map<Position, Position> attaquants) {
		Piece piece = null;
		int difference = -999;
		
		for(Entry<Position, Position> entry : attaquants.entrySet()) {
			if(difference == -999) {
				difference = plateau.getPieceAt(entry.getValue()).getScore() - plateau.getPieceAt(entry.getKey()).getScore();
				piece = plateau.getPieceAt(entry.getKey());
				continue;
			}
			
			int t = plateau.getPieceAt(entry.getValue()).getScore() - plateau.getPieceAt(entry.getKey()).getScore();
			if(t > difference) {
				difference = t;
				piece = plateau.getPieceAt(entry.getKey());
			}
		}
		
		return piece;
	}
	
	/**
	 * une fois que l'IA a choisie sa piece cette methode permet a l'IA de choisir ou la jouer en fonction des regles de jeu
	 * @param plateau
	 * @param piece
	 * @return la position finale de la piece
	 */
	
	public Position selectPositionPieceFinale(Plateau plateau, Piece piece) {
		
		Position pos = hasPieceEnnemy(plateau, piece);
		boolean correct = false;
		
		if(pos == null) {
			while (!correct) {
				pos = generatePositionFinale(plateau, piece);
				if(piece.contraintes(plateau, pos)) {
					correct = true;
				}
			}
		}

		return pos;
	}
	
	// verifie si il n'y a pas de pieces ennemies sur la trajectoire de la piece donnee en parametre
	public Position hasPieceEnnemy(Plateau plateau, Piece piece) {
		for(Position pos : piece.deplacementsPossibles(plateau)) {
			if(plateau.getPieceAt(pos) != null && plateau.getPieceAt(pos).getJoueur() != this.getLEJOUEUR()) {
				return pos;
			}
		}
		
		return null;
	}
	
	
	private Position generatePosition(Plateau plateau) {
		List<Position> list = new ArrayList<>();
		
		for(int y = 0; y < plateau.plateau.length; y++) {
			for(int x = 0; x < plateau.plateau[0].length; x++) {
				if(plateau.plateau[x][y] != null && plateau.plateau[x][y].getJoueur() == this.getLEJOUEUR()) {
					list.add(plateau.plateau[x][y].getPosition());
				}
			}
		}
		System.out.println("DEBUG 3 =====");
		Collections.shuffle(list);
		
		if(list.size() > 1 && plateau.getPieceAt(list.get(0)) instanceof Roi) {
			return new Position(list.get(1).getX(), list.get(1).getY());
		}
		else {
			return new Position(list.get(0).getX(), list.get(0).getY());
		}
	}
	
	private Position generatePositionFinale(Plateau plateau, Piece piece) {
		for(Position p : piece.deplacementsPossibles(plateau)) {
			if(piece.contraintes(plateau, p)) {
				return p;
			}
		}

		return new Position((int) (Math.random() * (double) plateau.getSize()), (int) (Math.random() * (double) plateau.getSize()));
	}
	

}
