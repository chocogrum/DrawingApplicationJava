// Project 3: MyLine.java
// Declaration of class MyLine to represent a line
// Re-used code from Project1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;

// begin class MyLine which inherits from MyShape
public class MyLine extends MyShape
{

   // constructor with input values
   public MyLine( int x1, int y1, int x2, int y2, Color colour )
   {
      // call superclass constructor with input values
      super( x1, y1, x2, y2, colour );

   } // end MyLine constructor with input values
   
   // default constructor with no input values
   public MyLine()
   {
      super(); // call superclass default constructor

   } // end MyLine default constructor
   
   // method draw overrides superclass abstract method draw and makes it concrete
   public void draw( Graphics g )
   {
      g.setColor( getColour() ); // set the colour for drawing the line
      g.drawLine( getX1(), getY1(), getX2(), getY2() ); // draw the line
   } // end method draw

} // end class MyLine

