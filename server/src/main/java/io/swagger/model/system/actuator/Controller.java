package io.swagger.model.system.actuator;

public interface Controller {
	public boolean isActuatorOn();
	public void turnActuatorOn();
	public void turnActuatorOff();
}