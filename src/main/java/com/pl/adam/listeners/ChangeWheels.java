package com.pl.adam.listeners;


public class ChangeWheels implements ProcessCarListener{

	@Override
	public void processCar(CarEvent car) {
		System.out.println("Changing the wheels of "+ car.getCar());
		
	}
	
}
