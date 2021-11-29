package hu.bme.aut.plaentyapp.model.io.actuator;

public interface Actuator {
	public boolean isActuatorOn();
	public void turnActuatorOn() throws RuntimeException;
	public void turnActuatorOff() throws RuntimeException;
}