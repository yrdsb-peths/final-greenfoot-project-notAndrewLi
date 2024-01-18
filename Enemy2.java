import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy2 extends Actor
{
    /**
     * Act - do whatever the Enemy2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer animationTimer = new SimpleTimer();
    private int animationSpeed = 200;
    GreenfootImage[] dragon = new GreenfootImage[3];
    GreenfootImage[] dragonLeft = new GreenfootImage[3];
    boolean facingRight = true;
    static  int hp = 3;
    public Enemy2(int side){
        for(int i = 0; i < 3; i++){
            dragon[i] = new GreenfootImage("images/dragon/dragon" + (i + 1) + ".png");
            dragonLeft[i] = new GreenfootImage("images/dragon/dragon" + (i + 1) + ".png");
            dragonLeft[i].mirrorHorizontally();
        }
        if(side == 1){
            facingRight = false;
            setImage(dragonLeft[0]);
        }
    }
    public void act()
    {
        // Add your action code here.
        MyWorld world = (MyWorld) getWorld();
        fly();
        if(facingRight){
            if(getX() > 990){
                despawnDragon();
                world.loseLife();
            }
        }else{
            if(getX() < 10){
                despawnDragon();
                world.loseLife();
            }
        }
    }
    int imageIdx = 0;
    public int dragonSpeed = 20;
    public void fly(){
        if(animationTimer.millisElapsed() < animationSpeed){
            return;
        }
        animationTimer.mark();
        if(facingRight){
            setImage(dragon[imageIdx]);
            move(dragonSpeed);
        }
        else{
            setImage(dragonLeft[imageIdx]);
            move(-dragonSpeed);
        }
        imageIdx = (imageIdx+1) % 3;
    }

    public void minusHp(){
        MyWorld world = (MyWorld) getWorld();
        hp--;
        System.out.println(hp);
        if(hp < 1){
            world.increaseScore(3);
            despawnDragon();
        }
    }
    
    public void despawnDragon(){
        MyWorld world = (MyWorld) getWorld();
        world.removeObject(this);
    }
}
