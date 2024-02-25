import java.util.Scanner;

public class RunEmployee {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    System.out.print("Enter Employee Name: ");
    String name = in.nextLine();
    
    System.out.println("\nEmployee Types:\n  F - Full Time\n  P - Part Time");
    System.out.print("Enter Employee Type:");
    String type = in.nextLine();
    if (type.compareToIgnoreCase("F") == 0) {
      FullTimeEmployee employee = new FullTimeEmployee(name);
      
      System.out.print("\nEnter Monthly Salary: ");
      employee.setMonthlySalary(in.nextDouble());
      
      System.out.println(employee);
    } else if(type.compareToIgnoreCase("P") == 0) {
      PartTimeEmployee employee = new PartTimeEmployee(name);
      
      System.out.print("\nEnter Rate Per Hour: ");
      double rate = in.nextDouble();
      
      System.out.print("\nEnter Number of Hours Worked: ");
      employee.setWage(rate, in.nextInt());
      
      System.out.println(employee);
    } else {
      System.out.println("Error: Ivalid Employee Type!");
    }
  }
}

abstract class Employee {
  private String name;
  
  public Employee(String name) {
    setName(name);
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
}

final class FullTimeEmployee extends Employee {
  private double monthlySalary;
  
  public FullTimeEmployee(String name) {
    super(name);
  }
  
  public void setMonthlySalary(double salary) {
    monthlySalary = salary;
  }
  
  public double getMonthlySalary() {
    return monthlySalary;
  }
  
  public String toString() {
    return "\nEmployee Name: " + getName()  + "\nMonthly Salary: " + monthlySalary;
  }
}

final class PartTimeEmployee extends Employee {
  private double ratePerHour;
  private int hoursWorked;
  private double wage;
  
  public PartTimeEmployee(String name) {
    super(name);
  }
  
  public void setWage(double rate, int hours) {
    ratePerHour = rate;
    hoursWorked = hours;
    wage = ratePerHour * hoursWorked;
  }
  
  public double getWage() {
    return wage;
  }
  
  public String toString() {
    return "\nEmployee Name: " + getName()  + "\nWage: " + wage;
  }
}