package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDrive extends Command {

    int state;

    public ShiftDrive(int state) {
        this.state = state;
    }

    @Override
    protected void initialize() {
        if (state == 0) 
            Robot.solenoid.shiftDrive(true);
        else if (state == 1)
            Robot.solenoid.shiftDrive(false);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}