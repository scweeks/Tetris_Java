package models;

import wheelsunh.users.Rectangle;

import java.awt.*;

public class SmashBoy extends Tetromino
{
	public SmashBoy ()
	{
		super.r1.setLocation( 0, 0 );
		super.r2.setLocation ( 0, Tetromino.SIZE );
		super.r3.setLocation ( Tetromino.SIZE, 0 );
		super.r4.setLocation ( Tetromino.SIZE, Tetromino.SIZE );
		super.r1.setFillColor ( new Color ( 255, 255, 0 ) );
		super.r2.setFillColor ( new Color ( 255, 255, 0 ) );
		super.r3.setFillColor ( new Color ( 255, 255, 0 ) );
		super.r4.setFillColor ( new Color ( 255, 255, 0 ) );
		
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
		
		super.r1.setLocation( 0, 0 );
		super.r2.setLocation ( 0, Tetromino.SIZE );
		super.r3.setLocation ( Tetromino.SIZE, 0 );
		super.r4.setLocation ( Tetromino.SIZE, Tetromino.SIZE );
		
		
		super.setLocation( curLoc );
	}
	
	@Override
	public Rectangle getBottom ()
	{
		System.out.println ("Bottom:");
		System.out.println (super.r2.getXLocation ());
		return super.r2;
	}
	
	@Override
	public Rectangle getRight ()
	{
		System.out.println ("Right:");
		System.out.println (super.r4.getXLocation ());
		return super.r4;
	}
	
	@Override
	public Rectangle getLeft ()
	{
		System.out.println ("Left:");
		System.out.println (super.r2.getXLocation ());
		return super.r2;
	}
	
	/**
	 * Gets the height of the tetronimo based on the orientation
	 *
	 * @return The height of the tetronimo
	 */
	@Override
	public int getHeight()
	{
		return Tetromino.SIZE * 2;
	}
	
	@Override
	public int getWidth()
	{
		return Tetromino.SIZE * 2;
	}
}
