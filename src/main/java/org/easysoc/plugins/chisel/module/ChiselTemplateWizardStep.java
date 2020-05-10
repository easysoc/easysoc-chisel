package org.easysoc.plugins.chisel.module;

import com.intellij.ide.projectWizard.NewProjectWizard;
import com.intellij.ide.projectWizard.ProjectTemplateList;
import com.intellij.ide.projectWizard.ProjectTypeStep;
import com.intellij.ide.util.newProjectWizard.TemplatesGroup;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.platform.ProjectTemplate;
import com.intellij.util.containers.MultiMap;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChiselTemplateWizardStep extends ModuleWizardStep implements Disposable {

  private final WizardContext myContext;
  private final NewProjectWizard myWizard;

  private final ProjectTemplateList myTemplatesList;
  private final MultiMap<TemplatesGroup,ProjectTemplate> myTemplatesMap;

  public ChiselTemplateWizardStep(WizardContext context, SettingsStep settingsStep) throws NoSuchFieldException, IllegalAccessException {
    myContext = context;
    myWizard = (NewProjectWizard)context.getWizard();

    // must new another
    myTemplatesList = new ProjectTemplateList();

    // data come from ProjectTypeStep
    Field fieldTemplatesMap = ProjectTypeStep.class.getDeclaredField("myTemplatesMap");
    fieldTemplatesMap.setAccessible(true);
    myTemplatesMap = (MultiMap<TemplatesGroup, ProjectTemplate>) fieldTemplatesMap.get(settingsStep);

    setTemplatesList();
  }

  private void setTemplatesList() {
    for (TemplatesGroup group : myTemplatesMap.keySet()) {
      if (group.getName().equals("Chisel")) {
        Collection<ProjectTemplate> templates = myTemplatesMap.get(group);
        List<ProjectTemplate> list = new ArrayList<>(templates);
        myTemplatesList.setTemplates(list, false);
        break;
      }
    }
  }

  @Override
  public JComponent getComponent() {
    return myTemplatesList;
  }

  // will call this method after click Next button
  @Override
  public void updateDataModel() {
    ProjectTemplate template = myTemplatesList.getSelectedTemplate();
    myContext.setProjectTemplate(template);
  }

  @Override
  public void dispose() {

  }
}
