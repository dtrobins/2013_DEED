====================================================================
How to run the SLUCE2 model on your machine within the Eclipse IDE
====================================================================
Created: 2010.11.19 - Meghan Hutchins

To run the SLUCE2 Model, simply check out the project from the SVN 
and change the necessary file paths. The paths should be changed to 
something that reflects your own operating system and workspace location. 

Example:

  /home/meghan/workspace/SLUCE2/src/sluce2/resources/landscape/IEMSS-RasterDesc.xml
might be changed to:
  C:\Users\sunsp\workspace\SLUCE2\src\sluce2\resources\landscape\IEMSS-RasterDesc.xml

Change all the paths in a similar fashion in the following files:

1. Open: src/sluce2/resources/sluce2.properties
2. For each location in the sluce2.properties file:
   Goto THAT particular file, and change all the paths in this file to reflect your own system.
   
NB: BIOME_BGC: The Biome-BGC properties file should point to the location where your BiomeBGC application is installed. 
                        For example, my Biome-BGC application is located here: /home/meghan/Applications/Biome-BGC/biomebgc-4.2/   


NB: REPORT_ROOT_DIRECTORY: 	This is where you want the report output files to go. This does not necessarily have to be in the project workspace 
                        	For example, my report files are written here: /home/meghan/ModelOutput/Results/SLUCE2/  

====================================================================
Running JUnit Tests
====================================================================
JUnit tests are a good way to make sure bits of code are still working as you expect,
without going through running the entire model.

JUnit tests are located in the /test src folder. 
Simply right click on a TestXXX.java file, goto Run As and select JUnit Test.

In the panel on the left, you'll be able to see if all the tests in this file passed, and how long each test took to run.  
There should also be output as well. 

* IMPORTANT: Some tests may change the values in the raster files (this should be fixed). 
             Its good to check the raster files these after you run a test. 

====================================================================
Raster Files
====================================================================
All raster files are located in the raster-data folder. 
Within each root folder (e.g. IEMSS) there are a few sub-folders:

current:   holds the raster files for the current landscape in the model.
history:   an archive of saved raster layers (there's a method to archive a layer in the code)
template:  each sub-folder under template holds raster files associated with a particular template. 

 