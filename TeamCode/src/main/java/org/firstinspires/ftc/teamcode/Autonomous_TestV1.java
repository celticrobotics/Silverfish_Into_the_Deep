package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.encoders.encoders;

@Autonomous(name = "Auto test")
public class Autonomous_TestV1 extends LinearOpMode {
//    @Override public void runOpMode() {
//        encoders enc = new encoders(hardwareMap, () -> {
//            telemetry.addLine("something is hapeong idk");
//            telemetry.update();
//            return opModeIsActive(); }, this::idle);
//
//        waitForStart();
//
//        enc.move(2000, 2000, 2000, 2000, 0.6);
//    }


    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    DcMotor sideSlide;

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_REV = 537.7;
    static final double CIRCUMFERENCE_CM = 9.6;

    static final double COUNTS_PER_CM = COUNTS_PER_REV / CIRCUMFERENCE_CM;

    static final double power = 0.20;

    @Override
    public void runOpMode() {
        setup();


        telemetry.addData("Status", "Initialized");
        telemetry.addData("EncoderLeft: ", String.valueOf(leftFront.getCurrentPosition()), "Encoder Right: ", rightFront.getCurrentPosition());
        telemetry.update();

        waitForStart();

        fwd(power, 8.0, 1.0);
        fwd(0.3, 3.0, 2.0);
        fwd(1.0, 4.0, 4.0);
        turn(0.2, 5, 1.0);
        strafe(0.2, 5, 1.0);

        bring_block_into_up_bucket();

        while (opModeIsActive()){

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    private void move(double speed, double[] distance, double timeout) {

        int leftBackT;
        int rightBackT;
        int leftFrontT;
        int rightFrontT;

        leftFrontT = leftFront.getCurrentPosition() + (int)(distance[0] * COUNTS_PER_CM);
        rightFrontT = rightFront.getCurrentPosition() + (int)(distance[1] * COUNTS_PER_CM);
        leftBackT = leftBack.getCurrentPosition() + (int)(distance[2] * COUNTS_PER_CM);
        rightBackT = rightBack.getCurrentPosition() + (int)(distance[3] * COUNTS_PER_CM);

        leftBack.setTargetPosition(leftBackT);
        rightBack.setTargetPosition(rightBackT);
        leftFront.setTargetPosition(leftFrontT);
        rightFront.setTargetPosition(rightFrontT);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        leftBack.setPower(Math.abs(speed));
        rightBack.setPower(Math.abs(speed));
        leftFront.setPower(Math.abs(speed));
        rightFront.setPower(Math.abs(speed));


            while (opModeIsActive() && (leftFront.isBusy() || rightFront.isBusy()) && (runtime.seconds() < timeout)){

//                telemetry.addData("LeftDrive ", (leftFront.getCurrentPosition() / CIRCUMFERENCE_CM));
//                telemetry.addData("RightDrive ", (rightFront.getCurrentPosition() / CIRCUMFERENCE_CM));

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

    private void fwd(double speed, double distance, double timeout) {
        move(speed, new double[]{distance, distance, distance, distance}, timeout);
    }

    private void turn(double speed, double deg, double timeout) {
        move(speed, new double[]{-deg, deg, -deg, deg}, timeout);
    }

    private void strafe(double speed, double distance, double timeout) {
        move(speed, new double[]{-distance, distance, distance, -distance}, timeout);
    }

    private void setup() {
        leftFront = hardwareMap.get(DcMotor.class, "FL");
        rightFront = hardwareMap.get(DcMotor.class, "FR");
        leftBack = hardwareMap.get(DcMotor.class, "BL");
        rightBack = hardwareMap.get(DcMotor.class, "BR");

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

        sideSlide = hardwareMap.get(DcMotor.class, "sideSlide");
        sideSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void bring_block_into_up_bucket() {
        RobotLog.a(String.copyValueOf(new char[]{104, 101, 108, 108, 111}));

        // bring side slide into robot
        sideSlide.setTargetPosition(-2100);
        sideSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sideSlide.setPower(1);

        while (sideSlide.isBusy() && opModeIsActive()) {
            idle();
        }
    }
}