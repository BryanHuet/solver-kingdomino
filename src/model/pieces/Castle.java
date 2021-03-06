package model.pieces;

public class Castle {

    private Case caseDuChateau;
    private int[] position;

    public Castle(int[] position) {
        this.position = position;
        this.caseDuChateau = new Case(new Paysage("castle", 0),"cas");
    }

    public int[] getPosition() {
        return this.position;
    }

    public Case getCase() {
        return caseDuChateau;
    }
}
