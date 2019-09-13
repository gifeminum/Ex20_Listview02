package com.egci428.ex_listview

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val redColor = Color.parseColor("#FF0000")
        //listView.setBackgroundColor(redColor)

        val adapter = myCustomAdapter()
        main_listView.adapter = adapter

        main_listView.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Toast.makeText(this,"${item} ${position}", Toast.LENGTH_LONG).show()
        }

        button.setOnClickListener {
            adapter.getName().add(editText.text.toString())
            adapter.setnotifyDataSetChanged()
        }


    }

    private class myCustomAdapter: BaseAdapter(){

        fun getName():ArrayList<String>{
            return names
        }
        fun setnotifyDataSetChanged(){
            notifyDataSetChanged()
        }
        private val names = arrayListOf<String>(
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama","Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama","Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama","Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama","Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama","Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama"
        )

        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val rowMain: View
            val whiteColor = Color.parseColor("#FFFFFF")
            val greyColor = Color.parseColor("#E0E0E0")

            if(convertView==null){

                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main, viewGroup, false)
                val viewHolder = ViewHolder(rowMain.name_textView , rowMain.position_textView)
                rowMain.tag = viewHolder
            }else{
                rowMain = convertView

            }
            val viewHolder = rowMain.tag as ViewHolder
            viewHolder.nameTextView.text = names.get(position)
            viewHolder.positionTextview.text = "Row Number: $position"

            if ((position%2)==1){
                rowMain.setBackgroundColor(greyColor)
            } else {

                rowMain.setBackgroundColor(whiteColor)
            }
            rowMain.setOnClickListener {
                rowMain.animate().setDuration(2000).alpha(0f).withEndAction({
                    names.removeAt(position)
                    notifyDataSetChanged()
                    rowMain.setAlpha(1.0F)
                })



            }
            return rowMain
        }
        private class ViewHolder(val nameTextView: TextView , val positionTextview: TextView)

    }
}
