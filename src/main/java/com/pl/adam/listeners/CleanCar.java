package com.pl.adam.listeners;

public class CleanCar implements ProcessCarListener{

	@Override
	public void processCar(CarEvent car) {
		System.out.println("Cleaning the "+ car.getCar());
		
	}
	
}
