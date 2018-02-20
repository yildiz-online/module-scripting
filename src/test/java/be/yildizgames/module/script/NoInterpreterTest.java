/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.script;

import be.yildizgames.module.script.dummy.NoInterpreterProvider;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grégory Van den Borre
 */
class NoInterpreterTest {

    private final ScriptInterpreter interpreter = new NoInterpreterProvider().getInterpreter();

    @Test
    void testRunScript() throws ScriptException {
        ParsedScript p = interpreter.runScript("");
        p.run();
    }

    @Test
    void testGetFileExtension() {
        assertEquals("txt", interpreter.getFileExtension());
    }

    @Test
    void testGetFileHeader() {
        assertEquals("", interpreter.getFileHeader());
    }

    @Test
    void testGetClassMethods() {
        assertEquals("", interpreter.getClassMethods(String.class));
        assertEquals("", interpreter.getClassMethods(Integer.class));
    }

    @Test
    void testRunCommand() throws ScriptException {
        assertEquals("", interpreter.runCommand("azerty"));
    }

    @Test
    void testPrint() {
        interpreter.print("azerty");
    }

    @Test
    void testSetOutput() {
        interpreter.setOutput(new PrintWriter(new StringWriter()));
    }

    @Test
    void testClose() throws Exception {
        ScriptInterpreter i = new NoInterpreterProvider().getInterpreter();
        assertFalse(i.isClosed());
        i.close();
        assertTrue(i.isClosed());
    }
}
