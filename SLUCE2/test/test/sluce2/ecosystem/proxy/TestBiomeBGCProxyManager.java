package test.sluce2.ecosystem.proxy;

import java.io.FileNotFoundException;

import sluce2.ecosystem.biomebgc.proxy.BiomeBGCProxyManager;
import sluce2.utility.file.FileHandler;
import sluce2.utility.file.TextFileHandler;
import junit.framework.TestCase;

public class TestBiomeBGCProxyManager extends TestCase
{
	BiomeBGCProxyManager proxyManager;
	
	private String pathToWorkingDir = "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-tmp/";
	private String relativePathRead = "tick-0/lu.0/";
	private String relativePathWrite = "tick-1/lu.0/";
	private String initializationFileName = "initialization.ini";
	private String restartInputFileName = "restartInput.endpoint";
	private String restartOutputFileName = "restartOutput.endpoint";
	
	//private String initFilePath = "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-tmp/tick-0/lu.0/iemss.ini";
	private TextFileHandler initFileHandler; 
	
	//private String initOutputPath = "/home/meghan/Desktop/SLUCE2-Environment/SLUCE2-BGC-tmp/tick-2/lu.0/lc-TURFGRASS/iemss.ini";
	private TextFileHandler initOutputHandler; 
	
	
	
	/**
     * 
     */
    protected void setUp() 
    {
		proxyManager = new BiomeBGCProxyManager(this.pathToWorkingDir);
		//proxyManager.setCurrentWorkingDir(relativePathRead);
		assertTrue(proxyManager.getCurrentWorkingDir().exists());
	}
	
    /**
     * 
     */
    protected void tearDown() 
    {
    	
    }
	
	/**
	 * 
	 */
    /*
	public void testReadInitFile()
	{
		proxyManager.createCurrentRootDir(relativePathRead);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentRootDir());
		assertTrue(proxyManager.getCurrentRootDir().exists());
		
		System.out.println("--------INIT READ---------");
		proxyManager.populateInitFileProxy();
		proxyManager.getInitFileProxy().print();
	}
	*/
	
	/**
	 * 
	 */
	/*
	public void testReadRestartFile()
	{
		proxyManager.createCurrentRootDir(relativePathRead);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentRootDir());
		assertTrue(proxyManager.getCurrentRootDir().exists());
		
		System.out.println("-------RESTART READ----------");
		proxyManager.populateRestartFileProxy();
		proxyManager.getRestartFileProxy().print();
	}
	*/
	
	/**
	 * 
	 */
	public void testWriteInitFile()
	{
		System.out.println("\n--------INIT READ---------");
		//proxyManager.setCurrentWorkingDir(relativePathRead);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentWorkingDir());
		assertTrue(proxyManager.getCurrentWorkingDir().exists());
		
		proxyManager.populateInitFileProxy(initializationFileName);
		
		System.out.println("\n--------INIT: WRITE---------");
		//proxyManager.setCurrentWorkingDir(relativePathWrite);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentWorkingDir());
		assertTrue(proxyManager.getCurrentWorkingDir().exists());
		
		proxyManager.getInitFileProxy().print();
		proxyManager.writeInitProxyToFile(initializationFileName);
	}
	
	/**
	 * 
	 */
	public void testWriteRestartFile()
	{
		System.out.println("\n--------INIT READ---------");
		//proxyManager.setCurrentWorkingDir(relativePathRead);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentWorkingDir());
		assertTrue(proxyManager.getCurrentWorkingDir().exists());
		
		//proxyManager.populateRestartInputFileProxyWithRestartOutput(restartOutputFileName);
		
		System.out.println("\n--------INIT: WRITE---------");
		//proxyManager.setCurrentWorkingDir(relativePathWrite);
		System.out.println("ProxyManager current dir: " + proxyManager.getCurrentWorkingDir());
		assertTrue(proxyManager.getCurrentWorkingDir().exists());
		
		//proxyManager.getRestartFileProxy().print();
		//proxyManager.writeRestartProxyToInputFile(restartInputFileName);
	}
	
	
	/**
	 * 
	 */
	/*
	public void testWriteOut()
	{
		System.out.println("-----------------");
		try
		{
			initOutputHandler = new TextFileHandler(initOutputPath);
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe + " : " + initOutputPath);
		}
		proxyManager.writeInitFileProxyToFile(initOutputHandler);
	}
	*/
	
}
