package sluce2.utility.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import sluce2.ModelContext;
import sluce2.GlobalConstants;

/**
 * 
 * 
 * Byte order might be different from one OS to another. <br> 
 * Little-endian operating systems:<br> 
 * {@code
 *  Linux  on x86, x64, Alpha and Itanium  Mac OS X on x86, x64  
 *  OpenVMS on VAX, Alpha and Itanium  Solaris on x86, x64, 
 *  PowerPC  Tru64 UNIX on Alpha  Windows on x86, x64 and Itanium
 *  } 
 * @author Meghan Hutchins, Shipeng Sun
 *
 */
public class BinaryFileHandler extends FileHandler
{
	public enum BinaryType
	{
		DOUBLE,
		FLOAT;
	}
	
	public enum ByteNumber
	{
		FOUR,
		EIGHT;
	}
	
	/** Reads input from the file **/
	private ReadableByteChannel reader; 
	/** Writes output to the file **/
	private FileChannel writer;
	
	/** Number of bytes to be read **/
	private int nbrOfBytesToRead;
	
	
	/** BIG_ENDIAN or LITTLE_ENDIAN : dependent on the type of operating system **/
	private java.nio.ByteOrder byteOrder;
	private int sizeDouble;
	private int sizeInt;
	
	/** 
	 * @param path to a binary file
	 */
	public BinaryFileHandler(String pathname, int nbrOfBytesToRead) throws FileNotFoundException
	{
		super(pathname);
		
		this.nbrOfBytesToRead = nbrOfBytesToRead;
		this.byteOrder = GlobalConstants.BYTE_ORDER;
		
		this.sizeDouble = 8; // ?
		this.sizeDouble = 4; // ?
	}
	
	/**
     * Creates a new FileHandler to act on the file having the given file.
     * @param pathname
     */
    public BinaryFileHandler(File file, int nbrOfBytesToRead) throws FileNotFoundException
    {
    	super(file);
    	
    	this.nbrOfBytesToRead = nbrOfBytesToRead;
		this.byteOrder = GlobalConstants.BYTE_ORDER;
		
		this.sizeDouble = 8; // ?
		//this.sizeDouble = 4; // ?
		this.sizeInt = 4;
    }
    
	/**
     * Returns an ordered ArrayList where each byte of the file is represented by one element in the ArrayList.<p>
     * For example index [0] of the ArrayList represents the first byte of the file.
     * @return the ArrayList representation of the file.
     */
	//@Override
	public ArrayList<String> fileToArray(BinaryType type, ByteNumber nbrOfBytes)
    {
		ArrayList<String> dataArray = new ArrayList<String>();
		
		// ??
		//int extraBytes = (System.getProperty("os.name").contains("Windows"))?1:0;
		
		// There are 73 pieces of data we need to get
		// I'm getting 36
		// Originally we have a value of 72 here. 
		// 36 is exactly 1/2 of 72. 
		// Lets see if 146 brings back 73 pieces of data...
		// Dingdingding!
		// 146 brought back 73 pieces of data and I was able to fill in the RestartFileProxy.
		//
		// Need to put this same value on line 119 of this file too - otherwise buffer overflow.
		//ByteBuffer dataBuffer = ByteBuffer.allocate(this.nbrOfBytesToRead*sizeDouble + (1+extraBytes)*sizeInt);
		
		ByteBuffer dataBuffer;
		if(nbrOfBytes.equals(ByteNumber.FOUR))
		{
			// Shipeng's code
			int num = (int)this.file.length()/4;
			dataBuffer = ByteBuffer.allocate(num*4);
		}
		else
		{
			dataBuffer = ByteBuffer.allocate(this.nbrOfBytesToRead*sizeDouble);
		}
		
		dataBuffer.order(this.byteOrder);
		
		try
    	{
			if(reader==null)
	    	{
				reader = new FileInputStream(this.file).getChannel();
	    	}
			reader.read(dataBuffer);
			dataBuffer.rewind();
				    	
	    	//double value;
	    	
	    	while(dataBuffer.hasRemaining())
	    	{
	    		// Reads the next eight bytes at this buffer's current position
	    		if(type.equals(BinaryType.FLOAT))
	    		{
	    			// Shipeng's code
	    			dataArray.add(String.valueOf(dataBuffer.getFloat()));
	    		}
	    		else
	    		{
	    			dataArray.add(String.valueOf(dataBuffer.getDouble()));
	    		}
	    	}
	    	dataBuffer.clear();
	    	reader.close();
	    	reader=null;
    	}
		catch(FileNotFoundException fnfe)
		{
			ModelContext.getLogger().error(fnfe.getMessage());
		}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error(ioe.getMessage());
    	}
    	
    	/*
    	if(type.equals(BinaryType.FLOAT))
		{
    		System.out.println("----------");
    		for(String f : dataArray)
    		{
    			System.out.println(f);
    		}
		}
		*/
		
		return dataArray;
    }
	
	/**
	 * 
	 * @param lineArray
	 * @param type
	 * @return
	 */
	// @Override
	public boolean writeArrayToFile(ArrayList<String> lineArray, BinaryType type, ByteNumber nbrOfBytes)
    {
		boolean success = false;
		
		// ??
		int extraBytes = (System.getProperty("os.name").contains("Windows"))?1:0;
		
		ByteBuffer dataBuffer;
		if(nbrOfBytes.equals(ByteNumber.FOUR))
		{
			dataBuffer = ByteBuffer.allocate(this.nbrOfBytesToRead*4 + (1+extraBytes)*sizeInt);
		}
		else
		{
			dataBuffer = ByteBuffer.allocate(this.nbrOfBytesToRead*sizeDouble + (1+extraBytes)*sizeInt);
		}
			
		dataBuffer.order(this.byteOrder);
		
		try
    	{
	    	if(writer==null)
	    	{
	    		writer = new FileOutputStream(this.file, false).getChannel();
				dataBuffer.rewind();
	    	}
	    	
	    	for(String line : lineArray)
	    	{
	    		
	    		if(type.equals(BinaryType.FLOAT))
	    		{
	    			// Writes four bytes containing the given float value
	    			dataBuffer.putDouble(Float.valueOf(line));
	    		}
	    		else
	    		{
	    			// Writes eight bytes containing the given double value
	    			dataBuffer.putDouble(Double.valueOf(line));
	    		}
	    		
	    	}
	    	
	    	// Residual bytes
	    	
	    	// Write the ByteBuffer contents; the bytes between the ByteBuffer's 
			// position and the limit is written to the file 
	    	dataBuffer.rewind();
			writer.write(dataBuffer); 
			// Close the file 
			writer.close(); 
			writer=null;
			dataBuffer.clear();

	    	success = true;
    	}
		catch(FileNotFoundException fnfe)
    	{
    		ModelContext.getLogger().error(fnfe.getMessage());
    	}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error(ioe.getMessage());
    	}
		
		return success;
    }
	
	

//--------------------------
// Accessors	
	
	public java.nio.ByteOrder getByteOrder()
	{
		return byteOrder;
	}

	public void setByteOrder(java.nio.ByteOrder byteOrder)
	{
		this.byteOrder = byteOrder;
	}

	public int getSizeDouble()
	{
		return sizeDouble;
	}

	public void setSizeDouble(int sizeDouble)
	{
		this.sizeDouble = sizeDouble;
	}

	public int getSizeInt()
	{
		return sizeInt;
	}

	public void setSizeInt(int sizeInt)
	{
		this.sizeInt = sizeInt;
	}

	@Override
	public ArrayList<String> fileToArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeArrayToFile(ArrayList<String> lineArray)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
