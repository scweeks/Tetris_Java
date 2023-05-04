package views;

import controllers.TetrisController;
import models.Tetromino;
import wheelsunh.users.*;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * TetrisBoard.java:
 * Class to model the tetris board
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 */
public class TetrisBoard implements KeyListener
{
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represent the height of the board
     */
    public static final int HEIGHT = 24;

    private final TetrisController CONTROLLER;
    private       Tetromino        tetromino;
    private       Rectangle[][]    playingField;

    /**
     * Constructor to initialize the board
     *
     * @param frame The wheelsunh frame (so we can add this class as a key listener for the frame)
     */
    public TetrisBoard( Frame frame )
    {
        frame.addKeyListener( this );
        this.CONTROLLER = new TetrisController( this );

        this.buildBoard();

        this.run();
        
        this.showScore ();
    }

    /**
     * Builds the playing field for tetris
     */
    private void buildBoard()
    {
        this.playingField = new Rectangle[ WIDTH ][ HEIGHT ];

        for ( int i = 0; i < TetrisBoard.WIDTH; i++ )
        {
            for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
            {
                this.playingField[ i ][ j ] = new Rectangle();
                this.playingField[ i ][ j ].setLocation( i * 20 + 40, j * 20 );
                this.playingField[ i ][ j ].setSize ( Tetromino.SIZE, Tetromino.SIZE );
                this.playingField[ i ][ j ].setColor( new Color ( 127, 127, 127 ) );
                this.playingField[ i ][ j ].setFrameColor( Color.BLACK );
            }
        }
    }

    /**
     * Starts gameplay and is responsible for keeping the game going (INCOMPLETE)
     */
    public void run()
    {
        
        this.tetromino = this.CONTROLLER.getNextTetromino ();

        while( this.CONTROLLER.tetrominoLanded ( this.tetromino ) )
        {
            int tetroCol = this.tetromino.getXLocation () / ( TetrisBoard.WIDTH * 2 );
            int tetroRightCol = this.tetromino.getRight ().getXLocation () / ( TetrisBoard.WIDTH * 2 );
            int tetroLeftCol = this.tetromino.getLeft ().getXLocation () / ( TetrisBoard.WIDTH * 2 );
            int tetroRow = ( this.tetromino.getBottom ().getYLocation () + Tetromino.SIZE * 2 ) / TetrisBoard.HEIGHT;
    
            //System.out.println (this.playingField [tetroCol - 1][tetroRow + 1].getFillColor ());
            //System.out.println (this.getColor ());
            //System.out.println (tetroRow);
            
                //System.out.println (this.playingField [tetroCol][tetroRow + 1].getColor ());
                this.tetromino.setLocation ( this.tetromino.getXLocation (), this.tetromino.getYLocation () + Tetromino.SIZE );
                Utilities.sleep ( 500 );
        }

        /*
         * This next line is a placeholder for now, you need to change this code so when a piece lands
         * the right squares on the board are painted the color of the tetromino and the tetromino itself gets hidden
         */
        for ( int i = 0; i < this.playingField.length; i++ )
        {
            int rect = 0;
            for ( int j = 0; j < this.playingField[i].length; j++ )
            {
                if ( this.tetromino.contains ( this.playingField[i][j].getLocation () ) )
                {
                    this.playingField[ i ] [ j ].setFillColor ( this.tetromino.getColor() );
                    this.tetromino.hide ();
                    ++rect;
                }
            }
        }
        
        this.CONTROLLER.keepPlaying ();
    }

    /**
     * Getter method for the array representing the playing field, not used yet but will be needed by
     * the controller later
     *
     * @return The playing field
     */
    public Rectangle[][] getPlayingField()
    {
        return this.playingField;
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
        //not in use
    }

    /**
     * Handles the key events by the user (INCOMPLETE)
     *
     * @param e The key event
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();

        if( this.tetromino == null )
        {
            return;
        }

        switch( key )
        {
            case 38:
                this.tetromino.rotate ();
                break;
            case 37:
                int x = (this.tetromino.getLeft ().getXLocation () % ( Tetromino.SIZE ) );
                int y = (this.tetromino.getLeft ().getYLocation () % ( Tetromino.SIZE ) );
    
                //System.out.println (this.tetromino.getLeft ().getXLocation ());
                if( ( this.tetromino.getLeft ().getXLocation () ) >= 60 )
                {
                    if ( tetromino.getLeft ().getXLocation () <= 0 )
                    {
                        tetromino.outOfBounds ( tetromino );
                    }
                    if ( !(this.CONTROLLER.collides ( tetromino ) ) )
                    {
                        this.tetromino.shiftLeft ();
                        //System.out.println ("Left " + this.tetromino.getLeft ().getXLocation ());
                    }
                }
                break;
            case 39:
                //System.out.println (this.tetromino.getRight ().getXLocation ());
                if( ( ( this.tetromino.getRight ().getXLocation () + Tetromino.SIZE ) ) <  240 )
                {
                    if ( tetromino.getRight ().getXLocation () >= 240 )
                    {
                        tetromino.outOfBounds ( tetromino );
                    }
                    if ( !(this.CONTROLLER.collides ( tetromino ) ) )
                    {
                        this.tetromino.shiftRight ();
                        //System.out.println ("Right " + this.tetromino.getRight ().getXLocation ());
                    }
                }
                break;
        }

    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        //not in use
    }
    
    public int getTetrominoY ()
    {
        return this.tetromino.getYLocation ();
    }
    
    public Color getColor ()
    {
        return new Color ( 127, 127, 127 );
    }
    
    private void showScore ()
    {
        IntInputBox scoreBoard = new IntInputBox ();
        scoreBoard.setText ( this.CONTROLLER.getScore () );
        scoreBoard.setLocation ( 300, 100 );
        scoreBoard.setSize ( 100, 100 );
        scoreBoard.setColor ( Color.BLUE );
        scoreBoard.setFillColor ( Color.YELLOW );
        scoreBoard.show ();
    }
    
    private void showNextPiece ()
    {
        InputBox nextPiece = new FilterInputBox ();
        nextPiece.show ();
        
    }
    
}