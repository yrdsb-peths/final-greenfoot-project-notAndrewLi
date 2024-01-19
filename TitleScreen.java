import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (Andrew Li) 
 * @version (Jan 18 2024)
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Knight-Guy", 100);
    Label instructions = new Label("Press space to play", 30);
    Label tutorialLabel = new Label("Press 't' for tutorial", 30);
    GreenfootImage bg = new GreenfootImage("images/titleScreen.jpg");
    /**
     * Constructor for objects of class TitleScreen.
     * Tells the player what to do
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        bg.scale(1000,600);
        setBackground(bg);
        //add labels to the title screen
        addObject(titleLabel, 500, 300);
        addObject(instructions, 500, 500);
        addObject(tutorialLabel, 500, 550);
    }
    
    /**
     * the act loop detecting the correct button pressed
     * t for tutorial, space for main game
     */
    public void act(){
        if(Greenfoot.isKeyDown("t")){
            Greenfoot.setWorld(new Tutorial());
        }else if (Greenfoot.isKeyDown("space")){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
