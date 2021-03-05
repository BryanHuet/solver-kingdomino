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
    }

    @Override
    public void put() {
        if (isValid()) {
            grille.setDomino(domino);
        }else System.out.println("Domino invalide");
    }

    @Override
    public boolean isValid() {
        if (isDominoAdjacent() || isCastleAdjacent()) {
            return true;
        }
        return false;
    }

    // TODO Vérification du paysage adjacent (qui doit être identique) et gestion des orientations.

    public boolean isDominoAdjacent() {
        int dominoX = domino.getPosition()[0];
        int dominoY = domino.getPosition()[1];
        if (grille.getDominos().size() > 0) {
            for (Domino d : grille.getDominos()) {
                int dX = d.getPosition()[0];
                int dY = d.getPosition()[1];
                if ((dominoX == dX && dominoY == dY + 1) || (dominoX == dX-1 && dominoY == dY) ||
                        (dominoX == dX + 1 && dominoY == dY - 1) || (dominoX == dX && dominoY == dY - 2)
                        || (dominoX == dX - 1 && dominoY == dY -1)) {
                    return true;
                }
            }
        }
        return false;
    }

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
}
