import processing.core.PApplet;
import processing.core.PImage;

public class BEEGRunner extends PApplet {
    PImage duck;
    PImage background;
    PImage obstacle;


    int duckX = 140;
    int duckY = 300;

    int bgX = 0;
    int bgY = 0;

    int obstacleX;
    int obstacleY = 330;


    float speed = 5;

    float jumpHeight;


    int startTime;
    int timer;

    float speedIncrease = .8f;
    float rateOfDifficulty = 5;

    int difficultyTime = 0;
    int fairSpawn;

    public static void main(String[] args) {
        PApplet.main("BEEGRunner");
    }

    public void settings() {
        size(700, 400);
    }

    public void setup() {
        duck = loadImage("Images/RealDuck.png");
        background = loadImage("Images/ActualBackground.png");
        obstacle = loadImage("Images/RealObstacle.png");

        obstacle.resize(200,50);
        duck.resize(230,0);
        background.resize(1400,800);

        startTime = millis();


    }

    public void draw() {

        drawBackground();
        drawDuck();
        createObstacles();
        drawScore();
        increaseDifficulty();

        timer = (millis()-startTime)/1000;


    }

    public void drawDuck() {
        imageMode(CENTER);
        image(duck, duckX, duckY);

        if(duckY <= 300){
            jumpHeight+=0.5;


            duckY += jumpHeight;
        }
    }

    public void drawBackground() {
        imageMode(CORNER);
        image(background, bgX, bgY);
        image(background,bgX + background.width, 0);

        bgX -= speed;
        if(bgX <= (background.width  * - 1)){
            bgX = 0;
        }
    }
    public void mousePressed(){
        if(duckY >= 300){
            jumpHeight = -20;
            duckY += jumpHeight;

        }

    }
    public void createObstacles(){
        imageMode(CORNER);
        image(obstacle,obstacleX,obstacleY);
        obstacleX -= speed;
        if(obstacleX < -200){
            obstacleX = width + fairSpawn;
        }
        if((abs(duckX-obstacleX)<40)&& abs(duckY-obstacleY)<80){
            System.out.println("Gameover");
            System.exit(0);
        }

    }
    public void drawScore(){
        fill(255,255,255);
        textAlign(CENTER);
        text("Score:" + timer,width-70,30);
    }
    public void increaseDifficulty(){
        if(timer % rateOfDifficulty == 0 && difficultyTime != timer){
            speed += speedIncrease;
            difficultyTime = timer;

            fairSpawn += 100;

        }
    }


}
