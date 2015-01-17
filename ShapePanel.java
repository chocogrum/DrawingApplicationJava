// Project 3: ShapePanel.java
// Panel to provide GUI options for drawing shapes
// Adapted code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// import required classes
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

// begin class ShapePanel which inherits from class JPanel
public class ShapePanel extends JPanel
{
   private JButton undoButton; // declare button to allow user to undo the last shape drawn
   private JButton clearButton; // declare button to allow user to clear all shapes drawn
   private JComboBox < ShapesEnum > shapeComboBox; // declare combo box containing objects of ShapesEnum to allow users to select a shape to draw
   private JCheckBox filledCheckBox; // declare a check box to allow users to indicate whether a shape should be filled or not
   
   // no argument constructor
   public ShapePanel()
   {
      super(); // call superclass constructor
	  
	  undoButton = new JButton( "Undo" ); // initialise undo button
	  clearButton = new JButton( "Clear" ); // initialise clear button
	  
	  // initialise the combo box for shapes by passing in an array of values from the ShapesEnum enum
	  shapeComboBox = new JComboBox < ShapesEnum > ( ShapesEnum.values() );
	  filledCheckBox = new JCheckBox( "Filled" ); // initialise the check box

      // add elements to the control panel using the default layout - FlowLayout CENTER
	  add( undoButton ); // add the undo button to the control panel
	  add( clearButton ); // add the clear button to the control panel
	  add( shapeComboBox ); // add the shapes combo box to the control panel
	  add( filledCheckBox ); // add the filled check box to the control panel
   } // end constructor
   
	// method to add an action listener to the undo button
	public void undoButtonAddListener( ActionListener actionListener )
	{
		// add the action listener passed in to the undo button
		undoButton.addActionListener( actionListener );
	} // end method undoButtonAddListener
	
	// method to add an action listener to the clear button
	public void clearButtonAddListener( ActionListener actionListener )
	{
		// add the action listener passed in to the clear button
		clearButton.addActionListener( actionListener );
	} // end method clearButtonAddListener
	
	// method to add an item listener to the shape combo box
	public void shapeComboBoxAddListener( ItemListener itemListener )
	{
		// add the item listener passed in to the shape combo box
		shapeComboBox.addItemListener( itemListener );
	} // end method shapeComboBoxAddListener

	// method to add an action listener to the filled check box
	public void filledCheckBoxAddListener( ActionListener actionListener )
	{
		// add the action listener passed in to the filled check box
		filledCheckBox.addActionListener( actionListener );
	} // end method filledCheckBoxAddListener

	// method to return true or false depending on whether the filled check box is selected or not
	public boolean filled()
	{
		return filledCheckBox.isSelected(); // return true or false
	} // end method filled
	
	// method to get the type of shape currently selected in the shape combo box
	public ShapesEnum getSelectedShape()
	{
		return ( ShapesEnum ) shapeComboBox.getSelectedItem(); // return the currently selected shape type
	} // end method getSelectedShape

} // end class ShapePanel