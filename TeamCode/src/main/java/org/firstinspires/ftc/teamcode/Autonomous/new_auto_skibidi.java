package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous
public class new_auto_skibidi extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousTestV2withsigmaBrandonandOliver Auto = new AutonomousTestV2withsigmaBrandonandOliver(hardwareMap, telemetry, this::opModeIsActive, this::sleep);

        Auto.setup();

        waitForStart();

        Auto.Initialization();

        Auto.strafe(0.5, -16, 2);
        Auto.turn(0.5, -45, 2);
        Auto.fwd(0.5, -14, 2);
        Auto.strafe(0.5, -2, 1);
        Auto.moveVerticalLinearSlide(1, 4000);
        Auto.rotateThingyIntoBasket(2);
        Auto.origin(2);
        Auto.turn(0.5, -57, 2);
        Auto.strafe(0.5, 3.5, 2);
        Auto.fwd(0.5, 12, 2);
        Auto.moveHorizontalLinearSlide(0.5, 300, 2);
        Auto.ClawMove(AutonomousTestV2withsigmaBrandonandOliver.ClawPosition.CLOSED, 2);
        Auto.ElbowMove(AutonomousTestV2withsigmaBrandonandOliver.ElbowPos.UP, 2);
        Auto.ClawMove(AutonomousTestV2withsigmaBrandonandOliver.ClawPosition.OPEN, 2);
        Auto.ElbowMove(AutonomousTestV2withsigmaBrandonandOliver.ElbowPos.DOWN, 2);
        Auto.fwd(0.5, -13.8, 2);
        Auto.turn(0.5, 45, 2);
        Auto.strafe(0.5, -3, 1);
        Auto.fwd(0.5, -4, 1);
        Auto.moveVerticalLinearSlide(1, 4000);
        Auto.rotateThingyIntoBasket(2);
        Auto.origin(3);

    }
}
