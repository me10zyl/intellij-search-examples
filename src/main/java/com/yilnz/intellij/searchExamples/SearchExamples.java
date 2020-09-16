package com.yilnz.intellij.searchExamples;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.util.ui.EmptyClipboardOwner;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class SearchExamples extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        Project project = e.getData(CommonDataKeys.PROJECT);
        AnAction copyRefer = e.getActionManager().getAction(IdeActions.ACTION_COPY_REFERENCE);
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable old = c.getContents(this);
        copyRefer.actionPerformed(e);
        Transferable t = c.getContents(this);
        String transferData = null;
        try {
            transferData = (String) t.getTransferData(DataFlavor.stringFlavor);
            StringSelection selection = new StringSelection((String)old.getTransferData(DataFlavor.stringFlavor));
            c.setContents(old, selection);
        } catch (UnsupportedFlavorException unsupportedFlavorException) {
            unsupportedFlavorException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        BrowserUtil.openURL("https://www.programcreek.com/java-api-examples?api=" + transferData);
    }
}
