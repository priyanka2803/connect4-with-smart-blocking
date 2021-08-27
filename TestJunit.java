package com.connect4;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class TestJunit {

    Connect4Game game;
    String discs[][], discsReverse[][];
    Validator v;

    @BeforeClass
    public void setUpBeforeClass()
    {
        game = new Connect4Game();
        discs = new String[6][7];
        discsReverse = new String[6][7];
        for(int i = 0;i < 6; i++)
		{
            for(int j=0;j<7;j++)
            {
                discsReverse[i][j] = "r"+i+"c"+j;
            }
        }
        for(int i = 0;i < 6; i++)
		{
			discsReverse[i] = discs[5-i];
        }
        v = game.validateColumn(8);
    }
    @Test
   public void testWin() {
       
      assertEquals(false,game.checkForWin("B"));

   }
   @Test
   public void testValidColumn() {
      
      assertEquals(false,v.valid);

   }
   @Test
   public void testReverse() {

      assertEquals(discsReverse,game.reverseDiscs(discs));
    
   }
   @Test
   public void testGetColumnIndex() {
       
      assertNotNull(game.getColumnIndex());

   }
}