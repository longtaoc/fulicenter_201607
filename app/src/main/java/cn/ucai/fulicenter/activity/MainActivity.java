package cn.ucai.fulicenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.utils.L;

public class MainActivity extends BaseActivity {

    @BindView(R.id.layout_new_good)
    RadioButton layoutNewGood;
    @BindView(R.id.layout_boutique)
    RadioButton layoutBoutique;
    @BindView(R.id.layout_category)
    RadioButton layoutCategory;
    @BindView(R.id.layout_cart)
    RadioButton layoutCart;
    @BindView(R.id.layout_personal_center)
    RadioButton layoutPersonalCenter;

    int index;
    int currentIndex;
    RadioButton [] rbs;
    Fragment[] mfragments;
    Fragment mNewGoodsFragment;
    BoutiqueFragment mBoutiqueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        L.i("MainActivity onCreate");
        super.onCreate(savedInstanceState);
    }

    private void initFragment() {
        mfragments = new Fragment[5];
        mBoutiqueFragment= new BoutiqueFragment();
        mNewGoodsFragment= new NewGoodsFragment();
        mfragments[0] =mNewGoodsFragment;
        mfragments[1] =mBoutiqueFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,mNewGoodsFragment)
                .add(R.id.fragment_container,mBoutiqueFragment)
                .hide(mBoutiqueFragment)
                .show(mNewGoodsFragment)
                .commit();

    }



    @Override
    protected void initView() {
        rbs=new RadioButton[5];
        rbs[0] =layoutNewGood;
        rbs[1] =layoutBoutique;
        rbs[2] =layoutCategory;
        rbs[3] =layoutCart;
        rbs[4] =layoutPersonalCenter;
    }

    @Override
    protected void initData() {
        initFragment();
    }

    @Override
    protected void setListener() {

    }

    public void onCheckedChange(View v) {
        switch (v.getId()){
            case R.id.layout_new_good:
                index=0;
                break;
            case R.id.layout_boutique:
                index=1;
                break;
            case R.id.layout_category:
                index=2;
                break;
            case R.id.layout_cart:
                index=3;
                break;
            case R.id.layout_personal_center:
                index=4;
                break;
        }
        setFragment();

    }

    private void setFragment() {
        if (index!=currentIndex) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(mfragments[currentIndex]);
            if (!mfragments[index].isAdded()){
                ft.add(R.id.fragment_container,mfragments[index]);
            }
            ft.show(mfragments[index]).commit();
        }
        setRadioButtonStatus();
        currentIndex = index;
    }

    private void setRadioButtonStatus() {
        L.e("index="+index);
        for (int i=0;i<rbs.length;i++) {
            if (i == index) {
                rbs[i].setChecked(true);
            }else {
                rbs[i].setChecked(false);
            }
        }
    }
    public void onBackPressed(){
        finish();
    }

}
