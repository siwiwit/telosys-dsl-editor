package org.telosys.tools.eclipse.plugin.editors.entitymodel;

import org.eclipse.ui.editors.text.TextEditor;

public class EntityEditor extends TextEditor {

	private ColorManager colorManager;

	public EntityEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new EntityEditorConfiguration(colorManager));
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
