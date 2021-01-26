package com.syd.good.feature.mdc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/29 15:22
 *
 * @author syd
 * @version 1.0
 */
public class MDCButtonsActivity extends BaseActivity {
    private static final int DISTANCE = 300;
    private static final int DISTANCE2 = 220;
    @BindView(R.id.fab_one)
    FloatingActionButton fabOne;
    @BindView(R.id.fab_two)
    FloatingActionButton fabTwo;
    @BindView(R.id.fab_two_one)
    FloatingActionButton fabTwoOne;
    @BindView(R.id.fab_two_two)
    FloatingActionButton fabTwoTwo;
    @BindView(R.id.fab_two_three)
    FloatingActionButton fabTwoThree;
    @BindView(R.id.fab_two_four)
    FloatingActionButton fabTwoFour;
    private boolean mMenuOpen = false;

    @Override
    protected int layoutId() {
        return R.layout.mdc_activity_buttons;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        fabOne.setOnClickListener(v -> {
            Snackbar.make(v, "normal", Snackbar.LENGTH_SHORT).show();
            if (fabTwo.isShown()) {
                fabTwo.hide();
            } else {
                fabTwo.show();
            }
        });

        fabTwo.setOnClickListener(v -> {
            Snackbar.make(v, "mini", Snackbar.LENGTH_SHORT).show();
        });

        fabTwoOne.setOnClickListener(v -> {
            if (mMenuOpen) {
                hideMenu();
            } else {
                showMenu();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    private void showMenu() {
        mMenuOpen = true;

        fabTwoTwo.animate().x(fabTwoOne.getX() - DISTANCE)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        fabTwoTwo.setVisibility(View.VISIBLE);
                        fabTwoThree.setVisibility(View.VISIBLE);
                        fabTwoFour.setVisibility(View.VISIBLE);
                    }
                })
                .start();
        fabTwoThree.animate().x(fabTwoOne.getX() - DISTANCE2)
                .y(fabTwoOne.getY() - DISTANCE2)
                .setDuration(500)
                .start();
        fabTwoFour.animate().y(fabTwoOne.getY() - DISTANCE)
                .setDuration(500)
                .start();


    }

    private void hideMenu() {
        mMenuOpen = false;
        fabTwoTwo.animate().x(fabTwoOne.getX())
                .setDuration(500)
                .start();
        fabTwoThree.animate().x(fabTwoOne.getX())
                .y(fabTwoOne.getY())
                .setDuration(500)
                .start();
        fabTwoFour.animate().y(fabTwoOne.getY())
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!mMenuOpen){
                            fabTwoFour.setVisibility(View.GONE);
                            fabTwoTwo.setVisibility(View.GONE);
                            fabTwoThree.setVisibility(View.GONE);
                        }

                    }
                })
                .start();

    }
}
