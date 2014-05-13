package org.telosys.tools.eclipse.plugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class EntityEditor extends TextEditor {

	private ColorManager colorManager;

	public EntityEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
