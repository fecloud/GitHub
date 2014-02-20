工程环境配置:

1.设置Java_Home Java_classpath 
2.设置Apache Ant环境 ,推荐用1.8以上
3.设置Android_SDK环境,具体把${SDK_DIR}/platform-tools,${SDK_DIR}/tools 加入系统PATH


工程简介:

attachment 为案例附件文件夹 如要调试运行,请先运行编辑cp_attachment.bat,然后运行
cp_attachment.bat 会把附件拷贝到手机sdcard里,注意 每个手机的sdcard挂载目录不同,请自行配置
case.xml 为该工程apk中写的测试案例,每写一个测试案例类,请到case.xml中配置
ant.properties 为配置apk签名
local.properties 为配置本机Android SDK 目录,请编译工程前配置
build.xml 为ant工具配置文件,注意把文件project 标签下的name 改成自己的工程名

打包编译工程: 

1.用命令行切换至工程根据目录
2.ant release 编译工程 生成apk 在bin下,编译完成后,在build目录下会有案例打包zip文件,编译中如需要密码 ,密码均为:aspire
3.ant clean 为清除编译

