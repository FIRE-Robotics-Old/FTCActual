package org.firstinspires.ftc.teamcode.cameravision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class HeightDetector {
    /**
     * This connects to the phone's camera to capture an image of the rings.
     * <p>
     * Currently, this uses the {@link OpenCvInternalCamera} class to temporally get
     * access to advanced features. However, when we switch to a web camera, this will have to be
     * refactored to use the webcam.
     */
    private OpenCvInternalCamera camera;

    /**
     * A Simple constructor which creates a Ring Height Detector Instance
     * @param hardwareMap The {@link HardwareMap} Generated by OpMode to connect to the phone
     */
    public HeightDetector(HardwareMap hardwareMap) {
        init(hardwareMap);
    }

    /**
     * Initializes the Object and starts streaming (will be split up later)
     * <p>
     * This connects the camera to the pipeline and initializes development.
     * @param hardwareMap A HardwareMap which allows the creation of a phone instance
     */
    private void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId",
                "id",
                hardwareMap.appContext.getPackageName()
        );
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(
                OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId
        );

        // Sets up the Camera to use the pipeline which detects the height of the rings
        camera.setPipeline(new RingHeightPipeline());

        // Starts Streaming the Camera Contents to the phone
        camera.openCameraDeviceAsync(() ->
                camera.startStreaming(640 /*320*/, 480 /*240*/, OpenCvCameraRotation.UPRIGHT)
        );
    }

    static class RingHeightPipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            return null;
        }
    }
}
