package sluce2.ecosystem.biomebgc.proxy;

/**
 * Defines a subset of variables found in the BiomeBGC Initialization (.ini) file.<p> 
 * This file is the entry point into each run of BiomeBGC. 
 * This ABM will only need to access a subset of these variables.
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 02.14.2011
 */
public enum InitType
{
	title(1),
	MET_INPUT(3),
	meteorologyInputFileLoc(4),
	meteorologyHeaderLines(5),
	RESTART(7),
	doReadRestartFile(8),
	doWriteRestartFile(9),
	doUseRestartMeteorologicalYear(10),
	inputRestartFileLoc(11),
	outputRestartFileLoc(12),
	TIME_DEFINE(14),
	nbrOfmeteorologicalYears(15),
	nbrOfSimYears(16),
	startYear(17),
	doSpinupSim(18),
	maxNbrSpinupYears(19),
	CLIM_CHANGE(21),
	offsetTMax(22),
	offsetTMin(23),
	prcpMultiplier(24),
	vpdMultiplier(25),
	shortWaveRadiationMultiplier(26),
	CO2_CONTROL(28),
	co2CtrlType(29),
	co2ConstantConcentration(30),
	co2FileLoc(31),
	SITE(33),
	soilDepth(34),
	sandPrct(35),
	siltPrct(36),
	clayPrct(37),
	elevation(38),
	latitudeDeg(39),
	shortWaveAlbedo(40),
	nitrogenAtmDeposition(41),
	nitrogenFixation(42),
	RAMP_NDEP(44),
	doRampedNitrogenDepoRun(45),
	refYearIndustNitrogenDepo(46),
	industNitrogenDepoValue(47),
	EPC_FILE(49),
	epcFileLoc(50),
	W_STATE(52),
	waterInSnow(53),
	initSoilWaterPropOfSat(54),
	C_STATE(56),
	maxLeafCFirstYear(57), 
	maxStemCFirstYear(58),
	coarseWoodyDebrisC(59),
	litterCLabilePool(60),
	litterCUnshieldCellPool(61),
	litterCShieldCellPool(62),
	litterCLigninPool(63),
	soilCFastMicrobRecyclPool(64),
	soilCMedMicroRecyclPool(65),
	soilCSloMicrobRecyclPool(66),
	soilCrecalSOM(67),
	N_STATE(69),
	litterNLiabilePool(70),
	soilNMineralPool(71),
	OUTPUT_CONTROL(73),
	outputFilePrefix(74),
	doWriteDailyOutput(75),
	doOutputMonthlyAvg(76),
	doOutputAnnualDailyVars(77),
	doWriteAnnualOutput(78),
	doWriteOutputToConsole(79),
	DAILY_OUTPUT(81),
	nbrOfDailyOutputVars(82),
	wssoilw(83),
	wssnoww(84),
	wfcanopyw_evap(85),
	wfsnoww_subl(86),
	wfsoilw_evap(87),
	wfsoilw_trans(88),
	wfsoilw_outflow(89),
	cscwdc(90),
	epvproj_lai(91),
	epvdaily_net_nmin(92),
	summarydaily_npp(93),
	summarydaily_nep(94),
	summarydaily_nee(95),
	summarydaily_gpp(96),
	summarydaily_mr(97),
	summarydaily_gr(98),
	summarydaily_hr(99),
	summarydaily_fire(100),
	summaryvegc(101),
	summarylitrc(102),
	summarysoilc(103),
	summarytotalc(104),
	psn_sunA(105),
	ANNUAL_OUTPUT(107),
	nbrOfAnnualOutputVars(108),
	tsoil(109),
	vpd(110),
	soilw(111),
	snoww(112),
	leafc(113),
	frootc(114),
	livestemc(115),
	deadstemc(116),
	livecrootc(117),
	deadcrootc(118),
	cwdc(119),
	litr1c(120),
	litr2c(121),
	litr3c(122),
	litr4c(123),
	soil1c(124),
	soil2c(125),
	soil3c(126),
	soil4c(127),
	psnsun_src(128),
	psnshade_src(129),
	remdays_curgrowth(130),
	remdays_transfer(131),
	remdays_litfall(132),
	all_lai(133),
	cum_npp(134),
	cum_nep(135),
	cum_nee(136),
	cum_gpp(137),
	cum_mr(138),
	cum_gr(139),
	cum_hr(140),
	vegc(141),
	litrc(142),
	soilc(143),
	totalc(144),
	END_INIT(146);

	/** Line number of where this value is located in the BiomeBGC Initialization (.ini) file. **/
	private int lineNbr; 
	
	/**
	 * @param lineNbr of this variable value in the BiomeBGC Initialization (.ini) file.
	 */
	private InitType(int lineNbr)
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

