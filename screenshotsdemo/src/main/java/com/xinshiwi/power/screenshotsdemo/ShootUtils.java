package com.xinshiwi.power.screenshotsdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;


/**
 * 截图工具类
 *
 * @author xinyang
 * @date 2017/12/11
 */

public class ShootUtils {


    /**
     * recyclerView 滚动截图
     *
     * @param view recyclerView
     */
    public static Bitmap shotRecyclerView(RecyclerView view) {
        //获取设置的adapter
        RecyclerView.Adapter adapter = view.getAdapter();
        if (adapter == null) {
            return null;
        }
        //创建保存截图的bitmap
        Bitmap bigBitmap = null;
        //获取item的数量
        int size = adapter.getItemCount();
        //recycler的完整高度 用于创建bitmap时使用
        int height = 0;
        //获取最大可用内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // 使用1/8的缓存
        final int cacheSize = maxMemory / 8;
        //把每个item的绘图缓存存储在LruCache中
        LruCache<String, Bitmap> bitmapCache = new LruCache<>(cacheSize);
        for (int i = 0; i < size; i++) {
            //手动调用创建和绑定ViewHolder方法，
            RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
            adapter.onBindViewHolder(holder, i);
            //测量
            holder.itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //布局
            holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                    holder.itemView.getMeasuredHeight());
            //开启绘图缓存
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.buildDrawingCache();
            Bitmap drawingCache = holder.itemView.getDrawingCache();
            if (drawingCache != null) {
                bitmapCache.put(String.valueOf(i), drawingCache);
            }
            //获取itemView的实际高度并累加
            height += holder.itemView.getMeasuredHeight();
        }
        //根据计算出的recyclerView高度创建bitmap
        bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.RGB_565);
        //创建一个canvas画板
        Canvas canvas = new Canvas(bigBitmap);
        //获取recyclerView的背景颜色
        Drawable background = view.getBackground();
        //画出recyclerView的背景色 这里只用了color一种 有需要也可以自己扩展
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            canvas.drawColor(color);
        }
        //当前bitmap的高度
        int top = 0;
        //画笔
        Paint paint = new Paint();
        for (int i = 0; i < size; i++) {
            Bitmap bitmap = bitmapCache.get(String.valueOf(i));
            canvas.drawBitmap(bitmap, 0f, top, paint);
            top += bitmap.getHeight();
            //如果有在第二次截图时崩溃等状况，注掉下面方法就好，原因我还没想明白。。。
            bitmap.recycle();
        }
        return bigBitmap;
    }


    /**
     * 普通View截图
     *
     * @param view view对象
     * @return
     */
    public static Bitmap shotView(View view) {
        //开启绘图缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取绘图缓存
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        //清理绘图缓存，释放资源
        view.destroyDrawingCache();
        return bitmap;
    }

    /**
     * ScrollView截图
     */
    public static Bitmap shotScrollView(ScrollView view) {
        int height = 0;
        //理论上scrollView只会有一个子View啦
        for (int i = 0; i < view.getChildCount(); i++) {
            height += view.getChildAt(i).getHeight();
        }
        //创建保存缓存的bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), height, Bitmap.Config.RGB_565);
        //可以简单的把Canvas理解为一个画板 而bitmap就是块画布
        Canvas canvas = new Canvas(bitmap);
        //获取ScrollView的背景颜色
        Drawable background = view.getBackground();
        //画出ScrollView的背景色 这里只用了color一种 有需要也可以自己扩展 也可以自己直接指定一种背景色
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            canvas.drawColor(color);
        }
        //把view的内容都画到指定的画板Canvas上
        view.draw(canvas);
        return bitmap;
    }
}
