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
    public void act()
    {
        // Add your action code here
        GreenfootImage image = this.getImage();
        image.scale(100,100);
        image.setTransparency(image.getTransparency() - 5);
        if(image.getTransparency() < 10){
            getWorld().removeObject(this);
        }
    }
}
