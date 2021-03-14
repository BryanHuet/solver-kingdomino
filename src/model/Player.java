package model;

import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;

public interface Player {
    IPut play();
    Grille getPlateau();
    ArrayList<PutDomino> actionsPossible(ArrayList<Domino> dominos);
    int getScore();
    int getId();
}
