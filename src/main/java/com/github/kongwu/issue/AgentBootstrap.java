package com.github.kongwu.issue;

import java.lang.instrument.Instrumentation;

public class AgentBootstrap {
    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new SimpleClassFileTransformer());
    }
}