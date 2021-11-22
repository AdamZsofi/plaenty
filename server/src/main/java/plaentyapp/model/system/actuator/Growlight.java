package plaentyapp.model.system.actuator;

import java.time.LocalTime;

/**
 * A smart growlight, which can be turned on and off
 * It won't turn on at nighttime. It can always be turned off.
 * There is no real light added, so it is mocked
 */
public class Growlight implements Controller {
	private boolean isOn = false;
	private final LocalTime sunset;
	private final LocalTime sunrise;

	public Growlight(LocalTime sunset, LocalTime sunrise) {
		this.sunset = sunset;
		this.sunrise = sunrise;
	}

	@Override
	public boolean isActuatorOn() {
		return isOn;
	}

	@Override
	// TODO add logs
	public void turnActuatorOn() {
		LocalTime now = LocalTime.now();
		if(now.isBefore(sunset) && now.isAfter(sunrise)) {
			isOn = true;
		}
	}

	@Override
	// TODO add logs
	public void turnActuatorOff() {
		isOn = false;
	}
}
