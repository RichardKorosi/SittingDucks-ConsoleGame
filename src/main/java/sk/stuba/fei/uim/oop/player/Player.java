package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.card.action.ActionCard;
import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;

import static sk.stuba.fei.uim.oop.color.ConsoleColor.GREEN;
import static sk.stuba.fei.uim.oop.color.ConsoleColor.RESET;

public class Player {
    private final String name;
    private int health;
    private boolean alive;
    private final ArrayList<ActionCard> hand;
    private int handBalance;

    public int getHandBalance() {
        return handBalance;
    }

    public void setHandBalance(int handBalance) {
        this.handBalance = handBalance;
    }

    public ArrayList<ActionCard> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Player(String name) {
        this.name = name;
        this.health = 5;
        this.alive = true;
        this.hand = new ArrayList<>();
        this.handBalance = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        this.alive = (this.health > 0);
        return this.alive;
    }

    public void drawCard(ArrayList<ActionCard> actionCardsDeck){
        this.hand.add(actionCardsDeck.get(0));
        actionCardsDeck.remove(0);
    }

    public void playTurn(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<PondCard> pondDeck){
        if(isAlive()) {
            lookAtPlayground(pondArea);
            int selectedCard = 0;
            if (canMakeTurn(pondArea)) {
                do {
                    selectedCard = KeyboardInput.readInt("Pick your Card (1-3)") - 1;
                } while (selectedCard > 2 || selectedCard < 0 || !canPlayCard(selectedCard, pondArea));
                this.hand.get(selectedCard).play(pondArea, actionCardsDeck, this.hand, pondDeck);
            } else {
                KeyboardInput.readString("You cant play any card, press ENTER to switch one card from deck and hand!");
            }
            this.hand.get(selectedCard).returnToDeckAfterPlayed(actionCardsDeck, this.hand, selectedCard);
            if(isAlive()){
                // Shot himselft at his turn (he wont draw a card)
                drawCard(actionCardsDeck);
            }
        }
    }


    private boolean canMakeTurn(ArrayList<PondTile> pondArea){
        int checkHand = checkHand();
        if(checkHand != 0){
            return !(checkHand == checkPondStatus(pondArea));
        }
        return true;
    }


    private boolean canPlayCard(int selectedCard, ArrayList<PondTile> pondArea){
        int checkPondStatus = checkPondStatus(pondArea);
        if(this.hand.get(selectedCard).canPlayCard(checkPondStatus)){
            return true;
        }
        System.out.println("You cant play this card this turn!");
        return false;
    }


    private int checkHand(){
        // handBalance = AIM [+1] SHOOT [-1] Others[0]
        // 0-> Some places are AIM or SHOOT
        // 1-> Only AIM cards
        // 2-> Only SHOOT cards
         this.handBalance = 0;
        for(int i =0; i<3; i++){
            System.out.print("["+ hand.get(i)+"] ");
            this.hand.get(i).checkCardValue(this);
        }
        System.out.println();
        if(this.handBalance ==3) return 1;
        if(this.handBalance ==-3) return 2;
        return 0;
    }



    private int checkPondStatus(ArrayList<PondTile> pondArea){
        // 0-> Some places are aimed at (not every place)
        // 1-> fully Aimed
        // 2-> Nothing is being Aimed at
        int dangerousPlaces = 0;
        for (PondTile pondTile : pondArea) {
            if (pondTile.isAimed()) {
                dangerousPlaces += 1;
            }
        }
        if (dangerousPlaces == 0) return 2;
        if (dangerousPlaces == 6) return 1;
        return 0;
    }

    private void lookAtPlayground(ArrayList<PondTile> pondArea){
        System.out.println("------------------------------------");
        System.out.println(GREEN + this  +"'s turn and he/she has " + this.getHealth() + " HP" + RESET);
        for(int i =0; i< pondArea.size(); i++){
            String s = String.valueOf(i+1);
            System.out.println(s+" | "+ pondArea.get(i).getInfoIfAimedOn()+" | " +
                    pondArea.get(i).getCardOnTile());
        }
    }
}
