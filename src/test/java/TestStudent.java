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
 * Created by andy on 3/7/17.
 */
public class TestStudent {
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
    public void testRegistration() {
        this.student.registerForClass("student", "Test Class",2017);
        assertTrue(this.student.isRegisteredFor("student", "Test Class", 2017));
    }

    @Test
    public void testRegistrationWrongStudent() {
        this.student.registerForClass("student", "Test Class",2017);
        assertFalse(this.student.isRegisteredFor("student 2", "Test Class", 2017));
    }

    @Test
    public void testRegistrationWrongYear() {
        this.student.registerForClass("student", "Test Class",2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class", 2016));
    }

    @Test
    public void testRegistrationNonexistentClass() {
        this.student.registerForClass("student", "Test Class 2",2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class 2", 2017));
    }

    @Test
    public void testRegistrationInvalidYear() {
        this.student.registerForClass("student", "Test Class",2018);
        assertFalse(this.student.isRegisteredFor("student", "Test Class", 2017));
    }

    @Test
    public void testRegistrationTooManyStudents() {
        this.student.registerForClass("student1", "Test Class",2017);
        this.student.registerForClass("student2", "Test Class",2017);
        this.student.registerForClass("student3", "Test Class",2017);
        this.student.registerForClass("student4", "Test Class",2017);
        this.student.registerForClass("student5", "Test Class",2017);
        this.student.registerForClass("student6", "Test Class",2017);
        this.student.registerForClass("student7", "Test Class",2017);
        this.student.registerForClass("student8", "Test Class",2017);
        this.student.registerForClass("student9", "Test Class",2017);
        this.student.registerForClass("student10", "Test Class",2017);
        this.student.registerForClass("student11", "Test Class",2017);
        this.student.registerForClass("student12", "Test Class",2017);
        this.student.registerForClass("student13", "Test Class",2017);
        this.student.registerForClass("student14", "Test Class",2017);
        this.student.registerForClass("student15", "Test Class",2017);
        this.student.registerForClass("student16", "Test Class",2017);
        assertFalse(this.student.isRegisteredFor("student16", "Test Class", 2017));
    }

    @Test
    public void testDrop() {
        this.student.registerForClass("student", "Test Class", 2017);
        this.student.dropClass("student", "Test Class", 2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class", 2017));
    }

    @Test
    public void testHomeworkSubmission() {
        this.student.registerForClass("student", "Test Class", 2017);
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "hw1", "easy homework");
        this.student.submitHomework("student", "hw1", "answer", "Test Class", 2017);
        assertTrue(this.student.hasSubmitted("student", "hw1", "Test Class", 2017));
    }

    @Test
    public void testHomeworkNotSubmitted() {
        this.student.registerForClass("student", "Test Class", 2017);
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "hw1", "easy homework");
        assertFalse(this.student.hasSubmitted("student", "hw1", "Test Class", 2017));
    }

    @Test
    public void testHomeworkSubmitInFuture() {
        this.admin.createClass("Future Class", 2020, "Professor Test 2", 15);
        this.student.registerForClass("student", "Future Class", 2020);
        this.instructor.addHomework("Professor Test 2", "Future Class", 2020, "hw1", "easy homework");
        this.student.submitHomework("student", "hw1", "answer", "Future Class", 2020);
        assertFalse(this.student.hasSubmitted("student", "hw1", "Future Class", 2020));
    }

    @Test
    public void testHomeworkSubmitNonExistentHomework() {
        this.student.registerForClass("student", "Test Class", 2017);
        this.instructor.addHomework("Professor Test", "Test Class", 2017, "hw1", "easy homework");
        this.student.submitHomework("student", "hw100", "answer", "Test Class", 2017);
        assertFalse(this.student.hasSubmitted("student", "hw100", "Test Class", 2017));
    }
}
