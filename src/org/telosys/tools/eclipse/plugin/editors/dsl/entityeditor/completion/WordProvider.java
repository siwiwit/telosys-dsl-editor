package org.telosys.tools.eclipse.plugin.editors.dsl.entityeditor.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.dsl.entityeditor.EntityEditorException;
import org.telosys.tools.eclipse.plugin.editors.dsl.entityeditor.EntityEditorUtils;

/**
 * Word provider for autocompletion.
 * 
 */
public class WordProvider {

	public List<String> suggest(String word, int context) throws EntityEditorException{
		ArrayList<String> wordBuffer = new ArrayList<String>();
		switch (context){
			case EntityEditorUtils.TYPE:
				for (String str : EntityEditorUtils.getProperty("types").split(",")){
					if (str.startsWith(word))
						wordBuffer.add(str + " ");
				}
				break;
				
			case EntityEditorUtils.ANNOTATION:
				for (String str : EntityEditorUtils.getProperty("validation.rules").split(",")){
					if (str.startsWith(word))
						wordBuffer.add(str);
				}
				break;
				
			default:
					break;
		}		
		
		return wordBuffer;
	}
}
