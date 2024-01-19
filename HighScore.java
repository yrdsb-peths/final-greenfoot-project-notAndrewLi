import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HighScore here.
 * 
 * @author (Andrew Li) 
 * @version (Jan 18 2024)
 */
public class HighScore extends Actor
{
    /**
     * Act - do whatever the HighScore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //set the high score to a new score
    static int highScore = 0;
    public static void setScore(int score){
        if(score > highScore){
            highScore = score;
        }
    }
    //return high score
    public static int getScore(){
        return highScore;
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
