import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Enemy1 class represents an octopus enemy in the game.
 * Octopus enemies move horizontally, bob up and down, and despawn when out of bounds.
 * 
 * @author (Andrew Li)
 * @version (Jan 18 2024)
 */
public class Enemy1 extends Actor
{
    // Arrays to store animation frames for the octopus
    GreenfootImage[] octopus = new GreenfootImage[7];
    GreenfootImage[] octopusFlip = new GreenfootImage[7];

    // Variables to control movement and animation
    boolean movingRight = false;
    boolean facingRight = false;
    SimpleTimer bobbing = new SimpleTimer();
    public int octopusSpeed = 3;

    /**
     * Creates the enemy and assigns its initial direction and movement.
     * 
     * @param x The initial x-coordinate to determine the direction of movement.
     */
    public Enemy1(int x)
    {
        // Initialize animation frames for the octopus
        for (int i = 0; i < octopus.length; i++)
        {
            octopus[i] = new GreenfootImage("images/octopus/octopus_" + i + ".png");
            octopus[i].scale(100, 100);
            octopusFlip[i] = new GreenfootImage("images/octopus/octopus_" + i + ".png");
            octopusFlip[i].mirrorHorizontally();
            octopusFlip[i].scale(100, 100);
        }

        // Determine initial direction and orientation
        if (x < 300)
        {
            movingRight = true;
            facingRight = true;
        } 
        else
        {
            movingRight = false;
            facingRight = false;
        }

        setImage(octopus[0]);
        bobbing.mark();
    }

    /**
     * Moves the octopus depending on its facing direction.
     * Also handles bobbing animation and despawning when out of bounds.
     */
    public void act()
    {
        if (movingRight)
            move(octopusSpeed);
        else
            move(-octopusSpeed);
        
        bob();
        despawn();
    }

    int imageIdx = 0;

    /**
     * Performs the bobbing animation of the octopus.
     */
    public void bob()
    {
        if (bobbing.millisElapsed() < 300)
        {
            return;
        }
        bobbing.mark();

        // Set the image based on the facing direction
        if (facingRight)
        {
            setImage(octopusFlip[imageIdx]);
        } 
        else
        {
            setImage(octopus[imageIdx]);
        }

        imageIdx = (imageIdx + 1) % octopus.length;
    }

    /**
     * Despawns the octopus if it goes out of bounds.
     */
    public void despawn()
    {
        MyWorld world = (MyWorld) getWorld();
        if (getX() >= world.getWidth() * 0.99 || getX() <= 0)
        {
            world.removeObject(this);
            world.loseLife();
        }
    }
}
