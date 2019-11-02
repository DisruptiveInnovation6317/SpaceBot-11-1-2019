package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToRocket extends Command {

    NetworkTable table;
	NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
	NetworkTableEntry tv;

	double x;
	double y;
	double target;
    double area;
    
    boolean finished = false;

    public DriveToRocket() {
        requires(Robot.driveSystem);
    }

    @Override
    protected void initialize() {
        stage = 0;
        table = NetworkTableInstance.getDefault().getTable("limelight");
	    tx = table.getEntry("tx");
	    ty = table.getEntry("ty");
	    ta = table.getEntry("ta");
	    tv = table.getEntry("tv");
    }

    int stage = 0;

    @Override
    protected void execute() {
        updateVariables();

        if (stage == 0) {
            if (x < -2) {
                double diff = (x - 2)/30;
                // Robot.driveSystem.drive((diff) + 0.25,(-diff) + -0.25);
                Robot.driveSystem.drive(0.35, -0.35);
                Robot.status = "Turning Left";
            } else if (x > 2) {
                double diff = (x - 2)/30;
                // Robot.driveSystem.drive(((-diff) + -0.25), (diff) + 0.25);
                Robot.driveSystem.drive(-0.35,0.35);
                Robot.status = "Turning Right";
            } else {
                Robot.driveSystem.drive(0);
                stage = 1;
            }
        } else if (stage == 1) {
            // Robot.driveSystem.adjustDrive(0.5, 0.5);
            Robot.status = "Driving Straight";
            finished = true;
        }
    }

    private void updateVariables() {
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        target = tv.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

    @Override
    protected boolean isFinished() {
        return finished || Robot.shouldCancel;
    }
    
    @Override
    protected void end() {
        Robot.driveSystem.drive(0);
        finished = false;
        Robot.shouldCancel = false;
    }
}