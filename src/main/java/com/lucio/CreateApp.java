package com.lucio;

import com.lucio.entity.Course;
import com.lucio.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateApp {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.openSession()) {

            session.beginTransaction();

            // create course
            Course mathCourse = new Course();
            mathCourse.setTitle("Math Course");

            // save course
            session.save(mathCourse);

            // student
            Student student1 = new Student();
            student1.setEmail("zhao@email");
            student1.setFirstName("lucio");
            student1.setLastName("zhao");

            Student student2 = new Student();
            student2.setEmail("doe@email");
            student2.setFirstName("john");
            student2.setLastName("doe");

            // add student for both courses
            mathCourse.addStudent(student1);
            mathCourse.addStudent(student2);

            // save student
            session.save(student1);
            session.save(student2);

            session.getTransaction().commit();

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // close factory
        factory.close();

    }

}
