package echec.affichage;

/**
 * 
 * Classe permettant d'afficher le gagnant de la partie d'echec
 * @author Boukhebza Allan
 *
 */
public abstract class PrintWin {

	
	/**
	 * Permet d'afficher un message pour le gagnant
	 * @param winner le joueur gagnant
	 * 
	 * 	winner == 1 : le joueur 1 gagne
	 * 	winner == 2 : le joueur 2 gagne
	 * 	winner != 1 && winner != 2 : match nul
	 */
	public static void printWinner(int winner) {
		String str = "                  _   _        _                      _                 \n" + 
				"                 | | (_)      | |                    (_)                \n" + 
				" _ __   __ _ _ __| |_ _  ___  | |_ ___ _ __ _ __ ___  _ _ __   ___  ___ \n" + 
				"| '_ \\ / _` | '__| __| |/ _ \\ | __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _ \\/ _ \\\n" + 
				"| |_) | (_| | |  | |_| |  __/ | ||  __/ |  | | | | | | | | | |  __/  __/\n" + 
				"| .__/ \\__,_|_|   \\__|_|\\___|  \\__\\___|_|  |_| |_| |_|_|_| |_|\\___|\\___|\n" + 
				"| |                                                                     \n" + 
				"|_|                                                                     ";
		
		System.out.println(str);
		System.out.println();
		
		switch (winner) {
		case 1:
			System.out.println("Le joueur 1 gagne !");
			break;
		case 2:
			System.out.println("Le joueur 2 gagne !");
			break;
		default:
			System.out.println("Match nul !");
			break;
		}
	}
}
