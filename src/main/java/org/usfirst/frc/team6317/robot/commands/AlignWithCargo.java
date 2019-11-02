package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

public class AlignWithCargo extends Command {

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

    char side;

    public AlignWithCargo(char side) {
        requires(Robot.driveSystem);
        this.side = side;
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

        if (side == 'l') { 
            if (x < -9) { // Marker is to the left so turn left
                Robot.driveSystem.turnLeft(0.35);
            } else if (x > -7) { // Marker is to the right so turn right
                Robot.driveSystem.turnRight(0.35);
            } else {
                finished = true;
            }
        } else {
            if (x > 9) { // Marker is to the right so turn right
                Robot.driveSystem.turnRight(0.35);
            } else if (x < 7) { // Marker is to the left so turn left
                Robot.driveSystem.turnLeft(0.35);
            } else {
                finished = true;
            }
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