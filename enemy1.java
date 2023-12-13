import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class enemy1 extends Actor
{
    /**
     * Act - do whatever the enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = new GreenfootImage("images/Enemy1.png");
    public void enemy1(){
        image.scale(100,100);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
        move(1);
    }
}
