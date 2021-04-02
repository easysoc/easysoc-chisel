package org.easysoc.plugins.chisel.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import icons.Icons;
import org.jetbrains.annotations.NotNull;

public class CreateChiselModuleAction extends CreateFileFromTemplateAction implements DumbAware {
    public CreateChiselModuleAction() {
        super("Chisel Module", "Create new Chisel module", Icons.Chisel);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder
                .setTitle("New Chisel Module")
                .addKind("Module", Icons.Chisel, "Chisel Module")
                .addKind("RawModule", Icons.Chisel, "Chisel RawModule")
                .addKind("MultiIOModule", Icons.Chisel, "Chisel MultiIOModule");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Chisel Module";
    }
}
