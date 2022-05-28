package com.souvik.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.souvik.movieapp.databinding.FragmentMovieListingBinding

class MovieListingFragment : Fragment() {

    private lateinit var binding: FragmentMovieListingBinding
    private lateinit var viewModel: MovieViewModel
    private val pageNo: Int = 1
    private lateinit var adapter: MovieListingAdapter
    private var movieList : ArrayList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_listing,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initViewModel()
        setObserver()
    }

    private fun initUI() {
        adapter = MovieListingAdapter(movieList,object : MovieListingAdapter.OnMovieClickListener{
            override fun onClick(adaptePostion: Int) {

            }
        })
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    private fun setObserver() {
        viewModel.movieDataList.observe(viewLifecycleOwner,object : Observer<List<Movie>>{
            override fun onChanged(t: List<Movie>?) {
                if(pageNo==1) {
                    movieList.clear()
                }
                if (t != null) {
                    movieList.addAll(t)
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.fetchData(pageNo)
    }
}