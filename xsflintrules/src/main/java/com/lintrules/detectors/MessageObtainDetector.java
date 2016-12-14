package com.lintrules.detectors;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Collections;
import java.util.List;

import lombok.ast.ConstructorInvocation;
import lombok.ast.Node;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/14.
 * Description:
 */

public class MessageObtainDetector extends Detector implements Detector.JavaScanner{
    public static final Issue ISSUE = Issue.create(
            "MessageObtainNotUsed",
            "You should not call `new Message()` directly.",
            "You should not call `new Message()` directly. Instead, you should use `handler.obtainMessage` or `Message.Obtain()`.",
            Category.CORRECTNESS,
            9,
            Severity.ERROR,
            new Implementation(MessageObtainDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Collections.<Class<? extends Node>>singletonList(ConstructorInvocation.class);
    }

}
