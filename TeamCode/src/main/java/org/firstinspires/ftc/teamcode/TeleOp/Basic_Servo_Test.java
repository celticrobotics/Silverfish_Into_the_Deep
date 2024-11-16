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

        Servo = hardwareMap.get(Servo.class, "Elbow");

        waitForStart();

        Servo.setPosition(0.9);

        while(opModeIsActive()){

            //ServoPos = -this.gamepad1.left_stick_y;
            //Servo.setPosition(ServoPos);

            if(gamepad1.x)
            {
                Servo.setPosition(0.9);
            }
            else if(gamepad1.b)
            {
                Servo.setPosition(0.3);
            }

            telemetry.addData("Elbow Pos", Servo.getPosition());
            telemetry.update();

        }

    }
}
