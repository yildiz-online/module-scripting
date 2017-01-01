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

import be.yildiz.common.log.Logger;
import org.jruby.embed.*;
import org.jruby.exceptions.RaiseException;

import java.io.*;

/**
 * Ruby implementation as script interpreter.
 *
 * @author Grégory Van den Borre
 */
final class RubyInterpreter implements ScriptInterpreter {

    /**
     * JRuby container to execute scripts.
     */
    private final ScriptingContainer container;

    /**
     * Simple constructor.
     */
    RubyInterpreter() {
        super();
        this.container = new ScriptingContainer(LocalContextScope.CONCURRENT, LocalVariableBehavior.PERSISTENT);
    }

    /**
     * Initialize the ruby engine.
     *
     * @throws ScriptException If the initialization failed.
     */
    public void initialize() throws ScriptException {
        this.runCommand("require 'java'");
    }

    /**
     * Register a class to be recognized by Ruby.
     *
     * @param clazz Class to register.
     * @throws ScriptException If the class cannot be registered.
     */
    public void registerClass(@SuppressWarnings("rawtypes") final Class clazz) throws ScriptException {
        String packageName = clazz.getPackage().getName();
        String className = clazz.getSimpleName();
        this.runCommand("java_import Java::" + packageName + "::" + className);
    }

    @Override
    public void setOutput(final Writer output) {
        this.container.setOutput(output);
    }

    @Override
    public ParsedScript runScript(final String file) throws ScriptException {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(new File(file)))) {
            return this.container.parse(stream, file)::run;
        } catch (IOException e) {
            throw new ScriptException(e);
        }
    }

    @Override
    public Object runCommand(final String command) throws ScriptException {
        try {
            return this.container.runScriptlet(command);
        } catch (RaiseException | EvalFailedException | ParseFailedException e) {
            throw new ScriptException(e);
        }
    }

    @Override
    public Object getClassMethods(final Class<?> classToGet) {
        try {
            return this.runCommand(classToGet.getName() + ".java_class.declared_instance_methods");
        } catch (ScriptException e) {
            Logger.error(e);
            return e.getMessage();
        }

    }

    @Override
    public void print(final String toPrint) {
        try {
            this.runCommand("puts '" + toPrint + "';");
        } catch (ScriptException e) {
            Logger.error(e);
        }
    }

    @Override
    public String getFileHeader() {
        return "#!//usr//bin//ruby\n";
    }

    /**
     * @return rb
     */
    @Override
    public String getFileExtension() {
        return "rb";
    }
}
