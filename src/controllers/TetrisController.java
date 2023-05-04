package controllers;

import models.*;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.util.Random;

/**
 * TetrisController.java:
 * Class to hold all the game logic for tetris
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;
    private Rectangle[][]     field;
    private int               score;
    
    /**
     * Constructor to take in a tetris board so the controller and the board can communicate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
    }
    
    /**
     * Randomly chooses the next tetromino and returns it (INCOMPLETE)
     *
     * @return The next tetromino to be played
     */
    public Tetromino getNextTetromino ()
    {
        Tetromino tetromino = null;
        
        int next = this.getNext();
        
        switch ( next )
        {
            case 0:
                tetromino = new Hero ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 1:
                tetromino = new SmashBoy ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 2:
                tetromino = new Teewee ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 3:
                tetromino = new OrangeRicky ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 4:
                tetromino = new BlueRicky ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 5:
                tetromino = new ClevelandZ ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
            case 6:
                tetromino = new RhodeIslandZ ();
                tetromino.setLocation ( 40 + ( 5 * Tetromino.SIZE), 0 );
                break;
        }
        return tetromino;
    }
    
    /**
     * Determines what the next piece in line is based on a randomly generated number that is seeded with the computer
     * time, in milliseconds, as the seed for the RNG. It then uses modulo 7 to determine which piece is next.
     *
     * @see java.util.Random
     * @return Returns the integer value of the next piece modulo 7
     */
    private int getNext ()
    {
        Random rand = new Random(System.currentTimeMillis());
        int piece = rand.nextInt ( Integer.MAX_VALUE );
        
        return ( piece % 7 );
    }
    
    /**
     * Method to determine if the tetromino has landed (INCOMPLETE)
     *
     * @param tetromino The tetromino to evaluate
     * @return True if the tetromino has landed (on the bottom of the board or another tetromino), false if it has not
     */
    public boolean tetrominoLanded ( Tetromino tetromino )
    {
        /*
        I would make sure that you have a way of figuring out on which row the bottom of the tetromino is on.
        If you know where it is then you can see what the next row down has.  If the next row is empty then you are
        good to go down one more level.  This won’t work with the T shaped piece though, and you will need to check
        each block.  Try and find a way to handle this polymorphically to make it easier to implement.
         */
        //System.out.println (tetromino.getCenterX ());
        int tetroCol = ( ( tetromino.getBottom ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroRightCol = ( ( tetromino.getRight ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroLeftCol = ( ( tetromino.getLeft ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroRow = ( tetromino.getBottom ().getYLocation () ) / Tetromino.SIZE;
        this.field = this.TETRIS_BOARD.getPlayingField ().clone ();
        
        tetromino.outOfBounds ( tetromino );
        
        if ( tetroLeftCol - 1 >= 0 && tetroRightCol - 1 <= TetrisBoard.WIDTH && tetroRow + 1 < ( TetrisBoard.HEIGHT ) )
        {
            if    ( this.getNextColor ( tetromino ) )
            {
                //System.out.println (this.field[ tetroCol - 1 ][ tetroRow ].getFillColor ());
                int nextY = tetromino.getBottom ().getYLocation () + Tetromino.SIZE;
                return nextY <= 460;
            }
            else
            {
                return false;
            }
        }
        else if ( tetroRow < ( TetrisBoard.HEIGHT ) )
        {
            if ( this.getNextColor ( tetromino ) )
            {
                int nextY = tetromino.getBottom ().getYLocation () + Tetromino.SIZE;
                return nextY <= 460;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        
    }
    
    
    public void keepPlaying()
    {
        boolean playOn = true;
        Rectangle[][] field = this.TETRIS_BOARD.getPlayingField ().clone ();
        
        for ( int i = 0; i < 10; i++ )
        {
            if ( field[0][i].getFillColor () != this.TETRIS_BOARD.getColor () )
            {
                System.out.println (this.TETRIS_BOARD.getColor ());
                playOn = true;
            }
            else
            {
                playOn = false;
            }
        }
        if ( playOn )
        {
            //this.setScore ( this.clearLines () );
            this.TETRIS_BOARD.run ();
        }
        else
        {
            this.getResults ();
        }
    }
    
    private void getResults ()
    {
        System.out.println ( "Your Score is : " + this.score );
    }
    
    public int clearLines ()
    {
        int numLines = 0;
        int counter = 0;
        
        Rectangle[][] field = this.TETRIS_BOARD.getPlayingField ().clone ();
        
        for ( int i = TetrisBoard.HEIGHT-1; i >= 0; i-- )
        {
            for ( int j = TetrisBoard.WIDTH - 1; j >=0; j-- )
            {
                if ( field[i][j].getFillColor () != this.TETRIS_BOARD.getColor() )
                {
                    counter++;
                }
            }
            
            if ( counter == 10 )
            {
                for ( int j = 0; j < 10; j++ )
                {
                    field[i][j].setColor ( this.TETRIS_BOARD.getColor () );
                }
                numLines++;
            }
        }
        
        return numLines;
    }
    
    private void setScore ( int lines )
    {
        if ( lines == 1 )
        {
            this.score += 100;
        }
        else if ( lines == 2 )
        {
            this.score += 200;
        }
        else if ( lines == 3 )
        {
            this.score += 400;
        }
        else
        {
            this.score += 800;
        }
    }
    
    private boolean getNextColor ( Tetromino tetromino )
    {
        int tetroCol      = ( ( tetromino.getBottom ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroRightCol = ( ( tetromino.getRight ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroLeftCol  = ( ( tetromino.getLeft ().getXLocation () - Tetromino.SIZE ) / ( Tetromino.SIZE ) );
        int tetroRow      = ( tetromino.getBottom ().getYLocation () ) / Tetromino.SIZE;
    
        if ( tetroRow + 1 < TetrisBoard.HEIGHT &&
             tetroRightCol + 1 < TetrisBoard.WIDTH &&
             tetroLeftCol - 1 > 0 )
        {
            if ( this.field[ tetroCol - 1 ][ tetroRow + 1 ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) &&
                 this.field[ tetroCol - 1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
            {
                if ( this.field[ tetroRightCol - 1 ][ tetroRow + 1 ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) &&
                     this.field[ tetroRightCol - 1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    if ( this.field[ tetroLeftCol - 1 ][ tetroRow + 1 ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) &&
                         this.field[ tetroLeftCol - 1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else if ( tetroRow < ( TetrisBoard.HEIGHT ) &&
                  tetroRightCol < TetrisBoard.WIDTH &&
                  tetroLeftCol >= 0 )
        {
            if ( this.field[ tetroCol ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
            {
                if ( this.field[ tetroRightCol ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    if ( this.field[ tetroLeftCol ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            if ( this.field[ tetroCol - 1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
            {
                if ( this.field[ tetroRightCol - 1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    if ( this.field[ tetroLeftCol -1 ][ tetroRow ].getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
    
    // Collision test for the dropping piece
    public boolean collides (Tetromino tetromino)
    {
        Rectangle b1 = tetromino.getR1 ();
        Rectangle b2 = tetromino.getR2 ();
        Rectangle b3 = tetromino.getR3 ();
        Rectangle b4 = tetromino.getR4 ();
        
        int b1X = ( ( b1.getXLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b2X = ( ( b2.getXLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b3X = ( ( b3.getXLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b4X = ( ( b4.getXLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
    
        int b1Y = ( ( b1.getYLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b2Y = ( ( b2.getYLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b3Y = ( ( b3.getYLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        int b4Y = ( ( b4.getYLocation () - Tetromino.SIZE ) / Tetromino.SIZE );
        
        for ( Rectangle[] r : this.field )
        {
            if      ( b1Y + 1 < TetrisBoard.HEIGHT &&
                      b2Y + 1 < TetrisBoard.HEIGHT &&
                      b3Y + 1 < TetrisBoard.HEIGHT &&
                      b4Y + 1 < TetrisBoard.HEIGHT &&
                      tetromino.getLeft ().getXLocation () > 0 &&
                      tetromino.getRight ().getXLocation () < TetrisBoard.WIDTH
                    )
            {
                if ( this.field
                             [ b1X - 1 ][ b1Y + 1 ]
                             .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b2X - 1 ][ b2Y + 1 ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b3X - 1 ][ b3Y + 1 ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b4X - 1 ][ b4Y + 1 ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
            }
            else if      ( b1Y < TetrisBoard.HEIGHT &&
                           b2Y < TetrisBoard.HEIGHT &&
                           b3Y < TetrisBoard.HEIGHT &&
                           b4Y < TetrisBoard.HEIGHT &&
                           tetromino.getLeft ().getXLocation () > 0 &&
                           tetromino.getRight ().getXLocation () < TetrisBoard.WIDTH
                           )
            {
                if ( this.field
                             [ b1X - 1 ][ b1Y ]
                             .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b2X - 1 ][ b2Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b3X - 1 ][ b3Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ b4X - 1 ][ b4Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
            }
            else
            {
                if      ( this.field
                             [ 0 ][ b1Y ]
                             .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ 0 ][ b2Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ 0 ][ b3Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
                else if ( this.field
                                  [ 0 ][ b4Y ]
                                  .getFillColor ().equals ( this.TETRIS_BOARD.getColor () ) )
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String getScore ()
    {
        return String.valueOf ( this.score );
    }
}
