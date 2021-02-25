package org.easysoc.plugins.chisel.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class OpenChiselDocAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    String searchUrl = "https://www.chisel-lang.org/api/latest/";
    if (editor != null && editor.getSelectionModel().hasSelection()) {
      searchUrl = searchUrl + "?search=" + editor.getSelectionModel().getSelectedText();
    }
    BrowserUtil.browse(searchUrl);
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    VirtualFile file = e.getData(PlatformDataKeys.VIRTUAL_FILE);
    Presentation presentation = e.getPresentation();

    boolean enable = file != null && file.getFileType().getName().equals("Scala");
    presentation.setVisible(enable);
  }
}
