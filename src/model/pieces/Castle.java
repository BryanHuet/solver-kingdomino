package model.pieces;

public class Castle {

    private final Case caseDuChateau;
    private int[] position;

    public Castle() {
        this.caseDuChateau = new Case(new Paysage("castle", 0),"cas");
    }

    /***
     * Getters
     */
    public int[] getPosition() {
        return this.position;
    }

    public Case getCase() {
        return caseDuChateau;
    }

    /***
     * Setters
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

}
