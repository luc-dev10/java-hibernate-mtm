package com.lucio;

import com.lucio.entity.Course;
import com.lucio.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourseApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.openSession()) {

            session.beginTransaction();

            // create course
            Course englishCourse = new Course();
            englishCourse.setTitle("English Course");

            // save course
            session.save(englishCourse);

            // student
            int id = 1;
            Student firstStudent = session.get(Student.class, id);

            // add course for student
            firstStudent.addCourse(englishCourse);

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
