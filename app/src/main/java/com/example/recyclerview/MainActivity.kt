package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.HeroCardAdapter
import com.example.recyclerview.adapter.HeroGridAdapter
import com.example.recyclerview.adapter.HeroListAdapter
import com.example.recyclerview.helper.HeroData
import com.example.recyclerview.model.Hero
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*
        bagian dibawa ini seperti pada variabel biasanya,
        dimana disini saya mendeklarasikan didalam companion object
        supaya teman-teman bisa mencari perbedaan mengenai fungsi dari mendeklarasikan m
        variabel or function didalam companion object
        dan tidak didalam companion object, dan bisa membedakan
        keduanya.

        sedangkan fungsi dari variabel-variabel yang saya deklarasikan didalam companion object
        ini sendiri digunakan untuk key atau kunci ketika menyinpan dan mengambil
        data pada atau dari saveInstance.

     */
    companion object {
        private const val STATE_TITLE = "state_title"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }
/*
    bagian dibawah ini mendeklarasikan beberapa variabel
    yang akan digunakan, dimana variabel heroData tipenya adalah class HeroData
    bisa disebut juga object dari class HeroData.
    dan lateinit menunjukan inisialisasi untuk object atau variabel tersebut bisa dilakukan
    belangkangan (late).
    sedangkan yang lainnnya, variabel list, title dan mode deklarasikan seperti biasanya.
 */
    private lateinit var heroData: HeroData
    private var list = ArrayList<Hero>()
    private var title = "Mode List"
    private var mode = 0

/*
    method onCreate ini yang akan dieksekusi statementnya
    ketika activity di jalankan -> untuk lebih detailnya silahkan
    cari materi dimanapun dengan
    keywordnya -> lifecycle activity in android app (indonesia : daur hidup activity pada apliaksi android)
 */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroData = HeroData(this) // menginisialisasi object dari class heroData

    /*
        mengechek apabilah saveInstancenya masih null,
        maka mengambil data dari function getDataHero yang berada didalam class HeroData
        kemudian dimasukan kedalam variabel list.
        dan memanggil function showRecyclerViewList()
        agar menampilkan recyclerview dengan bentuk tampilan list.

        jika saveIsntance tidak null lagi maka tinggal load kembali data yang disimpan pada saveInstance
        tersebut dan langsung ditampilkan lagi pada recyclerview.
     */
        if (savedInstanceState == null) {
            list.addAll(heroData.getDataHero())
            showRecyclerViewList()
            setActionBarTitle(title)
            mode = R.id.recyclerListMenu
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)

            setActionBarTitle(title)
            stateList?.let { list.addAll(it) }
            setMode(stateMode)
        }
    }

/*
    fungsi untuk menginfalte menu dari file menu_manu
    yang ada didalam folder res->menu->main_menu
 */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

/*
    proses menyimpan data disaat ketika activity di hancurkan (destroy) sementara
    baik pada saat orientasi layar maupun hal lainya.
 */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    /*
        membuat sebuah funtion bernama setMode,
        untuk mengatur item recyclerview yang akan ditampilkan,
        baik secara list, grid, maupun card.
     */
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.recyclerListMenu -> {
                title = "Mode List"
                showRecyclerViewList()
            }
            R.id.recyclerGridMenu -> {
                title = "Mode Grid"
                showRecyclerViewGrid()
            }
            R.id.recyclerCardMenu -> {
                title = "Mode Card"
                showRecyclerViewCard()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }

    /*
        membuat funtion untuk mengsetkan title pada actionbar
        sesuai dengan mode item recyclerview yang ditampilkan
     */
    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    /*
        membuat function untuk mengsetkan recyclerview
        menampilkan item-itemnya secara list, dengan LinearLayoutManager
     */
    private fun showRecyclerViewList() {
        val listAdapter = HeroListAdapter(list)
        recyclerViewHeroes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
            setHasFixedSize(true)
        }
        listAdapter.setOnItemClickCallBack(object : HeroListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                Toast.makeText(this@MainActivity, "You choice ${data.name}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*
       membuat function untuk mengsetkan recyclerview
       menampilkan item-itemnya secara grid, dengan GridLayoutManager
    */
    private fun showRecyclerViewGrid() {
        val gridAdapter = HeroGridAdapter(list)
        recyclerViewHeroes.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = gridAdapter
            setHasFixedSize(true)
        }
        gridAdapter.setOnItemClickCallBackGrid(object : HeroGridAdapter.OnItemClickCallBackGrid {
            override fun onItemClickedGrid(data: Hero) {
                Toast.makeText(this@MainActivity, "You Choice ${data.name}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /*
       membuat function untuk mengsetkan recyclerview
       menampilkan item-itemnya secara card, dengan LinearLayoutManager.

       card disini sama seperti pada mode list, dimana menggunakan LinearLayoutManager
       hanya saja pada item dibagian layout itemnya menggunakan cardmview, bisa dilihat pada file
       res->layout->item_row_hero_card.xml
    */
    private fun showRecyclerViewCard() {
        val cardAdapter = HeroCardAdapter(list)
        recyclerViewHeroes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
            setHasFixedSize(true)
        }
        cardAdapter.setOnItemClickedCard(object : HeroCardAdapter.OnItemClickCallbackCard {
            override fun onItemClickedCard(data: Hero) {
                Toast.makeText(this@MainActivity, "You Choice ${data.name}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /*
        tambahan sedikit, pada saat setiap item recycler
        diclick maka akan menampilkan kata You Choice nama dari
        item yang diclick tersebut, dimana hal tersebut karena ketika
        item diclick terdapat function Toast yang berfungsi untuk menampilkan pesan tertentu.
        untuk Toast sendiri bisa dicari sendiri di mana saja dengan
        keyword-> Toast for android app
     */
}
