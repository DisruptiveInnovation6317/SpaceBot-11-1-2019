/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6317.robot;

import org.usfirst.frc.team6317.robot.commands.AlignWithCargo;
import org.usfirst.frc.team6317.robot.commands.CancelCommands;
import org.usfirst.frc.team6317.robot.commands.DriveToRocket;
import org.usfirst.frc.team6317.robot.commands.ShiftDrive;
import org.usfirst.frc.team6317.robot.commands.ShiftLifts;
import org.usfirst.frc.team6317.robot.commands.SolenoidShift;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driveStick = new Joystick(0);
	public static Joystick driveStickTwo = new Joystick(1);
	public static Joystick controlStick = new Joystick(2);

	// Shifts Drive Modes
	Button driveShiftUp = new JoystickButton(driveStick, 1);
	Button driveShiftDown = new JoystickButton(driveStick, 2);

	// Aligns with human player
	Button reflectiveRight = new JoystickButton(driveStick, 4);
	Button reflectiveLeft = new JoystickButton(driveStick, 3);

	//Button testTurning = new JoystickButton(driveStickTwo, 2);

	// Shifts the solenoid cylinder up and down
	Button shiftUp = new JoystickButton(controlStick, 12);
	Button shiftDown = new JoystickButton(controlStick, 11);

	// Strafes to the lines
	// Button toLineLeft = new JoystickButton(driveStick, 5);
	// Button toLineRight = new JoystickButton(driveStick, 6);

	// Button turning = new JoystickButton(driveStick, 3);
	// Button turnToLoading = new JoystickButton(driveStick, 1);
	Button cancelStrafe = new JoystickButton(driveStick, 2);

	// Lifts to specific heights
	// 7 9 11
	Button toRocket = new JoystickButton(controlStick, 3);
	// Button testDrive = new JoystickButton(controlStick, 4);
	Button cancel = new JoystickButton(controlStick, 6);

	// Robot Lift Controls
	Button frontUp = new JoystickButton(controlStick, 9);
	Button frontDown = new JoystickButton(controlStick, 10);
	Button backUp = new JoystickButton(controlStick, 12);
	Button backDown = new JoystickButton(controlStick, 11);

	public OI() {
		//baby seal mechanism
		shiftDown.whenPressed(new SolenoidShift(0));
		shiftUp.whenPressed(new SolenoidShift(1));

		// Deploys Mantis Arms
		// deloreanMeetGround.whenPressed(new HalfMantis());
		// delorean.whenPressed(new DeployMantis());

		// Toggles Mantis Drives
		driveShiftUp.whenPressed(new ShiftDrive(0));
		driveShiftDown.whenPressed(new ShiftDrive(1));
		// testing.whenPressed(new RunFor(0.0005));

		reflectiveRight.whenPressed(new AlignWithCargo('r'));
		reflectiveLeft.whenPressed(new AlignWithCargo('l'));

		//testTurning.whenPressed(new TestDrive());

		// turning.whenPressed(new TurnToRocket());
		// turnToLoading.whenPressed(new TurnToHatch());
		cancelStrafe.whenPressed(new CancelCommands());

		toRocket.whenPressed(new DriveToRocket());
		
		// Cancel Manuevers
		cancel.whenPressed(new CancelCommands());
		cancelStrafe.whenPressed(new CancelCommands());

		// Robot Lift Controls
		frontUp.whenPressed(new ShiftLifts(Robot.solenoid.frontLift, true));
		frontDown.whenPressed(new ShiftLifts(Robot.solenoid.frontLift, false));
		backUp.whenPressed(new ShiftLifts(Robot.solenoid.backLift, true));
		backDown.whenPressed(new ShiftLifts(Robot.solenoid.backLift, false));
		
	}
}
