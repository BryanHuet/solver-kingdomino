package control;

import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import model.player.Human;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHuman {


    public static IPut chooseAction(Human player){
        int j=0;
        for(Domino d : player.getGame().getPick()){
            System.out.print(j+" "+d+"|");
            j=j+1;
        }
        System.out.println("\nVeuillez choisir un domino");
        Scanner myObj = new Scanner(System.in);
        int idDomino = myObj.nextInt();
        Domino domino = player.getGame().getPick().get(idDomino);
        ArrayList<Domino> justTest = new ArrayList<>();
        justTest.add(domino);
        player.getGame().getPick().remove(player.getGame().getPick().get(idDomino));
        System.out.println("Veuillez choisir une action");
        int i=0;
        for (IPut action: player.getPlateau().actionsPossible(justTest)){
            System.out.print(i+" "+action+" | ");
            if (i%2==0){
                System.out.println();
            }
            i=i+1;
        }
        System.out.println();
        Scanner myObj2 = new Scanner(System.in);
        int position = myObj2.nextInt();
        return player.getPlateau().actionsPossible(justTest).get(position);
    }

}
