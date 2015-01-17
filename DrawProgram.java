// Project 3: DrawProgram.java
// Class to launch the application
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import javax.swing.JFrame;

// declare class DrawProgram
public class DrawProgram
{
	// main method for launching the application
	public static void main( String args[] )
	{
		DrawFrame application = new DrawFrame(); // create new DrawFrame object
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // set the application frame to exit when closed by the user
		application.setSize( 1000, 900 ); // set the size of the application frame
		application.setVisible( true ); // set the application frame to be visible
	} // end method main
} // end class DrawProgram