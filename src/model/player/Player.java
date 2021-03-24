package model.player;

import model.plateau.Grille;
import model.plateau.actions.IPut;


//go classe abstraite.
public interface Player {
    boolean play();
    Grille getPlateau();
    int getScore();
    int getId();
    void setId(int id);
    IPut getLastAction();
}
