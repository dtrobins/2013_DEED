package sluce2.utility.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

import sluce2.ModelContext;

public abstract class FileHandler
{
	/** Absolute path to this file. **/
	private String filepath;
	
	/** The File on which this FileHandler acts. **/
	protected File file;
	

	public FileHandler()
	{
		this.file = null;
		this.filepath = null;
	}
	
	 /**
     * Creates a new FileHandler to act on the file having the given pathname.
     * @param pathname
     */
    public FileHandler(String pathname) throws FileNotFoundException
    {
    	this.filepath = pathname;
    	this.file = new File(pathname);
    	
    	if(!file.exists()) 
    	{
    		throw new FileNotFoundException();
    	}
    }
    
	 /**
     * Creates a new FileHandler to act on the file having the given file.
     * @param pathname
     */
    public FileHandler(File file) throws FileNotFoundException
    {
    	this.file = file;
    	if(!file.exists()) 
    	{
    		throw new FileNotFoundException();
    	}
    }
    
    public abstract ArrayList<String> fileToArray();
    public abstract boolean writeArrayToFile(ArrayList<String> lineArray);
    
    // ** Optional Override ** //
    public ArrayList<String> fileToArray(String regex, int limit)
    {
    	return new ArrayList<String>();
    }
    
    /**
     * 
     */
    public String toString()
    {
    	return this.file.getName();
    }
    
    /**
     * 
     */
    public void printFile()
    {
    	for(String str : this.fileToArray())
    	{
    		System.out.println(str);
    	}
    }
    
    /**
     * @param directoryPath
     * @return true if the given directory was created, false otherwise.
     */
    public static boolean createDirectory(String directoryPath)
    {
    	boolean success = false;
    	File directory = new File(directoryPath);
    	
    	if(!directory.exists())
    	{
    		try
    		{
    			success = directory.mkdir();
    		}
    		catch(SecurityException ioe)
    		{
    			ModelContext.getLogger().error("Unable to create new directory: " + directoryPath);
    		}
    	}
    	
    	return success;
    }
    
    /**
     * @param filePath
     * @return true if the given file was created, false otherwise.
     */
    public static boolean createFile(File file)
    {
    	boolean success = false;    	
		try
		{
			success = file.createNewFile();
		}
		catch(IOException ioe)
		{
			ModelContext.getLogger().error(ioe + " : Unable to create new file: " + file.getAbsolutePath());
		}
    	return success;
    }
    
	/**
	 * Rename the given file to the new name 
	 * @return true if the file was successfully renamed, false otherwise. 
	 */
	public static boolean renameTo(File file, String newName)
	{
		File newFileName = new File(file.getParent() + "/" + newName);
		return file.renameTo(newFileName);
	}
    
    
    /**
     * Deletes the directory or file associated with the given path. 
     * This is a recursive deletion: if a directory is provided, all files/directires within will also be deleted.
     * @param pathToFileOrDirectory
     * @return true if the given file or directory was deleted, false otherwise
     */
    public static boolean delete(String pathToFileOrDirectory)
    {
    	boolean success = false;
    	File targetFile = new File(pathToFileOrDirectory);
    	
    	if( targetFile.exists() ) 
    	{
    		// If a directory...
    		if(targetFile.isDirectory())
    		{
    			File[] files = targetFile.listFiles();
    			for(int i=0; i<files.length; i++) 
    			{
    				if(files[i].isDirectory()) 
    				{
    					FileHandler.delete(files[i].getAbsolutePath());
    				}
    				else 
    				{
    					success = files[i].delete();
    				}
			   }
    			success = targetFile.delete();
    		}
    		// Otherwise a just a file...
    		else
    		{
    			success = targetFile.delete();
    		}
    	}	
    	return success; 
    }
    
    /**
     * Moves the given file into the given directory.
     * @param pathToFileToMove
     * @param pathToDirectory
     * @return true if the move was successful, false otherwise
     */
    /*
    public static boolean copy(String pathToFile, String pathToDirectory)
    {
    	boolean success = false;
    	
    	File file = new File(pathToFile);
    	File directory = new File(pathToDirectory);
    	File newFile = new File(directory, file.getName());
    	
    	try
    	{
	    	if( file.exists() && directory.exists() )
	    	{
	    		success = file.createNewFile();
	    	}
    	}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error("Unable to create: " + newFile);
    	}
    	
    	return success;
    }
    */
    
    /**
     * Moves the given file into the given directory and renames it.
     * @param pathToFileToMove
     * @param pathToDirectory
     * @param newName
     * @return true if the move was successful, false otherwise
     */
    /*
    public static boolean copy(String pathToFile, String pathToDirectory, String newName)
    {
    	System.out.println("pathToFile: " + pathToFile);
    	System.out.println("pathToDirectory: " + pathToDirectory);
    	System.out.println("newName: " + newName);
    	
    	boolean success = false;
    	
    	File file = new File(pathToFile);
    	File directory = new File(pathToDirectory);
    	File newFile = new File(directory, file.getName());
    	

    	
    	try
    	{
	    	//if( file.exists() && directory.exists() )
	    	{ 
	    		System.out.println("0");
	    		if(!file.getName().equals(newName))
	    		{System.out.println("1");
	    		newFile.renameTo(new File(file.getParent(), newName));
	    		}
	    		System.out.println("file: " + file.getAbsolutePath());
	    		success = newFile.createNewFile();
	    		System.out.println(success);
	    	}
    	}
    	catch(SecurityException se)
    	{
    		ModelContext.getLogger().error("Unable to rename: " + file.getName() + " to " + newName);
    	}
    	catch(IOException ioe)
    	{
    		ModelContext.getLogger().error("Unable to create: " + newFile);
    	}
    	
    	return success;
    }
    */
    
    /** TODO MIGHT NEED A NEW FUNCTION "copyBinary" for Binary files or something **/
    public static void copy(String fromFileName, String toFileName) throws IOException 
    {
      File fromFile = new File(fromFileName);
	  File toFile = new File(toFileName);
	
	  if (!fromFile.exists())
	    throw new IOException("FileCopy: " + "no such source file: "
	        + fromFileName);
	  if (!fromFile.isFile())
	    throw new IOException("FileCopy: " + "can't copy directory: "
	        + fromFileName);
	  if (!fromFile.canRead())
	    throw new IOException("FileCopy: " + "source file is unreadable: "
	        + fromFileName);
	
	  if (toFile.isDirectory())
	    toFile = new File(toFile, fromFile.getName());
	
	  if (toFile.exists()) {
	    if (!toFile.canWrite())
	      throw new IOException("FileCopy: "
	          + "destination file is unwriteable: " + toFileName);
	    System.out.print("Overwrite existing file " + toFile.getName()
	        + "? (Y/N): ");
	    System.out.flush();
	    BufferedReader in = new BufferedReader(new InputStreamReader(
	        System.in));
	    String response = in.readLine();
	    if (!response.equals("Y") && !response.equals("y"))
	      throw new IOException("FileCopy: "
	          + "existing file was not overwritten.");
	  } else {
	    String parent = toFile.getParent();
	    if (parent == null)
	      parent = System.getProperty("user.dir");
	    File dir = new File(parent);
	    if (!dir.exists())
	      throw new IOException("FileCopy: "
	          + "destination directory doesn't exist: " + parent);
	    if (dir.isFile())
	      throw new IOException("FileCopy: "
	          + "destination is not a directory: " + parent);
	    if (!dir.canWrite())
	      throw new IOException("FileCopy: "
	          + "destination directory is unwriteable: " + parent);
	  }
	
	  FileInputStream from = null;
	  FileOutputStream to = null;
	  try {
	    from = new FileInputStream(fromFile);
	    to = new FileOutputStream(toFile);
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	
	    while ((bytesRead = from.read(buffer)) != -1)
	      to.write(buffer, 0, bytesRead); // write
	  } finally {
	    if (from != null)
	      try {
	        from.close();
	      } catch (IOException e) {
	        ;
	      }
	    if (to != null)
	      try {
	        to.close();
	      } catch (IOException e) {
	        ;
	      }
	  }
    }


//--------------------------
// Accessors
     
 	public File getFile()
 	{
 		return file;
 	}

 	public void setFile(File file)
 	{
 		this.file = file;
 	}

	public String getFilepath()
	{
		return filepath;
	}

	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}
   
}
