package sk.stuba.fei.uim.oop.card.tile;

import java.util.ArrayList;

import static sk.stuba.fei.uim.oop.color.ConsoleColor.BLUE;
import static sk.stuba.fei.uim.oop.color.ConsoleColor.RESET;

public class Water extends PondCard {

    public Water() {
        super( BLUE +"\uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A \uD83C\uDF0A" + RESET);
    }


    public void getShot(ArrayList<PondTile> pondArea, ArrayList<PondCard> pondDeck, int placeToShoot){
        pondArea.get(placeToShoot).setAimed(false);
        System.out.println("Missed!");
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean turboTheCard(ArrayList<PondTile> pondArea, int duck_to_speed_up) {
        System.out.println("You cant use Turboduck on Water!");
        return false;
    }
}
