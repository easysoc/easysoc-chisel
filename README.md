## Chisel plugin for Intellij platform

### Features

- Create new Chisel project based on ProjectWizard and Templates
- Open/Search Chisel/Firrtl API Documentation

![](https://plugins.jetbrains.com/files/14269/screenshot_21779.png)

### Build

1. [learn](https://www.jetbrains.org/intellij/sdk/docs/basics/basics.html) how to develop an intellij plugin with Gradle

2. modify build.gradle configuration according to your environment

    change `ideDirectory`  to "your idea install path"

    comment or remove `jvmArgs '-Didea.platform.prefix=Chip'`

3. run gradle tasks buildPlugin or runIde directly