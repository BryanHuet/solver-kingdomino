package model.pieces;

public class Castle extends Case {

    private int[] position;

    public Castle(int[] position) {
        super(new Paysage("castle", 0), "c");
        this.position = position;
    }

    public int[] getPosition() {
        return this.position;
    }
}
