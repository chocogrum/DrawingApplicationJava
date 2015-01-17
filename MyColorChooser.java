// Project 3: MyColourChooser.java
// Allows user to define a colour using sliders or entering values in text fields
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;

// declare MyColorChooser class which inherits from JPanel
public class MyColorChooser extends JPanel
{
	private JLabel redLabel; // label for the red slider 
	private JLabel greenLabel; // label for the green slider
	private JLabel blueLabel; // label for the blue slider
	private JSlider redSlider; // slider to adjust the amount of red in a colour
	private JSlider greenSlider; // slider to adjust the amount of green in a colour
	private JSlider blueSlider; // slider to adjust the amount of blue in a colour
	private JTextField redTextField; // text field to display the amount of red in a colour
	private JTextField greenTextField; // text field to display the amount of green in a colour
	private JTextField blueTextField; // text field to display the amount of blue in a colour
	private GridBagLayout layout; // gridbag layout will be used to add components to the panel
	private GridBagConstraints constraints; // object to hold the gridbag constraints
	private Color colour; // Color object to represent the current colour
	
	// no argument constructor
	public MyColorChooser()
	{
		super(); // call superclass constructor
		
		// initialise slider labels
		redLabel = new JLabel( "Red" );
		greenLabel = new JLabel( "Green" );
		blueLabel = new JLabel( "Blue" );
	
		// initialise sliders as horizontal sliders with a range of 0 - 255 and an initial value of 127 (halfway)
		redSlider = new JSlider( SwingConstants.HORIZONTAL, 0, 255, 127 );
		greenSlider = new JSlider( SwingConstants.HORIZONTAL, 0, 255, 127 );
		blueSlider = new JSlider( SwingConstants.HORIZONTAL, 0, 255, 127 );
		
		// add change listeners to sliders
		redSliderAddListener( new SliderListener() );
		greenSliderAddListener( new SliderListener() );
		blueSliderAddListener( new SliderListener() );
		
		// initialise text fields with five columns
		redTextField = new JTextField( 5 );
		greenTextField = new JTextField( 5 );
		blueTextField = new JTextField( 5 );
		
		// add action listener to text fields
		redTextFieldAddListener( new RedTextFieldListener() );
		greenTextFieldAddListener( new GreenTextFieldListener() );
		blueTextFieldAddListener( new BlueTextFieldListener() );
		
		updateTextFields(); // update text fields with slider values
		
		// initialise current colour to that represented by the current slider levels
		colour = new Color( redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue() );
		
		layout = new GridBagLayout(); // initialise gridbag layout
		constraints = new GridBagConstraints(); // initialise gridbag constraints
		setLayout( layout ); // set layout of this panel to gridbag layout
		
		// add the labels to the panel
		addComponent( redLabel, 0, 0, 1, 1 ); // gridx 0, gridy 0, gridwidth 1, gridheight 1
		addComponent( greenLabel, 0, 1, 1, 1 ); // gridx 0, gridy 1, gridwidth 1, gridheight 1
		addComponent( blueLabel, 0, 2, 1, 1 ); // gridx 0, gridy 2, gridwidth 1, gridheight 1
		
		// add the sliders to the panel
		addComponent( redSlider, 1, 0, 1, 1 );  // gridx 1, gridy 0, gridwidth 1, gridheight 1
		addComponent( greenSlider, 1, 1, 1, 1 );  // gridx 1, gridy 1, gridwidth 1, gridheight 1
		addComponent( blueSlider, 1, 2, 1, 1 );  // gridx 1, gridy 2, gridwidth 1, gridheight 1
		
		// add the text fields to the panel
		addComponent( redTextField, 2, 0, 1, 1 );  // gridx 2, gridy 0, gridwidth 1, gridheight 1
		addComponent( greenTextField, 2, 1, 1, 1 );  // gridx 2, gridy 1, gridwidth 1, gridheight 1
		addComponent( blueTextField, 2, 2, 1, 1 );  // gridx 2, gridy 2, gridwidth 1, gridheight 1
	} // end constructor
	
	// method to add components to the panel based on the constraints passed in
	public void addComponent( Component component, int gridx, int gridy, int gridwidth, int gridheight )
	{
		constraints.gridx = gridx; // set gridx constraint
		constraints.gridy = gridy; // set gridy constraint
		constraints.gridwidth = gridwidth; // set gridwidth constraint
		constraints.gridheight = gridheight; // set gridheight constraint
		layout.setConstraints( component, constraints ); // set constraints for the component
		add( component ); // add the component to the panel
	} // end method addComponent
	
	// method to add a change listener to the red slider
	public void redSliderAddListener( ChangeListener changeListener )
	{
		// add the change listener passed in to the red slider
		redSlider.addChangeListener( changeListener );
	} // end method redSliderAddListener
	
	// method to add a change listener to the green slider
	public void greenSliderAddListener( ChangeListener changeListener )
	{
		// add the change listener passed in to the green slider
		greenSlider.addChangeListener( changeListener );
	} // end method redSliderAddListener
	
	// method to add a change listener to the blue slider
	public void blueSliderAddListener( ChangeListener changeListener )
	{
		// add the change listener passed in to the blue slider
		blueSlider.addChangeListener( changeListener );
	} // end method redSliderAddListener
	
	// method to add a change listener to the red text field
	public void redTextFieldAddListener( ActionListener actionListener )
	{
		// add the change listener passed in to the red text field
		redTextField.addActionListener( actionListener );
	} // end method redSliderAddListener
	
	// method to add a change listener to the green text field
	public void greenTextFieldAddListener( ActionListener actionListener )
	{
		// add the change listener passed in to the green text field
		greenTextField.addActionListener( actionListener );
	} // end method redSliderAddListener
	
	// method to add a change listener to the blue text field
	public void blueTextFieldAddListener( ActionListener actionListener )
	{
		// add the change listener passed in to the blue text field
		blueTextField.addActionListener( actionListener );
	} // end method redSliderAddListener
	
	// inner class to define a listener for the sliders which implements ChangeListener interface
	private class SliderListener implements ChangeListener
	{
		// make method concrete
		public void stateChanged( ChangeEvent e )
		{
			updateTextFields(); // update text fields with slider values
			updateColour(); // update the current colour based on slider values
		} // end method stateChanged
	} // end inner class SliderListener
	
	// inner class to define a listener for the red text field which implements ActionListener interface
	private class RedTextFieldListener implements ActionListener
	{
		// make method concrete
		public void actionPerformed( ActionEvent event )
		{			
			// set the red slider to the value in the red text field
			// but first validate the value in the text field by calling converStringToValidRGB
			redSlider.setValue( convertStringToValidRGB( redSlider, redTextField.getText() ) );
			
			// set the red text field to the value of the red slider to ensure nonsense entries are replaced
			redTextField.setText( Integer.toString( redSlider.getValue() ) );
		} // end method actionPerformed
	} // end inner class RedTextFieldListener
	
	// inner class to define a listener for the green text field which implements ActionListener interface
	private class GreenTextFieldListener implements ActionListener
	{
		// make method concrete
		public void actionPerformed( ActionEvent event )
		{
			// set the green slider to the value in the green text field
			// but first validate the value in the text field by calling converStringToValidRGB
			greenSlider.setValue( convertStringToValidRGB( greenSlider, greenTextField.getText() ) );
			
			// set the green text field to the value of the green slider to ensure nonsense entries are replaced
			greenTextField.setText( Integer.toString( greenSlider.getValue() ) );
		} // end method actionPerformed
	} // end inner class GreenTextFieldListener
	
	// inner class to define a listener for the blue text field which implements ActionListener interface
	private class BlueTextFieldListener implements ActionListener
	{
		// make method concrete
		public void actionPerformed( ActionEvent event )
		{
			// set the blue slider to the value in the blue text field
			// but first validate the value in the text field by calling converStringToValidRGB
			blueSlider.setValue( convertStringToValidRGB( blueSlider, blueTextField.getText() ) );
			
			// set the blue text field to the value of the blue slider to ensure nonsense entries are replaced
			blueTextField.setText( Integer.toString( blueSlider.getValue() ) );
		} // end method actionPerformed
	} // end inner class BlueTextFieldListener
	
	// method to validate a string as a value of a slider
	public int convertStringToValidRGB( JSlider slider, String string )
	{
		int value; // temporary int variable
		
		try // begin try block
		{
			value = Integer.parseInt( string ); // parse the string as an integer and assign to temporary int variable
			
			if( value < 0 || value > 255 ) // if the value is not within the range 0-255
				value = slider.getValue(); // get the current value of the slider
		} // end try block
		catch( NumberFormatException e ) // catch NumberFormatException if string cannot be parsed as an integer
		{
			// string cannot be parsed as an integer therefore get the current value of the slider
			value = slider.getValue();
		} // end catch block
		
		return value; // return the value as calculated above
	} // end method convertStringToValidRGB
	
	// method to update the text fields with their corresponding slider's values
	public void updateTextFields()
	{
		// update the red text field with the value of the red slider
		redTextField.setText( Integer.toString( redSlider.getValue() ) );
		
		// update the green text field with the value of the green slider
		greenTextField.setText( Integer.toString( greenSlider.getValue() ) );
		
		// update the blue text field with the value of the blie slider
		blueTextField.setText( Integer.toString( blueSlider.getValue() ) );
	} // end method updateTextFields
	
	// method to update the colour as per the values of the sliders
	public void updateColour()
	{
		// update colour to a new colour with RGB values as determined by the sliders
		colour = new Color( redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue() );
	} // end method updateColour
	
	// get method for the colour
	public Color getColour()
	{
		return colour; // return the colour
	} // end method getColour
} // end class MyColorChooser