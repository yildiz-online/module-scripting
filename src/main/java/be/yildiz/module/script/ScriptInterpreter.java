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

import java.io.Writer;

/**
 * Behavior for scripting languages.
 *
 * @author Grégory Van den Borre
 */
public interface ScriptInterpreter extends AutoCloseable{

    /**
     * Parse and run a script file.
     *
     * @param file File to execute.
     * @return A ParsedScript object to run the script again without parsing it.
     * @throws ScriptException If an exception occurs while parsing the script.
     */
    ParsedScript runScript(String file) throws ScriptException;

    /**
     * Execute a command.
     *
     * @param command Command to execute.
     * @return The object resulting from the command, Long for numeric result.
     * @throws ScriptException If an exception occurs while parsing the command.
     */
    Object runCommand(String command) throws ScriptException;

    /**
     * Utility function, print a line in console.
     *
     * @param toPrint Line to print.
     */
    void print(String toPrint);

    /**
     * Redirect the output.
     *
     * @param output New script engine output.
     */
    void setOutput(Writer output);

    /**
     * Print all methods of a java class.
     *
     * @param classToGet Class to retrieve methods.
     * @return The object resulting from the command.
     */
    Object getClassMethods(Class<?> classToGet);

    /**
     * @return The header to set in a script file.
     */
    String getFileHeader();

    /**
     * @return The script file extension, without ".".
     */
    String getFileExtension();

    boolean isClosed();
}
