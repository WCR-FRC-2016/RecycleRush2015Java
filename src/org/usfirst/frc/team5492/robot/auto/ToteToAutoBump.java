package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.commands.*;

/**
 *
 */
public class ToteToAutoBump extends CommandGroup {
    
    public  ToteToAutoBump() {
        addSequential(new TimeDrive(3.890, -.4));
    }
}
