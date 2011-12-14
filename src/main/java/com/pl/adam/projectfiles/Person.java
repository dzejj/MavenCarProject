package com.pl.adam.projectfiles;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String name;

	private List<Car> cars= new ArrayList<Car>();
	
	public String toString()
	{
		return name + " has " + cars.size()+" cars.";
	}
	
	public void addCar(Car c)
	{
		cars.add(c);
		}
	
	public void printCars()
	{
		for(Car c: cars)
		{
			c.printCar();
		}
	}
	
	public Person(String name) {
		super();
		this.name = name;
		this.cars = new ArrayList<Car>();
	}
	
	
	
	public void deleteCar(Car car) {
		cars.remove(car);
	}



	public void editCarPrize(Car car, double prize) throws MyException {
		car.setPrize(prize);
	}

	public Car findCar(CarMarks mark) {
		for (Car car : cars) 
		{
			if (car.getMark().equals(mark)) {
				return car;
			}
		}
		return null;
	}
	
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
