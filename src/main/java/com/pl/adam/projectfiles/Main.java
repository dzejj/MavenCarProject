package com.pl.adam.projectfiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		Vehicle v=new Vehicle();
		v.setName("Car");
		
		Collection<Car> cars=new ArrayList<Car>();
		
		
		cars.add(new Car(CarMarks.BMW,"GDA1235"));
		cars.add(new Car(CarMarks.Volvo,"GDA38721"));
		cars.add(new Car(CarMarks.Peugot,"GDA37626"));
		p.setCars(cars);
		
		for(Car car : cars)
		{
			car.setOwner(p);
		}
		
		p.printCars();
		
		p.printCars();
		Car c =new Car(CarMarks.BMW,"GDA1235");
		p.deleteCar(p.findCar(CarMarks.Peugot));
		p.printCars();
		try {
			c.setPrize(-2.0);
		} catch (MyException e) {
		}
		
		System.out.println(p);
		
		Person p2 = new Person("Łukasz");
		
		Garage garage=new Garage();
		garage.setAddress("Adres1");
		
		Garage garage2=new Garage();
		garage2.setAddress("Adres2");
		p2.getGarages().add(garage2);
		garage2.getPersons().add(p2);
		
		p.getGarages().add(garage);
		p.getGarages().add(garage2);
		garage.getPersons().add(p);
		garage2.getPersons().add(p);
	
		
		ProcessCarListener clean=new CleanCar();
		ProcessCarListener changeWheels = new ChangeWheels();
		
		garage.addCarProcessListener(clean);
		garage.addCarProcessListener(changeWheels);
		
		garage.setCar(c);
		garage.processCar();
		
		List<Person> owners= new ArrayList<Person>();
		
		
		c.setVehicle(v);
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		/*session.save(p);
		for(Car car :cars)
		{
			session.save(car);
		}*/
		session.persist(p);
		
		session.save(p2);
		//session.save(garage2);
		//session.save(garage);
		session.get(Person.class, 1000);
		session.getTransaction().commit();
		int i=1;
		do
			{
			owners.add((Person)session.get(Person.class,i));
			i++;
			}
		while(session.get(Person.class,i)!=null);
		
		session.close();
		System.out.println(owners.size());
		for(Person person:owners)
		{
			System.out.println(person.getGarages().size());
		}
		
	}

}
