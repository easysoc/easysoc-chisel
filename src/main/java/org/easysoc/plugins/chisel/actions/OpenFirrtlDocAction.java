package org.easysoc.plugins.chisel.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;

public class OpenFirrtlDocAction extends OpenChiselDocAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    String searchUrl = "https://www.chisel-lang.org/api/firrtl/latest/";
    if (editor != null && editor.getSelectionModel().hasSelection()) {
      searchUrl = searchUrl + "?search=" + editor.getSelectionModel().getSelectedText();
    }
    BrowserUtil.browse(searchUrl);
  }
}
