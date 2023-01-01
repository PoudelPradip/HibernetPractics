package com.hibernate.annotation.execution;

import com.hibernate.annotation.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ExecutionXmlConfigurationBasedHibernate {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //Create Session Factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //adding/inserting new Students
        addStudents(sessionFactory);

        //fetching Student Record
        fetchStudentInfo(sessionFactory);

        //delete Student Record
        deleteStudentInfo(sessionFactory);

        //update Student Record
        updateStudentRecord(sessionFactory);
    }

    public static void addStudents(SessionFactory sessionFactory) {
        //Initialize the Session
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            Student student = new Student("Indra", "P");
            Student student1 = new Student("Satyam", "S");
            //starting the transaction
            tx = session.beginTransaction();
            session.save(student);
            session.save(student1);
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error Message ::" +e.getMessage());
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        Session session1 = sessionFactory.openSession();
        try{
            Student student2 = new Student("Bijay", "B");
            //starting the transaction
            tx = session1.beginTransaction();
            session1.save(student2);
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error Message ::" +e.getMessage());
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            if (session1 != null) {
                session1.close();
            }
        }
    }

    public static void fetchStudentInfo(SessionFactory sessionFactory) {
        //Initialize the Session
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            //starting the transaction
            tx = session.beginTransaction();
            Student student = session.get(Student.class, 3);
            System.out.println("Fetched Student Name ::" + student.getFirstName() + " " + student.getLastName());
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error Message ::" +e.getMessage());
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void deleteStudentInfo(SessionFactory sessionFactory) {
        //Initialize the Session
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            //starting the transaction
            tx = session.beginTransaction();
            Student student = session.get(Student.class, 2);
            session.delete(student);
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error Message ::" +e.getMessage());
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void updateStudentRecord(SessionFactory sessionFactory) {
        //Initialize the Session
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            //starting the transaction
            tx = session.beginTransaction();
            Student student = session.get(Student.class, 4);
            System.out.println("Fetched Student Name ::" + student.getFirstName() + " " + student.getLastName());
            student.setFirstName("Indra K.");
            student.setLastName("P. L");
            session.update(student);
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error Message ::" +e.getMessage());
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}