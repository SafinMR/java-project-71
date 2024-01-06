package hexlet.code;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    String filePath1Test = "src/test/resources/testFile1.json";
    String filePath2Test = "src/test/resources/testFile2.json";





    @Test
    void testDiffer1() {
        assertEquals(filePath1Test, "src/test/resources/file1.json");
    }

    @Test
    void testDiffer2() {
        assertEquals(filePath2Test, "src/test/resources/file1.json");
    }
}
