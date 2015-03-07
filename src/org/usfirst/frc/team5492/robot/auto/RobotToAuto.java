package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5492.robot.Robot;
/**
 *
 */
public class RobotToAuto extends CommandGroup {
    
    public  RobotToAuto() {
    	Robot.drivetrain.drive(0, -.5, 0, 0);
    	Timer.delay(3.0);
    	Robot.drivetrain.drive(0, 0, 0, 0);
    }
}