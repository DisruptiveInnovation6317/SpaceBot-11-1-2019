package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftLifts extends Command {

    DoubleSolenoid lift;
    boolean goUp;

    public ShiftLifts(DoubleSolenoid lift, boolean up) {
        this.lift = lift;
        this.goUp = up;
    }

    @Override
    protected void initialize() {
        if (goUp) {
            Robot.solenoid.shiftUp(lift);
        } else {
            Robot.solenoid.shiftDown(lift);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}