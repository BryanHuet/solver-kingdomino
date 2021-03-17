package model.player;

import model.Kingdomino;
import model.ia.*;
import model.pieces.cases.Castle;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;

public class Robot extends Human implements Player{

    private Strategy strategy;
    private int id;

    public Robot(Kingdomino game) {
        super(game);
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play(){
        if(!(this.strategy ==null)){
            if(this.actionsPossible(this.getGame().getPick()).size()>0){
                IPut action = this.strategy.resolution(this.actionsPossible(this.getGame().getPick()));
                action.put();
                this.getGame().getPick().remove(action.getDomino());
            }
        }else{
            System.out.println("Le robot n'a de statégie");
        }
    }
    @Override
    public String toString(){
        return "Robot "+this.id+" utilisant une strategie "+this.strategy;
    }
}
