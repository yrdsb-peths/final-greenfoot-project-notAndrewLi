import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HighScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighScore extends Actor
{
    /**
     * Act - do whatever the HighScore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static int highScore = 0;
    public static void setScore(int score){
        if(score > highScore){
            highScore = score;
        }
    }
    public static int getScore(){
        return highScore;
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
