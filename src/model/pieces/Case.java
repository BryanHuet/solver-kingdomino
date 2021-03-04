package model.pieces;

public class Case {

    private String paysage;
    private int couronne;
    private char symbole; // juste pour l'affichage

    // Case vide d'une grille
    public Case(){
        this.paysage = "";
        this.couronne = 0;
        this.symbole = '.';
    }

    // Case construite (pour une extremité de domino/ le chateau voir autres choses si on veut)
    public Case(String paysage, int couronne, char symbole) {
        this.paysage = paysage;
        this.couronne = couronne;
        this.symbole = symbole;
    }

    public String getPaysage() {
        return paysage;
    }

    public int getCouronne() {
        return couronne;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setCouronne(int nbCouronne) {
        this.couronne = nbCouronne;
    }

    public void setPaysage(String paysage) {
        this.paysage = paysage;
    }

}
