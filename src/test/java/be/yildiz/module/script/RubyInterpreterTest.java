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

import be.yildiz.common.shape.Box;
import be.yildiz.module.script.ScriptInterpreterFactory.ScriptLanguage;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * @author Grégory Van den Borre
 */
public class RubyInterpreterTest {

    @Test
    public void testSetOutput() throws IOException {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        File f = new File("test");
        String toPrint = "testing output in file";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            interpreter.setOutput(bw);
            interpreter.print(toPrint);
        }
        Assert.assertTrue(f.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            Assert.assertEquals(toPrint, br.readLine());
        }
        f.delete();
    }

    @Test(expected = ScriptException.class)
    public void testRunScriptNotExists() throws ScriptException {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        interpreter.runScript("none.rb");
    }

    @Test
    public void testRunScript() throws ScriptException {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        ParsedScript ps = interpreter.runScript(getFile("test-unit-1.rb").getAbsolutePath());
        ps.run();
    }

    private static File getFile(String name) {
        return new File(RubyInterpreter.class.getClassLoader().getResource(name).getFile()).getAbsoluteFile();
    }

    @Test
    public void testRunCommand() throws ScriptException {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        Assert.assertEquals(Long.valueOf(4L), interpreter.runCommand("2+2"));
        Assert.assertEquals(null, interpreter.runCommand("puts 'testing puts return code'"));
        Assert.assertEquals(new Box(5), interpreter.runCommand("a = Java::be.yildiz.common.shape.Box.new(5)"));
        Assert.assertEquals(new Box(5), interpreter.runCommand("a"));
    }

    @Test
    public void testPrint() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetFileHeader() {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        Assert.assertEquals("#!//usr//bin//ruby\n", interpreter.getFileHeader());
    }

    @Test
    public void testGetFileExtension() {
        ScriptInterpreter interpreter = ScriptInterpreterFactory.getInstance().getInterpreter(ScriptLanguage.RUBY);
        Assert.assertEquals("rb", interpreter.getFileExtension());
    }
}
