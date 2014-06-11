package org.telosys.tools.eclipse.plugin.editors.dsl.entityEditor.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.dsl.entityEditor.EntityEditorException;
import org.telosys.tools.eclipse.plugin.editors.dsl.entityEditor.EntityEditorUtils;

/**
 * Word provider for autocompletion.
 * 
 */
public class EntityEditorWordProvider {

	public List<String> suggest(String word) throws EntityEditorException{
		ArrayList<String> wordBuffer = new ArrayList<String>();
		for (String str : EntityEditorUtils.getProperty("entity.types").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str);
		}
		for (String str : EntityEditorUtils.getProperty("validation.rules").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str);
		}
		return wordBuffer;
	}
}
