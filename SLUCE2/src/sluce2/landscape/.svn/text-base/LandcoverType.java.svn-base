package sluce2.landscape;

import java.util.HashMap;
import java.util.Map;

import sluce2.exception.CodeNotFoundException;

/**
 * Enum describing different LandcoverTypes available in the model. 
 * 
 * TODO: Can we get this dynamically from the XML descriptors and store them in a Dictionary?
 * 
 * @author Meghan Hutchins
 * @date 10/25/2010
 */
public enum LandcoverType
{
	NA(-9999),
	CROP(1),
    IMPERVIOUS(2), 
    TURFGRASS(3), 
    TREECOVER(4),
    TURF50TREE50(5);
    
    private int code;   
   
    /*
     * Private constructor builds Enums with appropriate codes.
     */
    private LandcoverType(int code)
    {
    	this.code = code;
    }
    
    /**
     * TODO: Better way of doing this
     * 
     * Looks up the associated LanduseType given the code and returns it.
     *  
     * @param code
     * @return LanduseType associated with the given code
     * @throws CodeNotFoundException
     */
    public static LandcoverType getLandcoverType(int code)
    {
		LandcoverType type = null;
		
		/*
		 * NB: Make sure case number matches appropriate code.
		 */
		switch(code)
		{
		    case 1:
			type = CROP;
			break;
		    case 2:
			type = IMPERVIOUS;
			break;
		    case 3:
			type = TURFGRASS;
			break;
		    case 4: 
			type = TREECOVER;
			break;
		    case 5:
		    type = TURF50TREE50;
		    break;
		    default:
			type = null;
			LandscapeContext.logger.error("LandcoverType Code " + code + " not found.");
			break;
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