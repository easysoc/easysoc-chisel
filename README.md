## Chisel plugin for Intellij platform

### Features

- Create new Chisel project based on ProjectWizard and Templates
- Create Chisel module file from fileTemplates
- Open/Search Chisel/Firrtl API Documentation

![](images/chisel.png)

### Install

https://plugins.jetbrains.com/plugin/14269-easysoc-chisel

### Build

1. [learn](https://www.jetbrains.org/intellij/sdk/docs/basics/basics.html) how to develop an intellij plugin with Gradle

2. modify build.gradle configuration according to your environment

    change `ideDirectory`  to "your idea install path"

    comment out or remove `jvmArgs '-Didea.platform.prefix=Chip'`

3. run gradle tasks buildPlugin or runIde directly

### Sponsor

Please check if these plugins are helpful to you (Free for students and teachers)

- [EasySoC Verilog](https://plugins.jetbrains.com/plugin/14184-easysoc-verilog) 	Jump to the corresponding Chisel code

| Contact me by WeChat |
| :--------------------------------------------------------: |
| ![wechatpay_160](https://github.com/itviewer/personal/blob/main/wechat.jpg?raw=true) |
