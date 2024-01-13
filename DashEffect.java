import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DashEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DashEffect extends Actor
{
    /**
     * Act - do whatever the DashEffect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage effect = new GreenfootImage("images/dash.png");
    public DashEffect(){
        this.setImage(effect);
    }
    public void act()
    {
        // Add your action code here
        effect.setTransparency(effect.getTransparency() - 10);
        if(effect.getTransparency() < 10){
            getWorld().removeObject(this);
        }
    }
}
