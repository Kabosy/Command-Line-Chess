package pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import coordonnee.Position;

public class TestPion {

	@Test
	public void test() {
		Plateau plateau = new Plateau();
		assertTrue(plateau.getPieceAt(0, 1).contraintes(plateau, new Position(0, 2)));
		assertTrue(plateau.getPieceAt(0, 1).contraintes(plateau, new Position(0, 3)));
		assertFalse(plateau.getPieceAt(0, 1).contraintes(plateau, new Position(1, 0)));
		assertFalse(plateau.getPieceAt(0, 1).contraintes(plateau, new Position(1, 3)));
	}

}
