package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.OI;
import org.usfirst.frc.team6317.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DefaultLiftCommand extends Command {


	public DefaultLiftCommand() {
		this.requires(Robot.liftSystem);
		// this.requires(Robot.mantisArms);
	}
	
	@Override
	protected void execute() {
		//originally from DefaultCargoCommand.java
		if (OI.controlStick.getPOV() == 0) {
			Robot.liftSystem.lift.set(1.0);
			Robot.liftSystem.liftTwo.set(1.0);
        } else if (OI.controlStick.getPOV() == 180) {
			Robot.liftSystem.lift.set(-1.0);
			Robot.liftSystem.liftTwo.set(-1.0);
        } else {
			Robot.liftSystem.lift.set(0.0);
			Robot.liftSystem.liftTwo.set(0.0);
		}

		// Robot.mantisArms.powerMid(OI.controlStickTwo.getY());
		// Robot.mantisArms.powerBack(OI.controlStick.getY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
