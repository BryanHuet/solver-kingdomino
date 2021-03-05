package model.pieces;
public class Case {

    private Paysage paysage;
    private String symbole; // juste pour l'affichage
    private boolean occupe;

    // Case vide d'une grille
    public Case(){
        this.paysage = new Paysage("vide", 0);
        this.symbole = ".";
        this.occupe = false;
    }

    // Case construite (pour une extremité de domino/ le chateau voir autres choses si on veut)
    public Case(Paysage paysage, String symbole) {
        this.paysage = paysage;
        this.symbole = symbole;
        this.occupe = true;
    }

    public Paysage getPaysage() {
        return paysage;
    }

    public String getSymbole() {
        return symbole;
    }

    public boolean isOccuped() {
        return occupe;
    }


}
