package model.plateau;

import model.pieces.Case;

public class Grille{

    // Attributs
    private int nbLigne;
    private int nbColonne;
    private Case[][] grille;

    // Constructeur
    public Grille(int ligne, int col){

        nbLigne = ligne;
        nbColonne = col;
        grille = new Case[nbLigne][nbColonne];
        setGrille();
    }

    private void setGrille() {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                grille[i][j] = new Case();
            }
        }
    }

    public void afficheGrille() {
        for (int i = 0; i < nbLigne; i++) {
            System.out.print("|");
            for (int j = 0; j < nbColonne; j++) {
                System.out.print(grille[i][j].getSymbole());
            }
        System.out.println("|");
        }
    }

    public int getNbLigne() {
        return this.nbLigne;
    }

    public int getNbColonne() {
        return this.nbColonne;
    }

}