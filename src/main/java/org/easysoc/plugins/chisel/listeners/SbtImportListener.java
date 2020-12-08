package org.easysoc.plugins.chisel.listeners;

import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.externalSystem.autoimport.AutoImportProjectTracker;
import com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectId;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.easysoc.plugins.chisel.module.ChiselModuleType;
import org.easysoc.plugins.chisel.module.ChiselProjectAware;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.sbt.project.SbtProjectSystem;

import java.io.File;

public class SbtImportListener implements ProjectDataImportListener {

  private Project myProject;
  private String myType;

  public SbtImportListener(@NotNull Project project) {
    myProject = project;
    myType = (String) myProject.getUserData(ChiselModuleType.NEWPROJECT_TYPE);

    // fix WARN Project isn't registered by id=ExternalSystemProjectId ...
    if (myType != null) {
      AutoImportProjectTracker importTracker = AutoImportProjectTracker.getInstance(project);

      ExternalSystemProjectId projectId = new ExternalSystemProjectId(SbtProjectSystem.Id, project.getBasePath());
      importTracker.register(new ChiselProjectAware(projectId));
    }
  }

  @Override
  public void onImportFinished(String projectPath) {
    if (myType != null) {
      Module module = ModuleManager.getInstance(myProject).findModuleByName(myProject.getName());
      if (module != null) {

        // add path to module dependencies path?
        File classDir = new File(CompilerPaths.getModuleOutputPath(module,false));
        File testClassDir = new File(CompilerPaths.getModuleOutputPath(module,true));
        classDir.mkdirs();
        testClassDir.mkdirs();

        VirtualFileManager.getInstance().asyncRefresh(() -> {});

        // just in case reimport
        myProject.putUserData(ChiselModuleType.NEWPROJECT_TYPE,null);
      }
    }
  }
}
