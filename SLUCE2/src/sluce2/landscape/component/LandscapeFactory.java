package sluce2.landscape.component;

import sluce2.landscape.RasterRepository;
import sluce2.utility.Coord;

public interface LandscapeFactory
{
    public LandUnit buildLandUnit(RasterRepository repository, LandUnitType type, Number id);
    public LandUnit buildLandUnit(RasterRepository repository, LandUnitType type, Coord loc);
}