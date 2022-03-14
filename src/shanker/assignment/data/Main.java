package shanker.assignment.data;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {


    public static void createOperation(SessionFactory factory){
        // Create:
        Session session = factory.getCurrentSession();
        Employee emp1 = new Employee("Shanker","Sai","Zemoso");
        Employee emp2 = new Employee("Kalyan","Ch","TCS");
        Employee emp3 = new Employee("Dharnee","R","Accenture");
        Employee emp4 = new Employee("Rahul","G","TCS");

        //Start the transaction
        session.beginTransaction();

        //Save the student object
        session.save(emp1);
        session.save(emp2);
        session.save(emp3);
        session.save(emp4);



        //commit the transaction:
        session.getTransaction().commit();


    }

    public  static  void readOperation(SessionFactory factory ){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Employee tempEmp = session.get(Employee.class,3);
        System.out.println(tempEmp.toString());

        session.getTransaction().commit();
    }

    public  static  void queryOperation(SessionFactory factory){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Employee> emps = session.createQuery("from Employee").getResultList();

        for(Employee emp : emps ){
            System.out.println(emp.toString());
        }
        session.getTransaction().commit();
    }

    public static void updateQuery(SessionFactory factory){
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("update Employee e set lastName='rami' where id=3").executeUpdate();

        session.getTransaction().commit();




    }

    public static void deleteOperation(SessionFactory factory){
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from Employee e where e.id=2").executeUpdate();

        session.getTransaction().commit();
    }

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try{
            //Create
            //Main.createOperation(factory);

            // Read::
            Main.readOperation(factory);
            Main.queryOperation(factory);

            //Update:
           // Main.updateQuery(factory);

            //Delete:
            Main.deleteOperation(factory);



        }catch (Exception ex){
            ex.printStackTrace();
        }












    }
}
