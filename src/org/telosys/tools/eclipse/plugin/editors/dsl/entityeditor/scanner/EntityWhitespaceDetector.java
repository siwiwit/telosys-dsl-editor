package org.telosys.tools.eclipse.plugin.editors.dsl.entityEditor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class EntityWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == ':');
	}
}
