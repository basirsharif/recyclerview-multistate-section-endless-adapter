/*
 * Copyright 2016 "Henry Tao <hi@henrytao.me>"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.henrytao.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.henrytao.me.sample.R;
import me.henrytao.recyclerview.config.Visibility;
import me.henrytao.sample.adapter.MultiStateAdapter;
import me.henrytao.sample.adapter.SimpleAdapter;

public class MultiStateFragment extends Fragment {

  public static MultiStateFragment newInstance() {
    return new MultiStateFragment();
  }

  @Bind(android.R.id.list)
  RecyclerView vRecyclerView;

  private MultiStateAdapter mAdapter;

  private SimpleAdapter mSimpleAdapter;

  public MultiStateFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_simple, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mSimpleAdapter = new SimpleAdapter();

    mAdapter = new MultiStateAdapter(mSimpleAdapter, new MultiStateAdapter.OnItemClickListener() {
      @Override
      public void onClick(View view, int position) {
        mAdapter.setVisibility(position, getRandomVisibility());
      }
    });

    vRecyclerView.setHasFixedSize(false);
    vRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    vRecyclerView.setAdapter(mAdapter);
  }

  @Visibility
  private int getRandomVisibility() {
    Random r = new Random();
    int i = r.nextInt(100) % 2;
    return i == 0 ? View.GONE : View.INVISIBLE;
  }
}
