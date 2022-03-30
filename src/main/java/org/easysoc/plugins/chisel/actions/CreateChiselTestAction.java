package org.easysoc.plugins.chisel.actions;

import com.intellij.ide.actions.CreateFileAction;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.ide.fileTemplates.actions.CreateFromTemplateActionBase;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.util.IncorrectOperationException;
import icons.Icons;
import org.apache.velocity.runtime.parser.ParseException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class CreateChiselTestAction extends CreateFileFromTemplateAction implements DumbAware {
    public CreateChiselTestAction() {
        super("Chisel Test", "Create new Chisel Test", Icons.Chisel);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle("New Chisel Test")
                .addKind("Chisel Test", Icons.Chisel, "Chisel Test");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Chisel Test";
    }

    @Override
    protected PsiFile createFileFromTemplate(final String name, final FileTemplate template, final PsiDirectory dir) {
        return myCreateFileFromTemplate(name, template, dir, getDefaultTemplateProperty(), true);
    }

    @Nullable
    public static PsiFile myCreateFileFromTemplate(@Nullable String name,
                                                   @NotNull FileTemplate template,
                                                   @NotNull PsiDirectory dir,
                                                   @Nullable String defaultTemplateProperty,
                                                   boolean openFile) {
        return myCreateFileFromTemplate(name, template, dir, defaultTemplateProperty, openFile, Collections.emptyMap());
    }

    @Nullable
    public static PsiFile myCreateFileFromTemplate(@Nullable String name,
                                                   @NotNull FileTemplate template,
                                                   @NotNull PsiDirectory dir,
                                                   @Nullable String defaultTemplateProperty,
                                                   boolean openFile,
                                                   @NotNull Map<String, String> liveTemplateDefaultValues) {
        if (name != null) {
            CreateFileAction.MkDirs mkdirs = new CreateFileAction.MkDirs(name, dir);
            name = mkdirs.newName;
            dir = mkdirs.directory;
        }

        Project project = dir.getProject();
        try {
            Properties p = FileTemplateManager.getInstance(dir.getProject()).getDefaultProperties();
            p.put(FileTemplate.ATTRIBUTE_NAME, name);
            name += "Test";
            PsiFile psiFile = FileTemplateUtil.createFromTemplate(template, name, p, dir)
                    .getContainingFile();
            SmartPsiElementPointer<PsiFile> pointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer(psiFile);

            VirtualFile virtualFile = psiFile.getVirtualFile();
            if (virtualFile != null) {
                if (openFile) {
                    if (template.isLiveTemplateEnabled()) {
                        CreateFromTemplateActionBase.startLiveTemplate(psiFile, liveTemplateDefaultValues);
                    } else {
                        FileEditorManager.getInstance(project).openFile(virtualFile, true);
                    }
                }
                if (defaultTemplateProperty != null) {
                    PropertiesComponent.getInstance(project).setValue(defaultTemplateProperty, template.getName());
                }
                return pointer.getElement();
            }
        } catch (ParseException e) {
            throw new IncorrectOperationException("Error parsing Velocity template: " + e.getMessage(), (Throwable) e);
        } catch (IncorrectOperationException e) {
            throw e;
        } catch (Exception e) {
            LOG.error(e);
        }

        return null;
    }
}
