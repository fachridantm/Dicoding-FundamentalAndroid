package com.dicoding.fundamental.githubfavoriteapp.ui.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dicoding.fundamental.githubfavoriteapp.databinding.FragmentFollowBinding
import com.dicoding.fundamental.githubfavoriteapp.ui.detail.DetailViewModel

class FollowFragment : Fragment() {

    private var position = 0
    private val bindingFragment: FragmentFollowBinding by viewBinding()
    private val detailViewModel by activityViewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFollowBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.listGithubUser.observe(this) { listGithubUser ->
            bindingFragment.rvFollow.apply {
                adapter = ViewPagerAdapter(listGithubUser)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }
    }

    companion object {
        const val FOLLOWING = 0
        const val FOLLOWERS = 1

        fun newInstance(position: Int): FollowFragment =
            FollowFragment().apply {
                this.position = position
            }
    }
}