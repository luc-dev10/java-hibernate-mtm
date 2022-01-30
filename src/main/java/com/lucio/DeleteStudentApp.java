package com.lucio;

import com.lucio.entity.Course;
import com.lucio.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentApp {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.openSession()) {

            session.beginTransaction();

            // student
            int id = 3;
            Student student = session.get(Student.class, id);

            session.delete(student);

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // close factory
        factory.close();

    }

}
