package model.plateau;

import model.pieces.Case;
import model.pieces.Paysage;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

public class Grille{

    // Attributs
    private int nbLigne;
    private int nbColonne;
    private Case[][] grille;

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

    /*
    public boolean isColliding(PutDomino jouer) {
        try {
            if (jouer.getOrientation().equals("horizontal") || jouer.getOrientation().equals("horizontalReversed")) {
                if (grille[jouer.getX()][jouer.getY()].isOccuped() == false && grille[jouer.getX()][jouer.getY()+1].isOccuped() == false)
                        return false;
            }
            else if (jouer.getOrientation().equals("vertical") || jouer.getOrientation().equals("verticalReversed")) {
                if (grille[jouer.getX()][jouer.getY()].isOccuped() == false && grille[jouer.getX()+1][jouer.getY()].isOccuped() == false)
                        return false;
                }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Attention un index est invalide : " + e.getMessage());
        }
        return true;
    }

    /*
    public boolean neighbourValid(PutDomino jouer) {
        if (jouer.getOrientation().equals("horizontal") || jouer.getOrientation().equals("reversed")) {
            if (jouer.getDomino().getExtremiteGauche().getPaysage().equals(grille[jouer.getX()][jouer.getY()]))
        }
    }

     */

    /*
    public void playDomino(PutDomino jouer) {
        if (!isColliding(jouer)) {
            if (jouer.getOrientation().equals("horizontal")) {
                grille[jouer.getX()][jouer.getY()] = jouer.getDomino().getExtremiteGauche();
                grille[jouer.getX()][jouer.getY()+1] = jouer.getDomino().getExtremiteDroite();
            }
            else if (jouer.getOrientation().equals("vertical")) {
                grille[jouer.getX()][jouer.getY()] = jouer.getDomino().getExtremiteGauche();
                grille[jouer.getX()+1][jouer.getY()] = jouer.getDomino().getExtremiteDroite();
            }
            else if (jouer.getOrientation().equals("horizontalReversed")) {
                grille[jouer.getX()][jouer.getY()] = jouer.getDomino().getExtremiteDroite();
                grille[jouer.getX()][jouer.getY()+1] = jouer.getDomino().getExtremiteGauche();
            }
            else if (jouer.getOrientation().equals("verticalReversed")) {
                grille[jouer.getX()][jouer.getY()] = jouer.getDomino().getExtremiteDroite();
                grille[jouer.getX()+1][jouer.getY()] = jouer.getDomino().getExtremiteGauche();
            }
        }
    }
    */

    public Paysage getPaysageAt(int x, int y) {
        return grille[x][y].getPaysage();
    }

    public Case[][] getGrille() {
        return this.grille;
    }

    public void setCase(int[] position, Case uneCase) {
        grille[position[0]][position[1]] = uneCase;
    }

}