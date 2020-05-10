## Chisel plugin for Intellij platform

### Features

- Create new Chisel project based on ProjectWizard and Templates
- Optional primary maven repository ([Central](https://repo1.maven.org/maven2) or [AliRepo](https://maven.aliyun.com/repository/central))

### Build

1. [learn](https://www.jetbrains.org/intellij/sdk/docs/basics/basics.html) how to develop an intellij plugin with Gradle

2. modify build.gradle configuration according to your environment

    change `ideDirectory`  to "your idea install path"

    comment or remove `jvmArgs '-Didea.platform.prefix=Chip'`

3. run gradle tasks buildPlugin or runIde directly

### About AliRepo

如果你在国内，使用 [AliRepo](https://maven.aliyun.com/repository/central) 能够加快软件包下载速度，但由于 sbt 自1.3 版本开始使用 [Coursier](https://get-coursier.io/) 依赖解析引擎，除非你通过[此方式](https://get-coursier.io/blog/2019/07/05/1.1.0-M14)重定向 Central 仓库，否则目前 Coursier 解析引擎始终默认首选 Central 仓库去下载 sbt 相关依赖（可能不包括应用本身的依赖）。

目前解决方法：

- 使用 ~/.config/coursier/mirror.properties 配置
- 在 hosts 文件中屏蔽 Central 仓库 (127.0.0.1 repo1.maven.org )

无论哪种方法，都有可能（极少情况）出现代理仓库找不到，而在 repo1.maven.org 却能下载的软件包，根据需要取消上述配置即可。

**如果你选择 AliRepo，但不采用上述配置，因为解析器总是尝试链接 [Central](https://repo1.maven.org/maven2) 仓库，该网址不是不能链接，只是访问速度慢，因此依赖解析、下载过程可能依旧龟速。**

使用向导创建并选择 AliRepo 的项目，其配置在 项目目录/.idea/sbt.xml中：

```
<option name="customLauncherEnabled" value="true" />
<option name="customLauncherPath" value="$APPLICATION_PLUGINS_DIR$/easysoc-chisel/launcher/sbt-launch.jar" />
```

当使用 Scala 官方插件创建 sbt 项目或者打开没有上述配置的其它已存在项目时，其**默认使用 Scala 插件自己的 sbt-launch.jar，没有 AliRepo 的配置，所以默认使用 Central 仓库**，如需使用 AliRepo，可通过下面设置

```
File | Settings | Build, Execution, Deployment | Build Tools | sbt
```

将 launcher 设为本插件的 sbt-launch.jar