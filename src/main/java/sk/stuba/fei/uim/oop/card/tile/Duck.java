package sk.stuba.fei.uim.oop.card.tile;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

import static sk.stuba.fei.uim.oop.color.ConsoleColor.*;

public class Duck extends PondCard {

    public Duck(Player belongsToPlayer) {
        super(belongsToPlayer);
        this.name = YELLOW + "\uD83E\uDD86 of player "+ belongsToPlayer + RESET;
    }

    public void getShot(ArrayList<PondTile> pondArea, ArrayList<PondCard> pondDeck, int placeToShoot){
        System.out.println(RED + "You killed "+ this.belongsToPlayer +"'s  duck!"+ RESET);
        int hp = this.belongsToPlayer.getHealth();
        this.belongsToPlayer.setHealth(hp-1);
        pondArea.get(placeToShoot).setAimed(false);
        moveTiles(pondArea, pondDeck, placeToShoot);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean turboTheCard(ArrayList<PondTile> pondArea, int duck_to_speed_up) {
        for(int i = duck_to_speed_up; i > 0; i--){
            pondArea.get(i).setCardOnTile(pondArea.get(i-1).getCardOnTile());
        }
        pondArea.get(0).setCardOnTile(this);
        return true;
    }

}
