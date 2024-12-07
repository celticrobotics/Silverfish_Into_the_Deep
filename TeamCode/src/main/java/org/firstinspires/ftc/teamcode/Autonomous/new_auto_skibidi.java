package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTestV2withsigmaBrandonandOliver.Intake;


@Autonomous
public class new_auto_skibidi extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousTestV2withsigmaBrandonandOliver Auto = new AutonomousTestV2withsigmaBrandonandOliver(hardwareMap, telemetry, this::opModeIsActive, this::sleep);

        Auto.setup();
        //Auto for blue right:
        //Strafe one Tile
        //Forward one Tile

        Auto.turn(0.5, 90, 2);
        Auto.fwd(0.5, 10, 2);
        Auto.moveVerticalLinearSlide(0.5, 300, 10);
        Auto.rotateOurElbow(0.95);
        Auto.rotateThingyIntoBasket();
        Auto.origin();
        //Auto.ArmPos(1000, 2);
    }
}
