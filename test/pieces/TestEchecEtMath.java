package pieces;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import coordonnee.Position;
import joueurs.GestionJoueur;
import joueurs.Joueur;
import main.Game;


public class TestEchecEtMath {
	
	@Test
	public void test() {
	
	Plateau p = new Plateau();

	 Roi roi;
	 Dame d1;
	 Dame d2;
	 Dame d3;
	 GestionJoueur j1 = null;
	 GestionJoueur j2 = null;

	Game g = new Game(j1, j2) ;

	p=new Plateau();for(
	int y = 0;y<p.plateau.length;y++)
	{
		for (int x = 0; x < p.plateau[0].length; x++) {
			p.plateau[x][y] = null;
		}
	}

	roi=new Roi(new Position(0,3),Joueur.JOUEUR1);d1=new Dame(new Position(1,2),Joueur.JOUEUR2);d2=new Dame(new Position(1,3),Joueur.JOUEUR2);d3=new Dame(new Position(1,4),Joueur.JOUEUR2);

	p.plateau[0][3]=roi;p.plateau[0][2]=d1;p.plateau[0][3]=d2;p.plateau[0][4]=d3;

	assertTrue(g.detectionEchecEtMat(p, roi));
	}

}
