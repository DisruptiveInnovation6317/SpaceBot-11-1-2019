/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6317.robot;

/** 
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final class Drive {
		public static final int FRONT_LEFT = 0;
		public static final int BACK_LEFT = 1;
		public static final int FRONT_RIGHT = 2;
		public static final int BACK_RIGHT = 3;
	}

	public static final class EndEffectors {
		// Lift
		public static final int LIFT = 5;
		public static final int LIFT_TWO = 6;
		public static final int LIFT_TILT = 7;

		// Grabber
		public static final int GRABBER_ONE = 0;
		public static final int GRABBER_TWO = 1;

		// Cargo
		public static final int CARGO_HATCH = 4;
	}

	public static final class Sensors {
		//The following sensors are not used. Keep for future use. If not delete by Brosius approval.
		// public static final int LEFT_FOLLOWER = 7;
		// public static final int CENTER_FOLLOWER = 8;
		// public static final int RIGHT_FOLLOWER = 9;
		// public static final int MANTIS_SWITCH = 6;
		// public static final int LIFT_BOTTOM_LIMIT = 5;
	}
}
