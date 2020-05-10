package org.easysoc.plugins.chisel.module;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChiselModuleBuilder extends ModuleBuilder {

  private SettingsStep mySettingsStep;

  @Override
  public ModuleType getModuleType() {
    return ChiselModuleType.getInstance();
  }

  @Override
  public int getWeight() {
    return 999;
  }

  @Override
  public boolean isTemplateBased() {
    return false;
  }

  @Override
  public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep) {
    // ProjectWizardStepFactory need java plugin
    mySettingsStep = settingsStep;
    ModuleWizardStep step = ProjectWizardStepFactory.getInstance().createJavaSettingsStep(settingsStep, this,
            this::isSuitableSdkType);
    return step;
  }

  // fix setTemplatesList in ProjectTypeStep
  @Override
  public @Nullable ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
    ChiselTemplateWizardStep step = null;
    try {
      step = new ChiselTemplateWizardStep(context,mySettingsStep);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Disposer.register(parentDisposable, step);
    return step;
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new ChiselModuleSettingStep(wizardContext)};
  }
}
