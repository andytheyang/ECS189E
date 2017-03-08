import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andy on 3/5/17.
 */
public class TestInstructor {
    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
        this.admin.createClass("Test Class", 2017, "Professor Test", 15);
    }

    @Test
    public void testHomework() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        assertTrue(this.instructor.homeworkExists("Test Class", 2017, "homework1"));
    }

    @Test
    public void testHomeworkCreateNoProfessor() {
        this.instructor.addHomework("Not Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        assertFalse(this.instructor.homeworkExists("Test Class", 2017, "homework1"));
    }

    @Test
    public void testHomeworkCreateWrongClass() {
        this.instructor.addHomework("Professor Test", "Not Test Class", 2017, "homework1", "super hard homework");
        assertFalse(this.instructor.homeworkExists("Not Test Class", 2017, "homework1"));
    }

    @Test
    public void testHomeworkWrongHomework() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        assertFalse(this.instructor.homeworkExists("Test Class", 2017, "homework2"));
    }

    @Test
    public void testHomeworkWrongYear() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        assertFalse(this.instructor.homeworkExists("Test Class", 2016, "homework1"));
    }

    @Test
    public void testGrade() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        this.student.registerForClass("good student", "Test Class", 2017);
        this.instructor.assignGrade("Professor Test", "Test Class", 2017, "homework1", "good student", 99);
        assertTrue(this.instructor.getGrade("Test Class", 2017, "homework1", "good student").intValue() == 99);
    }

    @Test
    public void testGradeNullWrongStudent() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        this.student.registerForClass("good student", "Test Class", 2017);
        this.instructor.assignGrade("Professor Test", "Test Class", 2017, "homework1", "good student", 99);
        assertTrue(this.instructor.getGrade("Test Class", 2017, "homework1", "bad student") == null);
    }

    @Test
    public void testGradeNullWrongClass() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        this.student.registerForClass("good student", "Test Class", 2017);
        this.instructor.assignGrade("Professor Test", "Test Class", 2017, "homework1", "good student", 99);
        assertTrue(this.instructor.getGrade("Test Class 2", 2017, "homework1", "good student") == null);
    }

    @Test
    public void testGradeNullWrongYear() {
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "homework1", "super hard homework");
        this.student.registerForClass("good student", "Test Class", 2017);
        this.instructor.assignGrade("Professor Test", "Test Class", 2017, "homework1", "good student", 99);
        assertTrue(this.instructor.getGrade("Test Class", 2016, "homework1", "bad student") == null);
    }

}
