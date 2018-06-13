package sluce2.ecosystem.biomebgc.proxy;

/**
 * 
 * @author Meghan Hutchins
 * @since 2011.03.07
 */
public enum AnnualOutputType
{
	tsoil(1),
	vpd(2),
	soilw(3),
	snoww(4),
	leafc(5),
	frootc(6),
	livestemc(7),
	deadstemc(8),
	livecrootc(9),
	deadcrootc(10),
	cwdc(11),
	litr1c(12),
	litr2c(13),
	litr3c(14),
	litr4c(14),
	soil1c(16),
	soil2c(17),
	soil3c(18),
	soil4c(19),
	psnsun_src(20),
	psnshade_src(21),
	remdays_curgrowth(22),
	remdays_transfer(23),
	remdays_litfall(24),
	all_lai(25),
	cum_npp(26),
	cum_nep(27),
	cum_nee(28),
	cum_gpp(29),
	cum_mr(30),
	cum_gr(31),
	cum_hr(32),
	vegc(33),
	litrc(34),
	soilc(35),
	totalc(36);
	
	/** Line number of where this value is located in the BiomeBGC AnnualOutput file. **/
	private int lineNbr; 
	
	/**
	 * @param lineNbr of this variable value in the BiomeBGC AnnualOutput file.
	 */
	private AnnualOutputType(int lineNbr)
	{
		this.lineNbr = lineNbr;
	}

	public int getLineNbr()
	{
		return lineNbr;
	}

	public void setLineNbr(int lineNbr)
	{
		this.lineNbr = lineNbr;
	}
}
