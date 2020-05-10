package org.easysoc.plugins.chisel.listeners;

import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.easysoc.plugins.chisel.module.ChiselModuleType;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class SbtImportListener implements ProjectDataImportListener {

  private Project myProject;

  public SbtImportListener(@NotNull Project project) {
    myProject = project;
  }

  @Override
  public void onImportFinished(String projectPath) {
    String myType = (String) myProject.getUserData(ChiselModuleType.NEWPROJECT_TYPE);
    if (myType != null) {
      Module module = ModuleManager.getInstance(myProject).findModuleByName(myProject.getName());
      if (module != null) {
        // fix "Cannot find or load main class ……" error when execute run configuration
        new File(CompilerPaths.getModuleOutputPath(module,false)).mkdirs();
        new File(CompilerPaths.getModuleOutputPath(module,true)).mkdirs();

        // just in case reimport
        myProject.putUserData(ChiselModuleType.NEWPROJECT_TYPE,null);
      }
    }
  }
}
