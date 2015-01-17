// Project 3: DrawFrame.java
// Frame representing the main application window
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.JOptionPane;
import java.beans.PropertyVetoException;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

// begin class DrawFrame which inherits from JFrame
public class DrawFrame extends JFrame
{
	private JMenuBar menuBar; // menu bar for the application
	private JMenu fileMenu; // File menu
	private JMenu windowMenu; // Window menu
	private JMenuItem newDrawing; // New Drawing menu item
	private JMenuItem exit; // Exit menu item
	private JDesktopPane desktop; // main desktop for the application
	private int openWindowCounter; // int variable to maintain the number of open windows
	private int windowNameCounter; // int variable to maintain a suffix for naming new windows
	private InternalFrameListener frameListener; // listener for internal frames
	
	// final int containing the max number of windows that can be open at any one time
	private static final int MAX_NUMBER_OPEN_WINDOWS = 20;
	
	// final String containing the error message to display when a user attempts to open too many windows
	private static final String TOO_MANY_WINDOWS_MESSAGE =
		String.format( "There are too many windows open. Please close an existing window before opening another.\n"
					 + "A maximum of %d windows can be open at any one time.", MAX_NUMBER_OPEN_WINDOWS );
	
	// no argument constructor
	public DrawFrame()
	{
		super( "Graham's Drawing Application" ); // call superclass constructor with application name
		openWindowCounter = 0; // set number of open windows to 0
		windowNameCounter = 1; // set first window name suffix to 1
		
		menuBar = new JMenuBar(); // initialise menu bar
		
		fileMenu = new JMenu( "File" ); // initialise File menu
		newDrawing = new JMenuItem( "New Drawing" ); // initialise New Drawing menu item
		
		// add action listener to New Drawing menu item
		newDrawing.addActionListener(
			new ActionListener() // declare anonymous inner class
			{
				// make actionPerformed method concrete
				public void actionPerformed( ActionEvent event )
				{
					createNewDrawingWindow(); // create a new drawing window
				} // end method actionPerformed
			} // end anonymous inner class
		); // finished adding listener to New Drawing menu item
		
		exit = new JMenuItem( "Exit" ); // initialise Exit menu item
		
		// add action listener to Exit menu item
		exit.addActionListener(
			new ActionListener() // declare anonymous inner class
			{
				// make actionPerformed method concrete
				public void actionPerformed( ActionEvent event )
				{
					System.exit( 0 ); // exit the application
				} // end method actionPerformed
			} // end anonymous inner class
		); // finished adding listener to Exit menu item
		
		fileMenu.add( newDrawing ); // add the New Drawing menu item to the File menu
		fileMenu.add( exit ); // add the Exit menu item to the File menu
		
		windowMenu = new JMenu( "Window" ); // initialise Window menu
		
		// add menu listener to Window menu
		windowMenu.addMenuListener(
			new MenuListener() // declare anonymous inner class
			{
				// make menuSelected method concrete
				public void menuSelected( MenuEvent menuEvent )
				{
					populateWindowMenu(); // populate the Window menu with menu items
				} // end method menuSelected
				
				// make menuDeselected method concrete
				public void menuDeselected( MenuEvent menuEvent )
				{
					// method must be made concrete but no functionality required
				} // end method menuDeselected
				
				// make menuCanceled method concrete
				public void menuCanceled( MenuEvent menuEvent )
				{
					// method must be made concrete but no functionality required
				} // end method menuCanceled
			} // end anonymous inner class
		); // finished adding listener to Window menu
		
		menuBar.add( fileMenu ); // add file menu to menu bar
		menuBar.add( windowMenu ); // add window menu to menu bar
		setJMenuBar( menuBar ); // add menu bar to the frame
		
		desktop = new JDesktopPane(); // initialise main desktop
		add( desktop ); // add the desktop to the frame
		
		createNewDrawingWindow(); // create a new drawing window so the user can begin drawing
		
		frameListener = new InternalFrameListener(); // initialise the listener for internal frames
	} // end constructor
	
	// inner class for internal frame listener inherits from InternalFrameAdapter
	public class InternalFrameListener extends InternalFrameAdapter
	{
		// override method internalFrameClosing
		public void internalFrameClosing( InternalFrameEvent internalFrameEvent )
		{
			openWindowCounter--; // decrement the open window counter
		} // end method internalFrameClosing
	} // end inner class InternalFrameListener
	
	// inner class for the menu items under the Window menu
	public class NewWindowMenuItemListener implements ActionListener
	{
		JInternalFrame tempFrame; // temporary internal frame object for the frame represented by the menu item 
	
		// constructor accepts a JInternalFrame object as a parameter
		public NewWindowMenuItemListener( JInternalFrame frame )
		{
			// initialise the temporary internal frame object to reference the window that this listener's menu item is associated with
			tempFrame = frame;
		} // end constructor
	
		// make actionPerformed method concrete
		public void actionPerformed( ActionEvent event )
		{
			try // begin try block
			{
				tempFrame.setIcon( false ); // deiconify the internal frame if it is iconified
				tempFrame.setSelected( true ); // set the internal frame to be the currently selected frame
			} // end try block
			catch( PropertyVetoException propertyVetoException ) // catch PropertyVetoException
			{
				// if PropertyVetoException occurs, do nothing
			} // end catch block
		} // end method actionPerformed
	} // end inner class NewWindowMenuItemListener
	
	// method for creating a new internal drawing window
	public void createNewDrawingWindow()
	{
		// make sure the max number of open windows hasn't already been reached
		if( openWindowCounter < MAX_NUMBER_OPEN_WINDOWS )
		{
			// x and y variables to indicate the upper left corner of where the new window will be positioned
			// x and y values calculated depending on the number of windows that have already been opened
			// this will ensure the position of each new window is offset from the last so the users can see them more clearly
			int x = ( ( ( windowNameCounter - 1 ) * 20 ) % 200 ) + ( ( int ) ( ( windowNameCounter - 1 ) / 10 ) * 100 ) + 20;
			int y = ( ( ( windowNameCounter - 1 ) * 20 ) % 200 ) + 20;
			
			// create new internal frame with title including windowNameCounter as a suffix
			// increment windowNameCounter
			DrawInternalFrame drawingFrame = new DrawInternalFrame( "Drawing" + windowNameCounter++ );
			drawingFrame.addInternalFrameListener( frameListener ); // add listener for the new internal frame
			openWindowCounter++; // increment the number of open windows
			desktop.add( drawingFrame ); // add the new internal frame to the desktop
			drawingFrame.setLocation( x, y ); // set the location of the new internal frame as calculated above
			try // begin try block
			{
				drawingFrame.setSelected( true ); // set the new frame to be selected
			}  // end try block
			catch( PropertyVetoException propertyVetoException ) // catch PropertyVetoException
			{
				// if PropertyVetoException occurs, do nothing
			} // end catch block
		} // end if
		else // max number of open windows has been reached
		{
			// display error message to user
			JOptionPane.showMessageDialog( this, // parent window for the message dialog is this frame
										   TOO_MANY_WINDOWS_MESSAGE, // message to display
										   "Too Many Windows Open", // dialog box title
										   JOptionPane.ERROR_MESSAGE ); // message type is error message
		} // end else
	} // end method createNewDrawingWindow
	
	// method to populate the Window menu with menu items
	public void populateWindowMenu()
	{
		windowMenu.removeAll(); // first remove all existing menu items from the Window menu
		
		for( JInternalFrame frame : desktop.getAllFrames() ) // loop through all open windows in the desktop
		{
			// create a temporary menu item with the title of the current window
			JMenuItem tempMenuItem = new JMenuItem( frame.getTitle() );
			
			// add action listener for the new menu item
			// pass in a reference to the internal frame associated with the new menu item as an argument
			tempMenuItem.addActionListener( new NewWindowMenuItemListener( frame ) );
			
			windowMenu.add( tempMenuItem ); // add the new menu item to the Window menu
		} // end for loop
	} // end method populateWindowMenu
} // end class DrawFrame