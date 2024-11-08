package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.function.Supplier;

public class encoders {
    public encoders(HardwareMap map, Supplier<Boolean> active, Runnable idle) {
        FL = map.get(DcMotorEx.class, "FL");
        FR = map.get(DcMotorEx.class, "FR");
        BL = map.get(DcMotorEx.class, "BL");
        BR = map.get(DcMotorEx.class, "BR");

        Thing1 = map.get(Servo.class, "Thing1");
        Elbow = map.get(Servo.class, "Elbow");
        Thing2 = map.get(Servo.class, "Thing2");

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.active = active;
        this.idle = idle;
    }

    public void move(int fl, int fr, int bl, int br, double speed) {
        RobotLog.addGlobalWarningMessage("LLKV");

        fl_pos += fl;       fr_pos += fr;
        bl_pos += bl;       br_pos += br;

        FL.setTargetPosition(fl_pos);
        FR.setTargetPosition(fr_pos);
        BL.setTargetPosition(bl_pos);
        BR.setTargetPosition(br_pos);

        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);

        while ((FL.isBusy() || FR.isBusy() || BL.isBusy() || BR.isBusy()) && active.get()) {
            idle.run();
        }
    }

    public enum ServoPosition{INTAKE, OUTTAKE}


    /**
     * Intake method, intake or outtake for Thing1,
     * @param timeoutS Run for timeout seconds
     * @param Pos Run to intake or outtake
     */
    public void Intake(int timeoutS, ServoPosition Pos)
    {
        double ServoPos;
        switch(Pos){
            case INTAKE: ServoPos = 0;
            break;
            case OUTTAKE: ServoPos = 1;
            break;
            default: throw new RuntimeException("idiot, fix Intake Position in method");
        }
        ElapsedTime runtime = new ElapsedTime();


        while(runtime.seconds() <= timeoutS)
        {
            idle.run();
            Thing1.setPosition(ServoPos);
        }

    }

    int fl_pos = 0, fr_pos = 0, bl_pos = 0, br_pos = 0;
    DcMotorEx FL, FR, BL, BR;
    Servo Thing1, Elbow, Thing2;
    Supplier<Boolean> active;
    Runnable idle;
}