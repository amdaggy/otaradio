package com.otaradio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private SliderAdapter sliderAdapter;

    public static class SliderAdapter extends PagerAdapter {
        private final Context mContext;
        private final int[] mImageIds = new int[]{R.drawable.photo1, R.drawable.photo2, R.drawable.photo3, R.drawable.photo4, R.drawable.photo5};
        private int currentPage = 0;
        private Timer timer;

        SliderAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImageIds[position]);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }

        public void startAutoSlide(ViewPager viewPager) {
        }

        public void stopAutoSlide() {
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        WebView webView = findViewById(R.id.webView);
        webView.clearCache(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://zeno.fm/radio/ota-radio");

        ViewPager viewPager = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        sliderAdapter.startAutoSlide(viewPager);

        TextView textView= findViewById(R.id.privacyPolicyTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="\n" +
                        "Thank you for choosing the Ota Radio App (\"the App\"). This Privacy Policy explains how we collect, \n" +
                        "use, disclose, and safeguard your personal information when you use our mobile application, the \n" +
                        "Ota Radio App. Please read this Privacy Policy carefully. By accessing or using the App, you agree to \n" +
                        "be bound by the terms and conditions described in this Privacy Policy.\n" +
                        "Information We Collect\n" +
                        "1.1 Personal Information:\n" +
                        "We don’t collect any personal information like name or email address.\n" +
                        "1.2 Non-Personal Information:\n" +
                        "We may collect non-personal information about you automatically when you use the App. This may \n" +
                        "include usage data, such as your IP address, device type, operating system, and browsing behavior \n" +
                        "within the App. This information is collected to improve the functionality of the App and to provide \n" +
                        "personalized content and recommendations.\n" +
                        "How We Use Your Information\n" +
                        "2.1 We may use the information we collect for various purposes, including but not limited to:\n" +
                        "Providing, maintaining, and improving the App's features and services.\n" +
                        "Personalizing your experience within the App.\n" +
                        "Sending you technical notices and updates\n" +
                        "Analyzing usage trends and preferences to enhance the App's functionality and performance.\n" +
                        "Protecting our legal rights and preventing potential fraud or abuse of the App.\n" +
                        "How We Share Your Information\n" +
                        "3.1 We do not share, sell, trade, or rent your personal information to third parties for their \n" +
                        "marketing purposes. However, we may share your personal information with trusted third-party \n" +
                        "service providers who assist us in operating the App, conducting our business, or providing services \n" +
                        "to you, as long as they agree to keep your information confidential.\n" +
                        "3.2 We may disclose your information if required to do so by law or in response to valid requests by \n" +
                        "public authorities (e.g., a court or government agency).\n" +
                        "Security\n" +
                        "4.1 We strive to implement reasonable security measures to protect your personal information from \n" +
                        "unauthorized access, disclosure, alteration, or destruction. However, please be aware that no \n" +
                        "method of transmission over the internet or method of electronic storage is 100% secure.\n" +
                        "Children's Privacy\n" +
                        "5.1 The App is not intended for use by individuals under the age of 13. We do not knowingly collect \n" +
                        "personal information from children under 13 years of age. If we discover that we have collected \n" +
                        "personal information from a child under 13, we will promptly delete such information from our \n" +
                        "records.\n" +
                        "Changes to This Privacy Policy\n" +
                        "6.1 We may update this Privacy Policy from time to time to reflect changes in our practices and \n" +
                        "services. We encourage you to review this page periodically for the latest information on our privacy \n" +
                        "practices. Your continued use of the App after the revised Privacy Policy has been posted constitutes \n" +
                        "your acceptance of the changes.\n" +
                        "Contact Us\n" +
                        "7.1 If you have any questions or concerns about this Privacy Policy or our privacy practices, please \n" +
                        "contact us at otaradio@gmail.com.\n" +
                        "By using the Ota Radio App, you consent to the collection and use of your information as described \n" +
                        "in this Privacy Policy.";

                Intent intent= new Intent(MainActivity.this, viewpolicy.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sliderAdapter != null) {
            sliderAdapter.stopAutoSlide();
        }
    }

    private void openPrivacyPolicyUrl() {
        String privacyPolicyUrl = "https://otaradioprivacypolicy.tiiny.site/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicyUrl));
        startActivity(intent);
    }
}
