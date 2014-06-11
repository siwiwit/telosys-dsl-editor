package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.scanner;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Detector for default words.
 * 
 */
public class EnumDefaultDetector implements IWordDetector{
	
	@Override
	public boolean isWordStart(char c) {
		return true;
	}

	@Override
	public boolean isWordPart(char c) {
		return !(c == ' ' || c == '\t' || c == '\n' || 
				 c == '\r' || c == '#' || c == '@' || c=='/' || c=='(' || c=='"' );
	}
}
