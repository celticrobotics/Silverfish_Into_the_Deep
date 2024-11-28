package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTestV2withsigmaBrandonandOliver.Intake;


@Autonomous
public class new_auto_skibidi extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousTestV2withsigmaBrandonandOliver idk_man = new AutonomousTestV2withsigmaBrandonandOliver(hardwareMap, telemetry, this::opModeIsActive, this::sleep);

        idk_man.setup();
        //idk_man.fwd(0.5, 10, 2);
        idk_man.intake(Intake.OUTTAKE, 10);
    }
}
