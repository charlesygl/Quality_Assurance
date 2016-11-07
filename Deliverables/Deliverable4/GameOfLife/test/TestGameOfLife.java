/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gaole
 */
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;

public class TestGameOfLife {
    
    // Tests for toString() method in Cell class
    @Test
    public void testCellToStringWhenAlive() {
        Cell aliveCell = new Cell(true);
        assertEquals(aliveCell.toString(), "X");
    }
    
    @Test
    public void testCellToStringWhenDead() {
        Cell deadCell = new Cell(false);
        assertEquals(deadCell.toString(), ".");
    }
    
    @Test
    public void testCellToStringMatrix() {
      Cell[][] tCell = new Cell[15][15];
      for (int i=0; i < 15; i++){
        for (int j=0; j<15; j++){
          tCell[i][j]= new Cell();
          tCell[i][j].setText(" ");
        }
      }
      tCell[1][1].setText("X");
      assertEquals(tCell[2][2].toString(),".");
    }
    
    
    
    // Tests for convertToInt() method in MainPanel class
    @Test
    public void testConvertToInt() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method;
        Class[] argTypes = new Class[] { int.class };
        method = MainPanel.class.getDeclaredMethod("convertToInt", argTypes);
        method.setAccessible(true);
        MainPanel newPanel = new MainPanel(15);
        Object invokeConvertToInt = method.invoke(newPanel, 0);
        int returnValue = ((Integer) invokeConvertToInt).intValue();
        assertEquals(0, returnValue);
    }
    
    @Test
    public void testConvertToIntWithPositiveNumber() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method;
        Class[] argTypes = new Class[] { int.class };
        method = MainPanel.class.getDeclaredMethod("convertToInt", argTypes);
        method.setAccessible(true);
        MainPanel newPanel = new MainPanel(15);
        Object invokeConvertToInt = method.invoke(newPanel, 3);
        int returnValue = ((Integer) invokeConvertToInt).intValue();
        assertEquals(3, returnValue);
    }
    
    @Test
    public void testConvertToIntWithNoNeighbors() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        Method method;
        Class[] argTypes = new Class[] { int.class, int.class };
        method = MainPanel.class.getDeclaredMethod("getNumNeighbors", argTypes);
        method.setAccessible(true);
        MainPanel newPanel = new MainPanel(15);
        Object invokeGetNumNeighbors = method.invoke(newPanel, 1, 1);
        int returnValue1 = ((Integer) invokeGetNumNeighbors).intValue();
        assertEquals(returnValue1, 0);
    }
    
    // *********************************************************************************************************
    // * runContinuous() method in MainPanel class is tested manually. Please see Readme.md in Git for reasons *
    // *********************************************************************************************************
}
