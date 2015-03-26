package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 *
 */
public class OpenClaw extends CommandGroup {
    
    public  OpenClaw() {
    	addSequential(new SetClawSetpoint(RobotMap.open_claw));
    }
}
