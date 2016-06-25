//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.script;

import be.yildiz.common.collections.Maps;
import be.yildiz.common.exeption.UnhandledSwitchCaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Retrieve the interpreter and ensure only one instance is created per engine.
 *
 * @author Grégory Van den Borre
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScriptInterpreterFactory {

    /**
     * Unique instance.
     */
    private static final ScriptInterpreterFactory INSTANCE = new ScriptInterpreterFactory();

    /**
     * Keep all interpreters in a list.
     */
    private final Map<ScriptLanguage, ScriptInterpreter> interpreters = Maps.newMap();

    /**
     * @return The unique instance.
     */
    public static ScriptInterpreterFactory getInstance() {
        return ScriptInterpreterFactory.INSTANCE;
    }

    /**
     * Get or create an interpreter for a given script language.
     *
     * @param language Script language.
     * @return The ScriptInterpreter implementation for the given
     * ScriptLanguage.
     */
    public ScriptInterpreter getInterpreter(final ScriptLanguage language) {
        if (!this.interpreters.containsKey(language)) {
            switch (language) {
                case RUBY:
                    this.interpreters.put(language, new RubyInterpreter());
                    break;
                case NONE:
                    this.interpreters.put(language, new NoInterpreter());
                    break;
                default:
                    throw new UnhandledSwitchCaseException(language);
            }
        }
        return this.interpreters.get(language);
    }

    /**
     * All languages available.
     *
     * @author Grégory Van den Borre
     */
    public enum ScriptLanguage {

        /**
         * Ruby script language using JRuby implementation.
         */
        RUBY,

        /**
         * No script language implementation.
         */
        NONE
    }
}
