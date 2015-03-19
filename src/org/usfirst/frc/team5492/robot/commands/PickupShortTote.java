package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupShortTote extends CommandGroup {
    
	public  PickupShortTote() {																																	//Activates wheel arm
    	addParallel(new OpenClaw());																															//Opens Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_one));																	//Moves to level one
		addSequential(new SetClawSetpoint(RobotMap.tote));								//Closes Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_two));																	//Moves to level two
    }
}
