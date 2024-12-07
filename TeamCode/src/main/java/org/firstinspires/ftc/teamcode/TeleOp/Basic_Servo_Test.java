package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Basic Servo Test")
public class Basic_Servo_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Servo Servo;
        double ServoPos;
        Servo Wrist;

        Servo = hardwareMap.get(Servo.class, "Thing 1");
        Wrist = hardwareMap.get(Servo.class, "Claw wrist");


        waitForStart();

        Servo.setPosition(0.9);
        Wrist.setPosition(0);

        while(opModeIsActive()){

            //ServoPos = -this.gamepad1.left_stick_y;
            //Servo.setPosition(ServoPos);

            if(gamepad1.x)
            {
                Servo.setPosition(0.5);
            }
            else if(gamepad1.b)
            {
                Servo.setPosition(0);
            }

            if(gamepad1.a)
            {
                Wrist.setPosition(0);
            }
            else if(gamepad1.y)
            {
                Wrist.setPosition(0.6);
            }

            telemetry.addData("Claw Pos", Servo.getPosition());
            telemetry.addData("Wrist Pos", Wrist.getPosition());

            telemetry.update();

        }

    }
}
