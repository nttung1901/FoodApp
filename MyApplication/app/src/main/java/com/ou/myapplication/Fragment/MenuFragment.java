package com.ou.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ou.myapplication.Activity.LoginActivity;
import com.ou.myapplication.Adapter.BestFoodsAdapter;
import com.ou.myapplication.Adapter.CategoryAdapter;
import com.ou.myapplication.Common.Common;
import com.ou.myapplication.Model.Category;
import com.ou.myapplication.Model.Food;
import com.ou.myapplication.R;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MenuFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MenuFragment extends Fragment {
    private ImageView mImageViewLogout;
    private FirebaseDatabase database;
    private ProgressBar mProgressBarCategory;
    private RecyclerView mViewCategory;
    private ProgressBar mProgressBarBestFood;
    private RecyclerView mViewBestFood;

//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MenuFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuFragment newInstance(String param1, String param2) {
//        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        database = FirebaseDatabase.getInstance("https://oufood-19fee-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //Button Logout
        mImageViewLogout = view.findViewById(R.id.button_logout);
        mImageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentUser = null;
                startActivity(new Intent(requireActivity(), LoginActivity.class));
                requireActivity().finish();
            }
        });

        //Category
        DatabaseReference myRefCategory = database.getReference("Category");
        mProgressBarCategory = view.findViewById(R.id.progressBar_category);
        mProgressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> listCategory = new ArrayList<>();
        myRefCategory.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        listCategory.add(issue.getValue(Category.class));
                    }
                    if(listCategory.size()>0){
                        mViewCategory = view.findViewById(R.id.view_category);
                        mViewCategory.setLayoutManager(new GridLayoutManager(requireActivity(),4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(listCategory);
                        mViewCategory.setAdapter(adapter);
                    }
                    mProgressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //BestFood
        DatabaseReference myRefBestFood = database.getReference("Food");
        mProgressBarBestFood = view.findViewById(R.id.progressBar_bestFood);
        mProgressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Food> listBestFood = new ArrayList<>();
        Query query = myRefBestFood.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        listBestFood.add(issue.getValue(Food.class));
                    }
                    if(listBestFood.size()>0){
                        mViewBestFood = view.findViewById(R.id.view_bestFood);
                        mViewBestFood.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new BestFoodsAdapter(listBestFood);
                        mViewBestFood.setAdapter(adapter);
                    }
                    mProgressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}