import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy1 extends Actor
{
    /**
     * Act - do whatever the Enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] octopus = new GreenfootImage[7];
    GreenfootImage[] octopusFlip = new GreenfootImage[7];
    boolean movingRight = false;
    boolean facingRight = false;
    SimpleTimer bobbing = new SimpleTimer();
    /**
     * creates the enemy and assigns which direction to face and move
     */
    public Enemy1 (int x){
        //animation frames for the octopus
        for (int i = 0; i < octopus.length; i++){
            octopus[i] = new GreenfootImage("images/octopus/octopus_" + i + ".png");
            octopus[i].scale(100,100);
            octopusFlip[i] = new GreenfootImage("images/octopus/octopus_" + i + ".png");
            octopusFlip[i].mirrorHorizontally();
            octopusFlip[i].scale(100,100);
        }
        if(x < 300){
            movingRight = true;
            facingRight = true;
        } else{
            movingRight = false;
            facingRight = false;
        }
        setImage(octopus[0]);
        bobbing.mark();
    }
    /**
     * moves the octopus depending on which way it is facing
     */
    public void act()
    {
        // Add your action code here.
        if(movingRight){
            move(3);
        } else{
            move(-3);
        }
        bob();
        despawn();
    }
    int imageIdx = 0;
    public void bob(){
        if(bobbing.millisElapsed() < 300){
            return;
        }
        bobbing.mark();
        if(facingRight){
            setImage(octopusFlip[imageIdx]);
        } else{
            setImage(octopus[imageIdx]);
        }
        imageIdx = (imageIdx + 1) % octopus.length;
    }
    /**
     * if the octopus is out of bounds, delete it
     */
    public void despawn(){
        MyWorld world = (MyWorld) getWorld();
        if(getX() >= world.getWidth() * 0.99 || getX() <= 0){
            world.removeObject(this);
        }
    }
}