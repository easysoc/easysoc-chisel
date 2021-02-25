package org.easysoc.plugins.chisel.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;

public class OpenStackOverflowAction extends OpenChiselDocAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Editor editor = e.getData(CommonDataKeys.EDITOR);
    String searchUrl = "https://stackoverflow.com/search?q=%5Bchisel%5D";
    if (editor != null && editor.getSelectionModel().hasSelection()) {
      searchUrl = searchUrl + "+" + editor.getSelectionModel().getSelectedText();
    }
    BrowserUtil.browse(searchUrl);
  }
}
