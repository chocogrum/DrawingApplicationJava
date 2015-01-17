// Project 3: MyRect.java
// Declaration of class MyRect to represent a rectangle
// Re-used code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;

// begin class MyRect which inherits from MyBoundedShape
public class MyRect extends MyBoundedShape
{

   // constructor with input values
   public MyRect( int x1, int y1, int x2, int y2, Color colour, Boolean filled )
   {
      // call superclass constructor with input values
      super( x1, y1, x2, y2, colour, filled );
      
   } // end MyRect constructor with input values

   // default constructor with no input values
   public MyRect()
   {
      super(); // call superclass default constructor
   } // end MyRect default constructor

   // method draw overrides superclass abstract method draw and makes it concrete
   public void draw( Graphics g )
   {
      g.setColor( getColour() ); // set the colour for drawing/filling the rectangle
      
      if ( getFill() ) // check if rectangle should be filled
         g.fillRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); // draw filled rectangle
      else
         g.drawRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); // draw unfilled rectangle
   } // end method draw
   
} // end class MyRect

