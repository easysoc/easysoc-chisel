package org.easysoc.plugins.chisel.actions;

import com.intellij.CommonBundle;
import com.intellij.icons.AllIcons;
import com.intellij.ide.GeneralSettings;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class Hello extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    // TODO: insert action logic here
    Project project = e.getData(PlatformDataKeys.PROJECT);
    String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
//    Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages.getInformationIcon());

//    DialogWrapper.DoNotAskOption option = new DialogWrapper.DoNotAskOption() {
//      @Override
//      public boolean isToBeShown() {
//        return true;
////        return GeneralSettings.getInstance().isConfirmExit() && ProjectManager.getInstance().getOpenProjects().length > 0;
//      }
//
//      @Override
//      public void setToBeShown(boolean value, int exitCode) {
//        System.out.println(value);
////        GeneralSettings.getInstance().setConfirmExit(value);
//      }
//
//      @Override
//      public boolean canBeHidden() {
//        return true;
//      }
//
//      @Override
//      public boolean shouldSaveOptionsOnCancel() {
//        return false;
//      }
//
//      @NotNull
//      @Override
//      public String getDoNotShowMessage() {
//        return "Do not ask me again";
//      }
//    };

//    int result = MessageDialogBuilder.yesNo("Title",
//            ApplicationBundle.message("exit.confirm.prompt", "name"))
//            .yesText("Ok")
//            .noText("Cancel")
//            .doNotAsk(option).show();
  }
}
