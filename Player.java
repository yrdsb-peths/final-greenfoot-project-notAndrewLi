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
     * Makes a left and right facing knight and animations
     * depending on which way the player is facing.
     * running, idle, attacking
     */
    public Player (){
        // set up all the animation arrays
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
        setImage(idleRight[0]);
    }
    SimpleTimer swingCD = new SimpleTimer();
    public void act()
    {
        // Add your action code here.
        idle = false;
        int direction = 0;
        if(Greenfoot.isKeyDown("a"))direction--;
        else if(Greenfoot.isKeyDown("d"))direction++;
        else if(Greenfoot.mouseClicked(null)){
            if(swingCD.millisElapsed() > 500){
                swingCD.mark();
                swingSword();
            }
        }
        else{
            idle = true;
        }
        move(direction * 5);
        if(direction >= 0) facingRight = true;
        else facingRight = false;
        animateKnight();
    }
    int imageIndex = 0;
    /**
     * Animates the knight by setting the actor to a new image every 
     * 100 milliseconds
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
    
    /**
     * The Player's animation to swing their sword, a different animation 
     * plays depending on the direction the player is facing.
     */
    SimpleTimer attackTimer = new SimpleTimer();
    private int currentFrame = 0;
    private int animationSpeed = 75; // Adjust this value based on your desired animation speed
    
    public void swingSword() {
        if (attackTimer.millisElapsed() > animationSpeed) {
            attackTimer.mark();
            if (facingRight) {
                setImage(swingRight[currentFrame]);
                currentFrame = (currentFrame + 1) % swingRight.length;
            } else {
                setImage(swingLeft[currentFrame]);
                currentFrame = (currentFrame + 1) % swingLeft.length;
            }
        }
    }
}
