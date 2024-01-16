import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Knight-Guy", 100);
    Label instructions = new Label("Press any button to Play", 30);
    Label tutorialLabel = new Label("Press 't' for tutorial", 30);
    GreenfootImage bg = new GreenfootImage("images/titleScreen.jpg");
    /**
     * Constructor for objects of class TitleScreen.
     * Has a knight for the player to play around with
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        bg.scale(1000,600);
        setBackground(bg);
        addObject(titleLabel, 500, 300);
        addObject(instructions, 500, 500);
        addObject(tutorialLabel, 500, 550);
    }
    
    /**
     * the act loop detecting the correct button pressed
     */
    public void act(){
        String key = null;
        key = Greenfoot.getKey();
        System.out.println(key);
        if(key != null){
            if(key == "t"){
                Greenfoot.setWorld(new Tutorial());
            }else{
                Greenfoot.setWorld(new MyWorld());
            }
        }
    }
}
