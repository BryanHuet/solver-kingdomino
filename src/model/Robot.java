package model;

import model.ia.Expectiminimax;
import model.plateau.actions.IPut;

public class Robot extends Player{

    private Expectiminimax strategy;

    public Robot(int id) {
        super(id);
        this.strategy=new Expectiminimax(this);
    }

    public IPut play(){

        return null;
    }
}
