package model.domino;

import model.Case;

public abstract class Domino {

    private Case extremiteGauche;
    private Case extremiteDroite;
    private boolean estPoser;

    public Domino(Case extremiteGauche, Case extremiteDroite) {
        extremiteGauche = new Case(0,0);
        extremiteDroite = new Case(0,1);
        estPoser = false;
    }

}
