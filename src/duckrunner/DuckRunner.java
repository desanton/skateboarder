package duckrunner;

import processing.core.PApplet;
import processing.core.PImage;

public class DuckRunner extends PApplet {
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

    int score = 0;
    int highScore = 0;

    static GameState currentState;


    public static void main(String[] args) {
        PApplet.main("duckrunner.DuckRunner");
    }

    public void settings() {
        size(700, 400);
    }

    public void setup() {
        currentState = GameState.RUNNING;

        duck = loadImage("Images/Duck.png");
        background = loadImage("Images/Sky.png");
        obstacle = loadImage("Images/Hydrant.png");

        obstacle.resize(50,0);


        startTime = millis();


    }

    public void draw() {

        switch(currentState){
            case RUNNING:
                drawBackground();
                drawDuck();
                createObstacles();
                drawScore();
                increaseDifficulty();

                timer = (millis()-startTime)/1000;

                break;

            case GAMEOVER:
                speed = 0;
                drawGameOver();
                break;


        }


    }

    public void drawDuck() {
        imageMode(CENTER);
        image(duck, duckX, duckY);

        if(duckY <= 300){
            jumpHeight+=1;


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

        if (currentState == GameState.RUNNING){
            if(duckY >= 300){
                jumpHeight = -20;
                duckY += jumpHeight;

            }
        }
        if (currentState == GameState.GAMEOVER) {
            obstacleX = 0;
            bgX = 0;
            startTime = millis();
            speed = 5;
            currentState = GameState.RUNNING;
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
            score = timer;
            if(score > highScore){
                highScore = score;
            }
            currentState = GameState.GAMEOVER;
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
    enum GameState{
        GAMEOVER, RUNNING
    }
    public void drawGameOver(){
        fill(255,190,190);
        noStroke();
        rect(width/2 - 125, height/2 - 80,250,160);
        fill(255,100,100);
        textAlign(CENTER);
        text("Game Over!", width/2, height/2 - 50);
        text("Your Score " +score, width/2, height/2 - 30);
        text("High Score " + highScore, width/2, height/2 - 10);

    }



}