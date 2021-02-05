package org.easysoc.plugins.chisel.listeners;

import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.easysoc.plugins.chisel.module.ChiselModuleType;
import org.jetbrains.annotations.NotNull;

public class SbtImportListener implements ProjectDataImportListener {

  private Project myProject;
  private String myType;

  public SbtImportListener(@NotNull Project project) {
    myProject = project;
    myType = (String) myProject.getUserData(ChiselModuleType.NEWPROJECT_TYPE);
  }

  @Override
  public void onImportFinished(String projectPath) {
    if (myType != null) {
      Module module = ModuleManager.getInstance(myProject).findModuleByName(myProject.getName());
      if (module != null) {
        // do some thing

        // just in case reimport
        myProject.putUserData(ChiselModuleType.NEWPROJECT_TYPE,null);
        myType = null;
      }
    }
  }
}
