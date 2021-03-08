package model.plateau.actions;

public interface IPut {

    /***
     * put Mettre une pièce.
     */
    public void put();

    /***
     *
     * @return Valide la possibilité de mettre une pièce.
     */
    public boolean isValid();
}
