package com.ryan.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 *
 */
public class MessageListItem extends RelativeLayout{

    private static final int[] STATE_MESSAGE_READED = new int[]{R.attr.state_message_readed};
    private boolean mMessgeReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageReaded(boolean readed) {
        if(this.mMessgeReaded != readed) {
            mMessgeReaded = readed;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if(mMessgeReaded) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
}
