package sluce2.landscape;

import sluce2.exception.CodeNotFoundException;

/**
 * Contains an enumeration of valid cardinal directions and associated numeric code. 
 * 
 * @author Meghan Hutchins
 * @date 11/05/2010
 */
public enum Direction
{
    NA(0),
    NORTH(1), 
    SOUTH(2), 
    EAST(3), 
    WEST(4);
    
    private int code;
    
    /*
     * Private constructor builds Enums with appropriate codes.
     */
    private Direction(int code)
    {
	this.code = code;
    }
    
    /**
     * Looks up the associated LanduseType given the code and returns it.
     *  
     * @param code
     * @return LanduseType associated with the given code
     * @throws CodeNotFoundException
     */
    public static Direction getDirection(int code) throws CodeNotFoundException
    {
	Direction type = null;
	
	/*
	 * NB: Make sure case number matches appropriate code.
	 */
	switch(code)
	{
	    case 0:
		type = NA;
		break;
	    case 1:
		type = NORTH;
		break;
	    case 2:
		type = SOUTH;
		break;
	    case 3:
		type = EAST;
		break;
	    case 4: 
		type = WEST;
		break;
	    default:
		type = null;
		throw new CodeNotFoundException(code);
	}
	
	return type;
    }
    
    /**
     * 
     * @return code associated with this LandcoverType
     */
    public int getCode()
    {
	return code;
    }
}
