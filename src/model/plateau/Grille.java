package model.plateau;

import model.pieces.cases.Case;
import model.pieces.cases.Castle;
import model.pieces.cases.Paysage;
import model.pieces.cases.Vide;
import model.pieces.domino.Domino;
import model.plateau.actions.PutCastle;

public class Grille{

    // Attributs
    private final int nbLigne;
    private final int nbColonne;
    private final Case[][] grille;
    // Sauvegarde
    private Case castle;

    /***
     *
     * @param ligne Nombre de lignes
     * @param colonne Nombre de colonnes
     */
    public Grille(int ligne, int colonne){

        nbLigne = ligne;
        nbColonne = colonne;
        grille = new Case[nbLigne][nbColonne];
        setGrille();
    }

    /***
     * Méthode initialisant la grille de Cases.
     */
    private void setGrille() {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                grille[i][j] = new Vide();
            }
        }
    }

    /***
     * Méthode d'affichage de la grille.
     */
    public void afficheGrille() {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                System.out.print("|" + grille[i][j].getSymbole() + "|");
            }
        System.out.println("");
        }
    }

    /***
     *
     * @param x Index x (ligne)
     * @param y Index y (colonne)
     * @return Un booléen qui valide ou non le fait qu'une cordonnée est hors index sur la grille.
     */
    public boolean isOutofBound(int x, int y) {
        if (x < 0 || x > nbLigne -1 || y < 0 || y > nbColonne - 1) {
            return true;
        }
        return false;
    }

    /***
     * Getters
     */
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

    public Case getCaseBis(int x, int y) { return grille[x][y]; }

    public String getPaysageName(int x, int y) {
        return grille[x][y].getPaysage().getName();
    }

    public Case getCastle() {
        if (castle != null) {
            return castle;
        }
        else {
            System.out.println("Attention ! aucun château n'existait, un a été mis par défaut au milieu de la grille.");
            PutCastle pCastle = new PutCastle(this, new Castle(), new int[] {(int) nbLigne / 2, (int) nbColonne / 2});
            pCastle.put();
        }
        return castle;
    }

    /***
     * Setters
     *
     */
    public void setCastle(Case castle) {
        setCase(new int[]{castle.getPosition()[0], castle.getPosition()[1]}, castle);
        this.castle = castle; // save
    }

    public void setDomino(Domino d, String orientation) {

        if (orientation.equals("horizontal")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteDroite();
            grille[d.getPosition()[0]][d.getPosition()[1] - 1] = d.getExtremiteGauche();
        }
        else if (orientation.equals("horizontalReversed")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteGauche();
            grille[d.getPosition()[0]][d.getPosition()[1] - 1] = d.getExtremiteDroite();
        }
        else if (orientation.equals("vertical")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteDroite();
            grille[d.getPosition()[0] - 1][d.getPosition()[1]] = d.getExtremiteGauche();
        }
        else if (orientation.equals("verticalReversed")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteGauche();
            grille[d.getPosition()[0] - 1][d.getPosition()[1]] = d.getExtremiteDroite();
        }
        d.setEstPoser(true);
    }

}