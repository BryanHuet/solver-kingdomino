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

        if (isValid()) {
            grille.setCase(castle.getPosition(), castle);
        }
    }

    @Override
    public boolean isValid() {
        try {
            return !grille.getGrille()[castle.getPosition()[0]][castle.getPosition()[1]].isOccuped();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Castle avec une position invalide" + e.getMessage());
        }
        return false;
    }
}
