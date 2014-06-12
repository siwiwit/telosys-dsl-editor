package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class EnumWhitespaceDetector implements IWhitespaceDetector {

    public boolean isWhitespace(char c) {
        return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
    }
}
