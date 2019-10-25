package com.developer.android.quickveggis.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.developer.android.quickveggis.R;
import com.developer.android.quickveggis.ui.adapter.PreviewAdapter;
import com.developer.android.quickveggis.ui.dialog.ActionListener;
import com.developer.android.quickveggis.ui.dialog.NotifyDialog;
import com.developer.android.quickveggis.ui.fragments.SubmitFragment;
import com.developer.android.quickveggis.ui.utils.BitmapUtils;
import com.developer.android.quickveggis.ui.utils.FragmentUtils;
import com.quickveggies.quickveggies.ui.custom.Preview;
import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration.Builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements PreviewAdapter.PreviewListener, ActionListener {
    private static final String TAG = "CamTestActivity";
    PreviewAdapter adapter;
    int angle;
    @Bind(R.id.btnActionLeft)
    TextView btnActionLeft;
    @Bind(R.id.btnActionRight)
    TextView btnActionRight;
    @Bind(R.id.btnCapture)
    ImageView btnCapture;
    @Bind(R.id.btnTips)
    View btnTips;
    Camera camera;
    int count;
    int currentCamera;
    String folderID;
    @Bind(R.id.img)
    ImageView image;
    PictureCallback jpegCallback;
    Preview preview;
    List<Uri> previews;
    PictureCallback rawCallback;
    @Bind(R.id.rv)
    RecyclerView rv;
    ShutterCallback shutterCallback;
    Uri uri;

    @Override
    public void onContinueClicked(int i) {
        if (!(this.uri == null || this.previews.contains(this.uri))) {
            this.previews.add(this.uri);
        }

        startActivityForResult(GroupCameraActivity.getStartIntent(this), 2);

        Intent intent = new Intent();
        ArrayList<String> data = new ArrayList();
        for (Uri uri : this.previews) {
            data.add(uri.toString());
        }
        intent.putStringArrayListExtra("data", data);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.1 */
    class C02421 implements OnClickListener {
        C02421() {
        }

        public void onClick(View v) {
            if (CameraActivity.this.camera != null) {
                Parameters params = CameraActivity.this.camera.getParameters();
                params.setFocusMode("auto");
                CameraActivity.this.camera.setParameters(params);
                CameraActivity.this.camera.takePicture(null, null, CameraActivity.this.jpegCallback);
                return;
            }
            if (CameraActivity.this.uri != null) {
                CameraActivity.this.previews.add(CameraActivity.this.uri);
                CameraActivity.this.adapter.notifyDataSetChanged();
            }
            CameraActivity.this.image.setImageBitmap(null);
            CameraActivity.this.image.setVisibility(View.GONE);
            CameraActivity.this.startCamera();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.2 */
    class C02442 implements OnTouchListener {

        /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.2.1 */
        class C02431 implements AutoFocusCallback {
            C02431() {
            }

            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();
                }
            }
        }

        C02442() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (CameraActivity.this.camera != null && CameraActivity.this.camera.getParameters().getSupportedFocusModes().contains("auto")) {
                Parameters params = CameraActivity.this.camera.getParameters();
                params.setFocusMode("auto");
                CameraActivity.this.camera.setParameters(params);
                CameraActivity.this.camera.autoFocus(new C02431());
            }
            return false;
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.3 */
    class C02453 implements OnClickListener {
        C02453() {
        }

        public void onClick(View v) {
            CameraActivity.this.submit();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.4 */
    class C02464 implements OnClickListener {
        C02464() {
        }

        public void onClick(View v) {
            CameraActivity.this.makeSureDialog();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.5 */
    class C02475 implements OnClickListener {
        C02475() {
        }

        public void onClick(View v) {
            CameraActivity.this.cancel();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.6 */
    class C02486 implements OnClickListener { //onClick
        C02486() {
        }

        public void onClick(View v) {
            CameraActivity.this.retake();
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.7 */
    class C02497 implements ShutterCallback {
        C02497() {
        }

        public void onShutter() {
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.8 */
    class C02508 implements PictureCallback {
        C02508() {
        }

        public void onPictureTaken(byte[] data, Camera camera) {
        }
    }

    /* renamed from: com.quickveggies.quickveggies.ui.activity.CameraActivity.9 */
    class C02519 implements PictureCallback {
        C02519() {
        }

        public void onPictureTaken(byte[] data, Camera camera) {
            CameraActivity cameraActivity = CameraActivity.this;
            cameraActivity.count++;
            CameraActivity.this.updateTakeState();
            new SaveImageTask().execute(new byte[][]{data});
            CameraActivity.this.stopCamera();
        }
    }

    private class SaveImageTask extends AsyncTask<byte[], Void, File> {
        private SaveImageTask() {
        }

        protected File doInBackground(byte[]... data) {
            FileNotFoundException e;
            IOException e2;
            Throwable th;
            Bitmap srcBmp = BitmapUtils.getScaledBitmap(data[0], 800, CameraActivity.this.angle);
            try {
                Bitmap dstBmp;
                if (srcBmp.getWidth() >= srcBmp.getHeight()) {
                    dstBmp = Bitmap.createBitmap(srcBmp, (srcBmp.getWidth() / 2) - (srcBmp.getHeight() / 2), 0, srcBmp.getHeight(), srcBmp.getHeight());
                } else {
                    dstBmp = Bitmap.createBitmap(srcBmp, 0, (srcBmp.getHeight() / 2) - (srcBmp.getWidth() / 2), srcBmp.getWidth(), srcBmp.getWidth());
                }
                File outFile = CameraActivity.this.getPhotoLink();
                FileOutputStream outStream = new FileOutputStream(outFile);
                FileOutputStream fileOutputStream;
                try {
                    outStream.write(BitmapUtils.bitmapToBytes(dstBmp));
                    outStream.flush();
                    outStream.close();
                    fileOutputStream = outStream;
                    return outFile;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream = outStream;
                    e.printStackTrace();
                    return null;
                } catch (IOException e4) {
                    e2 = e4;
                    fileOutputStream = outStream;
                    e2.printStackTrace();
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = outStream;
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                e.printStackTrace();
                return null;
            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace();
                return null;
            } catch (Throwable th3) {
                th = th3;
//                throw th3; //???
                return null;
            }
        }

        protected void onPostExecute(File outFile) {
            super.onPostExecute(outFile);
            CameraActivity.this.uri = Uri.fromFile(outFile);
            CameraActivity.this.setImageState();
        }
    }

    public CameraActivity() {
        this.currentCamera = 0;
        this.angle = 0;
        this.count = 0;
        this.previews = new ArrayList();
        this.shutterCallback = new C02497();
        this.rawCallback = new C02508();
        this.jpegCallback = new C02519();
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra("folderId", String.valueOf(System.currentTimeMillis()));
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.folderID = getIntent().getStringExtra("folderId");
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setContentView((int) R.layout.fragment_camera);
        ButterKnife.bind((Activity) this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        this.rv.setLayoutManager(lm);
        this.adapter = new PreviewAdapter(this.previews, this, this);
        this.rv.setAdapter(this.adapter);
        this.rv.addItemDecoration(((Builder) ((Builder) new Builder(this).color(getResources().getColor(17170445))).sizeResId(R.dimen.divider_preview)).build());
        this.preview = new Preview(this, (SurfaceView) findViewById(R.id.surfaceView));
        this.preview.setLayoutParams(new LayoutParams(-1, -1));
        ((RelativeLayout) findViewById(R.id.layout)).addView(this.preview);
        this.preview.setKeepScreenOn(true);
        this.btnCapture.setOnClickListener(new C02421());
        this.preview.setOnTouchListener(new C02442());
        this.btnActionRight.setOnClickListener(new C02453());
        this.btnTips.setOnClickListener(new C02464());
        setPhotoState();
    }

    private void updateTakeState() {
        if (this.count > 0) {
            this.btnCapture.setImageResource(R.drawable.ic_take_photo);
        } else {
            this.btnCapture.setImageResource(R.drawable.ic_take_group);
        }
    }

    private void makeSureDialog() {
        NotifyDialog.newInstance(0, R.string.make_sure, new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1)}))).show(getSupportFragmentManager(), "dialog");
    }

    private File getPhotoLink() {
        File folder = new File(getExternalCacheDir() + this.folderID);
        File file = new File(folder, "image" + System.currentTimeMillis() + ".jpg");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return file;
    }

    protected void onResume() {
        super.onResume();
        startCamera();
    }

    private void startCamera() {
        if (Camera.getNumberOfCameras() > 0) {
            try {
                this.camera = Camera.open(this.currentCamera);
                if (this.currentCamera == 0) {
                    this.angle = 90;
                } else {
                    this.angle = 270;
                }
                this.camera.setDisplayOrientation(90);
                this.camera.startPreview();
                Parameters params = this.camera.getParameters();
                if (params.getSupportedFocusModes().contains("continuous-picture")) {
                    params.setFocusMode("continuous-picture");
                }
                this.camera.setParameters(params);
                this.preview.setCamera(this.camera);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), getString(R.string.camera_not_found), 1).show();
            }
        }
    }

    private void showImage(Uri uri) {
        this.image.setVisibility(0);
        stopCamera();
        this.uri = null;
        Picasso.with(this).load(uri).fit().centerCrop().into(this.image);
        setImageState();
    }

    private void setPhotoState() {
        this.btnActionLeft.setText(R.string.camera_cancel);
        this.btnActionLeft.setOnClickListener(new C02475());
        this.btnActionRight.setVisibility(View.GONE);  // ?? must open this!!!
    }

    private void setImageState() {
        this.btnActionLeft.setText(R.string.camera_retake);
        this.btnActionLeft.setOnClickListener(new C02486());
        this.btnActionRight.setVisibility(View.VISIBLE);
    }

    private void cancel() {
        stopCamera();
        finish();
    }

    private void submit() {
//        verifyDialog();
        if (!(this.uri == null || this.previews.contains(this.uri))) {
            this.previews.add(this.uri);
        }
        Intent intent = new Intent();
        ArrayList<String> data = new ArrayList();
        for (Uri uri : this.previews) {
            data.add(uri.toString());
        }
        intent.putStringArrayListExtra("data", data);
        setResult(-1, intent);
        finish();
    }

    private void verifyDialog() {
        if (NotifyDialog.isShowDialog(this, 2)) {
            NotifyDialog notifyDialog = NotifyDialog.newInstance(2, R.string.verify_purchases, new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1), Integer.valueOf(R.string.item1)})));
            notifyDialog.show(getSupportFragmentManager(), "dialog");
            notifyDialog.setListener(this);
            return;
        }
        onContinueClicked(2);
    }

    private void retake() {
        this.image.setImageBitmap(null);
        setPhotoState();
        startCamera();
        this.count = 0;
        updateTakeState();
    }

    private void stopCamera() {
        if (this.camera != null) {
            this.camera.stopPreview();
            this.preview.setCamera(null);
            this.camera.release();
            this.camera = null;
        }
    }

    protected void onPause() {
        stopCamera();
        super.onPause();
    }

    private void resetCam() {
        this.camera.startPreview();
        this.preview.setCamera(this.camera);
    }

    public void onShow(Uri uri) {
        showImage(uri);
    }

    public void onDelete(Uri uri) {
        this.previews.remove(this.previews.indexOf(uri));
        this.adapter.notifyDataSetChanged();
    }
}
