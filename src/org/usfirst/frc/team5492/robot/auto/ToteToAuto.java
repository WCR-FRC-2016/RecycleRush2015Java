package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.commands.*;

/**
 *
 */
public class ToteToAuto extends CommandGroup {
    
    public  ToteToAuto() {
        addSequential(new TimeDrive(3.390, -.4));
    }
}
