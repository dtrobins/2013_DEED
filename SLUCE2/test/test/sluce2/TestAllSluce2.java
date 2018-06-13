package test.sluce2;

import test.sluce2.landscape.TestCell;
import test.sluce2.landscape.TestLandscapeContext;
import test.sluce2.landscape.TestLandscapeFactory;
import test.sluce2.landscape.TestRasterRepository;
import test.sluce2.management.TestTemplateManager;
import junit.framework.TestSuite;

/**
 * Runs a series of JUnit tests to ensure the code runs as expected.
 * 
 * @author Meghan Hutchins
 * @date 11/01/2010
 */
public class TestAllSluce2 extends TestSuite
{
    
    public static TestSuite suite()
    {
	TestSuite suite = new TestSuite();
	
	/* Biome-BGC */
	//suite.addTestSuite(TestBiomeBGC.class);
	
	/* Raster Access and Landscape */
	suite.addTestSuite(TestRasterRepository.class);
	suite.addTestSuite(TestLandscapeContext.class);
	suite.addTestSuite(TestTemplateManager.class);
	suite.addTestSuite(TestCell.class);
	suite.addTestSuite(TestLandscapeFactory.class);
	
	return suite;
    }
 
}