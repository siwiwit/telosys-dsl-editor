package org.telosys.tools.eclipse.plugin.editors.entitymodel;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.telosys.tools.eclipse.plugin.editors.entitymodel.scanner.EntityScanner;

public class EntityEditorConfiguration extends SourceViewerConfiguration {
	private EntityScanner scanner;
	private ColorManager colorManager;

	public EntityEditorConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}

	protected EntityScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new EntityScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ColorManager.defaultColor))));
		}
		return scanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

}