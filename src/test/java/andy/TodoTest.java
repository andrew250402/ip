package andy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getStringTest(){
        Todo test = new Todo("An example to do");
        assertEquals(test.getString(), "T|0|An example to do");
    } 

    @Test
    public void markDoneTest() {
        Todo test = new Todo("An example done to do");
        Todo testDone = test.markDone();
        assertEquals(testDone.getString(), "T|1|An example done to do");
    }
}
