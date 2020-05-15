package org.easysoc.plugins.chisel.module

import com.intellij.openapi.Disposable
import com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectAware
import com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectId
import com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectRefreshListener

class ChiselProjectAware(id: ExternalSystemProjectId) : ExternalSystemProjectAware {
    override val projectId = id
    override val settingsFiles: Set<String>
        get() = setOf<String>()

    override fun subscribe(listener: ExternalSystemProjectRefreshListener, parentDisposable: Disposable) {}

    override fun refreshProject() {}
}