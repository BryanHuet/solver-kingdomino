package model.plateau.actions;

import model.pieces.Case;
import model.pieces.domino.Domino;
import model.plateau.Grille;

public class PutDomino implements IPut {

    private Grille grille;
    private Domino domino;
    private String orientation;

    // TODO
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

    public boolean verifyAjdacentNeighbours() {
        int indexX = domino.getPosition()[0];
        int indexY = domino.getPosition()[1];

        return false;
    }
    
    public boolean verifyPosition() {
        int indexX = domino.getPosition()[0];
        int indexY = domino.getPosition()[1];

        try {
            if (orientation.equals("horizontal") || orientation.equals("horizontalReversed")) {
                if (grille.getGrille()[indexX][indexY].isOccuped() == false && grille.getGrille()[indexX][indexY + 1].isOccuped() == false)
                    return false;
            } else if (orientation.equals("vertical") || orientation.equals("verticalReversed")){
                if (grille.getGrille()[indexX][indexY].isOccuped() == false && grille.getGrille()[indexX + 1][indexY].isOccuped() == false)
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Position de domino invalide : " + e.getMessage());
        }
        return true;
    }
}