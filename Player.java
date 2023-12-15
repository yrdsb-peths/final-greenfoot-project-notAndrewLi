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
    GreenfootImage knight = new GreenfootImage("images/knight.png");
    public Player (){
        knight.scale(150,150);
        setImage(knight);
    }
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("a")){
            move(-5);
        }
        if(Greenfoot.isKeyDown("d")){
            move(5);
        }
    }
}
