/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.script;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Grégory Van den Borre
 */
public class NoInterpreterTest {

    @Test
    public void testRunScript() throws ScriptException {
        ParsedScript p = new NoInterpreter().runScript("");
        p.run();
    }

    @Test
    public void testGetFileExtension() {
        Assert.assertEquals("txt", new NoInterpreter().getFileExtension());
    }

    @Test
    public void testGetFileHeader() {
        Assert.assertEquals("", new NoInterpreter().getFileHeader());
    }

    @Test
    public void testGetClassMethods() {
        Assert.assertEquals("", new NoInterpreter().getClassMethods(String.class));
        Assert.assertEquals("", new NoInterpreter().getClassMethods(Integer.class));
    }

    @Test
    public void testRunCommand() throws ScriptException {
        Assert.assertEquals("", new NoInterpreter().runCommand("azerty"));
    }

    @Test
    public void testPrint() {
        new NoInterpreter().print("azerty");
    }

    @Test
    public void testSetOutput() throws IOException {
        new NoInterpreter().setOutput(new PrintWriter(new StringWriter()));
    }

    @Test
    public void testClose() throws Exception {
        ScriptInterpreter i = new NoInterpreter();
        Assert.assertFalse(i.isClosed());
        i.close();
        Assert.assertTrue(i.isClosed());
    }
}
