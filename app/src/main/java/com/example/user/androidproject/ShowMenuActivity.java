package com.example.user.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.target.SquaringDrawable;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;

import static java.lang.Boolean.TRUE;


public class ShowMenuActivity extends AppCompatActivity {

    AQuery aq = new AQuery(this);

    private ImageView button1, button3, button4;
    private ImageView img, img_edit_btn, btn_back;

    private TextView title, information, number, location, info1, info2, info3, report;
    private String mTitle, mInformation, mNumber, mLocation, mCategory, mTime, mImage;
    private int mId, mReport, mLikeCount;

    private LinearLayout option1, option2, option3, warning;
    private boolean ifLike = false;


    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private ImageView photoFood; //사진
    private Uri mImageCaptureUri,ImageCaptureUri;
    private String imageString;
    private CallbackManager callbackManager;
    private LoginManager manager;

    int tmp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_show_menu);

        mTitle = getIntent().getStringExtra("title");
        mInformation = getIntent().getStringExtra("information");
        mNumber = getIntent().getStringExtra("number");
        mLocation = getIntent().getStringExtra("location");
        mCategory = getIntent().getStringExtra("category");
        mTime = getIntent().getStringExtra("time");
        mLikeCount = getIntent().getIntExtra("like_count", 0);
        mReport = getIntent().getIntExtra("report", 0);
        mImage = getIntent().getStringExtra("image");
        mId = getIntent().getIntExtra("id", 0);

        option1 = (LinearLayout) findViewById(R.id.option_like);
        option2 = (LinearLayout) findViewById(R.id.option_text);
        option3 = (LinearLayout) findViewById(R.id.option_facebook);
        warning = (LinearLayout) findViewById(R.id.warning);

        //
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(ShowMenuActivity.this, String.valueOf(mId), Toast.LENGTH_SHORT).show();
                if(CommonData.getCheck(mId)==1){
                    Toast.makeText(ShowMenuActivity.this, "이미 즐겨찾기에 등록된 항목입니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    CommonData.setCheck(mId, 1);

                    SharedPreferences pre=getSharedPreferences("bool", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putBoolean(mId+"", TRUE);
                    editor.commit();

                    new LovelyStandardDialog(ShowMenuActivity.this)
                            .setTopColorRes(R.color.darkDeepOrange)
                            .setButtonsColorRes(R.color.darkDeepOrange)
                            .setIcon(R.drawable.ic_info_outline_white_36dp)
                            .setTitle(mTitle)
                            .setMessage("이 음식점을 즐겨찾기에 추가하시겠습니까?")
                            .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("index", mId);
                                    aq.ajax("http://letsgo.woobi.co.kr/hobin/send.php", map, JSONObject.class, new AjaxCallback<JSONObject>() {
                                        @Override
                                        public void callback(String url, JSONObject object, AjaxStatus status) {
                                            //Log.d("response=", object.toString());
                                            if (object != null) {

                                            } else {
                                                Toast.makeText(ShowMenuActivity.this, "통신실패", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    Toast.makeText(ShowMenuActivity.this, "즐겨찾기 등록을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            }
        });
        //
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent(getApplicationContext(), MyPictureActivity.class);
                    SharedPreferences pref1 = getSharedPreferences("image", MODE_PRIVATE);
                    String imageUri = pref1.getString(mTitle, "FALSE");      //그다음 ,로 나누기
                    intent.putExtra("data", imageUri);
                    //Log.e("tag", imageUri);
                    //Log.d("log",imgurl);
                    startActivity(intent);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishImage();
            }
        });

        if(mReport >= 5){
            warning.setVisibility(View.VISIBLE);
        }

        photoFood = (ImageView) findViewById(R.id.img);


        title = (TextView) findViewById(R.id.title);
        information = (TextView) findViewById(R.id.information);

        title.setText(mTitle);
        information.setText(mInformation);
        information.setMovementMethod(new ScrollingMovementMethod());


        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info3 = (TextView) findViewById(R.id.info3);
        report = (TextView) findViewById(R.id.report);

        info1.setText(mCategory);
        info2.setText(mTitle);
        info3.setText(mTime);
        report.setText(String.valueOf(mReport));

        button1 = (ImageView) findViewById(R.id.menu_button1_call);
        button3 = (ImageView) findViewById(R.id.menu_button3_report);
        button4 = (ImageView) findViewById(R.id.menu_button4_maps);
        img = (ImageView) findViewById(R.id.img);
        img_edit_btn = (ImageView) findViewById(R.id.img_edit_btn);

        Glide.with(this).load(mImage).into(img);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+mNumber));
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                new LovelyStandardDialog(ShowMenuActivity.this)
                        .setTopColorRes(R.color.darkGreen)
                        .setButtonsColorRes(R.color.darkDeepOrange)
                        .setIcon(R.drawable.ic_info_outline_white_36dp)
                        .setTitle(mTitle)
                        .setMessage("이 음식점에 대한 정보가 잘못되었습니까?")
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("index", mId);
                                aq.ajax("http://letsgo.woobi.co.kr/hobin/report.php", map, JSONObject.class, new AjaxCallback<JSONObject>() {
                                    @Override
                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                        if (object != null) {

                                        } else {
                                            Toast.makeText(ShowMenuActivity.this, "통신실패", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                Toast.makeText(ShowMenuActivity.this, "신고가 접수되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String[] array = mLocation.split(",");

                double latitude = Double.parseDouble(array[0]);
                double longitude = Double.parseDouble(array[1]);

                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                intent.putExtra("LATITUDE", latitude);
                intent.putExtra("LONGITUDE", longitude);
                startActivity(intent);
            }
        });

        img_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(ShowMenuActivity.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.picture_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.camera_shot) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            // 임시로 사용할 파일의 경로를 생성
                            String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                            // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
                            //intent.putExtra("return-data", true);
                            startActivityForResult(intent, PICK_FROM_CAMERA);

                        } else if (item.getItemId() == R.id.move_gallay) {

                            Intent intent2 = new Intent(Intent.ACTION_PICK);
                            intent2.setType(MediaStore.Images.Media.CONTENT_TYPE);
                            startActivityForResult(intent2, PICK_FROM_ALBUM);
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
            return;
        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                final Bundle extras = data.getExtras();
                if(extras != null)
                {
                    Bitmap photo = extras.getParcelable("data");

                    //TODO: string
                    ImageCaptureUri = getImageUri(this,photo);

                    String name=title.getText().toString();
                    SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
                    imageString =  pref.getString(name, "FALSE");
                    if(imageString.equals("FALSE"))  //저장되어있는 string이 없음
                    {
                        SharedPreferences.Editor editor = pref.edit();
                        imageString=ImageCaptureUri.toString();
                        editor.putString(name, imageString);
                        editor.commit();

                    }
                    else    //저장되어 있는 string 이 있음
                    {
                        imageString= ImageCaptureUri.toString()+","+imageString;
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(name, imageString);
                        editor.commit();

                    }

                }


                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM:
            {

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA:
            {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 400);
                intent.putExtra("outputY", 300);
                intent.putExtra("aspectX", 10);
                intent.putExtra("aspectY", 8);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    private void publishImage(){

        Bitmap bitmap = null;
        Drawable drawable = img.getDrawable();
        if (drawable instanceof GlideBitmapDrawable) {
            bitmap = ((GlideBitmapDrawable) drawable).getBitmap();
        }
        else if (drawable instanceof TransitionDrawable) {
            TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;
            int length = transitionDrawable.getNumberOfLayers();
            for (int i = 0; i < length; ++i) {
                Drawable child = transitionDrawable.getDrawable(i);
                if (child instanceof GlideBitmapDrawable) {
                    bitmap = ((GlideBitmapDrawable) child).getBitmap();
                    break;
                } else if (child instanceof SquaringDrawable
                        && child.getCurrent() instanceof GlideBitmapDrawable) {
                    bitmap = ((GlideBitmapDrawable) child.getCurrent()).getBitmap();
                    break;
                }
            }
        }
        else if (drawable instanceof SquaringDrawable) {
            bitmap = ((GlideBitmapDrawable) drawable.getCurrent()).getBitmap();
        }
        else {
            bitmap=((BitmapDrawable)drawable).getBitmap();//bitmap->image

        }
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.setPackage("com.facebook.katana");
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "bitmap/jpeg");//bitmap->image
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);


        OutputStream outstream;
        try {
            outstream = getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);//bitmap->image
            outstream.close();
        } catch (Exception e) {
            System.err.println("여기");

        }

        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


}