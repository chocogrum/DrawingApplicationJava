// Project 3: MyShape.java
// Declaration of abstract class MyShape to represent a shape
// Re-used code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;

// begin abstract class MyShape
public abstract class MyShape
{
   private int x1; // x-coordinate of first endpoint
   private int y1; // y-coordinate of first endpoint
   private int x2; // x-coordinate of second endpoint
   private int y2; // y-coordinate of second endpoint
   private Color myColour; // color of the shape

   // default constructor with no input values
   public MyShape()
   {
      // call five argument constructor using default values
      this( 0, 0, 0, 0, Color.BLACK );
   } // end MyShape default constructor
   
   // constructor with input values
   public MyShape( int x1, int y1, int x2, int y2, Color colour )
   {
      setX1( x1 ); // set x-coordinate of first endpoint
      setY1( y1 ); // set y-coordinate of first endpoint
      setX2( x2 ); // set x-coordinate of second endpoint
      setY2( y2 ); // set y-coordinate of second endpoint
      setColour( colour ); // set the colour of the shape
   } // end MyShape constructor with input values
   
   // method to set x-coordinate of first endpoint
   public void setX1( int x )
   {
      // if integer passed in is less than zero, set x1 to zero
      x1 = ( x >= 0 ) ? x : 0;
   } // end method setX1
   
   // method to set y-coordinate of first endpoint
   public void setY1( int y )
   {
      // if integer passed in is less than zero, set y1 to zero
      y1 = ( y >= 0 ) ? y : 0;
   } // end method setY1
   
   // method to set x-coordinate of second endpoint
   public void setX2( int x )
   {
      // if integer passed in is less than zero, set x2 to zero
      x2 = ( x >= 0 ) ? x : 0;
   } // end method setX2
   
   // method to set y-coordinate of second endpoint
   public void setY2( int y )
   {
      // if integer passed in is less than zero, set y2 to zero
      y2 = ( y >= 0 ) ? y : 0;
   } // end method setY2
   
   // method to set colour of shape
   public void setColour( Color colour )
   {
      myColour = colour; // set the colour of the shape
   } // end method setColour
   
   // method to get first x coordinate
   public int getX1()
   {
      return x1; // return the first x coordinate
   } // end method getX1
   
   // method to get first Y coordinate
   public int getY1()
   {
      return y1; // return the first y coordinate
   } // end method getY1
   
   // method to get second x coordinate
   public int getX2()
   {
      return x2; // return the second x coordinate
   } // end method getX2
   
   // method to get second Y coordinate
   public int getY2()
   {
      return y2; // return the second y coordinate
   } // end method getY2
   
   // method to get colour of the shape
   public Color getColour()
   {
      return myColour; // return the colour of the shape
   } // end method getColour
   
   // abstract method to draw the shape
   public abstract void draw( Graphics g );

} // end class MyLine

