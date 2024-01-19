import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The FireBlast class represents a projectile that can explode upon contact with enemies or after a certain duration.
 * It includes an animation for charging and an explosion effect upon impact or after a timer.
 * 
 * @author Andrew Li
 * @version Jan 18, 2024
 */
public class FireBlast extends Actor
{
    GreenfootImage[] fire = new GreenfootImage[3];
    GreenfootImage explosion = new GreenfootImage("images/fireBlast/explosion.png");

    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer explosionTimer = new SimpleTimer();

    GreenfootSound explosionSound = new GreenfootSound("sounds/explosionSound.mp3");
    GreenfootSound chargeExplosion = new GreenfootSound("sounds/chargeExplosion.mp3");

    // Flag to track whether the fire blast has exploded
    boolean exploded = false;

    // Reference to the world
    World world;

    /**
     * Creates a FireBlast object and initializes its animation frames and explosion images.
     */
    public FireBlast()
    {
        for (int i = 0; i < fire.length; i++)
        {
            fire[i] = new GreenfootImage("images/fireBlast/fire" + (i + 1) + ".png");
            fire[i].scale(200, 200);
            explosion.scale(100, 100); // Initial size of the explosion
        }
        chargeExplosion.play();
    }

    int currentFrame = 0;

    /**
     * The act method controls the animation, movement, and explosion logic of the fire blast.
     */
    public void act()
    {
        world = getWorld();

        // Check if the fire blast has not exploded yet
        if (!exploded)
        {
            // Animate the fire blast
            if (animationTimer.millisElapsed() > 100)
            {
                setImage(fire[currentFrame]);
                animationTimer.mark();
                currentFrame = (currentFrame + 1) % fire.length;
            }

            // Orient the fire blast toward the mouse pointer
            if (Greenfoot.getMouseInfo() != null)
            {
                MouseInfo mouse = Greenfoot.getMouseInfo();
                turnTowards(mouse.getX(), mouse.getY());
            }

            // Move the fire blast
            move(10);
        }

        // Check conditions for explosion
        if (explosionTimer.millisElapsed() > 5000 || isTouching(Enemy1.class) || isTouching(Enemy2.class) || exploded)
        {
            explode();
        }
    }

    /**
     * Performs the explosion effect when called.
     * The explosion removes touching enemies and increases the player's score.
     */
    public void explode()
    {
        // Play explosion sound only once
        if (!exploded)
        {
            explosionSound.play();
        }

        exploded = true;
        setImage(explosion);

        // Remove touching enemies and increase score
        if (isTouching(Enemy1.class))
        {
            removeTouching(Enemy1.class);
            ((MyWorld) getWorld()).increaseScore(1);
        }
        if (isTouching(Enemy2.class))
        {
            removeTouching(Enemy2.class);
            ((MyWorld) getWorld()).increaseScore(3);
        }

        // Expand the explosion size gradually and reduce transparency
        if (explosion.getWidth() < 200)
        {
            explosion.scale(explosion.getWidth() + 5, explosion.getHeight() + 10);
        }
        if (explosion.getTransparency() > 10)
        {
            explosion.setTransparency(explosion.getTransparency() - 3);
        } else
        {
            // Remove the FireBlast object from the world when the explosion is done
            world.removeObject(this);
        }
    }
}
