import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        if(lives > 0){
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
        int x = Greenfoot.getRandomNumber(this.getWidth());
        int y = getHeight() - 50;
        Enemy1 enemy = new Enemy1(x);
        addObject(enemy,x,y);
    }
    int spawnRate = 5000; //change based on how many enemies you want
    public void act(){
        if(spawnTimer.millisElapsed() > spawnRate){
            sideSpawn();
            spawnTimer.mark();
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
        Label gameOverLabel = new Label ("Game Over", 100);
        addObject(gameOverLabel, 300, 200);
        gameOverLabel.setFillColor(Color.RED);
        endSound.play();
    }
}
