package org.usfirst.frc.team6203.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.RMap;
import org.usfirst.frc.team6203.subsystems.KnightDrive;

public class Hardware extends IterativeRobot {

	// MOTORS
	public static Victor leftMotor = new Victor(RMap.leftMotor);
	public static Victor rightMotor = new Victor(RMap.rightMotor);
	public static Victor basketMotor = new Victor(RMap.basketMotor);

	// SENSORS
	public static Encoder kEncoder = new Encoder(RMap.kEncoderDIO, RMap.kEncoderDIO1);
	public static Ultrasonic kUltra = new Ultrasonic(RMap.kUltraDIO, RMap.kUltraDIO1);
	public static Gyro kGyro = new Gyro(RMap.kGyro);
	public static Potentiometer kPotentiometer = new AnalogPotentiometer(RMap.kPotentiometer, 360, RMap.kPotentiometerOffset);

	// INTERFACE
	public static RobotDrive kDrive = new RobotDrive(leftMotor, rightMotor);
	public static Joystick xboxController = new Joystick(RMap.xboxController);

}
