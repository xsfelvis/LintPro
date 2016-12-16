package com.lintrules.detectors;

import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Attr;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import static com.android.SdkConstants.VALUE_ID;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/15.
 * Description: check layout id
 */

public class XsfViewIdNameDetector extends ResourceXmlDetector {
    private static final Class<? extends Detector> DETECTOR_CLASS = XsfViewIdNameDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.RESOURCE_FILE_SCOPE;
    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            DETECTOR_SCOPE
    );

    private static final String ISSUE_ID = "IdNameError";
    private static final String ISSUE_DESCRIPTION = "Name should be in accordance with the specification";
    private static final String ISSUE_EXPLANATION = "Name should be in accordance with the specification,that is a good habit";
    private static final Category ISSUE_CATEGORY = Category.LINT;
    private static final int ISSUE_PRIORITY = 9;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;
    private static final String ANDROID_ID = "android:id";

    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION
    );
    //name space
    String reportStrFormat = "FBI WARING!: {%s} Prefix Must Be: {%s}";
    /*linearlayout*/
    private static final String IDHEADER_LINEARLAYOUT_ABBREVIATION = "ll";
    private static final String ID_LAYOUT_LINEARLAYOUT = "LinearLayout";
    /*relativelayout*/
    private static final String IDHEADER_RELATIVELAYOUT_ABBREVIATION = "rl";
    private static final String ID_LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    /*framelayout*/
    private static final String IDHEADER_FRAMELAYOUT_ABBREVIATION = "fl";
    private static final String ID_LAYOUT_FRAMELAYOUT = "FrameLayout";
    /*tablayout*/
    private static final String IDHEADER_TABLAYOUT_ABBREVIATION = "tl";
    private static final String ID_LAYOUT_TABLAYOUT = "TableLayout";
    /*button*/
    private static final String IDHEADER_BUTTON_ABBREVIATION = "btn";
    private static final String ID_BUTTON_BUTTON = "Button";
    /*radiobuttion*/
    private static final String IDHEADER_RADIOBUTTON_ABBREVIATION = "rbtn";
    private static final String ID_BUTTON_RADIOBUTTON = "RadioButton";
    /*imagebuttion*/
    private static final String IDHEADER_IMAGEBUTTON_ABBREVIATION = "ibtn";
    private static final String ID_BUTTON_IMAGEBUTTON = "ImageButton";
    /*text*/
    private static final String IDHEADER_TEXT_ABBREVIATION = "tv";
    private static final String ID_TEXT_TEXTVIEW = "TextView";
    /*edtext*/
    private static final String IDHEADER_EDITTEXT_ABBREVIATION = "et";
    private static final String ID_TEXT_EDITTEXT = "EditText";
    /*imageView*/
    private static final String IDHEADER_IMAGEVIEW_ABBREVIATION = "iv";
    private static final String ID_IMAGEVIEW = "ImageView";
    /*webView*/
    private static final String IDHEADER_WEBVIEW_ABBREVIATION = "wv";
    private static final String ID_WEBVIEW = "WebView";
    /*checkBox*/
    private static final String IDHEADER_CHECKBOX_ABBREVIATION = "cb";
    private static final String ID_CHECKBOX = "CheckBox";
    /*progressBar*/
    private static final String IDHEADER_PROGRESSBAR_ABBREVIATION = "pb";
    private static final String ID_PROGRESSBAR = "ProgressBar";
    /*seekBar*/
    private static final String IDHEADER_SEEKBAR_ABBREVIATION = "sb";
    private static final String ID_SEEKBAR = "SeekBar";
    /*ScrollView*/
    private static final String IDHEADER_SCROLLVIEW_ABBREVIATION = "sv";
    private static final String ID_SCROLLVIEW = "ScrollView";
    /*GridView*/
    private static final String IDHEADER_GRIDVIEW_ABBREVIATION = "gv";
    private static final String ID_GRIDVIEW = "GridView";
    /*listView*/
    private static final String IDHEADER_LISTVIEW_ABBREVIATION = "lv";
    private static final String ID_LISTVIEW = "ListView";
    /*ExpandableListView*/
    private static final String IDHEADER_EXPANDABLE_LISTVIEW_ABBREVIATION = "elv";
    private static final String ID_EXPANDABLE_LISTVIEW = "ExpandableListView";


    @Override
    public void beforeCheckProject(Context context) {
        super.beforeCheckProject(context);
    }

    @Override
    public boolean appliesTo(ResourceFolderType folderType) {
        return ResourceFolderType.LAYOUT == folderType;
    }

    @Override
    public Collection<String> getApplicableAttributes() {
        return Collections.singletonList(VALUE_ID);
    }

    @Override
    public void visitAttribute(XmlContext context, Attr attribute) {
        super.visitAttribute(context, attribute);
        String prnMain = context.getMainProject().getDir().getPath();
        String prnCur = context.getProject().getDir().getPath();
        //only care id node
        //only care xml file that only in usefull ,exclude directory Build
        if (attribute.getName().startsWith(ANDROID_ID) && prnMain.equals(prnCur)) {
            checkNameSpace(context, attribute);
        }
    }

    private void checkNameSpace(XmlContext context, Attr attribute) {
        String tagName = attribute.getOwnerElement().getTagName();
        //layout
        int startIndex = 0;
        String idName = attribute.getValue().substring(5);
        String attrRight = "";
        switch (tagName) {
            case ID_LAYOUT_LINEARLAYOUT:
                attrRight = IDHEADER_LINEARLAYOUT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_LAYOUT_RELATIVELAYOUT:
                attrRight = IDHEADER_RELATIVELAYOUT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_LAYOUT_FRAMELAYOUT:
                attrRight = IDHEADER_FRAMELAYOUT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_LAYOUT_TABLAYOUT:
                attrRight = IDHEADER_TABLAYOUT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_BUTTON_BUTTON:
                attrRight = IDHEADER_BUTTON_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_BUTTON_IMAGEBUTTON:
                attrRight = IDHEADER_IMAGEBUTTON_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_BUTTON_RADIOBUTTON:
                attrRight = IDHEADER_RADIOBUTTON_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_TEXT_TEXTVIEW:
                attrRight = IDHEADER_TEXT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_TEXT_EDITTEXT:
                attrRight = IDHEADER_EDITTEXT_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_IMAGEVIEW:
                attrRight = IDHEADER_IMAGEVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_WEBVIEW:
                attrRight = IDHEADER_WEBVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_CHECKBOX:
                attrRight = IDHEADER_CHECKBOX_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_PROGRESSBAR:
                attrRight = IDHEADER_PROGRESSBAR_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_SEEKBAR:
                attrRight = IDHEADER_SEEKBAR_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_LISTVIEW:
                attrRight = IDHEADER_LISTVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_GRIDVIEW:
                attrRight = IDHEADER_GRIDVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_EXPANDABLE_LISTVIEW:
                attrRight = IDHEADER_EXPANDABLE_LISTVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
            case ID_SCROLLVIEW:
                attrRight = IDHEADER_SCROLLVIEW_ABBREVIATION;
                startIndex = idName.indexOf(attrRight);
                break;
        }

        if (startIndex != 0) {
            String reportStr = String.format(reportStrFormat, idName, attrRight);
            context.report(ISSUE,
                    attribute,
                    context.getLocation(attribute),
                    reportStr);
        }
    }
}
