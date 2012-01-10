package com.pl.adam.projectfiles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
public class Car {

	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	@JoinColumn(name="VEHICLE_ID")
	private Vehicle vehicle;
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Person owner;
	private String mark;
	
	private String register_number;
	
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegister_number() {
		return register_number;
	}

	public void setRegister_number(String register_number) {
		this.register_number = register_number;
	}
	private double prize;
	
	public double getPrize() {
		return prize;
	}
	
	public void setPrize(double prize) throws MyException
	{
		if(prize < 0)
			throw new MyException("prize can not be less than zero");
		else
			this.prize=prize;
	}
	
	public String toString()
	{
		return this.mark + " "+this.register_number;
	}
	
	public void printCar()
	{
		System.out.println("Mark: "+mark+"\nRegister Number: "+ register_number+"\nPrize: "+prize);
	}
	
	
	public Car(CarMarks mark, String reg_number)
	{
		this.mark=mark.toString();
		this.register_number=reg_number;
	}
	
	public Car(CarMarks mark,double prize)
	{
		this.mark=mark.toString();
		this.prize=prize;
	}
	
	public String _getMark()
	{
		return this.mark;
	}
	public String getMark()
	{
		return this.mark;
	}
	
	public void setMark(CarMarks mark)
	{
		this.mark=mark.toString();
	}
	
	public String getRegNumber()
	{
		return this.register_number;
	}
	public void setRegNumber(String reg_number)
	{
		this.register_number=reg_number;
	}

}
