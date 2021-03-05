package model.plateau.actions;

import model.pieces.Case;
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
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
