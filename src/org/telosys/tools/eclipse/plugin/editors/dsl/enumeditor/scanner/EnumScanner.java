package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.scanner;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.telosys.tools.eclipse.plugin.editors.dsl.common.ColorManager;
import org.telosys.tools.eclipse.plugin.editors.dsl.common.EditorsUtils;
import org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.EnumEditorException;

/**
 * Scanner rules.
 */
public class EnumScanner extends RuleBasedScanner {

    public EnumScanner(ColorManager manager) throws EnumEditorException {

        IRule[] rules = new IRule[5];

        // Add generic whitespace rule.
        rules[0] = new WhitespaceRule(new EnumWhitespaceDetector());

        // Entity Rule - MAJ
        IToken entityRule = new Token(new TextAttribute(
                manager.getColor(ColorManager.ENTITY_COLOR)));
        rules[1] = new WordRule(new EnumObjectDetector(), entityRule);

        // Comment rule
        IToken commentRule = new Token(new TextAttribute(
                manager.getColor(ColorManager.COMMENT_COLOR)));
        rules[2] = new EndOfLineRule("//", commentRule);

        // String rule
        IToken stringRule = new Token(new TextAttribute(
                manager.getColor(ColorManager.STRING_COLOR)));
        rules[3] = new SingleLineRule("\"", "\"", stringRule);

        // Default Rule
        IToken defaultRule = new Token(new TextAttribute(
                manager.getColor(ColorManager.DEFAULT_COLOR)));
        WordRule typewr = new WordRule(new EnumDefaultDetector(), defaultRule);

        // Type Rule
        IToken typeRule = new Token(new TextAttribute(
                manager.getColor(ColorManager.TYPE_COLOR)));
        for (String str : EditorsUtils.getProperty("enum.types").split(",")) {
            typewr.addWord(str, typeRule);
        }

        rules[4] = typewr;

        setRules(rules);

    }
}
