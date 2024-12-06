package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Qualifier Robot Code")
public class Qualifier_Robot extends LinearOpMode {
//    private final HardwareMap hardwareMap;
//    private final Telemetry telemetry;
//    private final Supplier<Boolean> opModeIsActive;
//    private final Consumer<Long> sleep;

// CONTROLS FOR 1 GAME PAD:
// Buttons: X Close Claw
//          B Open Claw
//          A Wrist for Sample Horizontal
//          Y Wrist for Sample Vertical

// Left stick and Right stick: Chassis movement

// Dpad: Up: upSlide up
//       Down: upSlide down
//       Right: sideSlide extract
//       Left: sideSlide retract

// Stick Button: Right: Elbow Up
//               Left: Elbow Down

    private DcMotor FL;
    private DcMotor FR;
    private DcMotor BL;
    private DcMotor BR;

    DcMotor sideSlide;
    DcMotor upSlide;

    Servo Wrist;
    Servo Elbow;
    Servo Claw;
    Servo Bucket;

    private final ElapsedTime runtime = new ElapsedTime();

    int sideSlidePos;
    int upSlidePos;

    @Override
    public void runOpMode() throws InterruptedException {

        setup();

        waitForStart();

        Claw.setPosition(0);
        Elbow.setPosition(0.1);
        Wrist.setPosition(0.44);
        sideSlide.setTargetPosition(300);

        while(opModeIsActive())
        {
            sideSlide.setPower(0.5);
            upSlide.setPower(0.5);

            Move();

            setUpSlidePos();

            // Control slides by tic increase
            if (gamepad1.dpad_right) {
                sideSlidePos += 75;

            } else if (gamepad1.dpad_left) {
                sideSlidePos -= 75;
            }

            //Claw Control
            if(gamepad1.x)
            {
                // Closed
                Claw.setPosition(1);
            }
            else if(gamepad1.b)
            {
                //Open
                Claw.setPosition(0);
            }

            // Wrist control
            if(gamepad1.a)
            {
                //Sample Horizontal
                Wrist.setPosition(0.1);
            }
            else if(gamepad1.y)
            {
                //Sample Vertical
                Wrist.setPosition(0.44);
            }

            // Elbow Control
            if (gamepad1.right_stick_button)
            {
                //Elbow up
                Elbow.setPosition(0.8);
            }
            else if(gamepad1.left_stick_button)
            {
                //Elbow down
                Elbow.setPosition(0.1);
            }

            //Display telemetry
            getTelemetry();

            // Slide Constraints --> SIDE SLIDE MUST BE BELOW 1900 FOR COMP (Horizontal expansion limit)
            upSlidePos = Math.max(0, Math.min(4000, upSlidePos));
            sideSlidePos = Math.max(0, Math.min(1900, sideSlidePos));

            // Slides run to pos
            upSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sideSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

    }



    // Setup: HardwareMap, motor direction, set brake, encoder setup(Run using encoders, reset encoders)
    public void setup() {
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        sideSlide = hardwareMap.get(DcMotor.class, "sideSlide");
        upSlide = hardwareMap.get(DcMotor.class, "upSlide");

        Wrist = hardwareMap.get(Servo.class, "Claw wrist");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Claw = hardwareMap.get(Servo.class, "Thing 1");
        Bucket = hardwareMap.get(Servo.class, "Thing2");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);

        upSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sideSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sideSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        upSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sideSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //Display telemetry during opMode: All servos and both slides positions
    public void getTelemetry(){
        telemetry.addData("Elbow", Elbow.getPosition());
        telemetry.addData("Wrist", Wrist.getPosition());
        telemetry.addData("Claw", Claw.getPosition());
        telemetry.addData("SideSlide", sideSlide.getCurrentPosition());
        telemetry.addData("UpSlide", upSlide.getCurrentPosition());
        telemetry.update();
    }


    public void setUpSlidePos(){
        if (gamepad1.dpad_up)
        {
            upSlide.setTargetPosition(4000);
        }
        else if(gamepad1.dpad_down)
        {
            upSlide.setTargetPosition(0);
        }
    }

    private void Move() {
        // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
        // We negate this value so that the topmost position corresponds to maximum forward power.
        BR.setPower(0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * gamepad1.left_stick_x - gamepad1.right_stick_x)));
        BL.setPower(0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * -gamepad1.left_stick_x - gamepad1.right_stick_x)));
        // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
        // We negate this value so that the topmost position corresponds to maximum forward power.
        FR.setPower(0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * -gamepad1.left_stick_x - gamepad1.right_stick_x)));
        FL.setPower(0.5 * (1 * gamepad1.left_stick_y + 1 * -(1 * gamepad1.left_stick_x + gamepad1.right_stick_x)));
    }


//    public void setElbow(boolean rotation){
//        if (rotation)
//        {
//            Elbow.setPosition(0.8);
//        } else
//        {
//            Elbow.setPosition(0.1);
//        }
//    }
//    public void setWrist(boolean rotation){
//        if (rotation){
//            Wrist.setPosition(0.5);
//        } else {
//            Wrist.setPosition(0.1);
//        }
//    }

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

// Currently moved, code above needs to be in the while loop to function

//    public void move(double rbPower, double lbPower, double rfPower, double lfPower){
//        rightBack.setPower(rbPower);
//        leftBack.setPower(lbPower);
//        rightFront.setPower(rfPower);
//        leftFront.setPower(lfPower);
//    }

}
