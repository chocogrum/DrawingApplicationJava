// Project 3: ShapesEnum.java
// Declare an enumerated list of the possible types of shapes that can be drawn.
// Using an enum to represent the list of possible shape types makes it easier
// to extend the drawing program if further shapes need to be added to the list in future
// Re-used code from Project 1
// Name: Graham Thomas
// Student Number: 1479585

// begin enum ShapesEnum
public enum ShapesEnum
{
	LINE      ( "Line" ), // line element
	RECTANGLE ( "Rectangle" ), // rectangle element
	OVAL      ( "Oval" ); // oval element
	
	private final String description; // string to contain a description of the shape
	
	// constructor to set the description of the shape
	ShapesEnum( String description )
	{
		this.description = description; // set the description
	}

	// override method toString() to return a description of the shape
	public String toString()
	{
		return description; // return the shape's description
	} // end method toString
	
	// constant to represent the number of elements in the list
	// constant is initialised at end of enum declaration so that all elements have been initialised
	public static final int LENGTH = ShapesEnum.values().length;

}; // end enum ShapesEnum