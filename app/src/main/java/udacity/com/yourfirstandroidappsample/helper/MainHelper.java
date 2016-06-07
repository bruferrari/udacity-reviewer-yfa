package udacity.com.yourfirstandroidappsample.helper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import udacity.com.yourfirstandroidappsample.R;

/**
 * Created by bruno on 6/6/16.
 */
public class MainHelper {

    private ImageView entityImage;
    private TextView phoneTextView;
    private Activity activity;

    public MainHelper(Activity activity) {
        this.activity = activity;
    }

    public void initializeUiElements() {
        entityImage = (ImageView) activity.findViewById(R.id.entity_image);
        phoneTextView = (TextView) activity.findViewById(R.id.entity_phone);
    }

//    public void setBitmapOnImageView(ImageView imageView, Bitmap bitmap) {
//        imageView.setImageBitmap(bitmap);
//    }

    public Bitmap getBitmapFromUrl(String literalUrl) {
        try {
            URL url = new URL(literalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream input = conn.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(input);

            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void dialEntityPhone(String phoneNumber) {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        dial.setData(Uri.parse("tel:" + phoneNumber));
        if (dial.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(dial);
        }
    }

    public ImageView getEntityImage() {
        return entityImage;
    }

    public TextView getPhoneTextView() {
        return phoneTextView;
    }
}
