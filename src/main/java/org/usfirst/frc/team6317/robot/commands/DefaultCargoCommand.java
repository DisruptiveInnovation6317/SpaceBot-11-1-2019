package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.OI;
import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DefaultCargoCommand extends Command {

	public DefaultCargoCommand() {
		this.requires(Robot.endEffector);
	}
	
	@Override
	protected void execute() {
		//originally from DefaultLiftCommand.java
		Robot.endEffector.cargoHatch.set(OI.controlStick.getY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
