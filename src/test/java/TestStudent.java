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
    public void testRegistrationWrongClass() {
        this.student.registerForClass("student", "Test Class",2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class 2", 2017));
    }

    @Test
    public void testRegistrationWrongYear() {
        this.student.registerForClass("student", "Test Class",2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class", 2016));
    }

    @Test
    public void testRegistrationInvalidClass() {
        this.student.registerForClass("student", "Test Class 2",2017);
        assertFalse(this.student.isRegisteredFor("student", "Test Class 2", 2017));
    }

    @Test
    public void testRegistrationInvalidYear() {
        this.student.registerForClass("student", "Test Class",2018);
        assertFalse(this.student.isRegisteredFor("student", "Test Class", 2017));
    }
}
