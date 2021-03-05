package model.plateau.actions;

import model.pieces.Castle;
import model.plateau.Grille;

public class PutCastle implements IPut {

    private Grille grille;
    private Castle castle;

    public PutCastle(Grille grille, Castle castle) {
        this.grille = grille;
        this.castle = castle;
    }

    @Override
    public void put() {
        int indexX = castle.getPosition()[0];
        int indexY = castle.getPosition()[1];


            grille.setCase(castle.getPosition(), castle);
            grille.setCastle(castle); // on garde en mémoire la chateau
    }

    // Pas besoin de vérifier si un domino est présent, le chateau est la première pièce.
    @Override
    public boolean isValid() {
        return !grille.isOutofBound(castle.getPosition()[0],castle.getPosition()[1]);
    }



}
