package org.telosys.tools.eclipse.plugin.editors.dsl.entityeditor.scanner;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class EntityWhitespaceDetector implements IWhitespaceDetector {

	private final String nonAccept = " \t\n\r:";
	
	public boolean isWhitespace(char c) {
		return nonAccept.indexOf(c) != -1;
	}
}
