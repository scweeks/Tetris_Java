package models;

import wheelsunh.users.Rectangle;

import java.awt.*;

/**
 * Hero.java:
 * Creates a straight line tetronimo
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Point
 */
public class Hero extends Tetromino
{
    
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public Hero ()
    {
        super.r1.setLocation( 0, -Tetromino.SIZE * 2 );
        super.r2.setLocation ( 0, -Tetromino.SIZE );
        super.r3.setLocation ( 0, 0 );
        super.r4.setLocation ( 0, Tetromino.SIZE);
        super.r1.setFillColor ( new Color ( 0, 255, 255 ) );
        super.r2.setFillColor ( new Color ( 0, 255, 255 ) );
        super.r3.setFillColor ( new Color ( 0, 255, 255 ) );
        super.r4.setFillColor ( new Color ( 0, 255, 255 ) );
        
        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the tetronimo
     */
    @Override
    public void rotate()
    {
        super.rotate();

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 0 )
        {
            super.r1.setLocation( -Tetromino.SIZE * 2, 0 );
            super.r2.setLocation ( -Tetromino.SIZE, 0 );
            super.r3.setLocation ( 0, 0 );
            super.r4.setLocation ( Tetromino.SIZE, 0 );
        }
        else
        {
            super.r1.setLocation( 0, -Tetromino.SIZE * 2 );
            super.r2.setLocation ( 0, -Tetromino.SIZE );
            super.r3.setLocation ( 0, 0 );
            super.r4.setLocation ( 0, Tetromino.SIZE);
        }

        super.setLocation( curLoc );
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
            return Tetromino.SIZE;
        }
        else
        {
            return Tetromino.SIZE * 4;
        }
    }

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
            return Tetromino.SIZE * 4;
        }
        else
        {
            return Tetromino.SIZE;
        }
    }
    
    @Override
    public Rectangle getBottom ()
    {
        //System.out.println ("Bottom:");
        if( this.curRotation % 2 == 0 )
        {
            //System.out.println (super.r3.getXLocation ());
            return super.r3;
        }
        else
        {
            //System.out.println (super.r4.getXLocation ());
            return super.r4;
        }
    }
    
    @Override
    public Rectangle getRight ()
    {
        //System.out.println ("Right:");
        if( this.curRotation % 2 == 0 )
        {
            //System.out.println (super.r4.getXLocation ());
            return super.r4;
        }
        else
        {
            //System.out.println (super.r3.getXLocation ());
            return super.r4;
        }
    }
    
    @Override
    public Rectangle getLeft ()
    {
        //System.out.println ("Left:");
        if( this.curRotation % 2 == 0 )
        {
            //System.out.println (super.r1.getXLocation ());
            return super.r1;
        }
        else
        {
            //System.out.println (super.r3.getXLocation ());
            return super.r4;
        }
    }
}
