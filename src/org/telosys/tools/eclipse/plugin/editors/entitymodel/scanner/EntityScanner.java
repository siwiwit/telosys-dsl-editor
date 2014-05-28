package org.telosys.tools.eclipse.plugin.editors.entitymodel.scanner;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordPatternRule;
import org.eclipse.jface.text.rules.WordRule;
import org.telosys.tools.eclipse.plugin.editors.entitymodel.ColorManager;
import org.telosys.tools.eclipse.plugin.editors.entitymodel.EntityEditorUtils;

public class EntityScanner extends RuleBasedScanner {

	public EntityScanner(ColorManager manager) {	

		IRule[] rules = new IRule[6];
		
		// Add generic whitespace rule.
		rules[0] = new WhitespaceRule(new EntityWhitespaceDetector());
		
		//Entity Rule - MAJ 
		IToken entityRule = 
			new Token( new TextAttribute(manager.getColor(ColorManager.entityColor)));		
		rules[1] = new WordRule(new EntityObjectDetector(), entityRule);
		//Entity Rule - Enum
		rules[2] = new WordPatternRule(new EntityIdentifierDetector(), "#", null, entityRule);
		
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
		WordRule typewr = new WordRule(new EntityDefaultDetector(), defaultRule);	

		//Type Rule			
		IToken typeRule =
			new Token( new TextAttribute(manager.getColor(ColorManager.typeColor)));		
		for (String str : EntityEditorUtils.getProperty("types").split(",")){
			typewr.addWord(str, typeRule);
		}
		
		//Validation Rule
		IToken validationRule =
			new Token( new TextAttribute(manager.getColor(ColorManager.validationColor)));		
		for (String str : EntityEditorUtils.getProperty("validation.rules").split(",")){
			typewr.addWord(str, validationRule);
		}
		rules[5] = typewr;

		setRules(rules);
	}
}
