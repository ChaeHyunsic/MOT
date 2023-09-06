package com.example.mot

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GalleryAdapter() : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private lateinit var imageList : ArrayList<Uri>
    lateinit var context: Context

    constructor(imageList: ArrayList<Uri>, context: Context): this (){
        this.imageList = imageList
        this.context = context
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val galleryView : ImageView = view.findViewById(R.id.upload_img1)
    }

    /**
     * 화면 설정
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.upload_img, parent, false)

        return ViewHolder(view)
    }

    /**
     * 아이템 개수
     */
    override fun getItemCount(): Int {
        return imageList.size
    }

    /**
     * 데이터 설정
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(imageList.size >0 && imageList.size <6) {

            Glide.with(context)
                .load(imageList[position])//이미지 위치
                .into(holder.galleryView)//보여줄 위치
        }
    }
}