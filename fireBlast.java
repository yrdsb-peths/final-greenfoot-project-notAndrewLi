import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fireBlast here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fireBlast extends Actor
{
    /**
     * Act - do whatever the fireBlast wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] fire = new GreenfootImage[3];
    GreenfootImage explosion = new GreenfootImage("images/fireBlast/explosion.png");
    SimpleTimer animationTimer = new SimpleTimer();
    public fireBlast(){
        for(int i = 0; i < fire.length; i++){
            fire[i] = new GreenfootImage("images/fireBlast/fire" + (i+1) + ".png");
            fire[i].scale(200,200);
            explosion.scale(200,200);
        }
    }
    int currentFrame = 0;
    public void act()
    {
        // Add your action code here.
        if(animationTimer.millisElapsed() > 100){
            setImage(fire[currentFrame]);
            animationTimer.mark();
            currentFrame = (currentFrame + 1) % fire.length;
        }
        if(Greenfoot.getMouseInfo() != null){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            turnTowards(mouse.getX(), mouse.getY());
        }
        move(4);
        if(Greenfoot.getKey() == "e"){
            getWorld().removeObject(this);
            getWorld().getBackground().drawImage(explosion, explosion.getWidth(), explosion.getHeight());
        }
    }
}
