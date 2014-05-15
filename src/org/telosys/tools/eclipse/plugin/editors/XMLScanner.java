package org.telosys.tools.eclipse.plugin.editors;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.swt.graphics.RGB;

public class XMLScanner extends RuleBasedScanner {

	public XMLScanner(ColorManager manager) {
		Properties prop = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("/test/config.properties");
			prop.load(in);
		}
		catch (IOException e){
			
		}
		
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IXMLColorConstants.PROC_INSTR)));
		
		String[] rgb = prop.get("ENTITY_COLOR").toString().split(",");
		IToken entityRule =
			new Token(
				new TextAttribute(
					manager.getColor(new RGB(Integer.parseInt(rgb[0]), 
								Integer.parseInt(rgb[1]), 
								Integer.parseInt(rgb[2])))));

		IRule[] rules = new IRule[2];
		// Add generic whitespace rule.
		rules[0] = new WhitespaceRule(new XMLWhitespaceDetector());
		
		//Entity Rule - MAJ		
		rules[1] = new WordPatternRule(new IdentifierDetector(), "$([A-Z])", null, entityRule);

		setRules(rules);
	}
}
