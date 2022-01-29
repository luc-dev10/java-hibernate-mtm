package com.lucio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(null)
                .addAnnotatedClass(null)
                .buildSessionFactory();

        try (factory; Session session = factory.openSession()) {

            // // Instructor ID = 8
            // int id = 8;
            //
            // session.beginTransaction();
            //
            // // instructor
            // Instructor eagerInstructor = session.get(Instructor.class, id);
            // System.out.println(eagerInstructor.getEmail());
            //
            // // get all courses
            // eagerInstructor.getCourse().forEach(course -> {
            //     System.out.println(course.getTitle());
            // });

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // close factory
        factory.close();

    }

}
