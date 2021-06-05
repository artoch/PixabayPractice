package com.atsapp.pixabaytestapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.atsapp.pixabaytestapp.R
import com.atsapp.pixabaytestapp.adapter.PictureAdapter
import com.atsapp.pixabaytestapp.adapter.TagsAdapter
import com.atsapp.pixabaytestapp.aux_interface.view_.AppBarChangeListener
import com.atsapp.pixabaytestapp.aux_interface.view_.SetupView
import com.atsapp.pixabaytestapp.databinding.FragmentDetailPictureBinding
import com.atsapp.pixabaytestapp.utils.gridLayout
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout

/**
 * @author Alfredo Tochon
 * Detalle de la caracteristica
 */
class DetailPictureFragment(override val TAG: String = "DetailPictureFragment") : Fragment(), SetupView, View.OnClickListener{

    private lateinit var binding: FragmentDetailPictureBinding
    private val args: DetailPictureFragmentArgs by navArgs()
    private lateinit var adapter: TagsAdapter
    private val layoutManager by lazy {
        gridLayout(requireContext(), 3)
    }

    private val circleLoadBack by lazy {
        generateLocalCircleLoad()
    }

    private val circleLoad by lazy {
        generateLocalCircleLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailPictureBinding.bind(view)
        setupView()
    }

    override fun setupBindings() {
        circleLoad.start()
        circleLoadBack.start()
        binding.apply {
            if (args.hit!=null) {
                Glide.with(this@DetailPictureFragment)
                    .load(args.hit!!.largeImageURL)
                    .error(requireContext().getDrawable(R.drawable.pixbay_logo))
                    .placeholder(circleLoad)
                    .into(ivPicture)

                Glide.with(this@DetailPictureFragment)
                    .load(args.hit!!.userImageURL)
                    .error(requireContext().getDrawable(R.drawable.pixbay_logo))
                    .placeholder(circleLoadBack)
                    .into(ivUser)

                btnBack.setOnClickListener(this@DetailPictureFragment)
                tvName.text = args.hit!!.user
                tvBarName.text =  args.hit!!.user
                appBar.addOnOffsetChangedListener(appBarListener())
                val tags = args.hit!!.tags.split(",")
                adapter = TagsAdapter(tags)
                includeData.apply {
                    ivTotalLikes.text = args.hit!!.likes.toString()
                    ivTotalViews.text = args.hit!!.views.toString()
                    rvTag.layoutManager = layoutManager
                    rvTag.adapter = adapter

                }

            }
        }
    }

    override fun setupObservers() {

    }

    override fun onClick(p0: View?) {
        binding.apply {
            when(p0!!.id){
                btnBack.id -> {
                    val action = DetailPictureFragmentDirections.actionDetailPictureFragmentToPixMainFragment()
                    view?.findNavController()?.navigate(action)
                }
            }
        }
    }

    private fun generateLocalCircleLoad(): CircularProgressDrawable {
        val circle = CircularProgressDrawable(requireContext())
        circle.strokeWidth = 5f
        circle.setColorSchemeColors(requireContext().resources.getColor(R.color.seconndary_color))
        circle.centerRadius = 30f
        return circle
    }

    private fun appBarListener():AppBarChangeListener {
        return object : AppBarChangeListener(){
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                binding.apply {
                    when(state){
                        State.COLLAPSED ->{
                            tvBarName.visibility = View.VISIBLE
                        }
                        else -> {
                            tvBarName.visibility = View.GONE
                        }
                    }
                }
            }

        }
    }

}