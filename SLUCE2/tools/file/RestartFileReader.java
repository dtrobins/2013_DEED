package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import sluce2.GlobalConstants;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.utility.file.BinaryFileHandler;

public class RestartFileReader
{
	public static void main(String[] args)
	{
		String pathToFile = "/home/dtrobins/ecosystem/BGC-TEST/bgc-tmp/dtc_init_restart.endpoint"; //restartOutput.endpoint";	
		//String pathToFile = "/home/dtrobins/ecosystem/biomebgc-4.2/restart/enf_test1.endpoint";
		try
		{
			File file = new File(pathToFile);
			file.createNewFile();
			BinaryFileHandler fileHandler = new BinaryFileHandler(pathToFile, GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			
			RestartFileProxy restartProxy = new RestartFileProxy();
			restartProxy.readIn(fileHandler);
			
			restartProxy.print();
			
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("d'oh - file not found.");
		}
		catch(IOException ioe)
		{
			System.out.println("d'oh - io exception.");
		}
	}
}
