package org.telosys.tools.eclipse.plugin.editors.completion;

import java.util.ArrayList;
import java.util.List;

import org.telosys.tools.eclipse.plugin.editors.utils.Utils;

public class WordProvider {

	public List<String> suggest(String word){
		ArrayList<String> wordBuffer = new ArrayList<String>();
		for (String str : Utils.getProperty("types").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str);
		}
		for (String str : Utils.getProperty("validation.rules").split(",")){
			if (str.startsWith(word))
				wordBuffer.add(str);
		}
		return wordBuffer;
	}
}
