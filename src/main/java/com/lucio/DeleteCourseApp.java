package com.lucio;

import com.lucio.entity.Course;
import com.lucio.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteCourseApp {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.openSession()) {

            session.beginTransaction();

            // student
            int id = 1;
            Student firstStudent = session.get(Student.class, id);

            // get course for student
            List<Course> courseList = firstStudent.getCourseList();

            // delete all courses
            courseList.forEach(session::delete);

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
