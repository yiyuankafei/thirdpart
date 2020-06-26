#bin/sh
rm -rf ../java/com/leadpms/thirdpart/dao/entity/auto/*.*
rm -rf ../java/com/leadpms/thirdpart/dao/mapper/auto/*.*
rm -rf ../resources/mapper/auto/*.*
#/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/bin/java -Dmaven.multiModuleProjectDirectory=/Users/hanyijun/java/thirdpart/thirdpart-dao "-Dmaven.home=/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3" "-Dclassworlds.conf=/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin/m2.conf" "-Dmaven.ext.class.path=/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven-event-listener.jar" "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=62792:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/boot/plexus-classworlds-2.6.0.jar" org.codehaus.classworlds.Launcher -Didea.version2020.1 org.mybatis.generator:mybatis-generator-maven-plugin:1.3.7:generate


