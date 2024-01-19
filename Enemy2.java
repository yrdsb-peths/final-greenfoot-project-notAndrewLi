import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Enemy2 class represents a dragon enemy in the game.
 * Dragon enemies move horizontally, have a flying animation, and despawn when reaching the screen edges or when their HP reaches zero.
 * 
 * @author Andrew Li
 * @version 18 Jan 2024
 */
public class Enemy2 extends Actor
{
    // Timer for controlling the animation speed
    SimpleTimer animationTimer = new SimpleTimer();
    private int animationSpeed = 200;

    // Arrays to store animation frames for the dragon
    GreenfootImage[] dragon = new GreenfootImage[3];
    GreenfootImage[] dragonLeft = new GreenfootImage[3];

    // Variables to control direction and HP
    boolean facingRight = true;
    static int hp = 3;
    int imageIdx = 0;
    public int dragonSpeed = 20;

    /**
     * Creates a dragon enemy with the specified initial direction.
     * 
     * @param side The initial side (1 for left, 0 for right).
     */
    public Enemy2(int side)
    {
        // Initialize animation frames for the dragon
        for (int i = 0; i < 3; i++)
        {
            dragon[i] = new GreenfootImage("images/dragon/dragon" + (i + 1) + ".png");
            dragonLeft[i] = new GreenfootImage("images/dragon/dragon" + (i + 1) + ".png");
            dragonLeft[i].mirrorHorizontally();
        }

        // Set the initial direction based on the side parameter
        if (side == 1)
        {
            facingRight = false;
            setImage(dragonLeft[0]);
        }
    }

    /**
     * The act method controls the dragon's movement, animation, and despawning logic.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        fly();

        // Check if the dragon reaches the screen edges
        if (facingRight)
        {
            if (getX() > 990)
            {
                despawnDragon();
                world.loseLife();
            }
        }
        else
        {
            if (getX() < 10)
            {
                despawnDragon();
                world.loseLife();
            }
        }
    }

    /**
     * Performs the flying animation of the dragon.
     */
    public void fly()
    {
        // Check if enough time has elapsed for the next animation frame
        if (animationTimer.millisElapsed() < animationSpeed)
        {
            return;
        }
        animationTimer.mark();

        // Set the image and move the dragon based on its facing direction
        if (facingRight)
        {
            setImage(dragon[imageIdx]);
            move(dragonSpeed);
        }
        else
        {
            setImage(dragonLeft[imageIdx]);
            move(-dragonSpeed);
        }

        // Increment the image index for the next frame
        imageIdx = (imageIdx + 1) % 3;
    }
    /**
     * Decreases the dragon's HP and despawns it if HP reaches zero.
     */
    public void minusHp()
    {
        MyWorld world = (MyWorld) getWorld();
        hp--;

        // Print HP for debugging
        System.out.println(hp);

        // Check if HP reaches zero and increase score
        if (hp < 1)
        {
            world.increaseScore(3);
            despawnDragon();
        }
    }
    /**
     * Despawns the dragon by removing it from the world.
     */
    public void despawnDragon()
    {
        MyWorld world = (MyWorld) getWorld();
        world.removeObject(this);
    }
}
