package pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import coordonnee.Position;

public class TestDame {

	@Test
	public void test() {
		Plateau plateau = new Plateau();
		assertFalse(plateau.getPieceAt(4, 0).contraintes(plateau, new Position(3, 2)));
		assertFalse(plateau.getPieceAt(4, 0).contraintes(plateau, new Position(5, 2)));
		assertFalse(plateau.getPieceAt(4, 0).contraintes(plateau, new Position(2, 1)));
		plateau.moveTo(new Position(4, 0), new Position(4, 2));
		plateau.drawBoard();
		
		/* Horizontaux */
		for(int x = 0; x < plateau.getSize(); x++) {
			if(x != 4) assertTrue(plateau.getPieceAt(4, 2).contraintes(plateau, new Position(x, 2)));
		}
		
		/* Verticaux + entree sur ennemi */
		for(int y = 3; y < plateau.getSize() - 1; y++) {
			assertTrue(plateau.getPieceAt(4, 2).contraintes(plateau, new Position(4, y)));
		}
		
		/* Passer par dessus des ennemis */
		assertFalse(plateau.getPieceAt(4, 2).contraintes(plateau, new Position(4, 7)));
	}

}
