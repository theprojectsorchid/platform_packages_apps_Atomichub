package com.palladium.atomichub.categories;

import android.content.ContentResolver;
import android.os.Bundle;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
import android.os.ServiceManager;
import android.app.ActionBar;
import com.palladium.atomichub.*;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;
import android.provider.SearchIndexableResource;
import java.util.ArrayList;
import java.util.List;

@SearchIndexable(forTarget = SearchIndexable.ALL & ~SearchIndexable.ARC)
public class frag_ui extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private IOverlayManager mOverlayService;
    private static final String KEY_TORCH_LONG_PRESS_POWER_TIMEOUT = "torch_long_press_power_timeout";

    private ListPreference mTorchLongPressPowerTimeout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_ui);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        //Feature Additon!
        final PreferenceScreen prefScreen = getPreferenceScreen();

        mTorchLongPressPowerTimeout = findPreference(KEY_TORCH_LONG_PRESS_POWER_TIMEOUT);
        mTorchLongPressPowerTimeout.setOnPreferenceChangeListener(this);
        int TorchTimeout = Settings.System.getInt(getContentResolver(),
                Settings.System.TORCH_LONG_PRESS_POWER_TIMEOUT, 0);
        mTorchLongPressPowerTimeout.setValue(Integer.toString(TorchTimeout));
        mTorchLongPressPowerTimeout.setSummary(mTorchLongPressPowerTimeout.getEntry());    

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
        final String key = preference.getKey();
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mTorchLongPressPowerTimeout) {
            String TorchTimeout = (String) newValue;
            int TorchTimeoutValue = Integer.parseInt(TorchTimeout);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.TORCH_LONG_PRESS_POWER_TIMEOUT, TorchTimeoutValue);
            int TorchTimeoutIndex = mTorchLongPressPowerTimeout
                    .findIndexOfValue(TorchTimeout);
            mTorchLongPressPowerTimeout
                    .setSummary(mTorchLongPressPowerTimeout.getEntries()[TorchTimeoutIndex]);
            return true;
        }
        return false;
    }

    /**
     * For Search
     */

    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {

                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();
                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.ps_ui;
                    result.add(sir);
                    return result;
                }

           @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
                }
    };
}