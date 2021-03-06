package model.pieces;

import java.util.Objects;

public class Paysage {

    private String name;
    private int nbCouronnes;

    public Paysage(String name, int nbCouronnes) {
        this.name = name;
        this.nbCouronnes = nbCouronnes;
    }

    public String getName() {
        return this.name;
    }

    public int getNbCouronnes() {
        return this.nbCouronnes;
    }

}
