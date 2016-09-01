package com.sc.samples.design.mvp;

import java.util.List;

/**
 *
 */
public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);

}
