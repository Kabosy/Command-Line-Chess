package pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import coordonnee.Position;

public class TestFou {

	@Test
	public void test() {
		Plateau plateau = new Plateau();
		//superposition
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(3, 1)));
		//passage au dessu
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(4, 2)));
		//deplacement en diagonal
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(3, 1)));
		plateau.moveTo(new Position(3,1), new Position(3,2));
		assertTrue(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(3, 1)));
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(8, 6)));
		//deplacement horizontal et vertical
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(2, 1)));
		assertFalse(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(1,0)));
		//prise de piece
		plateau.moveTo(new Position(7,7), new Position(7,5));
		assertTrue(plateau.getPieceAt(2, 0).contraintes(plateau, new Position(7,5)));
	}

}
