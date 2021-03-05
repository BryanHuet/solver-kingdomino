package model.plateau;

import model.pieces.Case;
import model.pieces.Castle;
import model.pieces.Paysage;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

public class Grille{

    // Attributs
    private int nbLigne;
    private int nbColonne;
    private Case[][] grille;
    private Castle castle;

    // Constructeur
    public Grille(int ligne, int colonne){

        nbLigne = ligne;
        nbColonne = colonne;
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

    public Paysage getPaysageAt(int x, int y) {
        return grille[x][y].getPaysage();
    }

    public Case[][] getGrille() {
        return this.grille;
    }

    public void setCase(int[] position, Case uneCase) {
        grille[position[0]][position[1]] = uneCase;
    }

    public Case getCase(int[] position) {
        return grille[position[0]][position[1]];
    }

    public String getPaysageName(int x, int y) {
        return grille[x][y].getPaysage().getName();
    }

    public boolean isOutofBound(int x, int y) {
        if (x < 0 || x >= nbLigne -1 || y < 0 || y >= nbColonne - 1) {
            return true;
        }
        return false;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }

}