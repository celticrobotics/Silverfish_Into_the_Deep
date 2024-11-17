package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Locale;

@Autonomous(name = "Auto test sigma")
public class AutonomousTestV2withsigmaBrandonandOliver {
    AutonomousTestV2withsigmaBrandonandOliver(HardwareMap map) {
        hardwareMap = map;
    }


    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    DcMotor sideSlide;
    DcMotor upSlide;

    Servo Thing11;
    Servo Thing12;
    Servo Elbow;
    Servo Thing2;

    private final ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_REV = 537.7;
    static final double CIRCUMFERENCE_CM = 9.6;

    static final double COUNTS_PER_CM = COUNTS_PER_REV / CIRCUMFERENCE_CM;

    static final double power = 0.20;


    private void move(double speed, double[] distance, double timeout) {

        int leftBackT;
        int rightBackT;
        int leftFrontT;
        int rightFrontT;

        leftFrontT = leftFront.getCurrentPosition() + (int) (distance[0] * COUNTS_PER_CM);
        rightFrontT = rightFront.getCurrentPosition() + (int) (distance[1] * COUNTS_PER_CM);
        leftBackT = leftBack.getCurrentPosition() + (int) (distance[2] * COUNTS_PER_CM);
        rightBackT = rightBack.getCurrentPosition() + (int) (distance[3] * COUNTS_PER_CM);

        leftBack.setTargetPosition(leftBackT);
        rightBack.setTargetPosition(rightBackT);
        leftFront.setTargetPosition(leftFrontT);
        rightFront.setTargetPosition(rightFrontT);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        leftBack.setPower(speed);
        rightBack.setPower(speed);
        leftFront.setPower(speed);
        rightFront.setPower(speed);

        while (opModeIsActive() && (leftFront.isBusy() || rightFront.isBusy()) && (runtime.seconds() < timeout)) {
            telemetry.addData("LeftDrive ", leftFront.getCurrentPosition());
            telemetry.addData("RightDrive ", rightFront.getCurrentPosition());
            telemetry.addData("LeftDrive Target Pos", leftFrontT);
            telemetry.addData("RightDrive Target Pos", rightFrontT);

            telemetry.update();

        }

        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        sleep(50);
    }

    public void fwd(double speed, double distance, double timeout) {
        move(speed, new double[]{distance, distance, distance, distance}, timeout);
    }

    public void turn(double speed, double dgr, double timeout) {
        final double real_degrees_idk_bruh_dont_ask_me_skibidi = dgr / -6.42857142857; //360/56 --> (7 dgr = 45 degree) * 8 to reach 360

        move(speed, new double[]{
                        -real_degrees_idk_bruh_dont_ask_me_skibidi,
                        real_degrees_idk_bruh_dont_ask_me_skibidi,
                        -real_degrees_idk_bruh_dont_ask_me_skibidi,
                        real_degrees_idk_bruh_dont_ask_me_skibidi},
                timeout);

    }

    public void strafe(double speed, double distance, double timeout) {
        move(speed, new double[]{distance, -distance, -distance, distance}, timeout);
    }

    public void setup() {
        leftFront = hardwareMap.get(DcMotor.class, "FL");
        rightFront = hardwareMap.get(DcMotor.class, "FR");
        leftBack = hardwareMap.get(DcMotor.class, "BL");
        rightBack = hardwareMap.get(DcMotor.class, "BR");

        sideSlide = hardwareMap.get(DcMotor.class, "sideSlide");
        upSlide = hardwareMap.get(DcMotor.class, "upSlide");

        Thing11 = hardwareMap.get(Servo.class, "Thing1_1");
        Thing12 = hardwareMap.get(Servo.class, "Thing1_2");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Thing2 = hardwareMap.get(Servo.class, "Thing2");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sideSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        upSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sideSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        upSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void moveVerticalLinearSlide(double speed, int position, int timeout){
        int upSlideT = upSlide.getCurrentPosition() + position;

        upSlide.setTargetPosition(upSlideT);
        upSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        upSlide.setPower(speed);

        while (opModeIsActive() && upSlide.isBusy() && runtime.seconds() < timeout) {
            final int currentPosition = upSlide.getCurrentPosition();

            telemetry.addLine()
                    .addData("target position", upSlideT)
                    .addData("current position", currentPosition)
                    .addData("completion", String.format(Locale.CANADA, "%d%%", (currentPosition / upSlideT) * 100));
        }

        upSlide.setPower(0);
        upSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveHorizontalLinearSlide(double speed, int position, int timeout){
        int sideSlideT = sideSlide.getCurrentPosition() + position;

        sideSlide.setTargetPosition(sideSlideT);
        sideSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        sideSlide.setPower(speed);

        while (opModeIsActive() && sideSlide.isBusy() && runtime.seconds() < timeout) {
            final int currentPosition = sideSlide.getCurrentPosition();

            telemetry.addLine()
                    .addData("target position", sideSlideT)
                    .addData("current position", currentPosition)
                    .addData("completion", String.format(Locale.CANADA, "%d%%", (currentPosition / sideSlideT) * 100));
        }

        sideSlide.setPower(0);
        sideSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void rotateThingyIntoBasket() {
        // Might break because Emmy didn't give shit
        Thing2.setPosition(0);

        sleep(600);

        // Might break because Emmy didn't give shit
        Thing2.setPosition(1);
    }

    public void scoreBlockThingy() {
        moveVerticalLinearSlide(0.7, 4000, 5);
        rotateThingyIntoBasket();
        moveVerticalLinearSlide(0.7, 250, 5);
    }

    // might not work, imma blame emmy for lack of info
    public void intake() {
        Thing11.setPosition(0);
        Thing12.setPosition(1);
    }

    // might not work, imma blame emmy for lack of info
    public void outtake() {
        Thing11.setPosition(1);
        Thing12.setPosition(0);
    }

    ... void rotateOurElbow(double position) {
        Elbow.setPosition(position);
    }
}