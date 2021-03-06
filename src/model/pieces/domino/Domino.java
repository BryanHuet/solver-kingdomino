package model.pieces.domino;

import model.pieces.Case;

public class Domino {

    private Case extremiteGauche;
    private Case extremiteDroite;
    private boolean estPoser;
    private int numeroDomino;
    private int[] position;

    public Domino(Case extremiteGauche, Case extremiteDroite, int numeroDomino, int[] position) {
        this.extremiteGauche = extremiteGauche;
        this.extremiteDroite = extremiteDroite;
        this.numeroDomino = numeroDomino;
        this.position = position;
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

    public int[] getPosition() { return position; }
    public boolean estPoser() {
        return estPoser;
    }

    public void setEstPoser(boolean etat) {
        estPoser = etat;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String toString(){
        return "[" +this.getExtremiteGauche().getPaysage().getName() + " "+
                this.getExtremiteGauche().getPaysage().getNbCouronnes() +
                "|" + this.getExtremiteDroite().getPaysage().getName() + " " +
                 this.getExtremiteDroite().getPaysage().getNbCouronnes() +"]";
    }

    public void reverseExtremite() {
        Case tmp = extremiteDroite;
        this.extremiteDroite = extremiteGauche;
        this.extremiteGauche = tmp;
    }
}
