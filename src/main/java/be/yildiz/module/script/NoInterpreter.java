/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildiz.module.script;

import java.io.Writer;

/**
 * Simple implementation when no script engine is used.
 *
 * @author Grégory Van den Borre
 */
final class NoInterpreter implements ScriptInterpreter {

    private boolean closed;

    NoInterpreter() {
        super();
    }

    @Override
    public ParsedScript runScript(final String file) throws ScriptException {
        return () -> {
            // Does nothing.
        };
    }

    @Override
    public Object runCommand(final String command) throws ScriptException {
        return "";
    }

    @Override
    public void print(final String toPrint) {
        // Does nothing.
    }

    @Override
    public void setOutput(final Writer output) {
        // Does nothing.
    }

    /**
     * @param classToGet Class to retrieve methods.
     * @return "".
     */
    @Override
    public Object getClassMethods(final Class<?> classToGet) {
        return "";
    }

    /**
     * @return "".
     */
    @Override
    public String getFileHeader() {
        return "";
    }

    /**
     * @return "txt".
     */
    @Override
    public String getFileExtension() {
        return "txt";
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void close() throws Exception {
        this.closed = true;
    }
}
