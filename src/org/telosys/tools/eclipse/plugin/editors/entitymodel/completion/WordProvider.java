package org.telosys.tools.eclipse.plugin.editors.entitymodel.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.entitymodel.EntityEditorException;
import org.telosys.tools.eclipse.plugin.editors.entitymodel.EntityEditorUtils;

/**
 * Word provider for autocompletion.
 * 
 */
public class WordProvider {

	public List<String> suggest(String word) throws EntityEditorException{
		ArrayList<String> wordBuffer = new ArrayList<String>();
		for (String str : EntityEditorUtils.getProperty("types").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str + " ");
		}
		for (String str : EntityEditorUtils.getProperty("validation.rules").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str + " ");
		}
		return wordBuffer;
	}
}
