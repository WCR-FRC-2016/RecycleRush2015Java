package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Picks up tote on 2nd level, moves to 4th level
 *  Assumes driver is lined up
 */
public class PickupFromStation extends CommandGroup {
    
    public  PickupFromStation() {
    	addSequential(new SetElevatorSetpoint(RobotMap.level_three + (RobotMap.level_two / 2)));	//Moves to level 3.5
    	addSequential(new OpenClaw());																									//Opens Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_two));													//Moves to level 2
    	addSequential(new CloseClaw());																									//Closes Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_three + (RobotMap.level_two / 2)));	//Moves to level 3.5
    }
}
