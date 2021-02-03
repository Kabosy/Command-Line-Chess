package pieces;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import coordonnee.Position;
import joueurs.Joueur;

public class TestRoi {

	private Roi roi;
	private Pion pion;
	private Cavalier cavalier;
	
	private Plateau plateau;
	
	@Before
	public void before() {
		plateau = new Plateau();
		for(int y = 0; y < plateau.plateau.length; y++) {
			for(int x = 0; x < plateau.plateau[0].length; x++) {
				plateau.plateau[x][y] = null;
			}
		}
		
		roi = new Roi(new Position(0, 0), Joueur.JOUEUR1);
		pion = new Pion(new Position(2, 2), Joueur.JOUEUR2);
		cavalier = new Cavalier(new Position(4, 4), Joueur.JOUEUR2);
		
		plateau.plateau[0][0] = roi;
		plateau.plateau[2][2] = pion;
		plateau.plateau[4][4] = cavalier;
		
	}
	
	
	@Test
	public void testDeplacement() {
		System.out.println("TEST DEPLACEMENT");
		plateau.drawBoard();
		assertTrue(roi.contraintes(plateau, new Position(0, 1)));
		assertFalse(roi.contraintes(plateau, new Position(1, 1)));
		assertFalse(roi.contraintes(plateau, new Position(0, 2)));
		assertTrue(roi.contraintes(plateau, new Position(1, 0)));
		System.out.println("OK");
	}

}
