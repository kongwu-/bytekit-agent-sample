package com.github.kongwu.issue;

import com.alibaba.bytekit.utils.AsmUtils;
import com.alibaba.deps.org.objectweb.asm.tree.ClassNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class SimpleClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // skip jdk classes
        if(className.startsWith("java/") || className.startsWith("sun/") || className.startsWith("javax")){
            return classfileBuffer;
        }
        try {
            ClassNode classNode = AsmUtils.toClassNode(classfileBuffer);

            // 这里会触发一次 class load
            byte[] enhancedClassBytes = AsmUtils.toBytes(classNode);

            // 返回后，jvm会 redefine class，还是会进行一次 class load
            return enhancedClassBytes;
        }catch (Throwable e){
            System.err.println("enhance error!");
            e.printStackTrace();
            return classfileBuffer;
        }
    }
}
