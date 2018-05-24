package com.example.johnpaul.bookstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.johnpaul.bookstore.R
import com.example.johnpaul.bookstore.R.id.toolbar
import com.example.johnpaul.bookstore.adapters.TabPagerAdapter
import com.example.johnpaul.bookstore.fragments.FilterableFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.example.johnpaul.bookstore.models.BookRepository
import com.example.johnpaul.bookstore.models.MyBookRepository
import com.example.johnpaul.bookstore.models.OnlineBookRepository
import com.example.johnpaul.bookstore.presenters.BookPresenter


class MainActivity : AppCompatActivity(), FilterableFragment.OnFragmentInteractionListener {

    companion object {
        private val BOOKLIST_TAB = 0
        private val MYBOOK_TAB = 1
    }

    private lateinit var bookListRepository: BookRepository // Model
    private lateinit var bookListFragment: FilterableFragment // View
    private lateinit var bookListPresenter: BookPresenter // Presenter

    private lateinit var myBookRepository: MyBookRepository // Model
    private lateinit var myBookFragment: FilterableFragment // View
    private lateinit var myBookPresenter: BookPresenter // Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        configureModel()
        configureTabLayout()
    }

    override fun onFragmentCreated(tabNumber: Int) {
        when (tabNumber) {
            BOOKLIST_TAB -> bookListPresenter.start()
            MYBOOK_TAB -> myBookPresenter.start()
        }
    }

    override fun onListItemClicked(tabNumber: Int, position: Int) {
        when (tabNumber) {
            BOOKLIST_TAB -> {
                val book = bookListRepository.getBooks().get(position)
                myBookRepository.addBook(book)
                Toast.makeText(this, "Added \"${book.title}\"", Toast.LENGTH_SHORT).show()
            }
            MYBOOK_TAB -> {
            }
        }
    }

    private fun configureModel() {
        bookListRepository = OnlineBookRepository() // Model
        bookListFragment = FilterableFragment.newInstance(BOOKLIST_TAB) // View
        bookListPresenter = BookPresenter(bookListFragment, bookListRepository) // Presenter

        myBookRepository = MyBookRepository() // Model
        myBookFragment = FilterableFragment.newInstance(MYBOOK_TAB) // View
        myBookPresenter = BookPresenter(myBookFragment, myBookRepository) // Presenter
    }

    private fun configureTabLayout() {
        pager.adapter = TabPagerAdapter(
                supportFragmentManager,
                tabs.tabCount,
                bookListFragment,
                myBookFragment,
                Fragment()
        )

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.search_book_button)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    bookListPresenter.filter(newText)
                    myBookPresenter.filter(newText)
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}