package models;

import com.sun.org.apache.bcel.internal.util.ClassQueue;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;
import java.awt.Color;

/**
 * Tetromino.java:
 * An abstract class to model the base capabilities of a tetromino
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 */
public abstract class Tetromino extends ShapeGroup
{
    /**
     * Constant to represent the size of the tetromino
     */
    public static final int SIZE= 20;
    
    protected Rectangle r1;
    protected Rectangle r2;
    protected Rectangle r3;
    protected Rectangle r4;
    
    protected int curRotation = 1;
    private String name;
    
    /**
     * Generates the four rectangles for the tetromino and puts them on the screen, they are at the default coordinates
     * to start
     */
    public Tetromino ()
    {
        super();
        this.r1 = new Rectangle();
        this.r1.setSize ( Tetromino.SIZE, Tetromino.SIZE );
        this.r1.setFrameColor( Color.BLACK );
        
        this.r2 = new Rectangle();
        this.r2.setSize ( Tetromino.SIZE, Tetromino.SIZE );
        this.r2.setFrameColor( Color.BLACK );
        
        this.r3 = new Rectangle();
        this.r3.setSize ( Tetromino.SIZE, Tetromino.SIZE );
        this.r3.setFrameColor( Color.BLACK );
        
        this.r4 = new Rectangle();
        this.r4.setSize ( Tetromino.SIZE, Tetromino.SIZE );
        this.r4.setFrameColor( Color.BLACK );
    }
    
    /**
     * Increments the rotation of the tetromino, other classes need to override this to provide the full functionality
     */
    public void rotate()
    {
        this.curRotation++;
        System.out.println (this.curRotation);
    }
    
    /**
     * Shifts the tetromino left one row
     */
    public void shiftLeft()
    {
        super.setLocation ( super.getXLocation() - Tetromino.SIZE, super.getYLocation () );
    }
    
    /**
     * Shifts the tetromino right one row
     */
    public void shiftRight()
    {
        super.setLocation ( super.getXLocation() + Tetromino.SIZE, super.getYLocation () );
    }
    
    /**
     * Returns the color of the tetromino
     * @return RGB color of the tetromino
     */
    public Color getColor ()
    {
        return this.r1.getFillColor ();
    }
    
    /**
     * This method allows for the Tetromino to be hidden.
     * This should be called after the appropriate board locations have been colored the same as the Tetromino
     */
    public void hide ()
    {
        this.r1.hide ();
        this.r2.hide ();
        this.r3.hide ();
        this.r4.hide ();
    }
    
    
    /**
     * This method returns the lowermost portion of the Tetromino.
     * This is primarily used for collision detection
     * @return Returns the portion of the Tetromino that is closest to the bottom of the board
     */
    public abstract Rectangle getBottom ();
    
    /**
     * This method returns the right most portion of the Tetromino.
     * This is primarily used for collision detection
     * @return Returns the portion of the Tetromino that is closest to the right side of the board
     */
    public abstract Rectangle getRight ();
    
    /**
     * This method returns the left most portion of the Tetromino.
     * This is primarily used for collision detection
     * @return Returns the portion of the Tetromino that is closest to the left side of the board
     */
    public abstract Rectangle getLeft ();
    
    /**
     * Gets the width of the tetronimo based on the orientation
     *
     * @return The width of the tetronimo
     */
    @Override
    public int getWidth()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetromino.SIZE * 3;
        }
        else
        {
            return Tetromino.SIZE * 2;
        }
    }
    
    /**
     * Gets the height of the tetronimo based on the orientation
     *
     * @return The height of the tetronimo
     */
    @Override
    public int getHeight()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetromino.SIZE * 2;
        }
        else
        {
            return Tetromino.SIZE * 3;
        }
    }
    
    public int getCurRotation ()
    {
        return this.curRotation;
    }
    
    public Rectangle getR1 ()
    {
        return this.r1;
    }
    
    public Rectangle getR2 ()
    {
        return this.r2;
    }
    
    public Rectangle getR3 ()
    {
        return this.r3;
    }
    
    public Rectangle getR4 ()
    {
        return this.r4;
    }
    
    public String getName ()
    {
        return this.name;
    }
    
    public void outOfBounds ( Tetromino tetromino )
    {
        if ( tetromino.getRight ().getXLocation () > ( TetrisBoard.WIDTH * Tetromino.SIZE + Tetromino.SIZE ) )
        {
            tetromino.setLocation ( tetromino.getXLocation () - tetromino.getWidth (), tetromino.getYLocation () );
        }
        else if ( tetromino.getLeft ().getXLocation () <= 0 )
        {
            tetromino.setLocation ( tetromino.getXLocation () + tetromino.getWidth (), tetromino.getYLocation () );
        }
        else
        {
            tetromino.setLocation ( tetromino.getXLocation (), tetromino.getYLocation () );
        }
    }
}