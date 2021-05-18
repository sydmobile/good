package com.syd.good.feature.tabhost

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syd.good.R
import com.syd.good.databinding.TabhostFragmentContentBinding
/**
 * A simple [Fragment] subclass.
 * Use the [TabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabFragment : Fragment() {

    lateinit var viewBinding: TabhostFragmentContentBinding
    var mViewContent:View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mViewContent == null){

            viewBinding = TabhostFragmentContentBinding.inflate(inflater,container,false)
            mViewContent = viewBinding.root
        }

        val parent:ViewGroup = mViewContent!!.parent as ViewGroup
        if (parent!=null){
            parent.removeView(mViewContent);
        }

        return mViewContent as View


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tv.setText("Page:$tag")
    }


}