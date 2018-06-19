package ru.sigachev.station;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import app.advanced.tensor.Classifier;

public class CameraActivity extends AppCompatActivity {


    private static final int INPUT_SIZE = 300;

    private static final int IMAGE_MEAN = 117;

    private static final float IMAGE_STD = 1;

    private static final String INPUT_NAME = "input";

    private static final String OUTPUT_NAME = "output";



    private static final String MODEL_FILE = "//android_asset/saved_model.pb";

    private static final String LABEL_FILE = "//android_asset/labels.pbtxt";



    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();

    private Button btnDetect;

    private ImageView imageViewResult;

    private CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraView = (CameraView) findViewById(R.id.cameraView);
        btnDetect = (Button) findViewById(R.id.detect);
        imageViewResult = (ImageView) findViewById(R.id.ctrIMG);


        cameraView.addCameraKitListener(new CameraKitEventListener() {

            @Override

            public void onEvent(CameraKitEvent cameraKitEvent) {


            }


            @Override

            public void onError(CameraKitError cameraKitError) {


            }


            @Override

            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

                imageViewResult.setImageBitmap(bitmap);

                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);

               // textViewResult.setText(results.toString());


            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }


        });

        btnDetect.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                cameraView.captureImage();

            }

        });

        initTensorFlowAndLoadModel();
    }

    @Override

    protected void onResume() {

        super.onResume();

        cameraView.start();

    }



    @Override

    protected void onPause() {

        cameraView.stop();

        super.onPause();

    }



    @Override

    protected void onDestroy() {

        super.onDestroy();

        executor.execute(new Runnable() {

            @Override

            public void run() {

                classifier.close();

            }

        });

    }

    private void initTensorFlowAndLoadModel() {

        executor.execute(new Runnable() {

            @Override

            public void run() {

                try {

                    classifier = com.mindorks.tensorflowexample.TensorFlowImageClassifier.create(

                            getAssets(),

                            MODEL_FILE,

                            LABEL_FILE,

                            INPUT_SIZE,

                            IMAGE_MEAN,

                            IMAGE_STD,

                            INPUT_NAME,

                            OUTPUT_NAME);

                   // makeButtonVisible();

                } catch (final Exception e) {

                    throw new RuntimeException("Error initializing TensorFlow!", e);

                }

            }

        });

    }
}
