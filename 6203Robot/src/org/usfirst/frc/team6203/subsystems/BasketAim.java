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
				setAngle1();
				break;
			case 1:
				setAngle2();
				break;
			case 2:
				setAngle3();
				break;
			case 3:
				setAngle4();
				break;
			default:
				trigCount = 0;
				setAngle1();
				break;
			}
		}
	}
	
	public void setManualAim(){
		//TODO
	}

	public void setAngle1() {
		while (pot.get() != Constants.PRESET_1_ANGLE) {
			Hardware.basketMotor.set(pot.get() < Constants.PRESET_1_ANGLE ? 0.1 : -0.1);
		}
	}

	public void setAngle2() {
		while (pot.get() != Constants.PRESET_2_ANGLE) {
			Hardware.basketMotor.set(pot.get() < Constants.PRESET_2_ANGLE ? 0.1 : -0.1);
		}
	}

	public void setAngle3() {
		while (pot.get() != Constants.PRESET_3_ANGLE) {
			Hardware.basketMotor.set(pot.get() < Constants.PRESET_3_ANGLE ? 0.1 : -0.1);
		}
	}

	public void setAngle4() {
		while (pot.get() != Constants.PRESET_4_ANGLE) {
			Hardware.basketMotor.set(pot.get() < Constants.PRESET_4_ANGLE ? 0.1 : -0.1);
		}
	}

}
