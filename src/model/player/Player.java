package model.player;

import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;

public interface Player {
    void play();
    Grille getPlateau();
    ArrayList<IPut> actionsPossible(ArrayList<Domino> dominos);
    int getScore();
    int getId();
    void setId(int id);
}
