package chrono;

import java.time.Duration;
import java.time.LocalTime;

import joueurs.GestionJoueur;
import joueurs.Joueur;
import utils.Reader;

public class Chronometre {

	private Duration tempsJoueur1 = Duration.ZERO;
	private Duration tempsJoueur2 = Duration.ZERO;
	
	
	/**
	 * actualise le temps total du joueur
	 * @param joueur
	 * @param depart
	 */
	public void check(Joueur joueur, LocalTime depart) {

		if(joueur==Joueur.JOUEUR1) {
			tempsJoueur1 = tempsJoueur1.plus(Duration.between(depart, LocalTime.now()));		
		}else if(joueur==Joueur.JOUEUR2) {
			tempsJoueur2 = tempsJoueur2.plus(Duration.between(depart, LocalTime.now()));
		}
	}
	
	public Duration getDuration(GestionJoueur joueur) {
		if(joueur.getLEJOUEUR() == Joueur.JOUEUR1) return tempsJoueur1;
		else return tempsJoueur2;
	}
}
