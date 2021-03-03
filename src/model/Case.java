package model;

public class Case {

    private int x;
    private int y;
    private char symbole; // juste pour l'affichage
    private int couronne;
    private String paysage;

    public Case(int x, int y){
        x =x;
        y = y;
        symbole = '.';
        paysage = "";
        couronne = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setCouronne(int nbCouronne) {
        couronne = nbCouronne;
    }

    public void setPaysage(String paysage) {
        paysage = paysage;
    }
}
