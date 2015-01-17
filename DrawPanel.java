// Project 3: DrawPanel.java
// Panel for drawing a shape on
// Adapted code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Component;

// begin class DrawPanel which inherits from class JPanel
public class DrawPanel extends JPanel
{
   private MyShape shapes[]; // declare an array of shapes
   private int shapeCount; // declare an integer to hold the number of shapes drawn
   private ShapesEnum shapeType; // declare a ShapesEnum object to represent the type of the current shape
   private MyShape currentShape; // declare a MyShape object to represent the current shape
   private Color currentColour; // declare a Color object to represent the current colour
   private boolean filledShape; // declare a boolean to indicate whether the current shape should be filled or not
   private final int MAX_NUM_SHAPES = 100; // final variable to represent the maximum number of shapes that can be drawn on the panel

   // no argument constructor
   public DrawPanel()
   {
	  shapes = new MyShape[ MAX_NUM_SHAPES ]; // create array to hold the shapes
	  shapeCount = 0; // initialise the number of shapes to 0
	  shapeType = ShapesEnum.LINE; // initialise the shape type to a line
	  currentShape = null; // initialise the current shape to null
	  currentColour = Color.BLACK; // initialise the current colour to black
	  setBackground( Color.WHITE ); // set the background colour to white
	  filledShape = false; // initialise the filled flag to unfilled
	  MouseHandler handler = new MouseHandler( this ); // declare and initialise a MouseHandler object passing this panel as the parent component
	  addMouseListener( handler ); // register event handler for mouse events
	  addMouseMotionListener( handler ); // register event handler for mouse motion events
	  setPreferredSize( new Dimension( 300, 200 ) ); // set the preferred size of the drawing panel
   } // end constructor

   // method paintComponent polymorphically draws each shape in the array shapes
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g ); // call superclass method
      
      // loop through all shapes in the array up to shapeCount
	  for ( int i = 0; i < shapeCount; i++ )
	     shapes[ i ].draw( g ); // draw the shape
	  
	  // also draw the current shape if it is not null
	  if ( currentShape != null ) // check if the current shape is not null
	     currentShape.draw( g );// draw current shape

   } // end method paintComponent
   
   // set method for shapeType
   public void setShapeType( ShapesEnum shapeType )
   {
      this.shapeType = shapeType; // set the shape type
      
   } // end method setShapeType
   
   // set method for currentColour
   public void setCurrentColour( Color colour )
   {
      currentColour = colour; // set the current colour
   } // end method setCurrentColour
   
   // set method for filledShape
   public void setFilledShape( boolean filled )
   {
      filledShape = filled; // set the filled flag
   } // end method setFilledShape
   
   // method to clear the last drawn shape from the panel
   public void clearLastShape()
   {
      if ( shapeCount > 0 ) // check if the shape count is greater than 0
	     shapeCount--; // decrement the shape count

	  repaint(); // repaint the Graphics object without the last drawn shape 
   } // end method clearLastShape
   
   // method to clear all shapes from the panel
   public void clearDrawing()
   {
      shapeCount = 0; // set shape count to 0
	  repaint(); // repaint the Graphics object with no shapes
   } // end method clearDrawing
   
   // declare inner class for event handler for mouse events and mouse motion events
   private class MouseHandler implements MouseListener, MouseMotionListener // implement MouseListener and MouseMotionListener interfaces
   {
		private Component parentComponent; // component object to represent the parent component of the mouse handler
		
		// constructor that takes the parent component as a parameter
		public MouseHandler( Component component )
		{
			super(); // call superclass constructor
			parentComponent = component; // set parent component of the mouse handler
		} // end one argument constructor
		
		// default no argument constructor
		public MouseHandler()
		{
			this( null ); // call one argument constructor setting the parent component to null
		} // end default no argument constructor
   
      public void mousePressed( MouseEvent event ) // make abstract method mousePressed concrete
	  {
	     switch ( shapeType ) // check the current shape type
		 {
		    case LINE: // if current shape is a line
		       // create new MyLine object using coordinates of the mouse and the current color
	           currentShape = new MyLine( event.getX(), event.getY(), event.getX(), event.getY(), currentColour );
			   break; // break out of switch statement
			
			case RECTANGLE: // if current shape is a rectangle
			   // create new MyRect object using coordinates of the mouse, current color and the filled flag
	           currentShape = new MyRect( event.getX(), event.getY(), event.getX(), event.getY(), currentColour, filledShape );
			   break; // break out of switch statement
			
			case OVAL: // if current shape is an oval
			   // create new MyOval object using coordinates of the mouse, current color and the filled flag
	           currentShape = new MyOval( event.getX(), event.getY(), event.getX(), event.getY(), currentColour, filledShape );
			   break; // break out of switch statement
	     } // end switch statement
	  } // end method mousePressed
	  
	  public void mouseReleased( MouseEvent event ) // make abstract method mouseReleased concrete
	  {
	     // set the second x coordinate of the current shape to the x coordinate of the mouse
		 currentShape.setX2( event.getX() );
		 
		 // set the second y coordinate of the current shape to the y coordinate of the mouse
		 currentShape.setY2( event.getY() );
		 
		 // make sure the shape count isn't greater than the size of the shapes array
		 if ( shapeCount < shapes.length ) // if shape count is less than the size of the array
		    shapes[ shapeCount++ ] = currentShape; // add current shape to the array
		 else // otherwise display an error message advising user that the maximum number of shapes has been reached
		    JOptionPane.showMessageDialog( parentComponent, // parent component is this panel
		  	                               "Cannot create shape. Maximum number of shapes has already been reached.", // message to display
		  	                               "Maximum Shape Count Reached", // title of the dialog box
		  	                               JOptionPane.ERROR_MESSAGE // indicate that the type of message to be displayed is an error message
		  	                             ); // end call to showMessageDialog
		 		 
		 repaint(); // repaint the Graphics object with all shapes in the array including the newly added shape
		 currentShape = null; // reset the current shape to null
	  } // end method  mouseReleased
	  
	  public void mouseMoved( MouseEvent event ) // make abstract method mouseMoved concrete
	  {
	     // MouseListener interface requires method mouseEntered to be made concrete however
	     // no action is required for this event so method contains no functionality
	  } // end method mouseMoved
	  
	  public void mouseDragged( MouseEvent event ) // make abstract method mouseDragged concrete
	  {
	     currentShape.setX2( event.getX() ); // set the second x coordinate of the shape to the x coordinate of the mouse
		 currentShape.setY2( event.getY() ); // set the second y coordinate of the shape to the y coordinate of the mouse
		 repaint(); // repaint the Graphics object so that the user can see the shape being drawn
	  } // end method mouseDragged

	  public void mouseEntered( MouseEvent event ) // make abstract method mouseEntered concrete
	  {
	     // MouseListener interface requires method mouseEntered to be implemented however
	     // no action is required for this event so method contains no functionality
	  } // end method mouseEntered
	  
	  public void mouseExited( MouseEvent event ) // make abstract method mouseExited concrete
	  {
	     // MouseListener interface requires method mouseExited to be implemented however
	     // no action is required for this event so method contains no functionality
	  } // end method mouseExited
	  
	  public void mouseClicked( MouseEvent event ) // make abstract method mouseClicked concrete
	  {
	     // MouseListener interface requires method mouseClicked to be implemented however
	     // no action is required for this event so method contains no functionality
	  } // end method mouseClicked

   } // end inner class MouseHandler
   
} // end class DrawPanel