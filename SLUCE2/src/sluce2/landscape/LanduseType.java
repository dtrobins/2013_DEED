package sluce2.landscape;

import sluce2.exception.CodeNotFoundException;

/**
 * Enum describing different LanduseTypes available in the model. 
 * mdh: 
 * TODO: Not sure about having this as an enum. 
 * Might want to read in xml and save values in some sort of a dictionary dynamically
 * so that if we add or remove landscape layer names, we don't have to change any code. 
 * 
 * @author Meghan Hutchins
 * @date 10/25/2010
 */
public enum LanduseType
{
	NA(-9999),
    DEVELOPED(1), 
    AGRICULTURE(2), 
    UNDEVELOPED(3),
    PROTECTED(4);
    
    private int code;
   
    /*
     * Private constructor builds Enums with appropriate codes.
     */
    private LanduseType(int code)
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
    public static LanduseType getLanduseType(int code) throws CodeNotFoundException
    {
	LanduseType type = null;
	
	/*
	 * NB: Make sure case number matches appropriate code.
	 */
	switch(code)
	{
	    case 1:
		type = DEVELOPED;
		break;
	    case 2:
		type = AGRICULTURE;
		break;
	    case 3:
		type = UNDEVELOPED;
		break;
	    case 4: 
		type = PROTECTED;
		break;
	    default:
		type = null;
		throw new CodeNotFoundException(code);
	}
	
	return type;
    }
    
    /**
     * 
     * @return code associated with this LanduseType
     */
    public int getCode()
    {
	return code;
    }
    
}