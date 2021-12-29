import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    ShoppingCart cart;

    @Before
    public void setUp(){
        cart = new ShoppingCart();
    }

    @Test
    public void appendFormattedLengthMoreWidth()
    {
        String testValue = "Test string";
        int width = 5;

        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, 0, width);
        Assert.assertEquals(testValue.substring(0, width)+" ", sb.toString());
    }

    @Test
    public void appendFormattedAlignLeft()
    {
        String testValue = "Test string";
        int width = 50;

        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, -1, width);


        StringBuilder expectedSb = new StringBuilder();
        int after = width - testValue.length();
        expectedSb.append(testValue);
        while (after-- > 0)
            expectedSb.append(" ");
        expectedSb.append(" ");

        Assert.assertEquals(expectedSb.toString(), sb.toString());
    }

    @Test
    public void appendFormattedAlignCenter()
    {
        String testValue = "Test string";
        int width = 50;

        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, 0, width);


        StringBuilder expectedSb = new StringBuilder();
        int before = (width - testValue.length()) / 2;
        int after = width - testValue.length() - before;
        while (before-- > 0)
            expectedSb.append(" ");
        expectedSb.append(testValue);
        while (after-- > 0)
            expectedSb.append(" ");
        expectedSb.append(" ");

        Assert.assertEquals(expectedSb.toString(), sb.toString());
    }

    @Test
    public void appendFormattedAlignRight()
    {
        String testValue = "Test string";
        int width = 50;

        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, 1, width);


        StringBuilder expectedSb = new StringBuilder();
        int before =  width - testValue.length();
        while (before-- > 0)
            expectedSb.append(" ");
        expectedSb.append(testValue);
        expectedSb.append(" ");

        Assert.assertEquals(expectedSb.toString(), sb.toString());
    }

}
