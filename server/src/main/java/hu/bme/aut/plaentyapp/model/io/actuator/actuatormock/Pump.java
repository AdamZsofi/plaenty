package hu.bme.aut.plaentyapp.model.io.actuator.actuatormock;

import hu.bme.aut.plaentyapp.model.io.actuator.Actuator;

/**
 * A pump, which can be turned on and off
 * There is no real pump added, so it is mocked
 */
public class Pump implements Actuator {
	private boolean isOn = false;

	@Override
	public boolean isActuatorOn() {
		return isOn;
	}

	@Override
	// TODO add logs
	public void turnActuatorOn() {
		isOn = true;
	}

	@Override
	// TODO add logs
	public void turnActuatorOff() {
		isOn = false;
	}
}
