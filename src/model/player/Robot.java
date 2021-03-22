package model.player;

import model.Kingdomino;
import model.ia.*;
import model.plateau.actions.IPut;

public class Robot extends AbstractPlayer implements Player{

    private Strategy strategy;

    public Robot(Kingdomino game) {
        super(game);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean play(){
        if(!(this.strategy ==null)){
            if(this.getPlateau().actionsPossible(this.getGame().getPick()).size()>0){
                IPut action = this.strategy.resolution(this.getPlateau().actionsPossible(this.getGame().getPick()));
                getGame().move(action);
            }else{
                return false;
            }
        }else{ // -> renvoyer une exception
            System.out.println("Le robot n'a pas de statégie");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Robot "+this.getId()+" utilisant une strategie "+this.strategy;
    }
}
