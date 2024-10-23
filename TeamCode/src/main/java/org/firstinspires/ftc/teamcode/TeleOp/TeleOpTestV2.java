package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOp Test V2", group = "Qualifiers")
public class TeleOpTestV2 extends LinearOpMode {

    Servo Thing1;
    Servo Elbow;
    Servo Thing2;
    DcMotor FL;
    DcMotor BL;
    DcMotor FR;
    DcMotor BR;
    DcMotor upSlide;
    DcMotor sideSlide;

    //Slides Pos Variables
    int sideSlidePos;
    double upSlidePos;
    double Thing1Pos;
    double ElbowPos;
    double Thing2Pos;

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        Thing1 = hardwareMap.get(Servo.class, "Thing1");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
        Thing2 = hardwareMap.get(Servo.class, "Thing2");

        sideSlide = hardwareMap.get(DcMotor.class, "sideSlide");
        upSlide = hardwareMap.get(DcMotor.class, "upSlide");

        sideSlidePos = sideSlide.getCurrentPosition();
        upSlidePos = upSlide.getCurrentPosition();

        telemetry.addData("sideSlide Starting: ", sideSlidePos);
        telemetry.addData("upSlide Starting: ", upSlidePos);

        telemetry.addData("Thing1Pos Starting: ", Thing1Pos);

        waitForStart();

        while(opModeIsActive())
        {

        }

    }
}
