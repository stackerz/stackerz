package com.stackerz.app.Images;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stackerz.app.R;
import com.stackerz.app.Stackerz;
import com.stackerz.app.System.DividerItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ImagesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private OnFragmentInteractionListener mListener;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ImagesFragment newInstance(int sectionNumber) {
        ImagesFragment fragment = new ImagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public ImagesFragment() {
    }

    public ArrayList<HashMap<String, String>> jsonList;
    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle extras = getArguments();
        Serializable parsedList = extras.getSerializable("ImagesParsed");
        jsonList = (ArrayList<HashMap<String, String>>)parsedList;
        View rootView = inflater.inflate(R.layout.fragment_images, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.imagesRV);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.supportsPredictiveItemAnimations();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setClickable(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(),jsonList);
        recyclerView.setAdapter(imagesAdapter);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Stackerz) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        //try {
        //    mListener = (OnFragmentInteractionListener) activity;
        //} catch (ClassCastException e) {
        //    throw new ClassCastException(activity.toString()
        //            + " must implement OnFragmentInteractionListener");
        //}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}