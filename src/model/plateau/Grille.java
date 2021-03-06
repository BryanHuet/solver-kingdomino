package model.plateau;

import model.pieces.Case;
import model.pieces.Castle;
import model.pieces.Paysage;
import model.pieces.domino.Domino;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

import java.util.HashSet;

public class Grille{

    // Attributs
    private int nbLigne;
    private int nbColonne;
    private Case[][] grille;
    // sauvegarde
    private Castle castle;
    private HashSet<Domino> dominos;

    // Constructeur
    public Grille(int ligne, int colonne){

        nbLigne = ligne;
        nbColonne = colonne;
        grille = new Case[nbLigne][nbColonne];
        dominos = new HashSet<Domino>();
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
            for (int j = 0; j < nbColonne; j++) {
                System.out.print("|" + grille[i][j].getSymbole() + "|");
            }
        System.out.println("");
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

    public Case getCaseBis(int x, int y) { return grille[x][y]; }

    public String getPaysageName(int x, int y) {
        return grille[x][y].getPaysage().getName();
    }

    public boolean isOutofBound(int x, int y) {
        if (x < 0 || x > nbLigne -1 || y < 0 || y > nbColonne - 1) {
            return true;
        }
        return false;
    }

    public void setCastle(Castle castle) {
        setCase(new int[]{castle.getPosition()[0], castle.getPosition()[1]}, castle.getCase());
        this.castle = castle; // save
    }

    public Castle getCastle() {
        if (castle != null) {
            return castle;
        }
        else {
            System.out.println("Attention ! aucun château n'existait, un a été mis par défaut au milieu de la grille.");
            setCastle(new Castle(new int[]{(int) nbLigne/2, (int) nbColonne / 2}));
        }
        return castle;
    }

    public void setDomino(Domino d, String orientation) {
        dominos.add(d);

        if (orientation.equals("horizontal") || orientation.equals("horizontalReversed")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteDroite();
            grille[d.getPosition()[0]][d.getPosition()[1] - 1] = d.getExtremiteGauche();
        } else if (orientation.equals("vertical") || orientation.equals("verticalReversed")) {
            grille[d.getPosition()[0]][d.getPosition()[1]] = d.getExtremiteDroite();
            grille[d.getPosition()[0] - 1][d.getPosition()[1]] = d.getExtremiteGauche();
        }

        d.setEstPoser(true);
    }

    public void removeDomino(Domino d) {
        dominos.remove(d);
    }

    public HashSet<Domino> getDominos() {
        return dominos;
    }

}