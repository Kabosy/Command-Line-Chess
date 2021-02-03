package echec.affichage;

import joueurs.GestionJoueur;
import joueurs.Joueur;
import main.Game;
import utils.Reader;

public class Regles {
	public static void main(String[] args) {
		
	}
	
	public static void launchRegles() {
		for(int i = 0; i < 50; i++) {
			System.out.println();
		}
		System.out.println("\n" + 
				"\n" + 
				"                                                                    _  _  _    _______  _______  _______  _        _______  _______    _  _  _    \n" + 
				"                                                                   / )/ )/ )  (  ____ )(  ____ \\(  ____ \\( \\      (  ____ \\(  ____ \\  ( \\( \\( \\   \n" + 
				"                                                                  / // // /   | (    )|| (    \\/| (    \\/| (      | (    \\/| (    \\/   \\ \\\\ \\\\ \\  \n" + 
				"                                                                 / // // /    | (____)|| (__    | |      | |      | (__    | (_____     \\ \\\\ \\\\ \\ \n" + 
				"                                                                ( (( (( (     |     __)|  __)   | | ____ | |      |  __)   (_____  )     ) )) )) )\n" + 
				"                                                                 \\ \\\\ \\\\ \\    | (\\ (   | (      | | \\_  )| |      | (            ) |    / // // / \n" + 
				"                                                                  \\ \\\\ \\\\ \\   | ) \\ \\__| (____/\\| (___) || (____/\\| (____/\\/\\____) |   / // // /  \n" + 
				"                                                                   \\_)\\_)\\_)  |/   \\__/(_______/(_______)(_______/(_______/\\_______)  (_/(_/(_/   \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"Le jeu d'echecs est un jeu de plateau strategique joueur contre joueur. Chaque joueur possede 16 pieces dont 8 pions, 2 tours, 2 cavaliers, 2 fous, 1 Reine et 1 Roi.\n" + 
				"\n" + 
				"Chaque piece possede une maniere de se deplacer unique :\n" + 
				"\n" + 
				"Le pion peut se deplacer d'une seule case vers l'avant (donc il ne peut pas reculer). Au premier tour le joueur peut deplacer le pion de deux cases.\n" + 
				"\n" + 
				"La tour se deplace horizontalement et verticalement.\n" + 
				"\n" + 
				"Le cavalier se deplace seulement en realisant un \"L\".\n" + 
				"\n" + 
				"Le fou se deplace seulement diagonalement.\n" + 
				"\n" + 
				"La Reine possede les memes proprietes de deplacements la tour et le fou combine.\n" + 
				"\n" + 
				"Le Roi ne peut que se deplacer d'une case dans n'importe quelle direction, avant ou arriere.\n" + 
				"\n" + 
				"Chaque piece ne peut se deplacer qu'une fois par tour.\n" + 
				"\n" + 
				"Condition de victoires :\n" + 
				"-Le total des points d'un des joueurs est irrattrapable par son adversaire\n" + 
				"-Echec et mat : Le roi adverse ne peut pas se sauver a l'aide d'un deplacement\n" + 
				"-Pat : à voir                                                                                                      \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"                                                                                                                                                  \n" + 
				"Retour au menu principale : R                                                                                                                                                  \n" + 
				"\n" + 
				"");
		for(int i = 0; i < 10; i++) {
			System.out.println();
		}
		String input = Reader.scanner.nextLine();
		while((!input.toLowerCase().equals("j"))&&(!input.toLowerCase().equals("r"))){
			System.out.println("Entree invalide");
			input = Reader.scanner.nextLine();
		}
		MenuDepart.launchMenu();
	}
}
