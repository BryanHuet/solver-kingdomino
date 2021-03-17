package model.ia;

import model.plateau.actions.IPut;
import model.player.Player;

import java.util.ArrayList;

public interface Strategy {


    IPut resolution(ArrayList<IPut> actions);


}
