package main;

import java.time.LocalTime;

import chrono.Chronometre;
import coordonnee.Position;
import echec.affichage.PrintWin;
import joueurs.GestionIA;
import joueurs.GestionJoueur;
import joueurs.Joueur;
import pieces.Piece;
import pieces.PieceColor;
import pieces.Plateau;
import pieces.Roi;
import utils.CancelException;
import utils.PositionFormatException;
import utils.Reader;

public class Game {

	private Plateau plateau;
	private boolean inGame;
	private GestionJoueur j1;
	private GestionJoueur j2;
	private Chronometre chrono = new Chronometre();

	public Game(GestionJoueur j1, GestionJoueur j2) {
		this.inGame = false;
		this.plateau = new Plateau();
		this.j1 = j1;
		this.j2 = j2;
	}

	public void startGame() {
		this.inGame = true;
		runGame();
	}

	public void finishGame() {
		this.inGame = false;
	}

	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void runGame() {
		while (this.inGame != false) {
			if(this.inGame != false) {
				tourJoueur(j1);
			}
			if(this.inGame != false) {
				tourJoueur(j2);
			}
		}
		
		

	}
	
	private boolean detectionEchec(Joueur joueur) {
		return false;
		/*
		Roi roi = plateau.getRoi(joueur);
		return roi.isPosEchec(plateau, roi.getPosition());
		*/
	}

	private void detectionFinDeTour() {
		Roi roi1 = plateau.getRoi(Joueur.JOUEUR1), roi2 = plateau.getRoi(Joueur.JOUEUR2);

		if(roi1 != null) {
			/*if(detectionEchecEtMat(plateau,roi1) || detectionPat(this.plateau, roi1)) {
				this.inGame = false;
				if(roi1.getJoueur()==Joueur.JOUEUR1)PrintWin.printWinner(1);
			}*/
		}else{
			PrintWin.printWinner(2);
			System.exit(1);
		}

		if(roi2 != null) {
			/*if(detectionEchecEtMat(plateau,roi2) || detectionPat(this.plateau, roi2)) {
				this.inGame = false;
				if(roi2.getJoueur()==Joueur.JOUEUR2)PrintWin.printWinner(2);
			}*/
		}else{
			PrintWin.printWinner(1);
			System.exit(1);
		}

		

	}


	public boolean detectionEchecEtMat(Plateau plateau,Roi roi) {
		if(roi.isPosEchec(this.plateau, roi.getPosition())) {
			for(Position pos : roi.deplacementsPossibles(this.plateau)) {
				if(!roi.isPosEchec(this.plateau, pos)) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	public boolean detectionPat(Plateau p, Roi roi) {
		if(!roi.isPosEchec(p , roi.getPosition())) {
			if(roi.deplacementsPossibles(p).isEmpty()) return false;
			for(Position pos : roi.deplacementsPossibles(p)) {
				if(!roi.isPosEchec(p , pos)) {
					return false;
				}
			}

			return true;
		}

		return false;
	}
	
	private void tourJoueur(GestionJoueur joueur) {
		LocalTime t = LocalTime.now();
		
		Position positionPiece = null;
		Position positionFinal = null;
		
		if(joueur instanceof GestionIA) {
			GestionIA ia = (GestionIA) joueur;
			positionPiece = ia.selectPositionPiece(plateau);
			positionFinal = ia.selectPositionPieceFinale(plateau, this.plateau.getPieceAt(positionPiece));
		}else{
			boolean ok = false;
			do {
				clearScreen();
				this.plateau.drawBoard();
				detectionFinDeTour();
				System.out.println(joueur.getLEJOUEUR().getPlayerColor() + "=== TOUR DE " + joueur.getPSEUDO() + " ===" + PieceColor.RESET.getColor());
				System.out.println("Score de "+j1.getPSEUDO()+": " + plateau.scoreJ1 + "pts     Score de "+j2.getPSEUDO()+": " + plateau.scoreJ2 +"pts");
				System.out.println("Temps de "+j1.getPSEUDO()+": " + chrono.getDuration(j1) + " ; "+j2.getPSEUDO()+": "+chrono.getDuration(j2));
				boolean canCancel;
				if(!detectionEchec(joueur.getLEJOUEUR())) {
					positionPiece = getPositionPiece(joueur.getLEJOUEUR());
					canCancel = true;
				}else {
					positionPiece = plateau.getRoi(joueur.getLEJOUEUR()).getPosition();
					System.out.println("VOUS ETES EN POSITION D'ECHEC. APPUYEZ SUR ENTREE POUR CONTINUER");
					Reader.scanner.nextLine();
					canCancel = false;
				}
				clearScreen();
				plateau.drawBoard(plateau.getPieceAt(positionPiece));
				boolean posFinalOk = false;
				while(!posFinalOk) {
					try {
						positionFinal = getPositionFinal(joueur.getLEJOUEUR(), this.plateau.getPieceAt(positionPiece));
						posFinalOk = true;
						ok = true;
					} catch (CancelException e) {
						if(canCancel) posFinalOk = true;
						else System.out.println("Vous ne pouvez pas cancel");
					}
				}
			}while(!ok);
		}
		if(plateau.getPieceAt(positionFinal)!=null) {
			plateau.scoreJ1+=plateau.getPieceAt(positionFinal).getScore();
		}
		this.plateau.moveTo(positionPiece, positionFinal);

		chrono.check(joueur.getLEJOUEUR(), t);
		
		try {
			plateau.getRoi(j1.getLEJOUEUR()).verificationEchecFaite = false;
			plateau.getRoi(j2.getLEJOUEUR()).verificationEchecFaite = false;
		}catch(NullPointerException e) {
			
		}
	}

	private Position getPositionPiece(Joueur joueur) {
		Position positionPiece = null;
		boolean correct = false;

		while(!correct) {
			System.out.print( "Entrez les coordonnees de votre piece: ");
			try {
				positionPiece = Reader.askPosition();
				if(this.plateau.getPieceAt(positionPiece) != null) {
					if(this.plateau.getPieceAt(positionPiece).getJoueur() == joueur) {
						if(this.plateau.getPieceAt(positionPiece).deplacementsPossibles(plateau) != null && !this.plateau.getPieceAt(positionPiece).deplacementsPossibles(plateau).isEmpty()) {
							correct = true;
						}
					}
				}
			} catch (CancelException | PositionFormatException | StringIndexOutOfBoundsException e) {
				System.out.println("saisissez une position valide svp \n "
						+ "Rappel : lettre MAJUSCULE entre A et H suivi d'un chiffre entre 1 et 8\n"
						+ "Exemple : F5 ou A1");
			}
		}

		return positionPiece;
	}

	private Position getPositionFinal(Joueur joueur, Piece piece) throws CancelException {
		Position positionPiece = null;
		
		boolean correct = false;

		while (!correct) {
			System.out.print( "Entrez les coordonnees finales de votre piece (Entrez R pour annuler): ");
			try {
				positionPiece = Reader.askPosition();
				if(piece.contraintes(plateau, positionPiece)) {
					correct = true;
				}
			} catch (PositionFormatException | StringIndexOutOfBoundsException e) {
				System.out.println("saisissez une position valide svp \n "
						+ "Rappel : lettre MAJUSCULE entre A et H suivi d'un chiffre entre 1 et 8\n"
						+ "Exemple : F5 ou A1");
			}
		}

		return positionPiece;
	}
}
