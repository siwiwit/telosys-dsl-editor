package org.telosys.tools.eclipse.plugin.editors.dsl.enum_editor;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.telosys.tools.eclipse.plugin.editors.dsl.common.EditorsException;

/**
 * Main class for the Enum Editor.
 *
 */
@SuppressWarnings("deprecation")
public class EnumEditor extends TextEditor {

	public EnumEditor() {
		super();
		setSourceViewerConfiguration(new EnumEditorConfiguration());
	}

	public void dispose() {
		super.dispose();
	}

	protected void createActions() throws EditorsException {
		super.createActions();
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = new PropertyResourceBundle(
					new StringBufferInputStream(
							"ContentAssistProposal.label=Content assist\nContentAssistProposal.tooltip=Content assist\nContentAssistProposal.description=Provides Content Assistance"));
		} catch (IOException e) {
			throw new EditorsException("Error while creating the autocompletion : " + e.getMessage());
		}
		ContentAssistAction action = new ContentAssistAction(resourceBundle,
				"ContentAssistProposal.", this);
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssist", action);
	}

}
