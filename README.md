使用 bytekit时遇到的问题，复现 demo

## 环境
agent 和 测试程序，都是 Oracle JDK8 64 位

## 1. build
```
./gradlew shadow shadowJar
```

## 2. test
构建完成后，找到`build/libs/bytekit-agent.jar`，然后执行测试代码`java/com/github/kongwu/issue/AppAgentTest.java`运行测试。

测试程序 vm options:

```
-XX:+TraceClassLoading -XX:+TraceClassUnloading -javaagent:/Users/jiangxin/IdeaProjects/bytekit-agent/build/libs/bytekit-agent.jar
```

执行后就会出现 `  duplicate class definition`
```
java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class definition for name: "org/springframework/core/NestedRuntimeException"
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:757)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at com.alibaba.deps.org.objectweb.asm.ClassWriter.getCommonSuperClass(ClassWriter.java:1023)
	at com.alibaba.deps.org.objectweb.asm.SymbolTable.addMergedType(SymbolTable.java:1202)
	at com.alibaba.deps.org.objectweb.asm.Frame.merge(Frame.java:1299)
	at com.alibaba.deps.org.objectweb.asm.Frame.merge(Frame.java:1244)
	at com.alibaba.deps.org.objectweb.asm.MethodWriter.computeAllFrames(MethodWriter.java:1610)
	at com.alibaba.deps.org.objectweb.asm.MethodWriter.visitMaxs(MethodWriter.java:1546)
	at com.alibaba.deps.org.objectweb.asm.tree.MethodNode.accept(MethodNode.java:769)
	at com.alibaba.deps.org.objectweb.asm.tree.MethodNode.accept(MethodNode.java:649)
	at com.alibaba.deps.org.objectweb.asm.tree.ClassNode.accept(ClassNode.java:452)
	at com.alibaba.bytekit.utils.AsmUtils.toBytes(AsmUtils.java:82)
	at com.github.kongwu.issue.SimpleClassFileTransformer.transform(SimpleClassFileTransformer.java:21)
	at sun.instrument.TransformerManager.transform(TransformerManager.java:188)
	at sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:428)
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:757)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:757)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	at com.github.kongwu.issue.AppAgentTest.main(AppAgentTest.java:7)
[Loaded org.springframework.core.NestedRuntimeException from file:/Users/jiangxin/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/5.3.6/8f91f60f628075701fde72bb5a43a33feeb27e93/spring-core-5.3.6.jar]
Exception in thread "main" java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class definition for name: "org/springframework/core/NestedRuntimeException"
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:757)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:757)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	at com.github.kongwu.issue.AppAgentTest.main(AppAgentTest.java:7)
```