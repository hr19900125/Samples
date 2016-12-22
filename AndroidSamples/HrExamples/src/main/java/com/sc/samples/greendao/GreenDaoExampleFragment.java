package com.sc.samples.greendao;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;
import com.sc.samples.gen.NoteActivity;

/**
 *
 */
public class GreenDaoExampleFragment extends BaseExampleFragment{


    @Override
    protected String[] initData() {
        return new String[]{
                "Note GreenDao Example"
        };
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), NoteActivity.class);
                break;
        }

        if(intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
