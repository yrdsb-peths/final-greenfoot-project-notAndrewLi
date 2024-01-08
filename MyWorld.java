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
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 400, 1);
        Player knight = new Player();
        addObject(knight,200,300);
    }
    /**
     * Spawns enemies from either side
     */
    public void sideSpawn(){
        int x = Greenfoot.getRandomNumber(this.getWidth());
        int y = 275;
        Enemy1 enemy = new Enemy1(x);
        addObject(enemy,x,y);
    }
    
    public void act(){
        if(spawnTimer.millisElapsed() > 9000){
            sideSpawn();
            spawnTimer.mark();
        }
    }
}
