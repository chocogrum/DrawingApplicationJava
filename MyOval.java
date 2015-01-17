// Project 3: MyOval.java
// Declaration of class MyOval to represent an oval
// Re-used code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;

// begin class MyOval which inherits from MyBoundedShape
public class MyOval extends MyBoundedShape
{

   // constructor with input values
   public MyOval( int x1, int y1, int x2, int y2, Color colour, Boolean filled )
   {
      // call superclass constructor with input values
      super( x1, y1, x2, y2, colour, filled );
      
   } // end MyOval constructor with input values
   
   // default constructor with no input values
   public MyOval()
   {
      super(); // call superclass default constructor
   } // end MyOval default constructor
   
   // method draw overrides superclass abstract method draw and makes it concrete
   public void draw( Graphics g )
   {
      g.setColor( getColour() ); // set the colour for drawing/filling the oval
      
      if ( getFill() ) // check if oval should be filled
         g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); // draw filled oval
      else
         g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); // draw unfilled oval
   } // end method draw
   
} // end class MyOval

