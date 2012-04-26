package controllers;

import evented.Evented;

public interface Controller extends Evented{

	public abstract void update(long elapsedTime);
}
