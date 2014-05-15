package org.telosys.tools.eclipse.plugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class EntityDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return Character.isUpperCase(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return (Character.isLetterOrDigit(c));
	}

}
