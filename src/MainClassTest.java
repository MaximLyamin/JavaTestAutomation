import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Class number is not more than 45",getClassNumber() > 45);
    }
}
