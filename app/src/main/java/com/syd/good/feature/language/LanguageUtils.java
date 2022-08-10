package com.syd.good.feature.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/9/29 14:39
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LanguageUtils {

    public static void setAppLocale(Locale locale) {
        if (locale!=null) {
            Configuration configuration = Resources.getSystem().getConfiguration();
            configuration.setLocale(locale);
        }
    }

    public static void  changeAppLanguage(Context context, String language) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, null);
    }
}
