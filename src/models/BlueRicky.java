package models;

import wheelsunh.users.Rectangle;

import java.awt.*;

public class BlueRicky extends Tetromino
{
	public BlueRicky ()
	{
		super.r1.setLocation( -Tetromino.SIZE, -Tetromino.SIZE );
		super.r2.setLocation ( -Tetromino.SIZE, 0 );
		super.r3.setLocation ( 0, 0 );
		super.r4.setLocation ( Tetromino.SIZE, 0 );
		super.r1.setFillColor ( new Color ( 0, 0, 255 ) );
		super.r2.setFillColor ( new Color ( 0, 0, 255 ) );
		super.r3.setFillColor ( new Color ( 0, 0, 255 ) );
		super.r4.setFillColor ( new Color ( 0, 0, 255 ) );
		
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
		
		Point curLoc = super.getLocation ();
		super.setLocation( 0, 0 );
		
		if      ( super.curRotation % 4 == 2 )
		{
			super.r1.setLocation( -Tetromino.SIZE, Tetromino.SIZE );
			super.r2.setLocation ( 0, Tetromino.SIZE );
			super.r3.setLocation ( 0, 0 );
			super.r4.setLocation ( 0, -Tetromino.SIZE );
		}
		else if ( super.curRotation % 4 == 3 )
		{
			super.r1.setLocation( Tetromino.SIZE, Tetromino.SIZE );
			super.r2.setLocation ( Tetromino.SIZE, 0 );
			super.r3.setLocation ( 0, 0 );
			super.r4.setLocation ( -Tetromino.SIZE, 0 );
		}
		else if ( super.curRotation % 4 == 0 )
		{
			super.r1.setLocation( Tetromino.SIZE, -Tetromino.SIZE );
			super.r2.setLocation ( 0, -Tetromino.SIZE );
			super.r3.setLocation ( 0, 0 );
			super.r4.setLocation ( 0, Tetromino.SIZE );
		}
		else
		{
			super.r1.setLocation( -Tetromino.SIZE, -Tetromino.SIZE );
			super.r2.setLocation ( -Tetromino.SIZE, 0 );
			super.r3.setLocation ( 0, 0 );
			super.r4.setLocation ( Tetromino.SIZE, 0 );
		}
		
		super.setLocation( curLoc );
	}
	
	/**
	 * This method returns the lowermost portion of the Tetromino.
	 * This is primarily used for collision detection
	 *
	 * @return Returns the portion of the Tetromino that is closest to the bottom of the board
	 */
	@Override
	public Rectangle getBottom ()
	{
		System.out.println ("Bottom:");
		if      ( super.curRotation % 4 == 2 )
		{
			System.out.println (super.r2.getXLocation ());
			return super.r2;
		}
		else if ( super.curRotation % 4 == 3 )
		{
			System.out.println (super.r1.getXLocation ());
			return super.r1;
		}
		else if ( super.curRotation % 4 == 0 )
		{
			System.out.println (super.r4.getXLocation ());
			return super.r4;
		}
		else
		{
			System.out.println (super.r3.getXLocation ());
			return super.r3;
		}
	}
	
	/**
	 * This method returns the right most portion of the Tetromino.
	 * This is primarily used for collision detection
	 *
	 * @return Returns the portion of the Tetromino that is closest to the right side of the board
	 */
	@Override
	public Rectangle getRight ()
	{
		System.out.println ("Right:");
		if      ( super.curRotation % 4 == 2 )
		{
			System.out.println (super.r4.getXLocation ());
			return super.r4;
		}
		else if ( super.curRotation % 4 == 3 )
		{
			System.out.println (super.r2.getXLocation ());
			return super.r2;
		}
		else if ( super.curRotation % 4 == 0 )
		{
			System.out.println (super.r2.getXLocation ());
			return super.r2;
		}
		else
		{
			System.out.println (super.r1.getXLocation ());
			return super.r1;
		}
	}
	
	/**
	 * This method returns the left most portion of the Tetromino.
	 * This is primarily used for collision detection
	 *
	 * @return Returns the portion of the Tetromino that is closest to the left side of the board
	 */
	@Override
	public Rectangle getLeft ()
	{
		System.out.println ("Left:");
		if      ( super.curRotation % 4 == 2 )
		{
			System.out.println (super.r1.getXLocation ());
			return super.r1;
		}
		else if ( super.curRotation % 4 == 3 )
		{
			System.out.println (super.r4.getXLocation ());
			return super.r4;
		}
		else if ( super.curRotation % 4 == 0 )
		{
			System.out.println (super.r3.getXLocation ());
			return super.r3;
		}
		else
		{
			System.out.println (super.r2.getXLocation ());
			return super.r2;
		}
	}
}
