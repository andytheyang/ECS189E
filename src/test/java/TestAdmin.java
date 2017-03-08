import api.IAdmin;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andy on 3/5/17.
 */
public class TestAdmin {
    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testGetCapacity() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.getClassCapacity("Test", 2017) == 15);
    }

    @Test
    public void testChangeCapacityGreater() {
        this.admin.createClass("Test1", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test1", 2017, 30);
        assertTrue(this.admin.getClassCapacity("Test1", 2017) == 30);
    }

    @Test
    public void testChangeCapacityLesser() {
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test2", 2017, 10);
        assertTrue(this.admin.getClassCapacity("Test2", 2017) == 10);
    }

    @Test
    public void testChangeCapacitySame() {
        this.admin.createClass("Test3", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test3", 2017, 15);
        assertTrue(this.admin.getClassCapacity("Test3", 2017) == 15);
    }

    @Test
    public void testCreateClassNoInstructor() {
        this.admin.createClass("Test", 2017, null, 15);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testCreateClassInvalidCapacity() {
        this.admin.createClass("Test", 2017, "Instructor", -1);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test4", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test4", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test5", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test5", 2016));     // we  don't want classes in the past
    }
}
