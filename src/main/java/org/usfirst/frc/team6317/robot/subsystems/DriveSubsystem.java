package org.usfirst.frc.team6317.robot.subsystems;

import java.util.Arrays;
import java.util.Collections;

import org.usfirst.frc.team6317.robot.RobotMap;
import org.usfirst.frc.team6317.robot.commands.DefaultDriveCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	SpeedController fl = new Victor(RobotMap.Drive.FRONT_LEFT); 
	SpeedController bl = new Victor(RobotMap.Drive.BACK_LEFT);
	SpeedController fr = new Victor(RobotMap.Drive.FRONT_RIGHT);
	SpeedController br = new Victor(RobotMap.Drive.BACK_RIGHT);

	public boolean lifting = false;
	
	public double speedMultiplier = 1;

	public DriveSubsystem() {
		// fl.setInverted(true);
		// bl.setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultDriveCommand());
	}
	
	public void drive(double speed) {
		drive(speed,speed);
	}
	
	public void drive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed,rightSpeed,leftSpeed,rightSpeed);
	}

	public void adjustDrive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed,rightSpeed,
		leftSpeed ,rightSpeed);
	}
	
	public void drive(double flSpeed, double frSpeed, double blSpeed, double brSpeed) {
		fl.set(flSpeed);
		fr.set(frSpeed);
		bl.set(blSpeed);
		br.set(brSpeed);

	}

	// public void turnRight() {
	// 	turnRight(0.5);
	// }

	// public void turnRight(double power) {
	// 	drive(-power,power);
	// }

	// public void turnLeft() {
	// 	turnLeft(0.5);
	// }

	// public void turnLeft(double power) {
	// 	drive(power,-power);
	// }
	
	public void strafeRight() {
		strafeRight(1);
	}

	public void strafeRight(double power) {
		fl.set(-power);
		fr.set(power);
		bl.set(power);
		br.set(-power);
	}

	public void strafeLeft() {
		strafeLeft(1);
	}

	public void strafeLeft(double power) {
		fl.set(power);
		fr.set(-power);
		bl.set(-power);
		br.set(power);
	}

	public void shiftDrive(double y) {
		fl.set(y);
		bl.set(y);
		fr.set(y);
		br.set(y);
	}

	public void shiftDrive(double left, double right) {
		fl.set(left);
		bl.set(left);
		fr.set(right);
		br.set(right);
	}

	public void turnLeft(double speed) {
		fl.set(speed);
		bl.set(speed);
		fr.set(-speed);
		br.set(-speed);
	}

	public void turnRight(double speed) {
		fl.set(-speed);
		bl.set(-speed);
		fr.set(speed);
		br.set(speed);
	}
	
	public void mecanumDrive(double y, double x, double z, double throttle) {
		this.speedMultiplier = throttle;
			
		speedMultiplier += 1;
		speedMultiplier = Math.abs((speedMultiplier / 2) - 1);
		z *= 0.75;

		double flv = (-z + y - x) * speedMultiplier,
			blv = (-z + y + x)* speedMultiplier,
			brv = (z + y - x)* speedMultiplier,
			frv = (z + y + x)* speedMultiplier;
//david wrote this
		double max = Collections.max(Arrays.asList(Math.abs(flv), Math.abs(blv), Math.abs(brv), Math.abs(frv)));

		if (max > 1) {
			flv = scaleRange(flv, -max, max, -1, 1);
			blv = scaleRange(blv, -max, max, -1, 1);
			brv = scaleRange(brv, -max, max, -1, 1);
			frv = scaleRange(frv, -max, max, -1, 1);
		}
/////to here

		// flv = clip(flv);uncomment this if I need to
		// blv = clip(blv);
		// brv = clip(brv);
		// frv = clip(frv);

		fl.set(flv);
		bl.set(blv);
		br.set(brv);
		fr.set(frv);
	}
	
	// private double clip (double speed) {
	// 	return (speed > 1 ? 1 : speed < -1 ? -1 : speed);
	// }

	private double scaleRange(double value, double fromMin, double fromMax, double toMin, double toMax) {
		return (value - fromMin) * (toMax - toMin) / (fromMax - fromMin) + toMin;
	}

}
