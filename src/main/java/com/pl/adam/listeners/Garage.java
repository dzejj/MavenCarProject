package com.pl.adam.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pl.adam.projectfiles.Car;

public class Garage {

	private Car car;
	
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



