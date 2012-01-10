package com.pl.adam.projectfiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CascadeType;

import com.pl.adam.listeners.Garage;

@Entity
@Table(name="OSOBY")
public class Person {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	@OneToMany(mappedBy="owner",cascade=javax.persistence.CascadeType.PERSIST)
	private Collection<Car> cars= new ArrayList<Car>();
	@ManyToMany(cascade=javax.persistence.CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Collection<Garage> garages=new ArrayList<Garage>();
	
	public Person()
	{}
	
	
	public Collection<Garage> getGarages() {
		return garages;
	}

	public void setGarages(Collection<Garage> garages) {
		this.garages = garages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Collection<Car> getCars() {
		return cars;
	}

	
	public void setCars(Collection<Car> cars2) {
		this.cars = cars2;
	}
}
