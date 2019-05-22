package com.ramana.basicarchitecture.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.ramana.basicarchitecture.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramana.basicarchitecture.viewmodel.TopRatedMoviesViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TopRatedMovieFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TopRatedMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TopRatedMovieFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TopRatedMoviesViewModel
    private lateinit var adapter : TopRatedMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.fragment_top_rated_movie, container, false)
        recyclerView = mainView.findViewById(R.id.top_rated_movies_list)

        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        adapter = TopRatedMoviesAdapter(context = context!!)

        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(TopRatedMoviesViewModel::class.java)
        subscribeUi(adapter)
        return mainView
    }

    private fun subscribeUi(adapter: TopRatedMoviesAdapter) {
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { list ->
            if (list != null) adapter.submitList(list)
            adapter.notifyDataSetChanged()
        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TopRatedMovieFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TopRatedMovieFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
