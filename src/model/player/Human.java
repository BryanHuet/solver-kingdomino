package model.player;

import model.Kingdomino;
import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import control.InputHuman;
import java.util.ArrayList;
import java.util.Scanner;

public class Human extends AbstractPlayer implements Player{



    public Human(Kingdomino game){
        super(game);
    }


    //-> ne pas modifier des choses dans games
    public boolean play(){
        InputHuman.chooseAction(this).put();
        return true;
    }

    @Override
    public String toString(){
        return "Humain "+this.getId();
    }
}
