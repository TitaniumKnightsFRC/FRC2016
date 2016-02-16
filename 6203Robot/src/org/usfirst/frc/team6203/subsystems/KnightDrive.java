package org.usfirst.frc.team6203.subsystems;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class KnightDrive extends IterativeRobot {
	
	private Joystick xbox;
	private RobotDrive drive;

	public KnightDrive(Joystick xbox, RobotDrive drive){
		this.xbox = xbox;
		this.drive = drive;
		
	}

	public void drive() {

		String driveM = "ARCADE";

		if (xbox.getRawButton(1)) {
			if (driveM.equals("ARCADE")) {
				driveM = "TANK";
			} else if (driveM.equals("TANK")) {
				driveM = "ARCADE";
			}
		}
		if (driveM.equals("ARCADE")) {
			drive.tankDrive(xbox.getRawAxis(2), xbox.getRawAxis(5));
		} else if (driveM.equals("TANK")) {
			drive.arcadeDrive(xbox);
		}
	}

}
