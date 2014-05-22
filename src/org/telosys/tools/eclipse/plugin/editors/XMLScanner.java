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
		
		IToken procInstr =new Token(
				new TextAttribute(
					manager.getColor(IXMLColorConstants.PROC_INSTR)));		

		IRule[] rules = new IRule[6];
		
		// Add generic whitespace rule.
		rules[0] = new WhitespaceRule(new XMLWhitespaceDetector());
		
		//Entity Rule - MAJ 
		IToken entityRule = 
			new Token( new TextAttribute(manager.getColor(ColorManager.entityColor)));		
		rules[1] = new WordRule(new EntityDetector(), entityRule);
		//Entity Rule - Enum
		rules[2] = new WordPatternRule(new IdentifierDetector(), "#", null, entityRule);
		
		//Comment rule
		IToken commentRule =
			new Token(new TextAttribute( manager.getColor(ColorManager.commentColor)));
		rules[3] = new EndOfLineRule("//", commentRule);
		
		//String rule
		IToken stringRule =
			new Token(new TextAttribute( manager.getColor(ColorManager.stringColor)));
		rules[4] = new SingleLineRule("\"", "\"", stringRule);
		
		//Default Rule
		IToken defaultRule =
			new Token( new TextAttribute(manager.getColor(ColorManager.defaultColor)));
		WordRule typewr = new WordRule(new DefaultDetector(), defaultRule);	

		//Type Rule			
		IToken typeRule =
			new Token( new TextAttribute(manager.getColor(ColorManager.typeColor)));		
		for (String str : prop.getProperty("type").split(",")){
			typewr.addWord(str, typeRule);
		}
		
		//Validation Rule
		IToken validationRule =
			new Token( new TextAttribute(manager.getColor(ColorManager.validationColor)));		
		for (String str : prop.getProperty("validation.rule").split(",")){
			typewr.addWord(str, validationRule);
		}
		rules[5] = typewr;

		setRules(rules);
	}
}
