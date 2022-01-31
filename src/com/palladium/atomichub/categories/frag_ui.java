package com.palladium.atomichub.categories;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;
import android.provider.Settings;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
import android.os.ServiceManager;
import android.app.ActionBar;
import com.palladium.atomichub.*;

import com.palladium.support.preferences.SecureSettingMasterSwitchPreference;
import com.palladium.support.preferences.SecureSettingSwitchPreference;
import com.palladium.support.preferences.SystemSettingMasterSwitchPreference;
import com.palladium.support.preferences.SystemSettingSeekBarPreference;
import com.palladium.support.preferences.SystemSettingSwitchPreference;

import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import com.android.internal.util.palladium.PalladiumUtils;

@SearchIndexable
public class frag_ui extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private IOverlayManager mOverlayService;
    private static final String BRIGHTNESS_SLIDER = "qs_show_brightness";

    private SecureSettingMasterSwitchPreference mBrightnessSlider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_ui);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        //Feature Additon!
        PreferenceScreen prefSet = getPreferenceScreen();
        final ContentResolver resolver = getActivity().getContentResolver();
        mBrightnessSlider = (SecureSettingMasterSwitchPreference)
                findPreference(BRIGHTNESS_SLIDER);
        mBrightnessSlider.setOnPreferenceChangeListener(this);
        boolean enabled = Settings.Secure.getInt(resolver,
                BRIGHTNESS_SLIDER, 1) == 1;
        mBrightnessSlider.setChecked(enabled);
    }
    @Override
    public int getMetricsCategory() {
        return MetricsEvent.PALLADIUM;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    ContentResolver resolver = getActivity().getContentResolver();
    if (preference == mBrightnessSlider) {
            Boolean value = (Boolean) newValue;
            Settings.Secure.putInt(resolver,
                    BRIGHTNESS_SLIDER, value ? 1 : 0);
            return true;
        }
        return false;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.ps_ui);
}