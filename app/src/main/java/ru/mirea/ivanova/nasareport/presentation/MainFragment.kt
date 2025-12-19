package ru.mirea.ivanova.nasareport.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.databinding.FragmentMainBinding
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModel
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import ru.mirea.ivanova.nasareport.data.repository.NasaRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetApodUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModelFactory
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidPreviewViewModel
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidPreviewViewModelFactory
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val apodViewModel: ApodViewModel by viewModels {
        val repo = NasaRepositoryImpl(requireContext())
        val useCase = GetApodUseCase(repo)
        ApodViewModelFactory(useCase)
    }

    private val asteroidViewModel: AsteroidPreviewViewModel by viewModels {
        val repo = NasaRepositoryImpl(requireContext())
        val useCase = GetAsteroidsUseCase(repo)
        AsteroidPreviewViewModelFactory(useCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apodViewModel.mergedData.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                Picasso.get()
                    .load(list[0].imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_background)
                    .into(binding.apodImage)
            }
        }

        apodViewModel.refresh()

        asteroidViewModel.top3Asteroids.observe(viewLifecycleOwner) { list ->
            binding.asteroid1.text = list.getOrNull(0)?.name ?: "-"
            binding.asteroid2.text = list.getOrNull(1)?.name ?: "-"
            binding.asteroid3.text = list.getOrNull(2)?.name ?: "-"
        }

        binding.btnEpic.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_epicListFragment)
        }

        binding.btnAsteroids.setOnClickListener {
            startActivity(Intent(requireContext(), AsteroidsActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}