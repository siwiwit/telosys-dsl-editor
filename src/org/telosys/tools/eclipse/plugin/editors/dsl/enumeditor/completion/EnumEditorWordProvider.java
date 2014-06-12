package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.dsl.common.EditorsUtils;
import org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.EnumEditorException;


/**
 * Word provider for autocompletion.
 * 
 */
public class EnumEditorWordProvider {

	public List<String> suggest(String word) throws EnumEditorException{
		ArrayList<String> wordBuffer = new ArrayList<String>();
		for (String str : EditorsUtils.getProperty("enum.types").split(",")){
			if (str.startsWith(word)){
				wordBuffer.add(str + " ");
			}
		}
		return wordBuffer;
	}
}
