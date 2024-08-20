package frc.robot.commandGroups

import com.pathplanner.lib.util.GeometryUtil
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.units.Angle
import edu.wpi.first.units.Measure
import edu.wpi.first.units.Units
import edu.wpi.first.units.Velocity
import frc.robot.Constants

object CommandGroupsConstants {
    val HOOD_ANGLE_SUPER_POOP: Measure<Angle> = Units.Degrees.of(110.0)
    val SHOOTER_VELOCITY_SUPER_POOP: Measure<Velocity<Angle>> = Units.RotationsPerSecond.of(77.07)
    val CONVEYOR_VELOCITY_SUPER_POOP: Measure<Velocity<Angle>> = Units.RotationsPerSecond.of(60.0)

    private val SUPER_POOP_TRANSLATION_BLUE = Translation2d()

    val SUPER_POOP_TRANSLATION: Translation2d
        get() =
            if (Constants.isRed) GeometryUtil.flipFieldPosition(SUPER_POOP_TRANSLATION_BLUE)
            else SUPER_POOP_TRANSLATION_BLUE

    val SUPER_POOP_TURN_TOLERANCE: Measure<Angle> = Units.Degrees.of(2.5)
}