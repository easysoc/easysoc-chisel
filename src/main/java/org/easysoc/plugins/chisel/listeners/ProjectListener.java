package org.easysoc.plugins.chisel.listeners;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.util.SystemProperties;
import org.easysoc.plugins.chisel.module.ChiselModuleType;
import org.jetbrains.annotations.NotNull;

public class ProjectListener implements ProjectManagerListener {

  @Override
  public void projectOpened(@NotNull Project project) {
    String myType = (String) project.getUserData(ChiselModuleType.NEWPROJECT_TYPE);
    // new project
    if (myType != null) {
      // should be same as ChiselModuleType's getName() method value
      if (myType.equals("Chisel")) {
        // only listen once for new project, not for old project
//        project.getMessageBus().connect().subscribe(ProjectDataImportListener.TOPIC,new SbtImportListener(project));

        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String IS_FIRST_IMPORT = "easysoc.chip.first.import";
        if (propertiesComponent.getBoolean(IS_FIRST_IMPORT,true)) {
          showFirstImportMessage();
          propertiesComponent.setValue(IS_FIRST_IMPORT, "false");
        }
      }
    }
  }

  private void showFirstImportMessage() {
    String message = "You are creating a Chisel project for the first time using the Project Wizard.\n" +
            "If you have never used the sbt build tool, the application will try to resolve\n" +
            "and download all dependent packages of the current project.\n" +
            "This may takes some time, please be patient!\n";
    Notifications.Bus.notify(new Notification("","First Project Import Tips",
            message, NotificationType.INFORMATION));
  }
}
