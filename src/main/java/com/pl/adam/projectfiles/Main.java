package com.pl.adam.projectfiles;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pl.adam.listeners.ChangeWheels;
import com.pl.adam.listeners.CleanCar;
import com.pl.adam.listeners.Garage;
import com.pl.adam.listeners.ProcessCarListener;
import com.pl.adam.services.PersonDBManager;


public class Main {

	public static void main(String[] args) {

		
		
		Person p= new Person("Adam");
		
		p.addCar(new Car(CarMarks.BMW,"GDA1235"));
		p.addCar(new Car(CarMarks.Volvo,"GDA38721"));
		p.addCar(new Car(CarMarks.Peugot,"GDA37626"));
				
		p.printCars();
		try {
			p.editCarPrize(p.findCar(CarMarks.BMW), 6);
		} catch (MyException e1) {
			
			e1.printStackTrace();
		}
		p.printCars();
		Car c =new Car(CarMarks.BMW,"GDA1235");
		p.deleteCar(p.findCar(CarMarks.Peugot));
		p.printCars();
		try {
			c.setPrize(-2.0);
		} catch (MyException e) {
		}
		
		System.out.println(p);
		
		Garage garage=new Garage();

		ProcessCarListener clean=new CleanCar();
		ProcessCarListener changeWheels = new ChangeWheels();
		
		garage.addCarProcessListener(clean);
		garage.addCarProcessListener(changeWheels);
		
		garage.setCar(c);
		garage.processCar();
		
		PersonDBManager db= new PersonDBManager();
		db.addPerson(p);
		
		for(Person person: db.getAllPersons())
		{
			System.out.println(person);
		}
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		
		
	}

}
