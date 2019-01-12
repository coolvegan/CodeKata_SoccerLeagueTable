import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SoccerTableTest {
    private SoccerTableEvaluator soccerTableEvaluator;

    @Before
    public void setUp() {
        soccerTableEvaluator = new SoccerTableEvaluator("C:\\temp\\football.dat");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getNumberByPosition() {
        int data;
        data = soccerTableEvaluator.getNumberByPosition("    1. Arsenal         38    26   9   3    79  -  36    87", 43);
        assertEquals(79, data);
        data = soccerTableEvaluator.getNumberByPosition("   20. Leicester       38     5  13  20    30  -  64    28", 50);
        assertEquals(64, data);
    }

    @Test
    public void getTeamByPosition() {
        String data;
        data = soccerTableEvaluator.getTeamByPosition("    1. Arsenal         38    26   9   3    79  -  36    87", 7);
        assertEquals("Arsenal", data);
        data = soccerTableEvaluator.getTeamByPosition("   20. Leicester       38     5  13  20    30  -  64    28", 7);
        assertEquals("Leicester", data);
    }

    @Test
    public void getTeamWithSmallestDifference() {
        String data;

        data = soccerTableEvaluator.getTeamWithSmallestDifference();
        assertEquals("Leicester", data);
    }

}