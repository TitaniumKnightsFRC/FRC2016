
package org.usfirst.frc.team6203.robot;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Hardware;

import java.io.IOException;

import org.usfirst.frc.team6203.robot.RMap;
import org.usfirst.frc.team6203.subsystems.BasketAim;
import org.usfirst.frc.team6203.subsystems.KnightDrive;

public class Robot extends IterativeRobot {

	// kDrive already stores LR motors
	KnightDrive kDrive = new KnightDrive(Hardware.xboxController, Hardware.kDrive);

	BasketAim bAim = new BasketAim((AnalogPotentiometer) Hardware.kPotentiometer);

	NetworkTable table, table2;


	double Kp = 0.03;

	public Robot() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
		table2 = NetworkTable.getTable("GRIP/mySize");
	}

	public void robotInit() {
		//Subject to change kekmao
		while (true) {
			double cY = table.getNumber("centerX");
			double cX = table.getNumber("centerY");
			System.out.println("Center X: " + cX);
			System.out.println("Center Y: " + cY);
			System.out.println();
			Timer.delay(1);
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		//Check if turning is right
		//Add potentiometer vertical aim
		while (table.getNumber("centerX") != table2.getNumber("x") / 2) {
			if (table.getNumber("centerX") < table2.getNumber("x") / 2) {
				// Right turn
				Hardware.kDrive.drive(0.1, 1.0);
			} else {
				// Left turn
				Hardware.kDrive.drive(0.1, -1.0);
			}
		}
		//Gyro not working?
		Hardware.kGyro.reset();
		while (isAutonomous()) {
			double angle = Hardware.kGyro.getAngle(); // get current heading
			Hardware.kDrive.drive(-1.0, -angle * Kp); // drive towards heading 0
			Timer.delay(0.004);
		}
		Hardware.kUltra.setAutomaticMode(true);
		if (Hardware.kUltra.getRangeInches() < 36) {
			while (Hardware.kEncoder.getDistance() != 36 - Hardware.kUltra.getRangeInches()) {
				Hardware.leftMotor.set(-0.2);
				Hardware.rightMotor.set(-0.2);
			}

		}

		if (Math.abs(Hardware.leftMotor.getSpeed() - Hardware.rightMotor.getSpeed()) > .10 * 0.2) {
			Hardware.leftMotor.disable();
			Hardware.rightMotor.disable();
		}

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		while (isOperatorControl() && isEnabled()) {
			kDrive.drive();
			bAim.switchAim(Hardware.xboxController);

		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
