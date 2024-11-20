package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Motor Test")
public class Basic_Motor_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor Slide;

        Slide = hardwareMap.get(DcMotor.class, "Slide");
        //Slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //Slide.setDirection(DcMotorSimple.Direction.REVERSE);
        Slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();



        //Slide.setTargetPosition(4000);
        //.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(opModeIsActive())
        {
            Slide.setPower(1);

        }
    }
}
