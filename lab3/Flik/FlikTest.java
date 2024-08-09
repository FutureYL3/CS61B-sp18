import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testIsSameNumber1() {
        assertTrue(Flik.isSameNumber(new Integer(1), new Integer(1)));
    }

    @Test
    public void testIsSameNumber2() {
        Integer a = new Integer(1);
        assertTrue(Flik.isSameNumber(a, a));
    }
}
