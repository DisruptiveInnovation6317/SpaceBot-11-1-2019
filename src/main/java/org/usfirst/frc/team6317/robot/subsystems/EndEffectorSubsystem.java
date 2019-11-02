package org.usfirst.frc.team6317.robot.subsystems;

import org.usfirst.frc.team6317.robot.RobotMap;
import org.usfirst.frc.team6317.robot.commands.DefaultCargoCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class EndEffectorSubsystem extends Subsystem {
	
	public SpeedController cargoHatch = new Victor(RobotMap.EndEffectors.CARGO_HATCH);
	
	public boolean forward = true;

	public EndEffectorSubsystem() {

	}

	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new DefaultCargoCommand());
	}
	
	public void controlHatch(double speed) {
        cargoHatch.set(speed);
    }

    // 
    
}
