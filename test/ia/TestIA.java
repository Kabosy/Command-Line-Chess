package ia;


import org.junit.Before;
import org.junit.Test;

import joueurs.GestionIA;
import pieces.Plateau;

public class TestIA {

	private Plateau plateau;
	private GestionIA ia;
	
	@Before
	public void before() {
		this.plateau = new Plateau();
		this.ia = new GestionIA();
	}
	
	@Test
	public void test() {
		
	}

}
