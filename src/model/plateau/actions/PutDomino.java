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

    public PutDomino(Grille grille, Domino domino, String orientation) {
        this.grille = grille;
        this.domino = domino;
        this.orientation = orientation;
        adaptOrientation(); // On adapte les extremités du domino en fonction de l'orientation.
    }

    @Override
    public void put() {
        if (isValid()) {
            grille.setDomino(domino, orientation);
        }else System.out.println("Domino invalide");
    }

    @Override
    public boolean isValid() {
        if (isDominoAdjacent() || isCastleAdjacent() && dominoIsNotColliding()) {
            return true;
        }
        return false;
    }

    // TODO Vérification d'adjacence sur les cases + paysage identique à vérifier + la gestion des orientations.

    public boolean isDominoAdjacent() {
        return false;
    }
    /*
    public boolean isDominoAdjacent() {
        int dominoX = domino.getPosition()[0];
        int dominoY = domino.getPosition()[1];
        boolean identicPaysage = false;

        if (grille.getDominos().size() > 0) {
            for (Domino d : grille.getDominos()) {
                int dX = d.getPosition()[0];
                int dY = d.getPosition()[1];

                for (int i = -2; i < 2; i++) {

                }
            }
        }
        return identicPaysage;
    }
    */

    public boolean isCastleAdjacent() {
        Castle castle = grille.getCastle();
        // index Domino et castle
        int dX = domino.getPosition()[0];
        int dY = domino.getPosition()[1];
        int cX = castle.getPosition()[0];
        int cY = castle.getPosition()[1];

        if (!grille.isOutofBound(dX, dY) && !grille.isOutofBound(cX, cY)) {
            if ((cX - 1 == dX && cY == dY) || (cX + 1 == dX && cY + 1 == dY) || (cX == dX && cY + 2 == dY) ||
                    (cX + 1 == dX && cY + 1 == dY) || (cX + 1 == dX && cY == dY) || (cX == dX && cY - 1 == dY))
            {
                return true;
            }
        }
        return false;
    }

    public boolean dominoIsNotColliding() {
        int dX = domino.getPosition()[0];
        int dY = domino.getPosition()[1];

        switch (orientation) {
            case "horizontal":
            case "horizontalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX,dY-1))
                    return !grille.getCase(domino.getPosition()).isOccuped() && !grille.getCase(new int[]{dX,dY-1}).isOccuped();
                return false;
            case "vertical":
            case "verticalReversed":
                if (!grille.isOutofBound(dX,dY) && !grille.isOutofBound(dX - 1,dY))
                    return !grille.getCase(domino.getPosition()).isOccuped() && !grille.getCase(new int[]{dX-1,dY}).isOccuped();
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

    /*
    public void verifyPos() {

        int dominoX = domino.getPosition()[0];
        int dominoY = domino.getPosition()[1];
        boolean identicPaysage = false;

        for (Domino d : grille.getDominos()) {

            int dX = d.getPosition()[0];
            int dY = d.getPosition()[1];

            switch (orientation) {
                case "horizontal":
                    for (int i = - 1; i <= 1; i++) {
                        for (int j = 1; j >= -1; j--) {
                            if (dX + i == dominoX && dY - i == dominoY) {
                                identicPaysage = true;
                            }
                        }
                    }
            }
        }
    }
    */
}
