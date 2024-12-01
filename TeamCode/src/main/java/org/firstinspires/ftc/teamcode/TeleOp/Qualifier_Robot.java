package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Consumer;
import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Set;

@TeleOp(name = "Qualifier Robot Code")
public class Qualifier_Robot extends LinearOpMode {
//    private final HardwareMap hardwareMap;
//    private final Telemetry telemetry;
//    private final Supplier<Boolean> opModeIsActive;
//    private final Consumer<Long> sleep;

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    DcMotor sideSlide;
    DcMotor upSlide;

    Servo Wrist;
    Servo Elbow;
    Servo Claw;
    Servo Bucket;

    private final ElapsedTime runtime = new ElapsedTime();

    int sideSlidePos;

    @Override
    public void runOpMode() throws InterruptedException {

        setup();

        waitForStart();

        //Claw.setPosition(0);
        sideSlide.setTargetPosition(800);

        while(opModeIsActive())
        {

//            move((0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * gamepad1.left_stick_x - gamepad1.right_stick_x))),
//                    (0.5 * (1 * gamepad1.left_stick_y + 1 * (1 * gamepad1.left_stick_x - gamepad1.right_stick_x))),
//                    (0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * -gamepad1.left_stick_x - gamepad1.right_stick_x))),
//                    (0.5 * (1 * gamepad1.left_stick_y + 1 * -(1 * gamepad1.left_stick_x + gamepad1.right_stick_x))));

//            if (gamepad1.a){
//                // Sets Wrist position to 0.5
//                Wrist.setPosition(0.5);
//            } else if (gamepad1.y){
//                // Sets Wrist position to 0.1
//                Wrist.setPosition(0.1);
//            }


            // Sets Elbow position
            setElbow(gamepad1.x);
            setWrist(gamepad1.b);

//            if(gamepad1.dpad_left)
//            {
//                Claw.setPosition(0);
//            }
//            else if(gamepad1.dpad_right)
//            {
//                Claw.setPosition(0.5);
//            }


            if (gamepad2.y){
                // Sets UpSlide target position to 1500
                setUpSlidePos(true);

            } else if (gamepad2.a){
                // Sets UpSlide target position to 0
                setUpSlidePos(false);
            }

            if (gamepad2.x) {
                sideSlidePos += 100;

            } else if (gamepad2.b) {
                sideSlidePos -= 100;
            }

            getTelemetry();
        }

    }

    public void setup() {
        leftFront = hardwareMap.get(DcMotor.class, "FL");
        rightFront = hardwareMap.get(DcMotor.class, "FR");
        leftBack = hardwareMap.get(DcMotor.class, "BL");
        rightBack = hardwareMap.get(DcMotor.class, "BR");

        sideSlide = hardwareMap.get(DcMotor.class, "sideSlide");
        upSlide = hardwareMap.get(DcMotor.class, "upSlide");

        Wrist = hardwareMap.get(Servo.class, "Claw wrist");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Claw = hardwareMap.get(Servo.class, "Thing 1");
        Bucket = hardwareMap.get(Servo.class, "Thing2");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sideSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sideSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        upSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        upSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void getTelemetry(){
        telemetry.addData("Elbow", Elbow.getPosition());
        telemetry.addData("Wrist", Wrist.getPosition());
        telemetry.addData("Claw", Claw.getPosition());
        telemetry.addData("SideSlide", sideSlide.getCurrentPosition());
        telemetry.addData("UpSlide", upSlide.getCurrentPosition());
        telemetry.update();
    }

//    public void move(double rbPower, double lbPower, double rfPower, double lfPower){
//        rightBack.setPower(rbPower);
//        leftBack.setPower(lbPower);
//        rightFront.setPower(rfPower);
//        leftFront.setPower(lfPower);
//    }

    public void setElbow(boolean rotation){
        if (rotation){
            Elbow.setPosition(0.95);
        } else {
            Elbow.setPosition(0.3);
        }
    }
    public void setWrist(boolean rotation){
        if (rotation){
            Wrist.setPosition(0.5);
        } else {
            Wrist.setPosition(0.1);
        }
    }



    public void setUpSlidePos(boolean direction){
        if (direction) {
            upSlide.setTargetPosition(1500);
            upSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            upSlide.setTargetPosition(0);
            upSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
