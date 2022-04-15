package com.ayush.employeeManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ayush.employeeManager.model.Employee;
import com.ayush.employeeManager.util.Utility;


public class EmployeeDao
{
	Session session = Utility.getSession();

	public void saveEmployee(Employee employee)
	{
		session.save(employee);
		Transaction tx = session.beginTransaction();
		tx.commit();
	}

	public void updateEmployee(Employee employee)
	{
		session.update(employee);
		Transaction tx = session.beginTransaction();
		tx.commit();
	}

	public Employee getEmpById(int id)
	{
		return session.load(Employee.class, id);

	}

	public void deleteEmployee(Employee employee)
	{
		session.delete(employee);
		Transaction tx = session.beginTransaction();
		tx.commit();

	}

}
