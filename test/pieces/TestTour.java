package pieces;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import coordonnee.Position;

public class TestTour {

	public TestTour() {
		
	}
		@Test
		public void test() {
			Plateau plateau = new Plateau();
			//superposition
			assertFalse(plateau.getPieceAt(0, 0).contraintes(plateau, new Position(0, 1)));
			//passage au dessu
			assertFalse(plateau.getPieceAt(0, 0).contraintes(plateau, new Position(0, 2)));
			//deplacement lateraux et horizontaux
			plateau.moveTo(new Position(0,0),new Position(0,3));
			assertTrue(plateau.getPieceAt(0, 3).contraintes(plateau, new Position(5, 3)));
			assertTrue(plateau.getPieceAt(0, 3).contraintes(plateau, new Position(0, 5)));
			//deplacement diagonal
			assertFalse(plateau.getPieceAt(0, 3).contraintes(plateau, new Position(1, 4)));
			//prise de piece
			assertTrue(plateau.getPieceAt(0, 3).contraintes(plateau, new Position(0,6 )));
	}

}
