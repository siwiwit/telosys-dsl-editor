package org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.completion;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import org.telosys.tools.eclipse.plugin.editors.dsl.enumeditor.EnumEditorException;

/*
 * TODO : establishment of the context system to not propose the completion
 * between "{ }" and only propose after " : " (ITypedRegion or IDocumentPartitioner)
 */
public class EnumEditorContentAssistProcessor implements
        IContentAssistProcessor {

    public EnumEditorContentAssistProcessor() {
        this.wordProvider = new EnumEditorWordProvider();
    }

    private EnumEditorWordProvider wordProvider;
    private String lastError;

    public ICompletionProposal[] computeCompletionProposals(
            ITextViewer textViewer, int documentOffset) {
        IDocument document = textViewer.getDocument();
        int currOffset = documentOffset - 1;

        String currWord = "";
        char currChar;

        try {
            while (currOffset > 0
                    && !(Character.isWhitespace(currChar = document
                            .getChar(currOffset)) || currChar == ';')) {
                currWord = currChar + currWord;
                currOffset--;
            }
        } catch (BadLocationException e1) {
            throw new EnumEditorException("Error while proposing a word : "
                    + e1);
        }

        List<String> suggestions = wordProvider.suggest(currWord);
        ICompletionProposal[] proposals = null;

        if (suggestions.size() > 0) {
            try {
                proposals = buildProposals(suggestions, currWord,
                        documentOffset - currWord.length());
            } catch (Exception e) {
                throw new EnumEditorException("Error while proposing a word : "
                        + e);
            }
        }
        return proposals;
    }

    private ICompletionProposal[] buildProposals(List<String> suggestions,
            String replacedWord, int offset) throws Exception {
        ICompletionProposal[] proposals = new ICompletionProposal[suggestions
                .size()];

        int index = 0;

        for (Iterator<String> i = suggestions.iterator(); i.hasNext();) {
            String currSuggestion = (String) i.next();
            CompletionProposal cp = new CompletionProposal(currSuggestion,
                    offset, replacedWord.length(), currSuggestion.length(),
                    null, currSuggestion, null, null);
            proposals[index] = cp;
            index++;
        }

        return proposals;
    }

    @Override
    public IContextInformation[] computeContextInformation(
            ITextViewer itextviewer, int i) {
        lastError = "No Context Information available";
        return null;
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return null;
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return lastError;
    }
}