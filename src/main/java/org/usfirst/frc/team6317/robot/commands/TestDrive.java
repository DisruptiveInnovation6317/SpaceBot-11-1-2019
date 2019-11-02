package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestDrive extends Command {

    public TestDrive() {
        requires(Robot.driveSystem);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.driveSystem.turnRight(0.35);
    }

    @Override
    protected boolean isFinished() {
        return Robot.shouldCancel;
    }
    
    @Override
    protected void end() {
        Robot.driveSystem.drive(0);
        Robot.shouldCancel = false;
    }
}