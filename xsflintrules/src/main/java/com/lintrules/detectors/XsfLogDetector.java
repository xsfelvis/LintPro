package com.lintrules.detectors;

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
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/15.
 * Description:
 */

public class XsfLogDetector extends Detector implements Detector.JavaScanner {
    private static final Class<? extends Detector> DETECTOR_CLASS = XsfLogDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "LogUseError";
    private static final String ISSUE_DESCRIPTION = "FBI WARING!:You should use our {AppLog}";
    private static final String ISSUE_EXPLANATION = "You should NOT use Log directly. Instead you should use AppLog we offered.";
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;
    private static final String CHECK_CODE = "System.out.println";
    private static final String CHECK_PACKAGE = "android.util.Log";

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
        return Collections.<Class<? extends Node>>singletonList(MethodInvocation.class);
    }

    @Override
    public AstVisitor createJavaVisitor(final JavaContext context) {
        return new LogVisit(context);
    }

    private class LogVisit extends ForwardingAstVisitor {
        private final JavaContext javaContext;

        private LogVisit(JavaContext context) {
            javaContext = context;
        }

        @Override
        public boolean visitMethodInvocation(MethodInvocation node) {

            if (node.toString().startsWith(CHECK_CODE)) {
                javaContext.report(ISSUE, node, javaContext.getLocation(node),
                        ISSUE_DESCRIPTION);
                return true;
            }
            JavaParser.ResolvedNode resolve = javaContext.resolve(node);
            if (resolve instanceof JavaParser.ResolvedMethod) {
                JavaParser.ResolvedMethod method = (JavaParser.ResolvedMethod) resolve;
                JavaParser.ResolvedClass containingClass = method.getContainingClass();
                if (containingClass.matches(CHECK_PACKAGE)) {
                    javaContext.report(ISSUE, node, javaContext.getLocation(node),
                            ISSUE_DESCRIPTION);
                    return true;
                }
            }
            return super.visitMethodInvocation(node);
        }
    }
}
