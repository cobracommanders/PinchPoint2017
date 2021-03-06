package org.usfirst.frc.team498.robot;

//old code that was replaced by the Button Press class
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class IntakeClimb2017 { 

	Spark sparkBall;
	Talon talonClimb;
	CANTalon climb1; 
	CANTalon climb0;
	FancyJoystick thisStick;
	DoubleSolenoid ds;
	boolean aOldState = false;

	boolean wasIntakePressed = false;
	boolean isIntakeRunning = false;
	boolean intakeReverse = false;
	
	//boolean wasConveyorPressed = false;
	//boolean isConveyorRunning = false;
	
	boolean wasClimbPressed = false;
	boolean isClimbRunning = false;
	boolean climbReverse = false;

	public IntakeClimb2017(FancyJoystick thisStick, Ports ports) {
		ds = new DoubleSolenoid(ports.GEAR_INTAKE_FORWARD_CHANNEL, ports.GEAR_INTAKE_REVERSE_CHANNEL);
		this.thisStick = thisStick;
		sparkBall = new Spark(ports.SPARK_BALL_INTAKE_PWM_CHANNEL);
		climb0 = new CANTalon(ports.CANTALON_CLIMBER_0);
		climb1 = new CANTalon(ports.CANTALON_CLIMBER_1);

	}

	//checks if the a button is pressed
	public boolean ADown() {
		boolean localTemp = false;
		if (!aOldState && thisStick.getButton(Button.A))
			localTemp = true;
		aOldState = thisStick.getButton(Button.A);
		return localTemp;
	}
	
	// methods for the fuel intake (depreciated)
	public void IntakeOn() {
		sparkBall.set(1);
	}

	public void IntakeOff() {
		sparkBall.set(0);
	}

	public void IntakeReverse() {
		sparkBall.set(-1);
	}
	
	//conveyor
	/*public void ConveyorOn() {
		talonConveyor.set(1);
	}
	public void ConveyorOff() {
		talonConveyor.set(0);
	}*/
	
	//climber methods
	
	//turns it on
	public void ClimbOn() {
		//For some reason, the motors are calibrated in reverse?
		climb0.set(-1.0);
		climb1.set(-1.0);
	}
	
	//turns climber off
	public void ClimbOff() {
		climb0.set(0);
		climb1.set(0);
	}
	
	//reverses climber
	public void ClimbReverse() {
		climb0.set(1.0);
		climb1.set(1.0);
	}
	
	//the method where each button does something
	public void Listener() {
		
		/*if (thisStick.getButton(Button.B) && wasConveyorPressed == false) {
			isConveyorRunning = !isConveyorRunning;
			wasConveyorPressed = true;
		}
		
		if (wasConveyorPressed == true && (thisStick.getButton(Button.B) == false)) {
			wasConveyorPressed = false;
		}
		
		if (isConveyorRunning) {
			ConveyorOn();
		}
		
		if (isConveyorRunning) {
			ConveyorOff();
		}*/
		
		//climber controller with the back button (depreciated)
		if (thisStick.getButton(Button.BACK) && wasClimbPressed == false) {
			isClimbRunning = !isClimbRunning;
			wasClimbPressed = true;
		}
		if (wasClimbPressed == true && thisStick.getButton(Button.BACK) == false) {
			wasClimbPressed = false;
		}

		if (isClimbRunning) {
			ClimbOn();
		} else {
			ClimbOff();
		}
		//if (thisStick.getButton(Button.X)) {
		//	ClimbOff();
		//}
		
		/*if (thisStick.getButton(Button.B) && wasClimbPressed == false) {
			isClimbRunning = !isClimbRunning;
			wasClimbPressed = true;
			if(thisStick.getButton(Button.X)) {
				climbReverse = true;
			} else {
				climbReverse = false;
			}
		}
		
		if (wasClimbPressed == true && (thisStick.getButton(Button.B)) == false) {
			wasClimbPressed = false;
		}
		
		if (isClimbRunning) {
			if (climbReverse) {
				ClimbReverse();
			} else {
				ClimbOn();
			}
			ClimbOn();
		} else {
			ClimbOff();
		}*/

		//controller for intake with the x button, and the left bumper reverses it. (toggle) (depreciated)
		if (thisStick.getButton(Button.X) && wasIntakePressed == false) {
			isIntakeRunning = !isIntakeRunning;
			wasIntakePressed = true;
			if (thisStick.getButton(Button.LeftBumper))
				intakeReverse = true;
			else
				intakeReverse = false;

		}
		if (wasIntakePressed == true && thisStick.getButton(Button.X) == false) {
			wasIntakePressed = false;
		}

		if (isIntakeRunning) {
			if (intakeReverse)
				IntakeReverse();
			else
				IntakeOn();
		} else {
			IntakeOff();
		}

		// flaps for gear intake controller (using a button) (depreciated)
		if (ADown()) {
			if (ds.get() == Value.kOff || ds.get() == Value.kForward)
				OpenFlap();
			else if (ds.get() == Value.kReverse)
				CloseFlap();

		}
	}

	public void OpenFlap() {
		ds.set(Value.kReverse);
	}

	public void CloseFlap() {
		ds.set(Value.kForward);
	}

	}
