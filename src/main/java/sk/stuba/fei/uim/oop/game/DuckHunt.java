package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.card.action.*;
import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.Duck;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.card.tile.Water;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.Collections;

import static sk.stuba.fei.uim.oop.color.ConsoleColor.*;

public class DuckHunt {
    private final ArrayList<Player> players;
    private int intCurrentPlayer;
    private final ArrayList<PondCard> pondDeck;
    private final ArrayList<PondTile> pondArea;
    private final ArrayList<ActionCard> actionCardsDeck;

    public DuckHunt(){
        System.out.println("Welcome to DuckHunt made by Richard Korosi");
        System.out.println("This game is made for 2-6 players!");
        this.players = createPlayerList();
        this.pondDeck = createPondDeck(this.players.size());
        this.actionCardsDeck = createActionDeck();
        this.pondArea = createPondArea();
        this.playGame();
    }

    private ArrayList<Player> createPlayerList() {
        int numberOfPlayers = 0;
        ArrayList<Player> playersList = new ArrayList<>();
        while (numberOfPlayers < 2 || numberOfPlayers > 6){
            numberOfPlayers = KeyboardInput.readInt("Enter number of players");
        }
        for(int i=0; i< numberOfPlayers; i++){
            Player new_player = new Player(KeyboardInput.readString("Give name to player "+(i+1)));
            playersList.add(new_player);
        }
        return playersList;
    }


    private ArrayList<PondCard> createPondDeck(int numberOfPlayers){
        ArrayList<PondCard> pondCards = new ArrayList<>();
        for(int i =0; i<5; i++){
            pondCards.add(new Water());
            for(int j =0; j<numberOfPlayers; j++){
                pondCards.add(new Duck(this.players.get(j)));
            }
        }
        Collections.shuffle(pondCards);
        return pondCards;
    }

    private ArrayList<PondTile> createPondArea(){
        ArrayList<PondTile> pondArea = new ArrayList<>();
        for(int i =0; i<6; i++){
            pondArea.add(new PondTile(this.pondDeck.get(0)));
            pondDeck.remove(0);
            }
        return pondArea;
    }

    private ArrayList<ActionCard> createActionDeck(){
        ArrayList<ActionCard> actionCards = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            actionCards.add(new Aim());
        }
        for(int i = 0; i < 12; i++){
            actionCards.add(new Shoot());
        }
        for(int i = 0; i < 2; i++){
            actionCards.add(new WildBill());
            actionCards.add(new Scatter());
        }
        for(int i = 0; i < 6; i++){
            actionCards.add(new DuckMarch());
        }
        actionCards.add(new Turboduck());
        actionCards.add(new DuckDance());
        Collections.shuffle(actionCards);
        return actionCards;
    }



    private void playGame(){
        dealTheCards();
        while(countAlivePlayersAndTakeCardsFromDead() > 1){
            Player currentPlayer = this.players.get(this.intCurrentPlayer);
            currentPlayer.playTurn(this.pondArea, this.actionCardsDeck, this.pondDeck);
            giveNextPlayerTurn();
        }
        getWinner();
    }

    private int countAlivePlayersAndTakeCardsFromDead(){
        int count = 0;
        int i = 0;
        while(i < this.players.size()){
            if(this.players.get(i).isAlive()){
                count++;
            }
            else if (!this.players.get(i).getHand().isEmpty()){
                this.actionCardsDeck.addAll(this.players.get(i).getHand());
                this.players.get(i).getHand().clear();
                System.out.println(RED + "Player " + this.players.get(i) + " died!" + RESET);
            }
            i++;
        }
        return count;
    }

    private void giveNextPlayerTurn(){
        this.intCurrentPlayer = (this.intCurrentPlayer + 1) % this.players.size();
    }

    private void dealTheCards(){
        for (Player player : this.players) {
            for (int j = 0; j < 3; j++) {
                player.drawCard(this.actionCardsDeck);
            }
        }
    }

    private void getWinner(){
        System.out.println("----------- GAME OVER -----------");
        for (Player player : this.players) {
            if (player.isAlive()) {
                System.out.println("And the winner is: " + player + ", congratulations!");
            }
        }
    }
}
