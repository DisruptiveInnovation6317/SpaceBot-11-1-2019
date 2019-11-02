package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToRocket extends Command {

    final double TURN_SPEED = 0.65;
    final double COMPENSATION = Robot.COMPENSATION;

    double angles[] = {30.0, 150.0, (360.0-150.0), (360-30.0)};
    double endPosition;

    public TurnToRocket() {
        requires(Robot.driveSystem);
    }

    @Override
    protected void initialize() {
        double heading = (Robot.sensorSystem.getAngle() + COMPENSATION);

        double angle = heading % 360;
        int quadrant = (int)(angle / 90);
        endPosition = angles[quadrant];
    }

    @Override
    protected void execute() {
        double heading = (Robot.sensorSystem.getAngle() + COMPENSATION);

        if ((heading%360) < endPosition - 2) 
            Robot.driveSystem.turnRight(TURN_SPEED);
        else if ((heading%360) > endPosition + 2)
            Robot.driveSystem.turnLeft(TURN_SPEED);
        else
            Robot.driveSystem.drive(0);
    }

    @Override
    protected boolean isFinished() {
        double heading = (Robot.sensorSystem.getAngle() + COMPENSATION);

        return (((heading%360) > endPosition - 2) &&
         ((heading%360) < endPosition + 2)) || Robot.shouldCancel;
    }
    
    @Override
    protected void end() {
        Robot.driveSystem.drive(0);
        Robot.shouldCancel = false;
    }

}