package com.pl.adam.projectfiles;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	private int id;
	
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
	private CarMarks mark;
	
	private String register_number;
	
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
		this.mark=mark;
		this.register_number=reg_number;
	}
	
	public Car(CarMarks mark,double prize)
	{
		this.mark=mark;
		this.prize=prize;
	}
	
	public CarMarks getMark()
	{
		return this.mark;
	}
	
	public void setMark(CarMarks mark)
	{
		this.mark=mark;
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
