package model.pieces.domino;

import model.pieces.Case;

public class Domino {

    private Case extremiteGauche;
    private Case extremiteDroite;
    private boolean estPoser;
    private int numeroDomino;

    public Domino(Case extremiteGauche, Case extremiteDroite, int numeroDomino) {
        this.extremiteGauche = extremiteGauche;
        this.extremiteDroite = extremiteDroite;
        this.numeroDomino = numeroDomino;
        this.estPoser = false;
    }

    public Case getExtremiteGauche() {
        return extremiteGauche;
    }

    public Case getExtremiteDroite() {
        return extremiteDroite;
    }

    public int getNumeroDomino() {
        return numeroDomino;
    }

    public void rotate(String orientation) {
    }

    public void setPositions(int[] positions) {
    }

}
