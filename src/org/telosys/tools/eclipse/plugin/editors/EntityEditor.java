package org.telosys.tools.eclipse.plugin.editors;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

public class EntityEditor extends TextEditor {

	private ColorManager colorManager;

	public EntityEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new EditorSourceViewerConfiguration());
		setDocumentProvider(new XMLDocumentProvider());
	}

	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

	protected void createActions() throws EditorException {
		super.createActions();
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = new PropertyResourceBundle(
					new StringBufferInputStream(
							"ContentAssistProposal.label=Content assist\nContentAssistProposal.tooltip=Content assist\nContentAssistProposal.description=Provides Content Assistance"));
		} catch (IOException e) {
			throw new EditorException("Error while creating the autocompletion : " + e.getMessage());
		}
		ContentAssistAction action = new ContentAssistAction(resourceBundle,
				"ContentAssistProposal.", this);
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssist", action);
	}

}
