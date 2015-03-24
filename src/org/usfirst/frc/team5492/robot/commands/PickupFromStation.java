package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Picks up tote on 2nd level, moves to 3RD level
 *  Assumes driver is lined up
 */
public class PickupFromStation extends CommandGroup {
    
    public  PickupFromStation() {
    	addSequential(new OpenClaw());																									//Opens Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_two));													//Moves to level 2
    	addSequential(new PickupTote());																									//Closes Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_three));												//Moves to level 3
    }
}
