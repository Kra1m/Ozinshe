package com.example.ozinshe.presentation.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentAboutBinding
import com.example.ozinshe.provideNavigationHost


class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private val args: AboutFragmentArgs by navArgs()
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply{
            setNavigationVisibility(false)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply{
            setNavigationVisibility(false)
        }
        Toast.makeText(requireContext(), "${args.movieId}", Toast.LENGTH_SHORT).show()


        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMovieById(token, args.movieId)

        viewModel.moviesByIdResponse.observe(viewLifecycleOwner){

            val adapterScreenshot = ImageAdapter()
            adapterScreenshot.submitList(it.screenshots)
            binding.rcViewScreenshots.adapter = adapterScreenshot
            adapterScreenshot.setOnImageClickListener(object: RcViewImageCallback{
                override fun onClick(link: String) {
                    val action = AboutFragmentDirections.actionAboutFragmentToImageFragment(link)
                    findNavController().navigate(action)
                }
            })


            Glide.with(requireContext())
                .load(it.poster.link).into(binding.imageMovieAbout)

            binding.run {
                btnBackAbout.setOnClickListener {
                    requireActivity().onBackPressed()
                }
                btnPlayAbout.setOnClickListener { click->
                    if(it.video != null){
                        val action = AboutFragmentDirections.actionAboutFragmentToVideoFragment(it.video?.link?:"")
                        findNavController().navigate(action)
                    }else{
                        findNavController().navigate(R.id.seriesFragment)
                    }
                }

                textTvTittleMovie.text = it.name
                textTvAdditionalInfoYear.text = it.year.toString()
                textTvDescription.text = it.description
                tvTextDirector.text = it.director
                tvTexProducer.text = it.producer

                var additionalInfo = " "
                for(i in it.genres){
                    additionalInfo += "· ${i.name}"
                }
                textTvGenres.text = additionalInfo

                if (textTvDescription.lineCount == 1){
                    btnMoreDescription.visibility = View.GONE
                    fadingEdgeLayoutDescription.setFadeSizes(0,0, 0, 0)
                }else{
                    btnMoreDescription.setOnClickListener {
                        if(textTvDescription.maxLines == 100){
                            textTvDescription.maxLines = 7
                            btnMoreDescription.text = "Tолығырақ"
                            fadingEdgeLayoutDescription.setFadeSizes(0,0, 120, 0)
                        }else{
                            textTvDescription.maxLines = 1000
                            btnMoreDescription.text = "Жасыру"
                            fadingEdgeLayoutDescription.setFadeSizes(0,0, 0, 0)
                        }
                    }
                }
            }

            if (it.video == null){
                binding.run{
                    textTvBolimder.text = "${it.seasonCount} сезон, ${it.seriesCount} серия"
                    btnNextAllMovie.setOnClickListener {
                       //
                    }
                    textTvBolimder.setOnClickListener {
                        //
                    }
                }
            }else{
                binding.run{
                    textTvBolimder.visibility = View.GONE
                    textBolimder.visibility = View.GONE
                    btnNextAllMovie.visibility = View.GONE
                }
            }

        }
        viewModel.errorResponse.observe(viewLifecycleOwner){
            Log.d("error aboutfrag", it)
        }

    }


}