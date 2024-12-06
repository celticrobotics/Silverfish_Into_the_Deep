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
        Servo Elbow;

        Servo = hardwareMap.get(Servo.class, "Thing 1");
<<<<<<< Updated upstream
        Wrist = hardwareMap.get(Servo.class, "Claw wrist");
        Elbow = hardwareMap.get(Servo.class, "Elbow");
=======
>>>>>>> Stashed changes

        waitForStart();

        Servo.setPosition(0);
<<<<<<< Updated upstream
        Wrist.setPosition(0.5);
=======
>>>>>>> Stashed changes

        while(opModeIsActive()){

            //ServoPos = -this.gamepad1.left_stick_y;
            //Servo.setPosition(ServoPos);

            if(gamepad1.x)
            {
<<<<<<< Updated upstream
                Servo.setPosition(1);
=======
                Servo.setPosition(0.5);
>>>>>>> Stashed changes
            }
            else if(gamepad1.b)
            {
                Servo.setPosition(0);
            }

            if(gamepad1.a)
            {
                Wrist.setPosition(0.1);
            }
            else if(gamepad1.y)
            {
                Wrist.setPosition(0.44);
            }

            if (gamepad1.dpad_right){
                Elbow.setPosition(0.8);
            } else if(gamepad1.dpad_left) {
                Elbow.setPosition(0.1);
            }

            telemetry.addData("Elbow", Elbow.getPosition());
            telemetry.addData("Claw Pos", Servo.getPosition());
            telemetry.addData("Wrist Pos", Wrist.getPosition());

            telemetry.update();

        }

    }
}
