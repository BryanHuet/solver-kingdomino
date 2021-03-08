package model.pieces;
public class Case {

    private final Paysage paysage;
    private final String symbole; // juste pour l'affichage
    private final boolean occupe;


    // Case vide d'une grille
    public Case(){
        this.paysage = new Paysage("vide", 0);
        this.symbole = "___";
        this.occupe = false;
    }

    /***
     *
     * @param paysage Paysage d'une case.
     * @param symbole Le symbole est un String servant à l'affichage.
     */
    // Case construite (pour une extremité de domino/ le chateau voir autres choses si on veut)
    public Case(Paysage paysage, String symbole) {
        this.paysage = paysage;
        this.symbole = symbole;
        this.occupe = true;
    }

    /***
     * Getters
     */
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
