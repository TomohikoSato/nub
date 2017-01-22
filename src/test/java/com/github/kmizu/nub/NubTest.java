package com.github.kmizu.nub;

import com.github.kmizu.nub.tool.Streams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NubTest {
    static Object eval(String input) throws Exception {
        AstNode.ExpressionList program = new NubParser(Streams.streamFrom(input)).program().e;
        return new Evaluator().evaluate(program);
    }

    @Test
    public void testReminder() throws Exception {
        assertEquals(0, eval("24 % 3;"));
        assertEquals(1, eval("25 % 3;"));
        assertEquals(2, eval("26 % 3;"));
        assertEquals(0, eval("27 % 3;"));
    }

    @Test
    public void testString() throws Exception{
        assertEquals("hoge", eval("\"hoge\";"));
    }

    @Test
    public void testBoolean() throws Exception {
        assertTrue((Boolean) eval("true;"));
    }

    @Test
    public void testPow() throws Exception{
        assertEquals(256, eval("2**8;"));
    }

    @Test
    public void testPlus() throws Exception {
        assertEquals(1, eval("1;"));
        assertEquals(2, eval("1+1;"));
        assertEquals(3, eval("1+1+1;"));
    }

    @Test
    public void testMinus() throws Exception {
        assertEquals(0, eval("1-1;"));
        assertEquals(-1, eval("1-1-1;"));
        assertEquals(-2, eval("1-1-1-1;"));
    }
}
