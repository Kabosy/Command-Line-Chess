package pieces;

import coordonnee.Position;
import joueurs.Joueur;

public class Plateau {
	
	public Piece[][] plateau;
	public int scoreJ1 = 0;
	public int scoreJ2 = 0;

	public Plateau() {
		this.plateau = new Piece[8][8];
		
		/* Les tours */
		this.plateau[0][0] = new Tour(new Position(0, 0), Joueur.JOUEUR1);
		this.plateau[0][7] = new Tour(new Position(7, 0), Joueur.JOUEUR1);
		this.plateau[7][0] = new Tour(new Position(0, 7), Joueur.JOUEUR2);
		this.plateau[7][7] = new Tour(new Position(7, 7), Joueur.JOUEUR2);
		
		/* Les cavaliers */
		this.plateau[0][1] = new Cavalier(new Position(1, 0), Joueur.JOUEUR1);
		this.plateau[0][6] = new Cavalier(new Position(6, 0), Joueur.JOUEUR1);
		this.plateau[7][1] = new Cavalier(new Position(1, 7), Joueur.JOUEUR2);
		this.plateau[7][6] = new Cavalier(new Position(6, 7), Joueur.JOUEUR2);
		
		/* Les fous */
		this.plateau[0][2] = new Fou(new Position(2, 0), Joueur.JOUEUR1);
		this.plateau[0][5] = new Fou(new Position(5, 0), Joueur.JOUEUR1);
		this.plateau[7][2] = new Fou(new Position(2, 7), Joueur.JOUEUR2);
		this.plateau[7][5] = new Fou(new Position(5, 7), Joueur.JOUEUR2);
		
		/* Les rois */
		this.plateau[0][4] = new Roi(new Position(4, 0), Joueur.JOUEUR1);
		this.plateau[7][4] = new Roi(new Position(4, 7), Joueur.JOUEUR2);
		
		/* Les dames */
		this.plateau[0][3] = new Dame(new Position(3, 0), Joueur.JOUEUR1);
		this.plateau[7][3] = new Dame(new Position(3, 7), Joueur.JOUEUR2);
		
		/* Les pions */
		for(int i = 0; i < getSize(); i++) {
			this.plateau[1][i] = new Pion(new Position(i, 1), Joueur.JOUEUR1);
			this.plateau[6][i] = new Pion(new Position(i, 6), Joueur.JOUEUR2);
		}
	}
	
	public int getSize() {
		return plateau.length;
	}
	
	public Piece getPieceAt(int x, int y) {
		return plateau[y][x];
	}
	
	public Piece getPieceAt(Position pos) {
		return getPieceAt(pos.getX(), pos.getY());
	}
	
	private boolean checkRock(Position from, Position to) {
		if(getPieceAt(from) instanceof Rockers) {
			Rockers rocker = ((Rockers) getPieceAt(from));
			int start = getPieceAt(from).getJoueur().getStart();
			Joueur joueur = getPieceAt(from).getJoueur();
			if(rocker.wantToBigRock(to)) {
				plateau[start][0] = null;
				plateau[start][4] = null;
				plateau[start][2] = new Roi(new Position(2, start), joueur);
				plateau[start][3] = new Tour(new Position(3, start), joueur);
				return true;
			}else if(rocker.wantToLittleRock(to)){
				plateau[start][7] = null;
				plateau[start][4] = null;
				plateau[start][6] = new Roi(new Position(2, start), joueur);
				plateau[start][5] = new Tour(new Position(3, start), joueur);
				return true;
			}else return false;
		}else return false;
	}
	
	public void moveTo(Position from, Position to) {
		if(!checkRock(from, to)) {
			if(getPieceAt(to) != null) {
				plateau[to.getY()][to.getX()] = null;
			}
			getPieceAt(from).firstMove = false;
			plateau[to.getY()][to.getX()] = getPieceAt(from);
			plateau[to.getY()][to.getX()].setPosition(to);
			plateau[from.getY()][from.getX()] = null;
			getPieceAt(to).endAction(this);
		}
	}
	
	public Roi getRoi(Joueur j) {
		for (int i = 0; i < getSize() ;i++) {
			for(int k = 0; k < getSize(); k++) {
				if((getPieceAt(i, k) instanceof Roi ) && getPieceAt(i,k).getJoueur().equals(j)) {
					return (Roi)getPieceAt(i,k);
				}
			}
		}
		return null;
	}
	
	public void drawBoard() {
		drawBoard(null);
	}
	
	public void drawBoard(Piece piece) {
		
		/* Les lettres */
		
		System.out.print("  ");
		for(int i = 0; i < plateau[0].length; i++) {
			System.out.print(' ');
			System.out.print(" " + (char) ('A' + i) + " ");
			System.out.print(' ');
		}
		System.out.println(' ');
		
		/* Haut du plateau */
		System.out.print(" ┌");
		for(int i = 0; i < plateau[0].length; i++) {
			System.out.print("-----");
		}
		System.out.println('┐');
		
		/* Pour chaque ligne */
		for(int ligne = 0; ligne < plateau.length; ligne++) {
			/* Haut de la case */
			System.out.print(" |");
			for(int col = 0; col < plateau[0].length; col++) {
				System.out.print('┌');
				System.out.print("---");
				System.out.print('┐');
			}
			System.out.println('|');
			
			/* Millieu de la case */
			System.out.print(ligne + 1);
			System.out.print("|");
			
			for(int col = 0; col < plateau[0].length; col++) {
				System.out.print('|');
				try {
					String subrillance;
					if(piece != null && piece.contraintes(this, new Position(col, ligne))) subrillance = PieceColor.YELLOw.getColor() + "X" + PieceColor.RESET.getColor();
					else subrillance = " ";
					try {
						System.out.print(subrillance + plateau[ligne][col].draw() + subrillance);
					}catch(NullPointerException e) {
						System.out.print(subrillance + " " + subrillance);
					}
				}catch(NullPointerException e) {
					System.out.print("   ");
				}
				System.out.print('|');
			}
			
			System.out.println('|');
			
			/* Bas de la case */
			System.out.print(" |");
			for(int col = 0; col < plateau[0].length; col++) {
				System.out.print('└');
				System.out.print("---");
				System.out.print('┘');
			}
			System.out.println('|');
		}
		
		System.out.print(" └");
		for(int i = 0; i < plateau[0].length; i++) {
			System.out.print("-----");
		}
		System.out.println('┘');
	}
	
	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		plateau.drawBoard();
	}
	
	public boolean checkPosition(Position pos) {
		return pos.getX() >= 0 && pos.getX() < getSize() && pos.getY() >= 0 && pos.getY() < getSize();
	}
	
	public boolean canLittleRock(Joueur joueur) {
		return canRock(joueur, 7);
	}
	
	public boolean canBigRock(Joueur joueur) {
		return canRock(joueur, 0);
	}
	
	private boolean canRock(Joueur joueur, int locationOfTowerX) {
		Piece king = getPieceAt(4, joueur.getStart());
		Piece tour = getPieceAt(locationOfTowerX, joueur.getStart());
		boolean pieceEntre = false;
		int incr;
		int i = 4;
		if(locationOfTowerX > 4) incr = 1;
		else incr = -1;
		i += incr;
		while(!pieceEntre && i < 7 && i > 1) {
			pieceEntre = getPieceAt(i, joueur.getStart()) != null;
			i += incr;
		}
		return king != null
				&& tour != null
				&& king.getJoueur() == joueur
				&& tour.getJoueur() == joueur
				&& king instanceof Roi
				&& tour instanceof Tour
				&& king.firstMove
				&& tour.firstMove
				&& !pieceEntre;
	}

}
