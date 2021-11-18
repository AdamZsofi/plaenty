package io.swagger.model.system.actuator;

/**
 * A pump, which can be turned on and off
 * There is no real pump added, so it is mocked
 */
public class Pump implements Controller {
	private boolean isOn = false;

	@Override
	public boolean isActuatorOn() {
		return isOn;
	}

	@Override
	public void turnActuatorOn() {
		isOn = true;
	}

	@Override
	public void turnActuatorOff() {
		isOn = false;
	}
}
