package model.pieces.domino;

import model.pieces.cases.Case;

public class Domino {

    private Case extremiteGauche;
    private Case extremiteDroite;
    private boolean estPoser;
    private final int numeroDomino;
    private int[] position;

    /*** +
     *
     * @param extremiteGauche Extremité gauche du Domino.
     * @param extremiteDroite Extremité droite du Domino.
     * @param numeroDomino Le numéro du domino.
     */
    public Domino(Case extremiteGauche, Case extremiteDroite, int numeroDomino) {
        this.extremiteGauche = extremiteGauche;
        this.extremiteDroite = extremiteDroite;
        this.numeroDomino = numeroDomino;
        this.estPoser = false;
    }

    /***
     * Getters
     */
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

    /**
     * Setters
     */
    public void setEstPoser(boolean etat) {
        estPoser = etat;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return "[" +this.getExtremiteGauche().getPaysage().getName() + " "+
                this.getExtremiteGauche().getPaysage().getNbCouronnes() +
                "|" + this.getExtremiteDroite().getPaysage().getName() + " " +
                 this.getExtremiteDroite().getPaysage().getNbCouronnes() +"]";
    }

    /***
     * Méthode qui permet d'inverser les extremités du Domino.
     */
    public void reverseExtremite() {
        Case tmp = extremiteDroite;
        this.extremiteDroite = extremiteGauche;
        this.extremiteGauche = tmp;
    }
}