import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fireBlast here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBlast extends Actor
{
    /**
     * Act - do whatever the fireBlast wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] fire = new GreenfootImage[3];
    GreenfootImage explosion = new GreenfootImage("images/fireBlast/explosion.png");
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer explosionTimer = new SimpleTimer();
    GreenfootSound explosionSound = new GreenfootSound("sounds/explosionSound.mp3");
    GreenfootSound chargeExplosion = new GreenfootSound("sounds/chargeExplosion.mp3");
    boolean exploded = false;
    World world;
    public FireBlast(){
        for(int i = 0; i < fire.length; i++){
            fire[i] = new GreenfootImage("images/fireBlast/fire" + (i+1) + ".png");
            fire[i].scale(200,200);
            explosion.scale(200,200);
        }
        chargeExplosion.play();
    }
    int currentFrame = 0;
    public void act()
    {
        // Add your action code here.
        world = getWorld();
        if(!exploded){
            if(animationTimer.millisElapsed() > 100){
                setImage(fire[currentFrame]);
                animationTimer.mark();
                currentFrame = (currentFrame + 1) % fire.length;
            }
            if(Greenfoot.getMouseInfo() != null){
                MouseInfo mouse = Greenfoot.getMouseInfo();
                turnTowards(mouse.getX(), mouse.getY());
            }
            move(10);
        }
        if(explosionTimer.millisElapsed() > 5000 || isTouching(Enemy1.class) || isTouching(Enemy2.class) || exploded){
            
            explode();
        }
    }
    public void explode(){
        if(!exploded){
            explosionSound.play();
        }
        exploded = true;
        setImage(explosion);
        if(isTouching(Enemy1.class)){
            removeTouching(Enemy1.class);
            ((MyWorld)getWorld()).increaseScore(1);
        }
        if(isTouching(Enemy2.class)){
            removeTouching(Enemy2.class);
            ((MyWorld)getWorld()).increaseScore(3);
        }
        if(explosion.getWidth() < 100){
            explosion.scale(explosion.getWidth() + 5, explosion.getHeight() + 10);
        }
        if(explosion.getTransparency() > 10){
            explosion.setTransparency(explosion.getTransparency() - 3);
        } else{
            world.removeObject(this);
        }
    }
}
