import java.util.Random;
import java.io.File;

import jdk.jshell.execution.Util;
import processing.core.PApplet;
import processing.core.PImage;

public class DancingBadgers {
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Badger[] badgers; // perfect size array storing the badgers
    // present in this application
    private static Random randGen; // Generator of random numbers
    private static int backgroundColor;
    private static PImage badgerImage;



    /**
     * Checks if the mouse is over a specific Badger whose reference is provided
     * as input parameter
     *
     * @param Badger reference to a specific Badger object
     * @return true if the mouse is over the specific Badger object passed as input
     * (i.e. over the image of the Badger), false otherwise
     */
    public static boolean isMouseOver(Badger badger) {
        float badgerX = badger.getX();
        float badgerY = badger.getY();
        return Utility.mouseX() >= badgerX - badgerImage.width / 2 &&
                Utility.mouseX() <= badgerX + badgerImage.width / 2 &&
                Utility.mouseY() >= badgerY - badgerImage.height / 2 &&
                Utility.mouseY() <= badgerY + badgerImage.height / 2;
    }

    /**
     * Callback method called each time the user presses the mouse
     */
    public static void mousePressed() {
        for (int i = 0; i < badgers.length; i++) {
            if (badgers[i] != null && isMouseOver(badgers[i])) {
                badgers[i].startDragging();
                break; // Only start dragging the first Badger found under the mouse
            }
        }
    }
    /**
     * Callback method called each time the mouse is released
     */
    public static void mouseReleased() {
        for (int i = 0; i < badgers.length; i++) {
            if (badgers[i] != null) {
                badgers[i].stopDragging();
            }
        }
    }

    /**
     * Callback method called each time the user presses a key
     */
    public static void keyPressed() {
        if (Utility.key() == 'b' || Utility.key() == 'B'){
            for(int i = 0; i < 5; i++){
                if(badgers[i] == null){
                    float x = randGen.nextFloat() * Utility.width();
                    float y = randGen.nextFloat() * Utility.height();
                    badgers[i] = new Badger(x,y);
                    break;
                }
            }
        }

        if (Utility.key() == 'r' || Utility.key() == 'R'){
            for (int i = 0; i < 5; i++){
                if(badgers[i] != null){
                    badgers[i] = null;
                    break;
                }
            }
        }
    }


    public static void setup(){
        randGen = new Random();
        // load the image of the background
        backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
        badgerImage = Utility.loadImage("images" + File.separator + "badger.png");
        badgers = new Badger[5];
    }

    public static void draw(){
        Utility.background(Utility.color(255,218,185));
        // Draw the background image to the center of the screen
        Utility.image(backgroundImage, Utility.width()/2, Utility.height()/2);
        for (Badger badger : badgers) {
            if (badger != null) {
                badger.draw();
            }
        }
    }


    public static void main(String[] args){
        Utility.runApplication();
    }
}
