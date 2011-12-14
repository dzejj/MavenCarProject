package com.pl.adam.listeners;

import java.util.EventObject;

import com.pl.adam.projectfiles.Car;

public class CarEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private Car _car;
	
	public Car getCar() {
		return _car;
	}

	public CarEvent(Object source, Car car) {
		super(source);
		_car=car;
	}

}
