package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Quicker Auto")
public class FastAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousTestV2withsigmaBrandonandOliver Auto = new AutonomousTestV2withsigmaBrandonandOliver(hardwareMap, telemetry, this::opModeIsActive, this::sleep);

        Auto.setup();

        waitForStart();

        Auto.Initialization();

        // Pre-Load Sample
        Auto.strafe(0.5, -10, 0.8);
        Auto.fwd(0.5, -12, 0.8);
        Auto.turn(0.5, -55, 0.7);
        Auto.fwd(0.5, -8, 1);
        Auto.moveVerticalLinearSlide(1, 4000);
        Auto.rotateThingyIntoBasket(2);
        Auto.origin(2);

        // 1st Sample
        Auto.fwd(0.5, 12, 1);
        Auto.turn(0.5, -45, 1);
        Auto.fwd(0.5, 7, 1);
        Auto.moveHorizontalLinearSlide(0.5, 300, 1);
        Auto.ClawMove(AutonomousTestV2withsigmaBrandonandOliver.ClawPosition.CLOSED, 0.7);
        Auto.ElbowMove(AutonomousTestV2withsigmaBrandonandOliver.ElbowPos.UP, 0.7);
        Auto.ClawMove(AutonomousTestV2withsigmaBrandonandOliver.ClawPosition.OPEN, 0.7);
        Auto.ElbowMove(AutonomousTestV2withsigmaBrandonandOliver.ElbowPos.DOWN, 0.7);
        Auto.fwd(0.5, -10, 1);
        Auto.turn(0.5, 45, 1);
        Auto.fwd(0.5, -7, 1);
        Auto.moveVerticalLinearSlide(1, 4000);
        Auto.rotateThingyIntoBasket(2);
        Auto.origin(2);
    }
}
