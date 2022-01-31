package com.palladium.atomichub.categories;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.provider.Settings;
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

public class frag_lock extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private IOverlayManager mOverlayService;
    private static final String KEY_EDGE_LIGHTNING = "pulse_ambient_light";


    private SystemSettingMasterSwitchPreference mEdgeLightning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_lock);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        //Feature Additon!
        PreferenceScreen prefSet = getPreferenceScreen();
        final ContentResolver resolver = getActivity().getContentResolver();
        mEdgeLightning = (SystemSettingMasterSwitchPreference)
                findPreference(KEY_EDGE_LIGHTNING);
        boolean enabled = Settings.System.getIntForUser(resolver,
                KEY_EDGE_LIGHTNING, 0, UserHandle.USER_CURRENT) == 1;
        mEdgeLightning.setChecked(enabled);
        mEdgeLightning.setOnPreferenceChangeListener(this);
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
        if (preference == mEdgeLightning) {
            boolean value = (Boolean) newValue;
            Settings.System.putIntForUser(resolver, KEY_EDGE_LIGHTNING,
                    value ? 1 : 0, UserHandle.USER_CURRENT);
            return true;
        }
        return false;
    }
}