package com.sc.samples.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.samples.R;
import com.sc.samples.widget.view.TargetedSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

/**
 *
 */
public class RecyclerViewExample2Activity extends AppCompatActivity {

    private TargetedSwipeRefreshLayout swiper;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private LinearLayout mQrBar;
    private TextView mQrBarTitle;

    private RVAdapter mAdapter;
    private ArrayList<String> mDataSet;
    private Animation inAnim;
    private Animation outAnim;

    private static final String[] someTitles = new String[]{
            "This is a Title",
            "Another Short Title",
            "Here's a Much Longer Title for Everyone to Deal With!!",
            "Coming Soon...",
            "Hey Bud, How About That Local Sports Team?",
            "What's Up Pussycat? Whoaaa"
    };

    private Random randy = new Random();

    private int columnCount;
    private int qrBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_example_2);
        inAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_in_top);
        outAnim = AnimationUtils.loadAnimation(this, R.anim.abc_slide_out_top);
        columnCount = 2;
        initQrBar();
        initSwipeRefreshLayout();
        initRecylerView();
    }

    private void initQrBar() {
        mQrBar = (LinearLayout) findViewById(R.id.myQRBar);
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            //获取actionBar的高度，并设置给qrBarHeight
            qrBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        mQrBarTitle = (TextView) findViewById(R.id.tvQRBarTitle);
        mQrBarTitle.setText("Tap to add item at top...");
        mQrBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemAtPosition(0, "NEW CARD ADDED ON: " + new Date().toString());
            }
        });
    }

    private void initSwipeRefreshLayout() {
        swiper = (TargetedSwipeRefreshLayout) findViewById(R.id.swipe_container);
        swiper.setSize(SwipeRefreshLayout.LARGE);
        swiper.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataSet != null && mAdapter != null) {
                            Collections.shuffle(mDataSet);
                            mAdapter.notifyDataSetChanged();
                        }
                        swiper.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }

    private void initRecylerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.grid_rv);
        mRecyclerView.addItemDecoration(new QRBarDecoration(columnCount, qrBarHeight));

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        mDataSet = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDataSet.add(someTitles[randy.nextInt(someTitles.length)]);
        }

        mAdapter = new RVAdapter(this, mDataSet);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 3) {
                    if (mQrBar.getVisibility() == View.VISIBLE) hideQRBar();
                } else if (dy < -3) {
                    if (mQrBar.getVisibility() == View.GONE) showQRBar();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        swiper.setTargetScrollableView(mRecyclerView);
    }

    private void hideQRBar() {
        mQrBar.startAnimation(outAnim);
        mQrBar.setVisibility(View.GONE);
    }

    private void showQRBar() {
        mQrBar.startAnimation(inAnim);
        mQrBar.setVisibility(View.VISIBLE);
    }

    /**
     * Adds an item at postion, and scrolls to that position.
     *
     * @param position index in dataset
     * @param item     to add
     */
    public void addItemAtPosition(int position, String item) {
        mDataSet.add(position, item);
        mAdapter.notifyItemInserted(position);
        mStaggeredGridLayoutManager.scrollToPosition(position);

        // Items added to the top row? Better invalidate the decorator.
        // Delay to ensure that the previous layout pass has completed.
        // NO LONGER REQUIRED:  as of R22 support libs
//        if (position < columnCount) {
//            new Handler().post(new Runnable() {
//                @Override
//                public void run() {
//                    mRecycler.invalidateItemDecorations();
//                }
//            });
//        }
    }


    private static class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> implements View.OnClickListener {

        private static final int[] bgColors = new int[]{
                0xAA000000,
                0xFF800000,
                0xFF008000,
                0xFF000080
        };

        private static final int[] sbgColors = new int[]{
                0xFF000000,
                0xFF600000,
                0xFF006000,
                0xFF000060
        };

        private static Random randy = new Random();

        // Hold the position of the expanded item
        private int expandedPosition = -1;

        private ArrayList<String> mDataSet;
        private Context mContext;

        public RVAdapter(Context context, ArrayList<String> dataSet) {
            this.mContext = context;
            this.mDataSet = dataSet;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_cell, parent, false);
            ViewHolder holder = new ViewHolder(view);
            holder.itemView.setOnClickListener(this);
            holder.itemView.setTag(holder);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int colorIndex = randy.nextInt(bgColors.length);
            holder.title.setText(mDataSet.get(position));
            holder.title.setBackgroundColor(bgColors[colorIndex]);
            holder.subTitle.setBackgroundColor(bgColors[colorIndex]);

            if (position == expandedPosition) {
                holder.expandArea.setVisibility(View.VISIBLE);
            } else {
                holder.expandArea.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        @Override
        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) v.getTag();
            String str = mDataSet.get(holder.getAdapterPosition());

            if (expandedPosition >= 0) {
                int prev = expandedPosition;
                notifyItemChanged(prev);
            }

            expandedPosition = holder.getAdapterPosition();
            notifyItemChanged(expandedPosition);

            Toast.makeText(mContext, "Clicked: " + str, Toast.LENGTH_SHORT).show();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView title;
            TextView subTitle;
            LinearLayout expandArea;

            public ViewHolder(View itemView) {
                super(itemView);

                title = (TextView) itemView.findViewById(R.id.tvTitle);
                subTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
                expandArea = (LinearLayout) itemView.findViewById(R.id.llExpandArea);
            }
        }
    }
}
