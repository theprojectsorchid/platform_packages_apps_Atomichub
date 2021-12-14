package com.palladium.atomichub;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.android.internal.logging.nano.MetricsProto;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.app.ActionBar;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.palladium.atomichub.categories.frag_ui;
import com.palladium.atomichub.categories.frag_theme;
import com.palladium.atomichub.categories.frag_keys;
import com.palladium.atomichub.categories.frag_misc;
import com.palladium.atomichub.categories.frag_lock;
import com.palladium.atomichub.categories.frag_statusbar;

public class Atomichub extends SettingsPreferenceFragment implements  View.OnClickListener{

    CardView mctheme, mcui, mcmisc, mclock, mcstatus, mclogo;
    ImageView ivui, ivtheme, ivstatus, ivmisc, ivlock, ivlogo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.atomichub, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
	getActivity().setTitle(R.string.atomichubtitle);
        ivui = view.findViewById(R.id.ivui);
        ivtheme = view.findViewById(R.id.ivtheme);
        ivlock = view.findViewById(R.id.ivlock);
        ivstatus = view.findViewById(R.id.ivstatus);
        ivmisc = view.findViewById(R.id.ivmisc);
        ivlogo = view.findViewById(R.id.ivlogo);
        ivui.setOnClickListener(this);
        ivtheme.setOnClickListener(this);
        ivlock.setOnClickListener(this);
        ivstatus.setOnClickListener(this);
        ivmisc.setOnClickListener(this);
        ivlogo.setOnClickListener(this);

        mctheme = view.findViewById(R.id.mctheme);
        mcui = view.findViewById(R.id.mcui);
        mclock = view.findViewById(R.id.mclock);
        mcstatus = view.findViewById(R.id.mcstatus);
        mcmisc = view.findViewById(R.id.mcmisc);
        mclogo = view.findViewById(R.id.mclogo);

        mctheme.setOnClickListener(this);
        mcui.setOnClickListener(this);
        mclock.setOnClickListener(this);
        mcstatus.setOnClickListener(this);
        mcmisc.setOnClickListener(this);
        mclogo.setOnClickListener(this);
        

    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.ivui){
            fragchange(new frag_ui());        
        }

        if(id == R.id.ivtheme){
            fragchange(new frag_theme());
        }

        if(id == R.id.ivlock){
            fragchange(new frag_lock());
        }

        if(id == R.id.ivstatus){
            fragchange(new frag_statusbar());
        }

        if(id == R.id.ivmisc){
            fragchange(new frag_misc());
        }

         if(id == R.id.mcui){
            fragchange(new frag_ui());        
        }

        if(id == R.id.mctheme){
            fragchange(new frag_theme());
        }

        if(id == R.id.mclock){
            fragchange(new frag_lock());
        }

        if(id == R.id.mcstatus){
            fragchange(new frag_statusbar());
        }

        if(id == R.id.mcmisc){
            fragchange(new frag_misc());
        }

    }

    public void fragchange(Fragment f){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(this.getId(), f);
        ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.PALLADIUM;
    }



}
