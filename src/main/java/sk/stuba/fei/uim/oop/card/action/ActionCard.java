package sk.stuba.fei.uim.oop.card.action;
import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;

public abstract class ActionCard {
    protected String name;

    public abstract void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck);

    public ActionCard(String name){
        this.name = name;
    }

    public void returnToDeckAfterPlayed(ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, int cardNumber){
        actionCardsDeck.add(actionCardsDeck.size(), hand.get(cardNumber));
        hand.remove(cardNumber);
    }

    public boolean canPlayCard(int checkPondStatus){
        return true;
    }

    public void checkCardValue(Player player){
        int handBalance = player.getHandBalance();
        player.setHandBalance(handBalance);
    }

    protected int chooseTile(){
        int selectedPlace;
        do {
            selectedPlace = KeyboardInput.readInt("Select valid tile (1-6)")-1;
        } while (selectedPlace > 5 || selectedPlace < 0);
        return selectedPlace;
    }

    protected int activateCardOnTile(ArrayList<PondTile> pondArea, boolean start){
        int placeToActivate;
        do{
            placeToActivate = chooseTile();
        }while (pondArea.get(placeToActivate).isAimed() ^ start);
        return placeToActivate;
    }

}