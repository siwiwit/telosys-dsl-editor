package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.EnumEditorException;
import org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.EnumEditorUtils;

/**
 * Word provider for autocompletion.
 * 
 */
public class EnumEditorWordProvider {

	public List<String> suggest(String word) throws EnumEditorException{
		ArrayList<String> wordBuffer = new ArrayList<String>();
		for (String str : EnumEditorUtils.getProperty("enum.types").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str);
		}
		return wordBuffer;
	}
}
