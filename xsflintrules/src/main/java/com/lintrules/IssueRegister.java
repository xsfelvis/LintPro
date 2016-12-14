package com.lintrules;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.lintrules.detectors.ActivityFragmentLayoutNameDetector;
import com.lintrules.detectors.LoggerUsageDetector;

import java.util.Arrays;
import java.util.List;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/14.
 * Description: IssueReguster
 */

public class IssueRegister extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        System.out.println("*******XSF LINT RULES WORKS*******");
        return Arrays.asList(
                LoggerUsageDetector.ISSUE,
                ActivityFragmentLayoutNameDetector.ACTIVITY_LAYOUT_NAME_ISSUE,
                ActivityFragmentLayoutNameDetector.FRAGMENT_LAYOUT_NAME_ISSUE
        );
    }
}
