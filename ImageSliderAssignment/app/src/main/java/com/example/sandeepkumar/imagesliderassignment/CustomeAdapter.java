package com.example.sandeepkumar.imagesliderassignment;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static android.content.Context.MODE_PRIVATE;


public class CustomeAdapter extends PagerAdapter {

    static int customposition = 0;

    private int[] img = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8};
    private static int[] count = {0,0,0,0,0,0,0,0};
    private LayoutInflater inflater;
    private Context ctx;
    private DataSetObservable mObservable = new DataSetObservable();
    private ViewPager viewPager;

    List<String> words;
    public CustomeAdapter(Context ctx)
    {
        this.ctx = ctx;

    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public int getItemPosition(Object object) {
        super.getItemPosition(object);
        SharedPreferences pos = ctx.getSharedPreferences("forposition", MODE_PRIVATE);
        int tom = pos.getInt("pos",0);
        Log.d("posTom","value : "+tom );
        return tom;
    }

    public void notifyDataSetChanged() {
        Log.d("posNotifyMethod","Notify Method Chal rha hai");
        mObservable.notifyChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe,container,false);
       // View vp = inflater.inflate(R.layout.activity_views_image,container,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageview);
        //viewPager = vp.findViewById(R.id.image);
        SharedPreferences sp = ctx.getSharedPreferences("your_shared_pref_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        SharedPreferences post = ctx.getSharedPreferences("forposition", MODE_PRIVATE);
        int tom = post.getInt("pos",0);
       /* if(tom != 0 && position == 0)
        {
            Log.d("posTomInside","value : "+tom );
            viewPager.setCurrentItem(tom);
        }
*/
        if((sp.getString("array", null)) == null) {
            String temp = Arrays.toString(count);
            editor.putString("array", temp);
            editor.commit();
        }
        String fetch = sp.getString("array", null);
        Log.d("positionlist ","value : "+fetch);
        fetch = fetch.substring(1, fetch.length()-1);
        String strarray[] =fetch.split(", ") ;


       /* SharedPreferences  spt= ctx.getSharedPreferences("your_shared_pref_name", MODE_PRIVATE);
        int post = spt.getInt("pos",1);*/
        Log.d("position","value : "+position );
        Log.d("posvalue","value : "+strarray[position] );
        Log.d("poscondition","value : "+(Integer.parseInt(strarray[position]) == 0) );

        if (Integer.parseInt(strarray[position]) == 0) {
                BackgroundTask task = new BackgroundTask(this);
                task.execute();
                imageView.setImageResource(img[position]);
                container.addView(v);
                strarray = setValue(position, strarray);
                String temp1 = Arrays.toString(strarray);
                editor.putString("array", temp1);
                editor.commit();
        } else {
                imageView.setImageResource(img[position]);
                container.addView(v);
        }
        customposition = position;
        SharedPreferences pos = ctx.getSharedPreferences("forposition", MODE_PRIVATE);
        SharedPreferences.Editor poseditor = pos.edit();
        poseditor.putInt("pos", position);
        poseditor.commit();

        return v;
    }

  /*  private int isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return 0;
        }
        return customposition+1;
    }*/
    private boolean getValue(int position) {


        return false;
    }

    private String[] setValue(int position, String[] strarray) {
        strarray[position]=""+1;
       return strarray;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }


    private class BackgroundTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private Window window;

        public BackgroundTask(CustomeAdapter activity) {

            dialog = new ProgressDialog(activity.ctx);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("please wait for 5 second");
            dialog.show();
            dialog.setCancelable(false);
        }



        @Override
        protected void onPostExecute(Void result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
    
    

}
