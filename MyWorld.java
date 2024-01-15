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
    public GreenfootImage[] hearts = new GreenfootImage[lives];
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
        for (int i = 0; i < lives; i++){
            hearts[lives - 1 - i] = new GreenfootImage("images/heart.png");
            hearts[lives - 1 - i].scale(50,50);
            getBackground().drawImage(hearts[2-i], 50 + 70 * i, 50);
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
    
    public void increaseScore(){
        score++;
        scoreLabel.setValue(score);
    }
}
