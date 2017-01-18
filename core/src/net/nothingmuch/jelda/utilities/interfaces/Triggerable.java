package net.nothingmuch.jelda.utilities.interfaces;

/**
 * Base triggerable interface, intended primarily for use with sensors
 */
public interface Triggerable {
	
	void trigger();
	void untrigger();
	void activate();
	void deactivate();
	boolean isActive();
}
