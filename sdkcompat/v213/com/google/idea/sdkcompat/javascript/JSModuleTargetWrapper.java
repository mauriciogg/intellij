package com.google.idea.sdkcompat.javascript;

import com.intellij.lang.typescript.tsconfig.TypeScriptConfig.ModuleTarget;
public enum JSModuleTargetWrapper {
    COMMON_JS(ModuleTarget.COMMON_JS),
    OTHER(ModuleTarget.OTHER),
    // NODENEXT(ModuleTarget.NODENEXT), not existing in v213 yet
    UNKNOWN(ModuleTarget.UNKNOWN);

    private final ModuleTarget value;

    public ModuleTarget value() {
        return value;
    }

    JSModuleTargetWrapper(ModuleTarget value) {
        this.value = value;
    }
}
