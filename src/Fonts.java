import processing.core.PApplet;
import processing.core.PFont;


public class Fonts extends PApplet {

    PFont font;
    public static void main(String[] args) {
        PApplet.main("Fonts");
        String[] fontList = PFont.list();
        printArray(fontList);
    }

    public void settings() {
        size(600, 500);
    }

    public void setup() {
        font= createFont("Arial",16,true);
    }


    public void draw() {
        background (255, 255, 255);
    }

}
