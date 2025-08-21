package com.example.ozinshe.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentHomeBinding
import com.example.ozinshe.presentation.login.LoginViewModel
import com.example.ozinshe.provideNavigationHost
import kotlin.getValue

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)

        val adapterMainMovie = MainMovieAdapter()
        binding.rcMainMovies.adapter = adapterMainMovie
        viewModel.mainMoviesResponse.observe(viewLifecycleOwner){
            adapterMainMovie.submitList(it)
        }

        val adapterMoviesByCategory = MoviesByCategoryMainAdapter()
        viewModel.getMoviesByCategoryMain(token)
        viewModel.moviesByCategoryMainModel.observe(viewLifecycleOwner){
            binding.rcMainMoviesCategories1.adapter = adapterMoviesByCategory
            binding.tvCategoryTitle1.text = it[0].categoryName
            adapterMoviesByCategory.submitList(it[0].movies )

            binding.rcMainMoviesCategories2.adapter = adapterMoviesByCategory
            binding.tvCategoryTitle2.text = it[1].categoryName
            adapterMoviesByCategory.submitList(it[1].movies)
        }

        adapterMainMovie.setOnMovieClickListener(object :RcViewMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })


        adapterMoviesByCategory.setOnMovieClickListener(object : RcViewMainMoviesCallback{
            override fun onClick(movieId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToAboutFragment(movieId)
                findNavController().navigate(action)
            }
        })


    }

}