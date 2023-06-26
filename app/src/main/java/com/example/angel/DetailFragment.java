package com.example.angel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.db.MenstruationDao;

import java.util.List;

public class DetailFragment extends Fragment {
	private ListView slvSuggest;
	private View view;
	private MenstruationDao mtDao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view == null){
			view = inflater.inflate(R.layout.fragment_detail, container, false);
		}
		slvSuggest = (ListView) view.findViewById(R.id.slv_suggest);
		
		mtDao = new MenstruationDao(getActivity());
		List<MenstruationModel> mtmList = mtDao.getMTModelList(0,0);
		slvSuggest.setAdapter(new SlvSuggestAdapter(getActivity(), mtmList));
		return view;
	}
}
