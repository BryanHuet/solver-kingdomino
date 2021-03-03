package model;

import java.util.*;

public class Grille{

    // Attributs
    private int nbLigne;
    private int nbCol;
    private Case[][] grille;

    // Constructeur
    public Grille(int ligne, int col){

        nbLigne = ligne;
        nbCol = col;
        grille = new Case[nbLigne][nbCol];
        setGrille();
    }

    private void setGrille() {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbCol; j++) {
                grille[i][j] = new Case(i,j);
            }
        }
    }

    public void afficheGrille() {
        for (int i = 0; i < nbLigne; i++) {
            System.out.print("|");
            for (int j = 0; j < nbCol; j++) {
                System.out.print(grille[i][j].getSymbole());
            }
        System.out.println("|");
        }
    }

    public int getNbLigne() {
        return this.nbLigne;
    }

    public int getNbCol() {
        return this.nbCol;
    }

}