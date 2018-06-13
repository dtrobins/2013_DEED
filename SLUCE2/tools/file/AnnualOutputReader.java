package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import sluce2.GlobalConstants;
import sluce2.ecosystem.biomebgc.proxy.AnnualOutputFileProxy;
import sluce2.utility.file.BinaryFileHandler;

public class AnnualOutputReader
{
	public static void main(String[] args)
	{
		String pathToFile = "/home/dtrobins/ecosystem/BGC-TEST/bgc-tmp/output.annout";		
		//String pathToFile = "/home/dtrobins/ecosystem/biomebgc-4.2/outputs/enf_test1.annout";
		try
		{
			File file = new File(pathToFile);
			file.createNewFile();
			BinaryFileHandler fileHandler = new BinaryFileHandler(pathToFile, GlobalConstants.BGC_NBR_BYTES_IN_ANNUALOUTPUT);
			
			AnnualOutputFileProxy annoutFileProxy = new AnnualOutputFileProxy();
			annoutFileProxy.readIn(fileHandler);
			
			annoutFileProxy.print();
			
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
