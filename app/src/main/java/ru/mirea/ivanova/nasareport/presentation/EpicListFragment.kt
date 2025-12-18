package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.databinding.FragmentEpicListBinding
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModel
import ru.mirea.ivanova.nasareport.data.repository.EpicRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetEpicUseCase
import ru.mirea.ivanova.nasareport.presentation.adapter.EpicAdapter
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModelFactory

class EpicListFragment : Fragment() {

    private var _binding: FragmentEpicListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpicViewModel by viewModels {
        val repo = EpicRepositoryImpl(requireContext())
        val useCase = GetEpicUseCase(repo)
        EpicViewModelFactory(useCase)
    }

    private val adapter = EpicAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewEpic.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewEpic.adapter = adapter

        viewModel.epicList.observe(viewLifecycleOwner) { list ->
            adapter.setItems(list)
        }

        viewModel.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}