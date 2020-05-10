package org.easysoc.plugins.chisel.module;

import com.intellij.openapi.module.*;
import com.intellij.openapi.util.Key;
import icons.Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

public class ChiselModuleType extends ModuleType<ChiselModuleBuilder> {
  public static final String ID = "CHISEL_MODULE";
  public static final Key EASYSOC_CHIP = Key.create("EASYSOC_CHIP");
  public static final Key NEWPROJECT_TYPE = Key.create("NEWPROJECT_TYPE");

  public ChiselModuleType() {
    super(ID);
  }

  public static ChiselModuleType getInstance() {
    return (ChiselModuleType) ModuleTypeManager.getInstance().findByID(ID);
  }

  @NotNull
  @Override
  public ChiselModuleBuilder createModuleBuilder() {
    return new ChiselModuleBuilder();
  }

  @NotNull
  @Override
  public String getName() {
    return "Chisel";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Create new Chisel project based on templates.";
  }


  @Override
  public Icon getNodeIcon(@Deprecated boolean b) {
    return Icons.Chisel;
  }

}
