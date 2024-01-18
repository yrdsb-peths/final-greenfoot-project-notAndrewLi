import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The world where the game takes place.
 * It includes player, enemies, score, lives, and handles game logic.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    // Timer for enemy spawn
    SimpleTimer spawnTimer = new SimpleTimer();
    
    // Background image
    GreenfootImage bg = new GreenfootImage("images/bg.png");
    
    // Score display
    Label scoreLabel;
    
    // Player lives and score
    public int lives = 3;
    public Heart[] hearts = new Heart[lives];
    public int score = 0;
    
    // Flag indicating whether the game is over
    public boolean gameOver = false;
    
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        bg.scale(1000,600);
        setBackground(bg);
        
        // Add player
        Player knight = new Player();
        addObject(knight,200,getHeight() - 70);
        
        // Add score label
        scoreLabel = new Label(score,80);
        addObject(scoreLabel,900,50);
        
        // Add player lives
        for (int i = 0; i < lives; i++){ 
            hearts[i] = new Heart();
            addObject(hearts[i], 50 + 70 * i, 50);
        }
    }
    
    /**
     * Removes a heart actor and subtracts a life when player loses a life.
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
     * Spawns enemies from either side.
     */
    public void sideSpawn(){
        int x = Greenfoot.getRandomNumber(this.getWidth() - 100);
        x += 50; // To ensure it doesn't spawn on the ends and instantly despawns
        int y = getHeight() - 50;
        Enemy1 octopus = new Enemy1(x);
        addObject(octopus,x,y);
    }
    
    // Change based on how many enemies you want
    int spawnRate = 5000; 
    boolean dragonSpawned = false;
    
    /**
     * Handles game logic.
     */
    public void act(){
        String key = Greenfoot.getKey();
        
        // Spawn dragon after every 5 points and set spawn conditions
        if(score % 5 == 0 && score > 0 && !dragonSpawned){
            int side = Greenfoot.getRandomNumber(2);
            Enemy2 dragon = new Enemy2(side);
            int x; 
            if(side == 1) x = this.getWidth();
            else x = 0;
            int y = Greenfoot.getRandomNumber(100)+ 100;
            addObject(dragon,x,y);
            dragonSpawned = true;
        }
        
        // Reset dragonSpawned flag
        if(score % 5 != 0){
            dragonSpawned = false;
        }
        
        // Spawn enemies based on timer
        if(!gameOver && spawnTimer.millisElapsed() > spawnRate){
            sideSpawn();
            spawnTimer.mark();
        } else if (gameOver){
            // Restart the game on space key press after game over
            if(key == "space"){
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }
    
    /**
     * Increases the score when the player kills an enemy.
     */
    public void increaseScore(int x){
        score += x;
        scoreLabel.setValue(score);
        if(spawnRate >= 1000){
            spawnRate -= 200;
        }
    }
    
    /**
     * Displays a game over screen when the player loses all their lives.
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
