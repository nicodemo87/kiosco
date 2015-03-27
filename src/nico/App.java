package nico;

import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Employee emp = new Employee();
        
        
        emp.setFirstName("Carlos");
        emp.setLastName("Alonso");
        emp.setSalary(1000);
        
       session.save(emp);
       
        
        
        //session.delete(stock);
        
        session.getTransaction().commit();
        
        
    }
}
