package echec.affichage;

import joueurs.GestionIA;
import joueurs.GestionJoueur;
import joueurs.Joueur;
import main.Game;
import utils.Reader;

public class MenuDepart {

	/**
	 * cette methode sert a afficher le menu de depart ainsi que de demander une saisie a l'utilisateur afin qu'il puisse 1-jouer en pvp , 
	 * 2-jouer en PVE , 3 lire les regles  ainsi que le choix des pseudos des joueurs en debut de partie
	 * @param args
	 */
	public static void main(String[] args) {
		launchMenu();
	}
	
	public static void launchMenu() {
		boolean done = false;
		for(int i = 0; i < 50; i++) {
			System.out.println();
		}
		System.out.println("\n" + 
				"\n" + 
				"                                                                          _______   ______  __    __   _______   ______ \n" + 
				"                                                                         |   ____| /      ||  |  |  | |   ____| /      |\n" + 
				"                                                                         |  |__   |  ,----'|  |__|  | |  |__   |  ,----'\n" + 
				"                                                                         |   __|  |  |     |   __   | |   __|  |  |     \n" + 
				"                                                                         |  |____ |  `----.|  |  |  | |  |____ |  `----.\n" + 
				"                                                                         |_______| \\______||__|  |__| |_______| \\______|\n" + 
				"                                                                                                    \n" + 
				"\n" + "");
		System.out.println(
				"\n\n");
		System.out.println("\n" + 
				"\n" + 
				"                          __              _____        _____        _        ___               _____        ______       _       ____               _____            _            \n" + 
				"                         /_ |            |  __ \\      |  __ \\      | |      |__ \\             |  __ \\      |  ____|     | |     |___ \\             |  __ \\          | |           \n" + 
				"                          | |   ______   | |__) |_   _| |__) |     | |         ) |   ______   | |__) |_   _| |__        | |       __) |   ______   | |__) |___  __ _| | ___  ___  \n" + 
				"                          | |  |______|  |  ___/\\ \\ / /  ___/      | |        / /   |______|  |  ___/\\ \\ / /  __|       | |      |__ <   |______|  |  _  // _ \\/ _` | |/ _ \\/ __| \n" + 
				"                          | |            | |     \\ V /| |          | |       / /_             | |     \\ V /| |____      | |      ___) |            | | \\ \\  __/ (_| | |  __/\\__ \\ \n" + 
				"                          |_|            |_|      \\_/ |_|          |_|      |____|            |_|      \\_/ |______|     |_|     |____/             |_|  \\_\\___|\\__, |_|\\___||___/ \n" + 
				"                                                                                                                                                                __/ |             \n" + 
				"                                                                                                                                                               |___/              \n" + 
				"                                                                                                                     _:_\n" + 
				"                                                                                                                    '-.-'\n" + 
				"                                                                                                           ()      __.'.__\n" + 
				"                                                                                                        .-:--:-.  |_______|\n" + 
				"                                                                                                 ()      \\____/    \\=====/\n" + 
				"                                                                                                 /\\      {====}     )___(\n" + 
				"                                                                                      (\\=,      //\\\\      )__(     /_____\\\n" + 
				"                                                                      __    |'-'-'|  //  .\\    (    )    /____\\     |   |\n" + 
				"                                                                     /  \\   |_____| (( \\_  \\    )__(      |  |      |   |\n" + 
				"                                                                     \\__/    |===|   ))  `\\_)  /____\\     |  |      |   |\n" + 
				"                                                                    /____\\   |   |  (/     \\    |  |      |  |      |   |\n" + 
				"                                                                     |  |    |   |   | _.-'|    |  |      |  |      |   |\n" + 
				"                                                                     |__|    )___(    )___(    /____\\    /____\\    /_____\\\n" + 
				"                                                                    (====)  (=====)  (=====)  (======)  (======)  (=======)\n" + 
				"                                                                    }===={  }====={  }====={  }======{  }======{  }======={\n" + 
				"                                                                   (______)(_______)(_______)(________)(________)(_________)\n" + 
				"\n" + 
				"");
		for(int i = 0; i < 7; i++) {
			System.out.println();
		}
		System.out.println("Que voulez-vous faire ?");
		while(Reader.scanner.hasNextLine()&&!done) {
			String input = Reader.scanner.nextLine();
			if(input.equals("1")) {
				/*LAUNCH GAME*/
				done = true;
				
				System.out.println("Veuillez saisir le pseudo du joueur 1 :");
				String pseudoj1 = Reader.scanner.nextLine();
				GestionJoueur j1 = new GestionJoueur(Joueur.JOUEUR1, pseudoj1.toUpperCase());
				
				System.out.println("Veuillez saisir le pseudo du joueur 2 :");
				String pseudoj2 = Reader.scanner.nextLine();
				GestionJoueur j2 = new GestionJoueur(Joueur.JOUEUR2, pseudoj2.toUpperCase());
				Game game = new Game(j1 , j2);
				game.startGame();
			} else if(input.equals("2")) {
				
				done = true;
				
				System.out.println("Veuillez saisir le pseudo du joueur 1 :");
				String pseudoj1 = Reader.scanner.nextLine();
				GestionJoueur j1 = new GestionJoueur(Joueur.JOUEUR1, pseudoj1.toUpperCase());
				GestionIA ia = new GestionIA();
				Game game = new Game(j1, ia);
				
				game.startGame();
				
			} else if(input.equals("3")) {
				done = true;
				Regles.launchRegles();
			} else {
				System.out.println("Entrée invalide.");
			}
		}
	}
}
