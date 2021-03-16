package model.player;

import model.Kingdomino;
import model.ia.*;
import model.pieces.cases.Castle;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;

public class Robot extends Human implements Player{

    private Strategy strategy;
    private int id;

    public Robot(Kingdomino game, Strategy strategy) {
        super(game);
        this.strategy=strategy;
    }

    public void setStrategy(Strategy strategy){
        this.strategy=strategy;
    }

    public void play(){
        if(this.actionsPossible(this.getGame().getPick()).size()>0){
            this.strategy.resolution(this.actionsPossible(this.getGame().getPick())).put();
        }
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }



}
