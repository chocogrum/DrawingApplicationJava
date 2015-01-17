// Project 3: DrawInternalFrame.java
// Internal frame containing panels necessary to draw shapes
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.event.InternalFrameAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

// begin class DrawInternalFrame which inherits from JInternalFrame
public class DrawInternalFrame extends JInternalFrame
{
	private ShapePanel shapePanel; // declare panel to hold the GUI components
	private DrawPanel drawPanel; // declare panel for drawing on
	private MyColorChooser colourChooser; // declare panel for choosing the colour

	// constructor that accepts the title of the window as a parameter
	public DrawInternalFrame( String windowName )
	{
		// call superclass constructor setting the window title
		// also setting the window to be resizable, closable, maximizable and iconifiable
		super( windowName, true, true, true, true );
		
		shapePanel = new ShapePanel(); // initialise panel to hold GUI components
		drawPanel = new DrawPanel(); // initialise panel for drawing on
		colourChooser = new MyColorChooser(); // initialise panel for choosing the colour
		
		// set the window to be disposed of when closed by user
		setDefaultCloseOperation( JInternalFrame.DISPOSE_ON_CLOSE );
		
		setLayout( new BorderLayout() ); // set the layout of this frame to border layout
		add( shapePanel, BorderLayout.NORTH ); // add the shape panel to the north area of the frame
		add( drawPanel, BorderLayout.CENTER ); // add the draw panel to the centre area of the frame
		add( colourChooser, BorderLayout.SOUTH ); // add the colour chooser to the south area of the frame
		pack(); // resize the frame to a natural size for the added components
		setVisible( true ); // set the frame to be visible
		
		shapePanel.undoButtonAddListener( new UndoButtonListener() ); // add an action listener for the undo button
		shapePanel.clearButtonAddListener( new ClearButtonListener() ); // add an action listener for the clear button
		shapePanel.shapeComboBoxAddListener( new ShapeComboBoxListener() ); // add an item listener for the shape combo box
		shapePanel.filledCheckBoxAddListener( new FilledCheckBoxListener() ); // add an action listener for the filled check box
		
		ColourSliderListener colourSliderListener = new ColourSliderListener(); // declare and initialise a change listener for the sliders
		ColourTextFieldHandler colourTextFieldHandler = new ColourTextFieldHandler(); // declare and initialise an action listener for the text fields
		
		// add change listener to sliders
		colourChooser.redSliderAddListener( colourSliderListener );
		colourChooser.greenSliderAddListener( colourSliderListener );
		colourChooser.blueSliderAddListener( colourSliderListener );
		
		// add action listener to text fields
		colourChooser.redTextFieldAddListener( colourTextFieldHandler );
		colourChooser.greenTextFieldAddListener( colourTextFieldHandler );
		colourChooser.blueTextFieldAddListener( colourTextFieldHandler );
		
		// initialise the colour of the drawing panel from the colour chooser
		drawPanel.setCurrentColour( colourChooser.getColour() );
	} // end constructor
	
	// inner class for undo button listener implements ActionListener interface
	public class UndoButtonListener implements ActionListener
	{
		// make actionPerformed method concrete
		public void actionPerformed( ActionEvent event )
		{
			// clear the last shape from the drawing panel
			drawPanel.clearLastShape();
		} // end method actionPerformed
	} // end inner class UndoButtonListener
	
	// inner class for clear button listener implements ActionListener interface
	public class ClearButtonListener implements ActionListener
	{
		// make actionPerformed method concrete
		public void actionPerformed( ActionEvent event )
		{
			drawPanel.clearDrawing(); // clear all drawings from the drawing panel
		} // end method actionPerformed
	} // end inner class ClearButtonListener
	
	// inner class for shape combo box listener implements ItemListener interface
	public class ShapeComboBoxListener implements ItemListener
	{
		// make itemStateChanged method concrete
		public void itemStateChanged( ItemEvent event )
		{
			if( event.getStateChange() == ItemEvent.SELECTED ) // if an item has been selected
			{
				// set the shape type on the drawing panel to the item selected
				drawPanel.setShapeType( shapePanel.getSelectedShape() );
			}
		} // end method itemStateChanged
	} // end inner class ShapeComboBoxListener
	
	// inner class for filled check box listener implements ActionListener interface
	public class FilledCheckBoxListener implements ActionListener
	{
		// make actionPerformed method concrete
		public void actionPerformed( ActionEvent event )
		{
			// set the filled shape flag on the drawing panel
			drawPanel.setFilledShape( shapePanel.filled() );
		} // end method actionPerformed
	} // end inner class FilledCheckBoxListener
	
	// inner class for colour slider listener implements ChangeListener interface
	public class ColourSliderListener implements ChangeListener
	{
		// make stateChanged method concrete
		public void stateChanged( ChangeEvent e )
		{
			// set the colour of the drawing panel as per the colour chooser
			drawPanel.setCurrentColour( colourChooser.getColour() );
		} // end method stateChanged
	} // end inner class ColourSliderListener
	
	// inner class for colour text field listener implements ActionListener interface
	private class ColourTextFieldHandler implements ActionListener
	{
		// make actionPerformed method concrete
		public void actionPerformed( ActionEvent event )
		{
			// set the colour of the drawing panel as per the colour chooser
			drawPanel.setCurrentColour( colourChooser.getColour() );
		} // end method actionPerformed
	} // end inner class ColoutTextFieldHandler
} // end class DrawInternalFrame