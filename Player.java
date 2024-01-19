import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Player class represents a knight character that can run, jump, swing a sword, dash, and shoot fire blasts.
 * It includes various animations and interactions with the game environment.
 * 
 * @author Andrew Li
 * @version Jan 18, 2024
 */
public class Player extends Actor
{
    // Arrays to store animation frames for different actions
    GreenfootImage[] idleLeft = new GreenfootImage[4];
    GreenfootImage[] idleRight = new GreenfootImage[4];
    GreenfootImage[] runLeft = new GreenfootImage[4];
    GreenfootImage[] runRight = new GreenfootImage[4];
    GreenfootImage[] swingLeft = new GreenfootImage[4];
    GreenfootImage[] swingRight = new GreenfootImage[4];

    // Flags to track player state and actions
    boolean facingRight = true;
    boolean idle = true;
    boolean isSwinging = false;
    boolean canFireBlast = true;
    boolean jumping = false;
    boolean canDoubleJump = true;
    boolean canDash = true;
    private boolean isGrounded = true;
    
    private int slide = 0;
    private int gravity;
    int imageIndex = 0;
    int swingIndex = 0;
    private int animationSpeed = 75; // Adjust this value based on your desired animation speed

    // Timers to control various cooldowns and animation intervals
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer dashCD = new SimpleTimer();
    GreenfootSound swordSound = new GreenfootSound("sounds/swordSound.mp3");
    GreenfootSound jumpSound = new GreenfootSound("sounds/jump.mp3");
    SimpleTimer fireBlastCD = new SimpleTimer();


    /**
     * Creates a knight character with various animations.
     */
    public Player()
    {
        // Set up all the animation arrays
        for (int i = 0; i < 4; i++)
        {
            idleLeft[i] = new GreenfootImage("images/knightIdle/idle" + (i + 1) + ".png");
            idleLeft[i].scale(150, 150);
            idleLeft[i].mirrorHorizontally();
            idleRight[i] = new GreenfootImage("images/knightIdle/idle" + (i + 1) + ".png");
            idleRight[i].scale(150, 150);
            runLeft[i] = new GreenfootImage("images/knightRun/run" + (i + 1) + ".png");
            runLeft[i].scale(150, 150);
            runLeft[i].mirrorHorizontally();
            runRight[i] = new GreenfootImage("images/knightRun/run" + (i + 1) + ".png");
            runRight[i].scale(150, 150);
            swingRight[i] = new GreenfootImage("images/knightSwing/swing" + (i + 1) + ".png");
            swingRight[i].scale(150, 150);
            swingLeft[i] = new GreenfootImage("images/knightSwing/swing" + (i + 1) + ".png");
            swingLeft[i].mirrorHorizontally();
            swingLeft[i].scale(150, 150);
        }
        setImage(idleRight[0]);
    }

    /**
     * The loop that runs repeatedly while the game has started.
     */
    public void act()
    {
        gravity--;
        setLocation(getX(), getY() - gravity);
        if (slide > 0)
        {
            slide -= 2;
        }
        if (facingRight)
        {
            setLocation(getX() + slide, getY());
        } else
        {
            setLocation(getX() - slide, getY());
        }
        if (getY() > this.getWorld().getHeight() - 75)
        {
            setLocation(getX(), this.getWorld().getHeight() - 75);
            isGrounded = true;
            canDoubleJump = true;
        } else
        {
            isGrounded = false;
        }
        if (dashCD.millisElapsed() > 2000)
        {
            dashCD.mark();
            canDash = true;
        }
        if (fireBlastCD.millisElapsed() > 5000)
        {
            fireBlastCD.mark();
            canFireBlast = true;
        }
        checkKeys();
        animateKnight();
    }

    /**
     * Checks which keys have been pressed and triggers corresponding actions.
     */
    public void checkKeys()
    {
        idle = false;
        int direction = 0;
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("space"))
        {
            jump();
            jumping = true;
        } else
        {
            jumping = false;
        }
        if (Greenfoot.mouseClicked(null) && !isSwinging)
        {
            swingIndex = 0;
            isSwinging = true;
            if (swordSound.isPlaying())
            {
                swordSound.stop();
            }
            swordSound.play();
        }
        if (Greenfoot.isKeyDown("q"))
        {
            dash();
        }
        if (Greenfoot.isKeyDown("e") && canFireBlast)
        {
            canFireBlast = false;
            FireBlast fireBlast = new FireBlast();
            getWorld().addObject(fireBlast, getX(), getY());
        }
        if (Greenfoot.isKeyDown("a"))
        {
            facingRight = false;
            direction--;
        } else if (Greenfoot.isKeyDown("d"))
        {
            facingRight = true;
            direction++;
        } else
        {
            idle = true;
        }
        move(direction * 5);
    }

    /**
     * Initiates a jump action.
     */
    public void jump()
    {
        if (jumpSound.isPlaying())
        {
            jumpSound.stop();
        }
        jumpSound.play();
        if (!jumping && isGrounded)
            gravity = 20;
        if (!jumping && !isGrounded && canDoubleJump)
        {
            gravity = 20;
            canDoubleJump = false;
        }
    }

    /**
     * Animates the knight by setting the actor to a new image every 75 milliseconds.
     */
    public void animateKnight()
    {
        if (animationTimer.millisElapsed() < animationSpeed)
            return;
        animationTimer.mark();
        if (idle)
        {
            if (facingRight)
                setImage(idleRight[imageIndex]);
            else
                setImage(idleLeft[imageIndex]);
        } else
        {
            if (facingRight)
                setImage(runRight[imageIndex]);
            else
                setImage(runLeft[imageIndex]);
        }
        if (isSwinging)
        {
            if (facingRight)
                setImage(swingRight[swingIndex]);
            else
                setImage(swingLeft[swingIndex]);
            if (swingIndex == 3)
            {
                isSwinging = false;
                if (isTouching(Enemy1.class))
                {
                    getWorld().removeObject(getOneIntersectingObject(Enemy1.class));
                    ((MyWorld) getWorld()).increaseScore(1);
                }
                if (isTouching(Enemy2.class))
                {
                    Enemy2 enemy = (Enemy2) getOneIntersectingObject(Enemy2.class);
                    enemy.minusHp();
                }
            } else
            {
                swingIndex = (swingIndex + 1) % 4;
            }
        }
        imageIndex = (imageIndex + 1) % 4;
    }

    /**
     * Performs a horizontal dash to increase the player's mobility.
     */
    private void dash()
    {
        if (!canDash)
            return;
        canDash = false;
        DashEffect dashEffect = new DashEffect();
        if (facingRight)
        {
            dashEffect.getImage().mirrorHorizontally();
            getWorld().addObject(dashEffect, getX() - 100, getY());
        } else
        {
            getWorld().addObject(dashEffect, getX() + 100, getY());
        }
        slide = 30;
    }
}
