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
        Enemy1 octopus = new Enemy1(x);
        addObject(octopus,x,y);
    }
    int spawnRate = 5000; //change based on how many enemies you want
    boolean dragonSpawned = false;
    public void act(){
        String key = Greenfoot.getKey();
        if(score % 5 == 0 && score > 0 && !dragonSpawned){
            System.out.println("Spawned");
            int side = Greenfoot.getRandomNumber(2);
            Enemy2 dragon = new Enemy2(side);
            int x; 
            if(side == 1) x = this.getWidth();
            else x = 0;
            System.out.println(x);

            addObject(dragon,x,150);
            dragonSpawned = true;
        }
        if(score % 5 != 0){
            dragonSpawned = false;
        }
        if(!gameOver && spawnTimer.millisElapsed() > spawnRate){
            sideSpawn();
            spawnTimer.mark();
        } else if (gameOver){
            if(key == "space"){
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }
    /**
     * increases the score when the player kills an enemy
     */
    public void increaseScore(int x){
        score += x;
        scoreLabel.setValue(score);
        if(spawnRate >= 1000){
            spawnRate -= 500;
        }
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
