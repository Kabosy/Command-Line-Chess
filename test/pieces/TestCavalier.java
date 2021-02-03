package pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import coordonnee.Position;

public class TestCavalier {

	@Test
	public void test() {
		Plateau plateau = new Plateau();
		assertTrue(plateau.getPieceAt(1, 0).contraintes(plateau, new Position(0, 2)));
		assertTrue(plateau.getPieceAt(1, 0).contraintes(plateau, new Position(2, 2)));
		assertFalse(plateau.getPieceAt(1, 0).contraintes(plateau, new Position(3, 1)));
		plateau.moveTo(new Position(1, 0), new Position(3, 5));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(2, 3)));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(4, 3)));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(1, 4)));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(5, 4)));
		assertFalse(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(0, 0)));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(1, 6)));
		assertTrue(plateau.getPieceAt(3, 5).contraintes(plateau, new Position(5, 6)));
	}

}
