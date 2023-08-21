package sk.stuba.fei.uim.oop.card.tile;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class PondCard {
    protected String name;
    protected Player belongsToPlayer;
    public abstract void getShot(ArrayList<PondTile> pondArea, ArrayList<PondCard> pondDeck, int placeToShoot);
    public abstract String toString();
    public abstract boolean turboTheCard(ArrayList<PondTile> pondArea, int duck_to_speed_up);


    public PondCard(String name){
        this.name = name;
    }

    public PondCard(Player player){
        this.belongsToPlayer = player;
    }

    public void moveTiles(ArrayList<PondTile> pondArea,ArrayList<PondCard> pondDeck, int targetedTile){
        for(int i=targetedTile; i < pondArea.size()-1; i++){
            pondArea.get(i).setCardOnTile(pondArea.get(i+1).getCardOnTile());
        }
        pondArea.get(5).setCardOnTile(pondDeck.get(0));
        pondDeck.remove(0);
    }
}
