package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Motor Test")
public class Basic_Motor_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor upSlide;

        upSlide = hardwareMap.get(DcMotor.class, "upSlide");
        upSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        upSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upSlide.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        upSlide.setPower(0.5);


        upSlide.setTargetPosition(4000);
        upSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(opModeIsActive())
        {


        }
    }
}
