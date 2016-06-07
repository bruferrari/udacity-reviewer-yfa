package udacity.com.yourfirstandroidappsample.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import udacity.com.yourfirstandroidappsample.R;
import udacity.com.yourfirstandroidappsample.helper.MainHelper;

/**
 * Created by bruno on 6/6/16.
 */
public class MainAsyncTask extends AsyncTask<String, String, Bitmap> {

    private Activity activity;
    private ImageView entityImage;
    private MainHelper helper;
    private ProgressDialog progressDialog;

    public MainAsyncTask(Activity activity, ImageView entityImage) {
        this.activity = activity;
        this.helper = new MainHelper(activity);
        this.entityImage = entityImage;
    }

    @Override
    protected void onPreExecute() {
        this.progressDialog = new ProgressDialog(activity);
        this.progressDialog.setMessage(activity.getResources().getString(R.string.loading_msg));
        this.progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return helper.getBitmapFromUrl(activity.getResources().getString(R.string.layout_fixed_image_url));
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        entityImage.setImageBitmap(bitmap);
        this.progressDialog.dismiss();
    }
}
