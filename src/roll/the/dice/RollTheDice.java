/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roll.the.dice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class RollTheDice {
    
    private static boolean startGame(int pass){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            if(pass == 123){
                return true;
            }else{
                return false;
            }
        } catch(IOException exc) {
            System.out.println("I/OException: " + exc);
        }
        return false;
    }
    
    private static ArrayList<String> addNamePlayers(int players){
        ArrayList<String> playersName = new ArrayList<String>();
        String names = "";
        int password = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for(int i=0; i < players; i++){
                System.out.print("Players "+i+" names is ");
                names = br.readLine();
                playersName.add(names);
            }
            System.out.print("Please entry passcode to Start the Game : ");
            password = Integer.parseInt(br.readLine());
            boolean start = startGame(password);
            if(start){
               runDice(playersName); 
            }else{
                System.out.println("Incorect Passcode, Try Again!");
            }
        } catch(IOException exc) {
            System.out.println("I/OException: " + exc);
        }
        return playersName;
    }
    
    private static ArrayList<String> runDice(ArrayList players){
        ArrayList<Integer> skorPlayers = new ArrayList<Integer>();
        int point = 0;
        int[] skors = new int[5];
        for(int i=0; i<players.size(); i++){
            for(int j=0; j < 5; j++) {
                skors[j] = (int)(Math.random() * 6) + 1;
                if((skors[j] == 1) || (skors[j] == 3) || (skors[j] == 5)){
                    point = point + 5;
                }else if((skors[j] == 2) || (skors[j] == 4) || (skors[j] == 6)){
                    point = point- 3;
                }
                System.out.println(players.get(i)+", get dice side "+skors[j]+" and the point is : "+point);
            }
            skorPlayers.add(point);
            point = 0;
        }
        int win = Collections.max(skorPlayers);
        int whoIsWin = skorPlayers.indexOf(win);
        System.out.println("The Winner is : "+players.get(whoIsWin));
        return players;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Roll the Dice Game");
        System.out.println("in this game there are 5 rounds");
        int sumP = 0;
        System.out.print("How Many Players ? ");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            sumP = Integer.parseInt(br.readLine());
        } catch(IOException exc) {
            System.out.println("I/OException: " + exc);
        }
        addNamePlayers(sumP);
    }
}
