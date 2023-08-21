package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class WildBill extends Shoot {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        int placeToShoot = chooseTile();
        shoot(pondArea, pondDeck, placeToShoot);
    }


    public WildBill() {
        super("WildBill");
    }
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean canPlayCard(int checkPondStatus) {
        return true;
    }

    @Override
    public void checkCardValue(Player player) {
        int handBalance = player.getHandBalance();
        player.setHandBalance(handBalance);
    }
}
