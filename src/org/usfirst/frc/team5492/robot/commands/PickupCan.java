package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCan extends CommandGroup {
    
	public  PickupCan() {											//Activates wheel arm
    	addParallel(new OpenClaw());								//Opens Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_one));	//Moves to level one
		addSequential(new SetClawSetpoint(RobotMap.can - RobotMap.claw_grab));								//Closes Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_two));	//Moves to level two
    }
}
