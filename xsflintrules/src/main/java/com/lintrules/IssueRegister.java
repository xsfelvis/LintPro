package com.lintrules;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.lintrules.detectors.XsfActivityFragmentLayoutNameDetector;
import com.lintrules.detectors.XsfBaseActvityDetector;
import com.lintrules.detectors.XsfCustomToastDetector;
import com.lintrules.detectors.XsfMessageObtainDetector;
import com.lintrules.detectors.XsfLogDetector;
import com.lintrules.detectors.XsfViewIdNameDetector;

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
                XsfActivityFragmentLayoutNameDetector.ACTIVITY_LAYOUT_NAME_ISSUE,
                XsfActivityFragmentLayoutNameDetector.FRAGMENT_LAYOUT_NAME_ISSUE,
                XsfMessageObtainDetector.ISSUE,
                XsfCustomToastDetector.ISSUE,
                XsfLogDetector.ISSUE,
                XsfViewIdNameDetector.ISSUE,
                XsfBaseActvityDetector.ISSUE
        );
    }
}
