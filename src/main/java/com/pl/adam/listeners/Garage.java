package com.pl.adam.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.pl.adam.projectfiles.Car;
import com.pl.adam.projectfiles.Person;

@Entity
public class Garage {

	@Id
	@GeneratedValue
	private int id;
	
	private String address;
	@ManyToMany(mappedBy="garages")
	private Collection<Person> persons=new ArrayList<Person>();
	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}

	public List getCarProcesses() {
		return carProcesses;
	}

	public void setCarProcesses(List carProcesses) {
		this.carProcesses = carProcesses;
	}

	@Transient
	private Car car;
	
	@Transient
	private List carProcesses=new ArrayList();
	
	public synchronized void addCarProcessListener( ProcessCarListener l ) {
        carProcesses.add( l );
    }
    
    public synchronized void removeCarProcessListener( ProcessCarListener l ) {
        carProcesses.remove( l );
    }
    
    public synchronized void processCar() {
        CarEvent carEvent=new CarEvent(this, car);
        Iterator listeners = carProcesses.iterator();
        while( listeners.hasNext() ) {
            ( (ProcessCarListener) listeners.next() ).processCar(carEvent);
        }
}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
    
}



