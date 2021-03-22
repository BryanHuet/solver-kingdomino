package model.player;

import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;

//go classe abstraite.
public interface Player {
    boolean play();
    Grille getPlateau();
    int getScore();
    int getId();
    void setId(int id);
}
