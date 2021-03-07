package model.plateau.actions;

import model.pieces.Case;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.plateau.Grille;

import java.util.HashSet;

public class PutDomino implements IPut {

    private Grille grille;
    private Domino domino;
    private String orientation;
    private int[] position;

    public PutDomino(Grille grille, Domino domino, String orientation, int[] position) {
        this.grille = grille;
        this.domino = domino;
        this.orientation = orientation;
        this.position=position;
        adaptOrientation(); // On adapte les extremités du domino en fonction de l'orientation.
    }

    @Override
    public void put() {
        if (isValid()) {
            this.domino.setPosition(this.position);
            grille.setDomino(domino, orientation);
        }else System.out.println("Domino invalide");
    }

    @Override
    public boolean isValid() {
        if ((isDominoAdjacent() && dominoIsNotColliding())) {
            return true;
        }
        return false;
    }

    public boolean isDominoAdjacent() {
        HashSet<Case> voisins = verifyAdjacence();
        return voisins.size() > 0;
    }

    // Vérifie l'adjacence avec une case de domino avec paysage identique ou d'un chateau.
    public HashSet<Case> verifyAdjacence() {

        int dX = this.position[0];
        int dY = this.position[1];

        HashSet<Case> casesVoisine = new HashSet<>();

        switch (orientation)
        {
            case "horizontal":
            case "horizontalReversed":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                searchAdjacence(dX, dY - 1, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
                break;
            case "vertical":
            case "verticalReversed":
                searchAdjacence(dX, dY, casesVoisine, domino.getExtremiteDroite().getPaysage().getName());
                searchAdjacence(dX-1, dY, casesVoisine, domino.getExtremiteGauche().getPaysage().getName());
        }

        return casesVoisine;
        }

    public void searchAdjacence(int dX, int dY, HashSet<Case> casesVoisine, String dominoName) {

        if (!grille.isOutofBound(dX - 1,dY) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX-1,dY).getPaysage().getName().equals(dominoName) || grille.getCaseBis(dX-1,dY).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX-1,dY));
            }
        }
        if (!grille.isOutofBound(dX + 1,dY) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX+1,dY).getPaysage().getName().equals(dominoName) || grille.getCaseBis(dX+1,dY).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX + 1,dY));
            }
        }
        if (!grille.isOutofBound(dX,dY - 1) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX,dY - 1).getPaysage().getName().equals(dominoName) || grille.getCaseBis(dX,dY-1).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX,dY - 1));
            }
        }
        if (!grille.isOutofBound(dX,dY + 1) && dominoIsNotColliding()) {
            if (grille.getCaseBis(dX,dY + 1).getPaysage().getName().equals(dominoName) || grille.getCaseBis(dX,dY+1).getPaysage().getName().equals("castle")) {
                casesVoisine.add(grille.getCaseBis(dX,dY + 1));
            }
        }
    }

    public boolean dominoIsNotColliding() {
        int dX = this.position[0];
        int dY = this.position[1];

        switch (orientation) {
            case "horizontal":
            case "horizontalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX,dY-1))
                    return !grille.getCase(this.position).isOccuped() && !grille.getCase(new int[]{dX,dY-1}).isOccuped();
                return false;
            case "vertical":
            case "verticalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX - 1,dY))
                    return !grille.getCase(this.position).isOccuped() && !grille.getCase(new int[]{dX-1,dY}).isOccuped();
                return false;
            default:
                return false;
        }
    }

    public void adaptOrientation() {
        if (orientation.equals("horizontalReversed") || orientation.equals("verticalReversed")) {
            domino.reverseExtremite();
        }
    }

    public String toString(){
        return this.domino.toString() + " " +
                this.orientation + " " + this.position[0] + " "
                + this.position[1];
    }
}