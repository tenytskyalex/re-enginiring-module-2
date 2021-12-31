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

    @Test
    public void calculateDiscount()
    {
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 1), 0);// new item, discount must be 0

        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 1), 0);// regular item, 1 item, discount must be 0
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 10), 1);// regular item, 10 items discount must be 1
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 50), 5);// regular item, 50 items, iscount must be 5
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 810), 80);// regular item, 810 items, discount must be 80 anything

        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1), 0);// second free item, 1 item, discount must be 0
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 2), 50);// second free item, 1 item, discount must be 50
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 10), 51);// second free item, 10 items, discount must be 51
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 20), 52);// second free item, 20 item, discount must be 52
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1000), 80);// second free item, 1000 item, discount must be 80 anything

        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 1), 70);// sale item, 1 item, discount must be 70
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 10), 71);// sale item, 1 item, discount must be 70
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 30), 73);// sale item, 1 item, discount must be 70
        Assert.assertEquals(ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 1000), 80);// sale item, 1 item, discount must be 80 anything

    }

    @Test
    public void addItemCheckFormatTicket() {
        String title = "Test item";
        int price = 100;
        int quantity = 100;
        ShoppingCart.ItemType type = ShoppingCart.ItemType.REGULAR;

        String expectedResult = "# Item        Price Quan. Discount    Total \n" +
                "-------------------------------------------\n" +
                "1 Test item $100.00   100      10% $9000.00 \n" +
                "-------------------------------------------\n" +
                "1                                  $9000.00 ";

        cart.addItem(title, price, quantity, type);
        Assert.assertEquals("# Item        Price Quan. Discount    Total \n" +
                "-------------------------------------------\n" +
                "1 Test item $100.00   100      10% $9000.00 \n" +
                "-------------------------------------------\n" +
                "1                                  $9000.00 ", cart.formatTicket());
        cart.addItem("1", price, quantity, type);
        Assert.assertEquals("# Item        Price Quan. Discount     Total \n" +
                "--------------------------------------------\n" +
                "1 Test item $100.00   100      10%  $9000.00 \n" +
                "2 1         $100.00   100      10%  $9000.00 \n" +
                "--------------------------------------------\n" +
                "2                                  $18000.00 ", cart.formatTicket());
        cart.addItem("12412rnwehrtwekrter1123", price, quantity, type);
        Assert.assertEquals("# Item                      Price Quan. Discount     Total \n" +
                "----------------------------------------------------------\n" +
                "1 Test item               $100.00   100      10%  $9000.00 \n" +
                "2 1                       $100.00   100      10%  $9000.00 \n" +
                "3 12412rnwehrtwekrter1123 $100.00   100      10%  $9000.00 \n" +
                "----------------------------------------------------------\n" +
                "3                                                $27000.00 ", cart.formatTicket());
    }

}
