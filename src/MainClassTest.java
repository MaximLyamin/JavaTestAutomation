import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Class number is not more than 45",getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        Assert.assertTrue("Text doesnt contains 'hello' or 'Hello'", getClassString().contains("Hello")||getClassString().contains("hello"));
    }
}
