// Project 3: MyBoundedShape.java
// Declaration of abstract class MyShape to represent a bounded shape
// Re-used code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;

// begin class MyBoundedShape which inherits from MyShape
public abstract class MyBoundedShape extends MyShape
{
   private boolean filled; // flag to indicate whether rectangle is filled or not

   // constructor with input values
   public MyBoundedShape( int x1, int y1, int x2, int y2, Color colour, Boolean filled )
   {
      // call superclass constructor with input values
      super( x1, y1, x2, y2, colour );
      
      setFilledFlag( filled ); // set the filled flag
      
   } // end MyBoundedShape constructor with input values
   
   // default constructor with no input values
   public MyBoundedShape()
   {
      super(); // call superclass default constructor
      setFilledFlag( false ); // default the filled flag to false ie bounded shape is not filled
   } // end MyBoundedShape default constructor
   
   // method to set filled flag
   public void setFilledFlag( boolean filled )
   {
      this.filled = filled; // set flag indicating whether bounded shape is filled or not
   } // end method setFilledFlag
   
   // method to get X Coordinate of upper left corner of the bounded shape
   public int getUpperLeftX()
   {
      return Math.min( getX1(), getX2() ); // return the least of the two x coordinates
   } // end method getUpperLeftX
   
   // method to get Y Coordinate of upper left corner of the bounded shape
   public int getUpperLeftY()
   {
      return Math.min( getY1(), getY2() ); // return the least of the two y coordinates
   } // end method getUpperLeftY
   
   // method to get width of the bounded shape
   public int getWidth()
   {
      // return absolute value of the difference between the two x coordinate values
      return Math.abs( getX1() - getX2() );
   } // end method getWidth
   
   // method to get height of the bounded shape
   public int getHeight()
   {
      // return absolute value of the difference between the two y coordinate values
      return Math.abs( getY1() - getY2() );
   } // end method getHeight
   
   // method to get the fill of the bounded shape
   public boolean getFill()
   {
      return filled; // return the fill of the bounded shape
   } // end method getFill

   // abstract method to draw the bounded shape
   public abstract void draw( Graphics g );

} // end class MyRect