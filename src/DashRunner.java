import processing.core.PApplet;
import processing.core.PImage;

public class DashRunner extends PApplet {
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

    int jumpHeight;


    int startTime;
    int timer;

    float speedIncrease = .8f;
    float rateOfDifficulty = 5;

    int difficultyTime = 0;

    public static void main(String[] args) {
        PApplet.main("DashRunner");
    }

    public void settings() {
        size(700, 400);
    }

    public void setup() {
        duck = loadImage("Images/RealDuck.png");
        background = loadImage("Images/ActualBackground.png");
        obstacle = loadImage("Images/RealObstacle.png");

        obstacle.resize(100,0);
        duck.resize(150,0);

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
            jumpHeight+=30;


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
            jumpHeight = -600;
            duckY += jumpHeight;

        }

    }
    public void createObstacles(){
        imageMode(CENTER);
        image(obstacle,obstacleX,obstacleY);
        obstacleX -= speed;
        if(obstacleX < 0){
            obstacleX = width;
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

        }
    }


}
