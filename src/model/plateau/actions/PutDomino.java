package model.plateau.actions;

import model.pieces.Case;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.plateau.Grille;

import java.util.HashSet;

public class PutDomino implements IPut {

    private Grille grille;
    private Domino domino;
    private String orientation;

    public PutDomino(Grille grille, Domino domino, String orientation) {
        this.grille = grille;
        this.domino = domino;
        this.orientation = orientation;
    }

    @Override
    public void put() {
        if (isValid()) {
            grille.setDomino(domino);
        }else System.out.println("Domino invalide");
    }

    @Override
    public boolean isValid() {
        if (isDominoAdjacent() || isCastleAdjacent()) {
            return true;
        }
        return false;
    }

    // TODO Vérification du paysage adjacent (qui doit être identique) et gestion des orientations.

    public boolean isDominoAdjacent() {
        int dominoX = domino.getPosition()[0];
        int dominoY = domino.getPosition()[1];
        boolean identicPaysage = false;
        if (grille.getDominos().size() > 0) {
            for (Domino d : grille.getDominos()) {
                int dX = d.getPosition()[0];
                int dY = d.getPosition()[1];
                // Légende : Domino que l'on ajoute (côté) : extrémité du domino qu'on add sur l'extremité du domino voisin
                // Priorité à ses 2 if (cas haut et cas bas) donc 2 extremités avec des paysage identiques possible
                // Domino en haut/bas : contact des 2 extrémités respectives sur les 2 respectives (gauche gauche, droite droite)
                if ((dominoX == dX + 1 && dominoY == dY) || (dominoX == dX - 1 && dominoY == dY)) {
                    boolean check = d.getExtremiteDroite().getPaysage().getName().equals(domino.getExtremiteDroite().getPaysage().getName());
                    boolean check2 = d.getExtremiteGauche().getPaysage().getName().equals(domino.getExtremiteGauche().getPaysage().getName());
                    identicPaysage = (check || check2);
                }
                // Domino à droite : extremité gauche voisine sur droite
                else if (dominoX == dX && dominoY == dY + 2) {
                    identicPaysage = d.getExtremiteDroite().getPaysage().getName().equals(domino.getExtremiteGauche().getPaysage().getName());
                }
                // Domino à gauche : extremité droite voisine sur gauche
                else if (dominoX == dX && dominoY == dY - 2) {
                    identicPaysage = d.getExtremiteGauche().getPaysage().getName().equals(domino.getExtremiteDroite().getPaysage().getName());
                }
                // Domino en bas à gauche : extremité droite voisine sur gauche
                else if (dominoX == dX + 1 && dominoY == dY - 1)
                    identicPaysage = d.getExtremiteGauche().getPaysage().getName().equals(domino.getExtremiteDroite().getPaysage().getName());
                // Domino en bas à droite : extremité gauche voisine sur droite
                else if (dominoX == dX + 1 && dominoY == dY + 1)
                    identicPaysage = d.getExtremiteDroite().getPaysage().getName().equals(domino.getExtremiteGauche().getPaysage().getName());
                // Domino en haut à droite : extremité gauche voisine sur droite
                else if (dominoX == dX + 1 && dominoY == dY + 1) {
                    identicPaysage = d.getExtremiteDroite().getPaysage().getName().equals(domino.getExtremiteGauche().getPaysage().getName());
                }
                // Domine en haut à gauche : extremité droite voisine sur gauche
                else if (dominoX == dX - 1 && dominoY == dY - 1) {
                    identicPaysage = d.getExtremiteGauche().getPaysage().getName().equals(domino.getExtremiteDroite().getPaysage().getName());
                }
            }
        }
        return identicPaysage;
    }

    public boolean isCastleAdjacent() {
        Castle castle = grille.getCastle();
        // index Domino et castle
        int dX = domino.getPosition()[0];
        int dY = domino.getPosition()[1];
        int cX = castle.getPosition()[0];
        int cY = castle.getPosition()[1];

        if (!grille.isOutofBound(dX, dY) && !grille.isOutofBound(cX, cY)) {
            if ((cX - 1 == dX && cY == dY) || (cX + 1 == dX && cY + 1 == dY) || (cX == dX && cY + 2 == dY) ||
                    (cX + 1 == dX && cY + 1 == dY) || (cX + 1 == dX && cY == dY) || (cX == dX && cY - 1 == dY))
            {
                return true;
            }
        }
        return false;
    }

}
