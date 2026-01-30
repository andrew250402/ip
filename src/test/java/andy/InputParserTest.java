package andy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {
    @Test
    public void isByeTest() {
        InputParser parser = new InputParser("bye");
        assertEquals(true, parser.isBye());
    }

    @Test
    public void getTaskTest() {
        InputParser parser = new InputParser("event lecture /from 6pm /to 9pm");
        Task task = parser.getTask();
        assertEquals("E|0|lecture | 6pm |9pm", task.getString());
    }
}
