// package org.usfirst.frc.team6317.robot.commands;

// import org.usfirst.frc.team6317.robot.Robot;

// import edu.wpi.first.wpilibj.command.Command;
//
//
//This whole class is inactive dead code. This class is for example only on how to sync 
//drive system to a light sensor DO NOT RE IMPLEMENT WITHOUT MENTOR APPROVAL!!!!!

// public class StrafeToLine extends Command {
//     // White returns false for line sensor

//     char dir;

//     public StrafeToLine(char startingDir) {
//         requires(Robot.driveSystem);
//         this.dir = startingDir;
//     }

//     @Override
//     protected void initialize() {
//         // Robot.driveSystem.drive(0.5);
//         if (dir == 'r')
//             Robot.driveSystem.strafeRight(1);
//         else
//             Robot.driveSystem.strafeLeft(1);
//     }

//     @Override
//     protected void execute() {
//     //     if (!Robot.sensorSystem.leftFollower.get()){
//     //         Robot.driveSystem.strafeLeft(1);
//     //         Robot.strafeDir = 'l';
//     //     } else if (!Robot.sensorSystem.rightFollower.get()){
//     //         Robot.driveSystem.strafeRight(1);
//     //         Robot.strafeDir = 'r';
//     //     }
            
//     }

//     @Override
//     protected boolean isFinished() {
//         if (!Robot.sensorSystem.centerFollower.get()) {
//             Robot.strafeDir = 'c';
//         }
//         return !Robot.sensorSystem.centerFollower.get() || Robot.shouldCancel;
//     }

//     @Override
//     protected void end() {
//         Robot.driveSystem.drive(0);
//         Robot.shouldCancel = false;
//     }

// }