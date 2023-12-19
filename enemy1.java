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
    GreenfootImage image = new GreenfootImage("images/Enemy1.png");
    boolean movingRight = false;
    boolean facingRight = false;
    SimpleTimer bobbing = new SimpleTimer();
    /**
     * creates the enemy and assigns which direction to face and move
     */
    public Enemy1 (int x){
        image.scale(100,100);
        setImage(image);
        if(x < 300){
            movingRight = true;
            facingRight = true;
        } else{
            movingRight = false;
            facingRight = false;
        }
    }
    /**
     * moves the octopus depending on which way it is facing
     */
    boolean bobUp = true;
    public void act()
    {
        // Add your action code here.
        if(bobbing.millisElapsed() > 500){
            bobUp = !bobUp;
            bobbing.mark();
        }
        if(movingRight){
            move(1);
            if(facingRight){
                image.mirrorHorizontally();
            }
            facingRight = false;
        } else{
            move(-1);
        }
        if(bobUp){
            setLocation(getX(), getY()+1);
        } else {
            setLocation(getX(), getY()-1);
        }
        despawn();
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