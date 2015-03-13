package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Assumes claw is 1 LEVEL ABOVE THE TOTE
 */
public class PickupItem extends CommandGroup {
    
    public  PickupItem() {																						//Activates wheel arm
    	addParallel(new OpenClaw());																	//Opens Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_one));			//Moves to level one
		addSequential(new CloseClaw());															//Closes Claw
		addSequential(new SetElevatorSetpoint(RobotMap.level_two));			//Moves to level two
    }
}
