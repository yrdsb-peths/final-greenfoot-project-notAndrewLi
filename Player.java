import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] idleLeft = new GreenfootImage[4];
    GreenfootImage[] idleRight = new GreenfootImage[4];
    boolean facingRight = true;
    SimpleTimer animationTimer = new SimpleTimer();
    /**
     * Makes a left and right facing knight depending on which way the player is facing.
     */
    public Player (){
        for(int i = 0; i < idleLeft.length; i++){
            idleLeft[i] = new GreenfootImage("images/knightIdle/idle" + (i+1) + ".png");
            idleLeft[i].scale(150, 150);
            idleRight[i].mirrorHorizontally();
        }
        for(int i = 0; i < idleRight.length; i++){
            idleRight[i] = new GreenfootImage("images/knightIdle/idle" + (i+1) + ".png");
            idleRight[i].scale(150, 150);
        }
        animationTimer.mark();
        setImage(idleRight[1]);
    }
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("a")){
            facingRight = false;
            move(-5);
        }
        if(Greenfoot.isKeyDown("d")){
            facingRight = true;
            move(5);
        }
        
    }
    int imageIndex = 0;
    /**
     * Animates the knight by setting the knight to a new image every 100 milliseconds
     */
    public void animateKnight(){
        if(animationTimer.millisElapsed() < 100){
            return;
        }
        animationTimer.mark();
        if(facingRight){
            setImage(idleRight[imageIndex]);
        } else{
            setImage(idleLeft[imageIndex]);
        }
        imageIndex = (imageIndex + 1) % idleRight.length;
    }
}
