package utils;

import java.util.Scanner;

import coordonnee.Position;
import pieces.PiecesSelectionnable;

public class Reader {
	
	public static Scanner scanner = new Scanner(System.in);

	
/**
 * return une position (x et y) en fonction d'une chaine de charactere en entree
 * @param entree
 * @return une position (x et y) en fonction d'une chaine de charactere en entree
 */
	public static Position stringToPos(String entree) {
		//if(entree.equals("r"))return null;
		int y=Integer.parseInt(entree.substring(1, 2)) - 1;
		int x=entree.charAt(0)-'A';
		Position pos = new Position(x,y);
		return pos;
	}
	
	/**
	 * return true si la saisie de l'utilisateur est correct, sinon false
	 * @param entree
	 * @return true si la saisie de l'utilisateur est correct, sinon false
	 */
	public static boolean isCorrect(String entree) {
		int x;
		try {
			x=Integer.parseInt(entree.substring(1, 2));
		}catch(NumberFormatException e) {
			return false;
		}
		if(entree.length()!=2 || entree.charAt(0)<'A' || entree.charAt(0)>'H' || x < 1 || x > 8) return false;
		else return true;
	}
	
	/**
	 * return une position en fonction de la saisie de l'utilisateur
	 * @return une position en fonction de la saisie de l'utilisateur
	 * @throws CancelException 
	 * @throws PositionFormatException 
	 */
	public static Position askPosition() throws CancelException, PositionFormatException {
		Position pos = null;
		while(pos==null) {
			String saisie;
			saisie = scanner.nextLine().toUpperCase();
			if(saisie.equals("R")) throw new CancelException();
			if(!isCorrect(saisie)) throw new PositionFormatException();
			pos=stringToPos(saisie);
		}
		return pos;
	}
	/**
	 * return true si l'entree est valide
	 * @param entree
	 * @return true si l'entree est valide
	 */
	public static boolean isPieceCorrect(String entree) {
		return entree.toUpperCase().equals("CAVALIER") || entree.toUpperCase().equals("DAME") || entree.toUpperCase().equals("FOU") || entree.toUpperCase().equals("TOUR");
	}
	/**
	 * return une piece selectionnable en fonction de la saisie de l'utilisateur
	 * @return une piece selectionnable en fonction de la saisie de l'utilisateur
	 */
	public static PiecesSelectionnable askPiece() {
		String saisie="";
		boolean correct = false;
		while (!correct) {
			try {
				saisie = scanner.nextLine();
				Reader r = new Reader();
				if(isPieceCorrect(saisie))correct=true;
			}
			catch(Exception e) {
				System.out.println("invalide");
			}
		}
		if(saisie.toUpperCase().equals("DAME"))return PiecesSelectionnable.DAME;
		if(saisie.toUpperCase().equals("CAVALIER"))return PiecesSelectionnable.CAVALIER;
		if(saisie.toUpperCase().equals("FOU"))return PiecesSelectionnable.FOU;
		if(saisie.toUpperCase().equals("TOUR"))return PiecesSelectionnable.TOUR;
		else return null;
		
		
	}
/*
	public static void main(String[] args) {
		Reader r = new Reader(null);
		System.out.println(r.askPosition());
		
	}*/

}
