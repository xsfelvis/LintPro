package com.lintrules.detectors;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.ConstructorInvocation;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/14.
 * Description:
 */

public class XsfMessageObtainDetector extends Detector implements Detector.JavaScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = XsfMessageObtainDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "MessageObtainUseError";
    private static final String ISSUE_DESCRIPTION = "FBI WARING!:You should use {Message.Obtain()/handler.obtainMessage} directly.";
    private static final String ISSUE_EXPLANATION = "FBI WARING!:You should not call {new Message()} directly. Instead, you should use {handler.obtainMessage} or {Message.Obtain()}.";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;
    private static final String PACKAGE_NAME = "android.os.Message";

    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION
    );

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Collections.<Class<? extends Node>>singletonList(ConstructorInvocation.class);
    }

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new MessageObtainVisitor(context);
    }

    private class MessageObtainVisitor extends ForwardingAstVisitor {
        private final JavaContext javaContext;

        private MessageObtainVisitor(JavaContext context) {
            javaContext = context;
        }


        @Override
        public boolean visitConstructorInvocation(ConstructorInvocation node) {
            JavaParser.ResolvedNode resolvedType = javaContext.resolve(node.astTypeReference());
            JavaParser.ResolvedClass resolvedClass = (JavaParser.ResolvedClass) resolvedType;
            if (resolvedClass != null && resolvedClass.isSubclassOf(PACKAGE_NAME, false)) {
                javaContext.report(ISSUE,
                        node,
                        javaContext.getLocation(node),
                        ISSUE_DESCRIPTION);
                return true;
            }
            return super.visitConstructorInvocation(node);
        }
    }
}
