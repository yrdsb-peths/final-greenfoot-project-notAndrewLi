import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    SimpleTimer spawnTimer = new SimpleTimer();
    GreenfootImage bg = new GreenfootImage("images/bg.png");
    Label scoreLabel;
    public int lives = 3;
    public Heart[] hearts = new Heart[lives];
    public int score = 0;
    public boolean gameOver = false;
    public MyWorld()
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        bg.scale(1000,600);
        setBackground(bg);
        Player knight = new Player();
        addObject(knight,200,getHeight() - 70);
        scoreLabel = new Label(score,80);
        addObject(scoreLabel,900,50);
        for (int i = 0; i < lives; i++){ // add lives based on how many lives the player chose
            hearts[i] = new Heart();
            addObject(hearts[i], 50 + 70 * i, 50);
        }
    }
    /**
     * Removes a heart actor and subtracts a life
     */
    public void loseLife(){
        if(lives > 1){
            removeObject(hearts[lives - 1]);
            lives--;
        } else{
            gameOver();
        }
    }
    /**
     * Spawns enemies from either side
     */
    public void sideSpawn(){
        int x = Greenfoot.getRandomNumber(this.getWidth() - 100);
        x += 50;//to ensure it doesn't spawn on the ends and instantly despawns
        int y = getHeight() - 50;
        Enemy1 enemy = new Enemy1(x);
        addObject(enemy,x,y);
    }
    int spawnRate = 5000; //change based on how many enemies you want
    public void act(){
        if(!gameOver && spawnTimer.millisElapsed() > spawnRate){
            sideSpawn();
            spawnTimer.mark();
        } else if (gameOver){
            String key = Greenfoot.getKey();
            if(key == "space"){
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }
    /**
     * increases the score when the player kills an enemy
     */
    public void increaseScore(){
        score++;
        scoreLabel.setValue(score);
    }
    /**
     * a gameOver screen for when the player loses all their lives
     */
    GreenfootSound endSound = new GreenfootSound ("sounds/sadTrombone.mp3");
    public void gameOver(){
        gameOver = true;
        List objects = getObjects(null);
        removeObjects(objects);
        Label gameOverLabel = new Label ("Game Over", 100);
        addObject(gameOverLabel, getWidth() / 2, 200);
        gameOverLabel.setFillColor(Color.RED);
        endSound.play();
        HighScore.setScore(score);
        int highScore = HighScore.getScore();
        Label highestScore = new Label("High Score: " + highScore, 50);
        addObject(highestScore, getWidth() / 2, 100);
        Label restartLabel = new Label ("Space to Restart", 80);
        addObject(restartLabel, getWidth() / 2, 400);
    }
}
