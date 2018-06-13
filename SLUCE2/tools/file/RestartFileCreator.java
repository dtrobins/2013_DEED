package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import sluce2.GlobalConstants;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.ecosystem.biomebgc.proxy.RestartType;
import sluce2.utility.file.BinaryFileHandler;

public class RestartFileCreator
{
	public static void main(String[] args)
	{
		String pathToFile = "/home/dtrobins/ecosystem/BGC-TEST/bgc-tmp/restart.endpoint";		

		double ag1 = 0.7;
		double ag2 = 0.85;
		
		boolean isgrass = false;
		double deforestToForest = 0.1;
		double deforestToGrass = 0.0;
		
		try
		{
			File file = new File(pathToFile);
			file.createNewFile();	
			BinaryFileHandler fileHandler = new BinaryFileHandler(pathToFile, GlobalConstants.BGC_NBR_BYTES_IN_RESTART);
			
			// Read in the restart we will manipulate...
			RestartFileProxy restartProxy = new RestartFileProxy();
			restartProxy.readIn(fileHandler);
			
			System.out.println("----------------------------");
			System.out.println("Restart File From Spin-Up");
			System.out.println("----------------------------");
			restartProxy.print();
			
			// Make changes to the restart file...
			if (!isgrass)  {
			// "Reduce to 1%"
			System.out.println("not grass");
			restartProxy.put(RestartType.livestemc, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemc))*deforestToForest) );  //check
			restartProxy.put(RestartType.deadstemc, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemc))*deforestToForest) );  //check
			restartProxy.put(RestartType.cwdc, Double.toString( Double.valueOf(restartProxy.get(RestartType.cwdc))*deforestToForest) );            //check
			restartProxy.put(RestartType.litr1c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr1c))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr2c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr2c))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr3c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr3c))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr4c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr4c))*deforestToForest) );        //check
			restartProxy.put(RestartType.soil1c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil1c))*deforestToForest) );        //check
			restartProxy.put(RestartType.livestemn, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemn))*deforestToForest) );  //check
			restartProxy.put(RestartType.deadstemn, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemn))*deforestToForest) );  //check
			
			restartProxy.put(RestartType.cwdn, Double.toString( Double.valueOf(restartProxy.get(RestartType.cwdn))*deforestToForest) );            //check
			restartProxy.put(RestartType.litr1n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr1n))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr2n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr2n))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr3n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr3n))*deforestToForest) );        //check
			restartProxy.put(RestartType.litr4n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr4n))*deforestToForest) );        //check
			restartProxy.put(RestartType.soil1n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil1n))*deforestToForest) );        //check
			
			// "Reduce by 30%"
			restartProxy.put(RestartType.soil2c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil2c))*ag1) );       //check
			restartProxy.put(RestartType.soil3c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil3c))*ag1) );       //check
			
			// "Reduce by 15%"
			restartProxy.put(RestartType.soil2n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil2n))*ag2) );       //check 
			restartProxy.put(RestartType.soil3n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil3n))*ag2) );       //check
			}
			// "Set N values to zero if dealing with grasses"
			else {
				System.out.println("grass");
				//restartProxy.put(RestartType.soilw, Double.toString(359.559936510727 ) );
				restartProxy.put(RestartType.snoww, Double.toString(0 ) );
//				restartProxy.put(RestartType.canopyw, Double.toString(4.86E-015) );
				restartProxy.put(RestartType.canopyw, Double.toString(1.83E-015));

//				restartProxy.put(RestartType.leafc_transfer, Double.toString( 0.134118071408929) );
				restartProxy.put(RestartType.leafc_transfer, Double.toString( 0.156916926833324));

//				restartProxy.put(RestartType.frootc_transfer, Double.toString( 0.134118071408929) );
				restartProxy.put(RestartType.frootc_transfer, Double.toString( 0.156916926833324));
				
				restartProxy.put(RestartType.livestemc, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemc))*deforestToGrass) );
				restartProxy.put(RestartType.livestemc_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemc_storage))*deforestToGrass) );
				restartProxy.put(RestartType.livestemc_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemc_transfer))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemc, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemc))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemc_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemc_storage))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemc_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemc_transfer))*deforestToGrass) );

				restartProxy.put(RestartType.livecrootc, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootc))*deforestToGrass) );
				restartProxy.put(RestartType.livecrootc_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootc_storage))*deforestToGrass) );
				restartProxy.put(RestartType.livecrootc_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootc_transfer))*deforestToGrass) );
				restartProxy.put(RestartType.deadcrootc, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootc))*deforestToGrass) );
				restartProxy.put(RestartType.deadcrootc_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootc_storage))*deforestToGrass) );
				restartProxy.put(RestartType.deadcrootc_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootc_transfer))*deforestToGrass) );

//				restartProxy.put(RestartType.gresp_transfer, Double.toString( 5.57E-014) );
//				restartProxy.put(RestartType.gresp_transfer, Double.toString( 0.0000297826360711512) );
				
				restartProxy.put(RestartType.cwdc, Double.toString( Double.valueOf(restartProxy.get(RestartType.cwdc))*deforestToGrass) );
				
				restartProxy.put(RestartType.litr1c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr1c))*deforestToGrass)); //0.131633369794947) );
				restartProxy.put(RestartType.litr2c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr2c))*deforestToGrass)); //0.198120968127623) );
				restartProxy.put(RestartType.litr3c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr3c))*deforestToGrass)); //0.0) );
				restartProxy.put(RestartType.litr4c, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr4c))*deforestToGrass)); //0.2142082590868) );
				restartProxy.put(RestartType.soil1c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil1c))*deforestToGrass)); //0.0514049775844082) );
				restartProxy.put(RestartType.soil2c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil2c))*ag1)); //0.288215274432622) );
				restartProxy.put(RestartType.soil3c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil3c))*ag1)); //2.51057747973442) );
				//restartProxy.put(RestartType.soil4c, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil4c))*0.1)); //15.618348311204) );
				//restartProxy.put(RestartType.cpool, Double.toString( Double.valueOf(restartProxy.get(RestartType.cpool))*0.1)); //-4.24E-005) );
				
//				restartProxy.put(RestartType.leafn_transfer, Double.toString( 0.00670590357044643) );
				restartProxy.put(RestartType.leafn_transfer, Double.toString( 0.00784584634166619) );
				
//				restartProxy.put(RestartType.frootn_transfer, Double.toString( 0.00268236142817858) );				
				restartProxy.put(RestartType.frootn_transfer, Double.toString( 0.00313833853666647));

				restartProxy.put(RestartType.livestemn, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemn))*deforestToGrass) );
				restartProxy.put(RestartType.livestemn_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemn_storage))*deforestToGrass) );
				restartProxy.put(RestartType.livestemn_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.livestemn_transfer))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemn, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemn))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemn_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemn_storage))*deforestToGrass) );
				restartProxy.put(RestartType.deadstemn_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadstemn_transfer))*deforestToGrass) );
// this next block was commented out from here
				restartProxy.put(RestartType.livecrootn, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootn))*0) );
				restartProxy.put(RestartType.livecrootn_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootn_storage))*0) );
				restartProxy.put(RestartType.livecrootn_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.livecrootn_transfer))*0) );
				restartProxy.put(RestartType.deadcrootn, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootn))*0) );
				restartProxy.put(RestartType.deadcrootn_storage, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootn_storage))*0) );
				restartProxy.put(RestartType.deadcrootn_transfer, Double.toString( Double.valueOf(restartProxy.get(RestartType.deadcrootn_transfer))*0) );
// to here.				
				restartProxy.put(RestartType.cwdn, Double.toString( Double.valueOf(restartProxy.get(RestartType.cwdn))*deforestToGrass) );
				
				restartProxy.put(RestartType.litr1n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr1n))*deforestToGrass)); 
				restartProxy.put(RestartType.litr2n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr2n))*deforestToGrass)); 
				restartProxy.put(RestartType.litr3n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr3n))*deforestToGrass)); 
				restartProxy.put(RestartType.litr4n, Double.toString( Double.valueOf(restartProxy.get(RestartType.litr4n))*deforestToGrass)); 
				restartProxy.put(RestartType.soil1n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil1n))*deforestToGrass));
				
				restartProxy.put(RestartType.soil2n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil2n))*ag2));
				restartProxy.put(RestartType.soil3n, Double.toString( Double.valueOf(restartProxy.get(RestartType.soil3n))*ag2));
				//restartProxy.put(RestartType.soil4n, Double.toString( 1.56183483112033) );
				//restartProxy.put(RestartType.sminn, Double.toString( 4.68E-005) );
				//restartProxy.put(RestartType.retransn, Double.toString( 0.00887680441985711) );
				//restartProxy.put(RestartType.npool, Double.toString( 1.56E-018) );
				
				
				restartProxy.put(RestartType.day_leafc_litfall_increment, Double.toString( Double.valueOf(restartProxy.get(RestartType.day_leafc_litfall_increment))*0) );
				restartProxy.put(RestartType.day_frootc_litfall_increment, Double.toString( Double.valueOf(restartProxy.get(RestartType.day_frootc_litfall_increment))*0) );

				restartProxy.put(RestartType.day_livestemc_turnover_increment, Double.toString( Double.valueOf(restartProxy.get(RestartType.day_livestemc_turnover_increment))*0) );
				restartProxy.put(RestartType.day_livecrootc_turnover_increment, Double.toString( Double.valueOf(restartProxy.get(RestartType.day_livecrootc_turnover_increment))*0) );

//				restartProxy.put(RestartType.annmax_leafc, Double.toString( 0.288153671613662) );
//				restartProxy.put(RestartType.annmax_frootc, Double.toString( 0.288153671613662) );

				restartProxy.put(RestartType.annmax_leafc, Double.toString( 0.339542477497731) );
				restartProxy.put(RestartType.annmax_frootc, Double.toString( 0.339542477497731) );

				restartProxy.put(RestartType.annmax_livecrootc, Double.toString( Double.valueOf(restartProxy.get(RestartType.annmax_livecrootc))*0) );
				restartProxy.put(RestartType.annmax_livestemc, Double.toString( Double.valueOf(restartProxy.get(RestartType.annmax_livestemc))*0) );
			
				
				
			}
			//restartProxy.put(RestartType.metyr, "1.0");
			
			System.out.println("------------------------------------");
			System.out.println("Restart File After Modifications");
			System.out.println("------------------------------------");
			restartProxy.print();
			
			restartProxy.writeOut(fileHandler);
			
			// Water vars
			/*
			restartProxy.put(RestartType.soilw, "470.4740338");
			
			restartProxy.put(RestartType.snoww, "0.0");
			restartProxy.put(RestartType.canopyw, "0.0");
			
			// Carbon state vars
			restartProxy.put(RestartType.cpool, "-0.047314873");
			
			restartProxy.put(RestartType.leafc, "0.0");
			restartProxy.put(RestartType.leafc_storage, "0.0");
			//CUPRIT: Derek: restartProxy.put(RestartType.leafc_transfer, "0.052760753");
			restartProxy.put(RestartType.leafc_transfer, "0.004368578617641216");
			
			restartProxy.put(RestartType.frootc, "0.0");
			restartProxy.put(RestartType.frootc_storage, "0.0");
			
			restartProxy.put(RestartType.frootc_transfer, "0.063312903");
			//restartProxy.put(RestartType.frootc_transfer, "0.004368578617641216");
			
			
			restartProxy.put(RestartType.livestemc, "0.000532024");
			restartProxy.put(RestartType.livestemc_storage, "0.0");
			restartProxy.put(RestartType.livestemc_transfer, "0.018571785");
			restartProxy.put(RestartType.deadstemc, "0.37851619");
			restartProxy.put(RestartType.deadstemc_storage, "0.0");
			restartProxy.put(RestartType.deadstemc_transfer, "0.097501871");
		
			restartProxy.put(RestartType.livecrootc, "0.000117045");
			restartProxy.put(RestartType.livecrootc_storage, "0.0");
			restartProxy.put(RestartType.livecrootc_transfer, "0.004085793");
			restartProxy.put(RestartType.deadcrootc, "8.327356172");
			restartProxy.put(RestartType.deadcrootc_storage, "0.0");
			restartProxy.put(RestartType.deadcrootc_transfer, "0.021450412");
	
			restartProxy.put(RestartType.gresp_storage, "0.0");
			
			restartProxy.put(RestartType.gresp_transfer, "0.0000000448105");
			//restartProxy.put(RestartType.gresp_transfer, "0.0010073907372656823");
			
			restartProxy.put(RestartType.cwdc, "0.051609871");
			//restartProxy.put(RestartType.cwdc, "0.04370015897158241");
			
			restartProxy.put(RestartType.litr1c, "0.000355618");
			//restartProxy.put(RestartType.litr1c, "0.0");
			
			restartProxy.put(RestartType.litr2c, "0.007479337050648941");
			//restartProxy.put(RestartType.litr2c, "0.007479337050648941");
			
			restartProxy.put(RestartType.litr3c, "0.000264177");
			//restartProxy.put(RestartType.litr3c, "0.0034398204065430042");
			
			restartProxy.put(RestartType.litr4c, "0.002928384");
			//restartProxy.put(RestartType.litr4c, "0.011143693298333495");
			
			restartProxy.put(RestartType.soil1c, "0.000320822");
			//restartProxy.put(RestartType.soil1c, "0.0023497277763247742");
			
			restartProxy.put(RestartType.soil2c, "0.156220974");
			//restartProxy.put(RestartType.soil2c, "0.006583400135064985");
			
			restartProxy.put(RestartType.soil3c, "1.641367902");
			//restartProxy.put(RestartType.soil3c, "0.05763727333088332");
			
			restartProxy.put(RestartType.soil4c, "14.72711897");
			//restartProxy.put(RestartType.soil4c, "0.06445843498438569");
			
			// Nitrogen state vars
			restartProxy.put(RestartType.npool, "0.0");
			
			restartProxy.put(RestartType.leafn, "0.0");
			restartProxy.put(RestartType.leafn_storage, "0.0");
			
			restartProxy.put(RestartType.leafn_transfer, "0.00211043");
			//restartProxy.put(RestartType.leafn_transfer, "0.000182024109");
			
			restartProxy.put(RestartType.frootn, "0.0");
			restartProxy.put(RestartType.frootn_storage, "0.0");
			
			restartProxy.put(RestartType.frootn_transfer, "0.001319019");
			//restartProxy.put(RestartType.frootn_transfer, "0.0001040137770");
			
			restartProxy.put(RestartType.livestemn, "0.0000110838");
			//restartProxy.put(RestartType.livestemn, " 0.00004802521385901547");
			
			restartProxy.put(RestartType.livestemn_storage, "0.0");
			
			restartProxy.put(RestartType.livestemn_transfer, "0.000386912");
			//restartProxy.put(RestartType.livestemn_transfer, "0.000019221745917621375");
			
			restartProxy.put(RestartType.deadstemn, "0.000688211");
			//restartProxy.put(RestartType.deadstemn, "0.001362129746334716");
			
			restartProxy.put(RestartType.deadstemn_storage, "0.0");
			
			restartProxy.put(RestartType.deadstemn_transfer, "0.000177276");
			//restartProxy.put(RestartType.deadstemn_transfer, "0.000019569650821107735");
			
			restartProxy.put(RestartType.livecrootn, "0.000243844");
			//restartProxy.put(RestartType.livecrootn, "0.00001104579918757317");
			
			restartProxy.put(RestartType.livecrootn_storage, "0.0");
			
			restartProxy.put(RestartType.livecrootn_transfer, "0.0000851207");
			//restartProxy.put(RestartType.livecrootn_transfer, "0.000004421001561052919");
			
			restartProxy.put(RestartType.deadcrootn, "0.015140648");
			//restartProxy.put(RestartType.deadcrootn, "0.0003132898416569916");
			
			restartProxy.put(RestartType.deadcrootn_storage, "0.0");
			
			restartProxy.put(RestartType.deadcrootn_transfer, "0.0000390007");
			//restartProxy.put(RestartType.deadcrootn_transfer, "0.00000450101968885477");
			
			restartProxy.put(RestartType.cwdn, "0.051609871");
			//restartProxy.put(RestartType.cwdn,"0.0000988691379447563");
			
			restartProxy.put(RestartType.litr1n, "0.00000703047");
			//restartProxy.put(RestartType.litr1n, "0.000011278649222480188");
			
			restartProxy.put(RestartType.litr2n, "0.0000243716");
			//restartProxy.put(RestartType.litr2n, "0.00013970436037927925");
			
			restartProxy.put(RestartType.litr3n, "0.00000550369");
			//restartProxy.put(RestartType.litr3n, "0.00008190048587007128");
			
			restartProxy.put(RestartType.litr4n, "0.0000302535");
			//restartProxy.put(RestartType.litr4n, "0.000217055860610938");
			
			restartProxy.put(RestartType.soil1n, "0.0000267352");
			//restartProxy.put(RestartType.soil1n, "0.0001958106480270645");
			
			restartProxy.put(RestartType.soil2n, "0.015808075");
			//restartProxy.put(RestartType.soil2n, "0.0005486166779220833");
			
			restartProxy.put(RestartType.soil3n, "0.199308959");
			//restartProxy.put(RestartType.soil3n, "0.005763727333088302");
			 
			restartProxy.put(RestartType.soil4n, "1.472711897");
			//restartProxy.put(RestartType.soil4n, " 0.006445843498438554");
			
			restartProxy.put(RestartType.sminn, "0.0000402748");
			//restartProxy.put(RestartType.sminn, "0.0000025126379346008852");
			
			restartProxy.put(RestartType.retransn, "0.010798681");
			//restartProxy.put(RestartType.retransn, "0.0005832277213151879");
			
			restartProxy.put(RestartType.day_leafc_litfall_increment, "0.0");
			restartProxy.put(RestartType.day_frootc_litfall_increment, "0.0");
			
			restartProxy.put(RestartType.day_livestemc_turnover_increment, "0.00013202");
			//restartProxy.put(RestartType.day_livestemc_turnover_increment, "0.000005648116196718156");
			
			restartProxy.put(RestartType.day_livecrootc_turnover_increment, "0.0000290443");
			//restartProxy.put(RestartType.day_livecrootc_turnover_increment, "0.0000012990667252451833");
			
			restartProxy.put(RestartType.annmax_leafc, "0.172526795");
			//restartProxy.put(RestartType.annmax_leafc, "0.009173072484404257");
			
			restartProxy.put(RestartType.annmax_frootc, "0.207032154");
			//restartProxy.put(RestartType.annmax_frootc, "0.009173072484404257");
			
			restartProxy.put(RestartType.annmax_livestemc, "0.061194022");
			//restartProxy.put(RestartType.annmax_livestemc, "0.0027832493396828845");
			
			restartProxy.put(RestartType.annmax_livecrootc, "0.013462685");
			//restartProxy.put(RestartType.annmax_livecrootc, "0.0006401473481270698");
			
			restartProxy.put(RestartType.dsr, "0.0");
			restartProxy.put(RestartType.metyr, "1.0");
			*/
			
			//restartProxy.writeOut(fileHandler);
			
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
