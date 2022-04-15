package com.ayush.employeeManager;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

import com.ayush.employeeManager.model.Employee;



public class Manager
{

	private static void menu()
	{
		System.out.println("1. Add Employee");
		System.out.println("2. Update Employee");
		System.out.println("3. Delete Employee");
		System.out.println("4. Display Employee Info");
		System.out.println("5. Sort by id");
		System.out.println("6. Get data in range");
		System.out.println("7. Exit");
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		String ch = "";
		do
		{
			EmployeeDao dao = new EmployeeDao();
			
			Employee employee = null;
			
			menu();
			
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter employee firstName, lastName, designation, age");

				employee = new Employee();
				String fname = sc.next();
				String lname = sc.next();
				String designation = sc.next();
				int age = sc.nextInt();
				
				employee.setFirstName(fname);
				employee.setLastName(lname);
				employee.setDesignation(designation);
				employee.setAge(age);
			
				
				dao.saveEmployee(employee);
				
				System.out.println("Employee record successfully added");
				
				break;
			case 2:
				System.out.println("Enter the id for update");
				int id = sc.nextInt();
				employee = dao.getEmpById(id);
				if (employee != null)
				{
					System.out.println("Enter new name");
					String newName = sc.next();
					employee.setFirstName(newName);
					
					int newAge = sc.nextInt();
					System.out.println("Enter new age");
					employee.setAge(newAge);
					dao.updateEmployee(employee);
					
					System.out.println("Employee record updated successfully");
				} else
				{
					System.out.println("Employee is not exist with id = " + id);
				}
				break;
			case 3:
				System.out.println("Enter the id for delete");
				employee = dao.getEmpById(sc.nextInt());
				dao.deleteEmployee(employee);
				System.out.println("Empolyee record deleted successfully");
				break;
			case 4:
				Query query = dao.session.createQuery("FROM Employee",Employee.class);
		        List<Employee> list = query.getResultList();
		        list.forEach((e) -> System.out.println(e));
//		        for(Employee e:list)
//		            System.out.println(e);
				break;
			case 5:
				System.out.println("Enter the id for select");
				employee = dao.getEmpById(sc.nextInt());
				System.out.println(employee.getId());
				System.out.println(employee.getAge());
				break;
			
			case 6:
				System.out.println("Enter the start and end values of ids");
				int start = sc.nextInt(), end = sc.nextInt();
				Query query1 = dao.session.createQuery("FROM Employee e WHERE e.id BETWEEN :start AND :end")
                .setParameter("start", start)
                .setParameter("end", end);
		        displayList(query1.getResultList());
		        break;
			default:
				break;
			}
			System.out.println("Do you want to continue?(Y/N)");
			
			ch = sc.next();

		} while ("Y".equalsIgnoreCase(ch));
		
		sc.close();
	}
	
	 private static void displayList(List<Employee> list)
	    {
	        for(Employee e:list)
	            System.out.println(e);
	    }
}
