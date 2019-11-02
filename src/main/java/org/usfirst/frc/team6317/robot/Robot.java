/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6317.robot;

import org.usfirst.frc.team6317.robot.commands.LiftPositions;
import org.usfirst.frc.team6317.robot.commands.RunHour;
import org.usfirst.frc.team6317.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6317.robot.subsystems.EndEffectorSubsystem;
import org.usfirst.frc.team6317.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team6317.robot.subsystems.MantisSubsystem;
import org.usfirst.frc.team6317.robot.subsystems.SensorSubsystem;
import org.usfirst.frc.team6317.robot.subsystems.SolenoidSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveSubsystem driveSystem = new DriveSubsystem();
	public static final LiftSubsystem liftSystem = new LiftSubsystem();
	public static final SensorSubsystem sensorSystem = new SensorSubsystem();
	public static final SolenoidSubsystem solenoid = new SolenoidSubsystem();
	public static final EndEffectorSubsystem endEffector = new EndEffectorSubsystem();
	public static final MantisSubsystem mantisArms = new MantisSubsystem();

	public static String status = "nothing";

	public static OI m_oi;

	public static boolean shouldCancel = false;

//	public static int activeCamera = 1;

	public static char strafeDir = 'n';

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	// Camera Stream Variables
	UsbCamera frontCamera;
	UsbCamera backCamera;
	VideoSink cameraStream;

	// Camera Stream Control
	boolean prevButton = true;

	// Turning Stuffs
	public static final double COMPENSATION = (360*100);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addOption("Running for Hour", new RunHour());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);

		// backCamera = new UsbCamera("Back Camera", 1);

		// initializes switchable cameras
		frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
		backCamera = CameraServer.getInstance().startAutomaticCapture(1);

		frontCamera.setResolution(240,200);
		frontCamera.setFPS(30);

		backCamera.setResolution(240,200);
		backCamera.setFPS(30);

		cameraStream = CameraServer.getInstance().getServer();
		cameraStream.setSource(frontCamera);
		// CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// SmartDashboard.putNumber("seriously, this is not a joke. doge is in my nightmares.", 1);
		SmartDashboard.putNumber("Lift Value", sensorSystem.liftEncoder.getDistance());
		SmartDashboard.putNumber("Current Position", sensorSystem.currentLiftPos);
		SmartDashboard.putNumber("Diff", LiftPositions.diff);
		SmartDashboard.putString("Status", status);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	@Override
	public void robotPeriodic() { 
		// GYRO CODE
		SmartDashboard.putNumber("Heading", (sensorSystem.getAngle()+COMPENSATION));
		SmartDashboard.putNumber("Absolute Heading", sensorSystem.getAngle() % 360);
		
		// VISION CODE
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = table.getEntry("tx");
		NetworkTableEntry ty = table.getEntry("ty");
		NetworkTableEntry ta = table.getEntry("ta");
		NetworkTableEntry tv = table.getEntry("tv");

		double x = tx.getDouble(0.0);
		double y = ty.getDouble(0.0);
		double target = tv.getDouble(0.0);
		double area = ta.getDouble(0.0);

		SmartDashboard.putNumber("Limelight x",x);
		SmartDashboard.putNumber("Limelight y",y);
		SmartDashboard.putNumber("Valid Target",target);
		SmartDashboard.putNumber("Area",area);

		// Code to switch the camera streams
		if (OI.driveStick.getRawButton(11)) {
			cameraStream.setSource(frontCamera);
		} else if (OI.driveStick.getRawButton(12)) {
			cameraStream.setSource(backCamera);
		}

	}
}
