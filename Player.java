import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * It is a knight which runs around killing the evil octopus
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
    GreenfootImage[] runLeft = new GreenfootImage[4];
    GreenfootImage[] runRight = new GreenfootImage[4];
    GreenfootImage[] swingLeft = new GreenfootImage[4];
    GreenfootImage[] swingRight = new GreenfootImage[4];
    boolean facingRight = true;
    boolean idle = true;
    SimpleTimer animationTimer = new SimpleTimer();
    /**
     * Makes a left and right facing knight depending on which way the player is facing.
     */
    public Player (){
        for(int i = 0; i < 4; i++){
            idleLeft[i] = new GreenfootImage("images/knightIdle/idle" + (i+1) + ".png");
            idleLeft[i].scale(150, 150);
            idleLeft[i].mirrorHorizontally();
            idleRight[i] = new GreenfootImage("images/knightIdle/idle" + (i+1) + ".png");
            idleRight[i].scale(150, 150);
            runLeft[i] = new GreenfootImage("images/knightRun/run" + (i+1) + ".png");
            runLeft[i].scale(150, 150);
            runLeft[i].mirrorHorizontally();
            runRight[i] = new GreenfootImage("images/knightRun/run" + (i+1) + ".png");
            runRight[i].scale(150, 150);
            swingRight[i] = new GreenfootImage("images/knightSwing/swing" + (i+1) + ".png");
            swingRight[i].scale(150, 150);
            swingLeft[i] = new GreenfootImage("images/knightSwing/swing" + (i+1) + ".png");
            swingLeft[i].mirrorHorizontally();
            swingLeft[i].scale(150, 150);
        }
        animationTimer.mark();
        setImage(idleRight[1]);
    }
    public void act()
    {
        // Add your action code here.
        idle = false;
        if(Greenfoot.isKeyDown("a")){
            facingRight = false;
            move(-5);
        }
         else if(Greenfoot.isKeyDown("d")){
            facingRight = true;
            move(5);
        }
        else if(Greenfoot.mouseClicked(null)){
            swingSword();
        }
        else{
            idle = true;
        }
        animateKnight();
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
        if(idle){
            if(facingRight){
                setImage(idleRight[imageIndex]);
            } else {
                setImage(idleLeft[imageIndex]);
            }
        }else{
            if(facingRight){
                setImage(runRight[imageIndex]);
            } else {
                setImage(runLeft[imageIndex]);
            }
        }
        imageIndex = (imageIndex + 1) % idleRight.length;
    }
    public void swingSword(){
        if(facingRight){
            for(int i = 0; i < swingRight.length; i++){
                setImage(swingRight[i]);
                Greenfoot.delay(1);
            }
        } else{
            for(int i = 0; i < swingLeft.length; i++){
                setImage(swingLeft[i]);
                Greenfoot.delay(1);
            }
        }
        setImage(idleLeft[1]);
    }
}
