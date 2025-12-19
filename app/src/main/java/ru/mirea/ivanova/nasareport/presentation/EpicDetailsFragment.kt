package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.databinding.FragmentEpicDetailsBinding
import ru.mirea.ivanova.nasareport.domain.models.EpicImage
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModel
import androidx.fragment.app.viewModels
import ru.mirea.ivanova.nasareport.data.repository.EpicRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetEpicUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModelFactory

class EpicDetailsFragment : Fragment() {

    private var _binding: FragmentEpicDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpicViewModel by viewModels {
        val repo = EpicRepositoryImpl(requireContext())
        val useCase = GetEpicUseCase(repo)
        EpicViewModelFactory(useCase)
    }

    companion object {
        private const val ARG_EPIC_ID = "epicId"
        fun newInstance(epic: EpicImage): EpicDetailsFragment {
            val fragment = EpicDetailsFragment()
            fragment.arguments = Bundle().apply { putString(ARG_EPIC_ID, epic.id) }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpicDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epicId = arguments?.getString(ARG_EPIC_ID) ?: return

        viewModel.epicList.observe(viewLifecycleOwner) { list ->
            val epic = list.find { it.id == epicId } ?: return@observe
            binding.tvTitle.text = epic.id
            binding.tvDate.text = epic.date
            Picasso.get().load(epic.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_background)
                .into(binding.ivEpic)
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
