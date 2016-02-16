package org.usfirst.frc.team6203.subsystems;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Hardware;
import org.usfirst.frc.team6203.robot.RMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class BasketAim extends IterativeRobot {
	// Add switch from xbox toggle

	public AnalogPotentiometer pot;
	//public RobotDrive drive = new RobotDrive(RMap.basketMotor, (Integer) null); 

	public BasketAim(Potentiometer pot) {
		this.pot = (AnalogPotentiometer) pot;
	}
	

	public void switchAim(Joystick xbox) {
		int angle;
		int trigCount = 0;
		while (trigCount < 4) {
			if (xbox.getTrigger()) {
				trigCount++;
			}
			
			if (xbox.getRawButton(4)){
				setManualAim(); //fix
			}

			switch (trigCount) {
			case 0:
				angle = Constants.PRESET_1_ANGLE;
				break;
			case 1:
				angle = Constants.PRESET_2_ANGLE;
				break;
			case 2:
				angle = Constants.PRESET_3_ANGLE;
				break;
			case 3:
				angle = Constants.PRESET_4_ANGLE;
				break;
			default:
				trigCount = 0;
				angle = Constants.PRESET_1_ANGLE;
				break;
			}
			adjustAngle(angle);
		}
	}
	
	public void setManualAim(){
		//TODO
	}

	public void adjustAngle(int angle) {
		while (pot.get() != angle) {
			Hardware.basketMotor.set(pot.get() < angle ? 0.1 : -0.1);
		}
	}


}
