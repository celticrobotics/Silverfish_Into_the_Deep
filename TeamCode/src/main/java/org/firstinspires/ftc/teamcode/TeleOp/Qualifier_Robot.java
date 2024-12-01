package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Qualifier Robot Code")
public class Qualifier_Robot extends LinearOpMode {
    Robot1Setup Setup = new Robot1Setup(hardwareMap, telemetry, this::opModeIsActive, this::sleep);


    @Override
    public void runOpMode() throws InterruptedException {

        Setup.setup();

        waitForStart();

        while(opModeIsActive())
        {
            
        }

    }
}
