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
    boolean isSwinging = false;
    private int gravity;
    SimpleTimer animationTimer = new SimpleTimer();
    private int animationSpeed = 75; // Adjust this value based on your desired animation speed
    SimpleTimer dashCD = new SimpleTimer();
    SimpleTimer attackTimer = new SimpleTimer();

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
        attackTimer.mark();
        animationTimer.mark();
        dashCD.mark();
        setImage(idleRight[0]);
    }
    /**
     * The loop that runs repeatedly while the game has started
     */
    private boolean isGrounded = true;
    public void act()
    {
        // Add your action code here.
        gravity--;
        setLocation(getX(), getY() - gravity);
        if(slide > 0){
            slide-=2;
        }
        if(facingRight){
            setLocation(getX() + slide, getY());
        }else{
            setLocation(getX() - slide, getY());
        }
        if(getY() > this.getWorld().getHeight() - 75){
            setLocation(getX(), this.getWorld().getHeight() - 75);
            isGrounded = true;
        } else{
            isGrounded = false;
        }
        checkKeys();
        animateKnight();
    }
    /**
     * Checks which key has been pressed
     */
    public void checkKeys(){
        idle = false;
        int direction = 0;
        if((Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("space")) && isGrounded){
            jump();
        }
        if(Greenfoot.mouseClicked(null) && isSwinging == false){//detects for left click
            System.out.println("Swinging");
            isSwinging = true;
        }
        if(Greenfoot.isKeyDown("q")){
            dash();
        }
        if(Greenfoot.isKeyDown("a")){
            facingRight = false;
            direction--;
        }
        else if(Greenfoot.isKeyDown("d")){
            facingRight = true;
            direction++;
        }
        else{
            idle = true;
        }
        move(direction * 5);
    }
    /**
     * Sets the gravity to 20
     * in the act method, the location will be subtracted 20, causing the actor to go up
     */
    public void jump(){
        if(isGrounded){
            gravity = 20;
        }
    }
    int imageIndex = 0;
    /**
     * Animates the knight by setting the actor to a new image every 
     * 100 milliseconds
     */
    public void animateKnight(){
        if(animationTimer.millisElapsed() < animationSpeed) return;
        animationTimer.mark();
        if(idle){
            if(facingRight) setImage(idleRight[imageIndex]); 
            else setImage(idleLeft[imageIndex]);
        }else{
            if(facingRight) setImage(runRight[imageIndex]);
            else setImage(runLeft[imageIndex]);
        }
        if(isSwinging){
            if(facingRight) setImage(swingRight[imageIndex]);
            else setImage(swingLeft[imageIndex]);
            if(imageIndex == 3) isSwinging = false;
        }
        imageIndex = (imageIndex + 1) % 4; // sets the image to the next one
    }
    
    private int slide = 0;
    /**
     * a horizontal dash to increase the players mobility
     */
    private void dash(){
        if(dashCD.millisElapsed() < 2000) return;
        dashCD.mark();
        DashEffect dashEffect = new DashEffect();
        if(facingRight){
            dashEffect.getImage().mirrorHorizontally();
            getWorld().addObject(dashEffect, getX() - 100, getY());
        } else{
            getWorld().addObject(dashEffect, getX() + 100, getY());
        }    
        slide = 40;
    }
    
    /**
     * The Player's animation to swing their sword, a different animation 
     * plays depending on the direction the player is facing.
     */
    private int currentFrame = 0;
    
    public void swingSword() {
        System.out.println("procked");
        currentFrame = 0;
        if (attackTimer.millisElapsed() > animationSpeed) {
            System.out.println("removingObject");
            attackTimer.mark();
            if (facingRight) {
                setImage(swingRight[currentFrame]);
                currentFrame = (currentFrame + 1) % swingRight.length;
            } else {
                setImage(swingLeft[currentFrame]);
                currentFrame = (currentFrame + 1) % swingLeft.length;
            }
        }
        getWorld().removeObject(getOneIntersectingObject(Enemy1.class));
    }
}
