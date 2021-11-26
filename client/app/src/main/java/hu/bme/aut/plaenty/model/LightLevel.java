package hu.bme.aut.plaenty.model;

/**
 * 	Light values converted from lux to three levels
 * 	typical lux values:
 *  30 000 - 100 000 - direct sunlight - HIGH level
 *  10 000 lux - ambient daylight - NORMAL level
 * 	1000 lux - overcast daylight - LOW level below this
 * 	400 lux - sunset/sunrise
 * 	0.01 - 1 lux - night (moonlight)
 */
public enum LightLevel {
	LOW(0,0), NORMAL(1000,1), HIGH(30000,2);

	public final double minValue;
	public final int index;

	private LightLevel(double minValue, int index) {
		this.minValue = minValue;
		this.index = index;
	}
}
