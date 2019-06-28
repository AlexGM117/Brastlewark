package com.alejandro.hob.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.alejandro.hob.R
import com.alejandro.hob.data.remote.Brastlewark
import com.alejandro.hob.ui.DetailFragment
import com.alejandro.hob.ui.adapters.PopulationAdapter
import com.alejandro.hob.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PopulationAdapter.OnItemClickListener,
    SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private lateinit var mViewModel: MainViewModel
    private lateinit var adapter: PopulationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        recyclerPopulation.setHasFixedSize(true)
        recyclerPopulation.layoutManager = GridLayoutManager(this, 3)

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        if (NetworkUtil().isOnline(this)) {
            mViewModel.getData()
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }

        mViewModel.data.observe(this, Observer {
            if(!it.isNullOrEmpty()) {
                adapter = PopulationAdapter(it)
                adapter.setListener(this)
                recyclerPopulation.adapter = adapter
            } else {
                Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnSearchClickListener {
            toolbarImage.setImageResource(R.drawable.book_open)
        }
        searchView.setOnCloseListener(this)
        return true
    }

    override fun onClose(): Boolean {
        toolbarImage.setImageResource(R.drawable.book)
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return false
    }

    override fun onItemSelected(brastlewark: Brastlewark) {
        val fragment = DetailFragment()
        fragment.setData(brastlewark)
        fragment.show(supportFragmentManager, "DetailFragment")
    }
}
