package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Set;

@TeleOp(name = "Qualifier Robot Code")
public class Qualifier_Robot extends LinearOpMode {
    Robot1Setup Setup = new Robot1Setup(hardwareMap, telemetry, this::opModeIsActive, this::sleep);


    @Override
    public void runOpMode() throws InterruptedException {

        Setup.setup();

        waitForStart();

        while(opModeIsActive())
        {
            Setup.move((0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * gamepad1.left_stick_x - gamepad1.right_stick_x))),
                    (0.5 * (1 * gamepad1.left_stick_y + 1 * (1 * gamepad1.left_stick_x - gamepad1.right_stick_x))),
                    (0.5 * (1 * -gamepad1.left_stick_y + 1 * (1 * -gamepad1.left_stick_x - gamepad1.right_stick_x))),
                    (0.5 * (1 * gamepad1.left_stick_y + 1 * -(1 * gamepad1.left_stick_x + gamepad1.right_stick_x))));

            if (gamepad1.a){
                // Sets Wrist position to 0.5
                Setup.setWrist(true);
            } else if (gamepad1.y){
                // Sets Wrist position to 0.1
                Setup.setWrist(false);
            }

            // Sets Elbow position to 0.95
            Setup.setElbow(gamepad1.x);

            if (gamepad2.y){
                // Sets UpSlide target position to 1500
                Setup.setUpSlidePos(true);

            } else if (gamepad2.a){
                // Sets UpSlide target position to 0
                Setup.setUpSlidePos(false);
            }

            Setup.getTelemetry();
        }

    }
}
