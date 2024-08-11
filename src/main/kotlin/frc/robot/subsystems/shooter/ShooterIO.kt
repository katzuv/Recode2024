package frc.robot.subsystems.shooter

import edu.wpi.first.units.Angle
import edu.wpi.first.units.MutableMeasure
import edu.wpi.first.units.Units
import edu.wpi.first.units.Velocity
import edu.wpi.first.units.Voltage
import frc.robot.lib.webconstants.LoggedTunableNumber
import org.team9432.annotation.Logged

interface ShooterIO {
    val topRollerInputs: LoggedRollerInputs
    val bottomRollerInputs: LoggedRollerInputs

    fun setTopVelocity(velocity: MutableMeasure<Velocity<Angle>>) {}

    fun setBottomVelocity(velocity: MutableMeasure<Velocity<Angle>>) {}

    fun stop() {}

    fun updateInputs() {}

    fun updatePID() {}

    fun hasPIDChanged(PIDValues: Array<LoggedTunableNumber>): Boolean{
        var hasChanged = false
        for (value in PIDValues){
            if (value.hasChanged()) hasChanged = true
        }
        return hasChanged
    }

    @Logged
    open class RollerInputs {
        var velocity: MutableMeasure<Velocity<Angle>> = MutableMeasure.zero(Units.RotationsPerSecond)
        var voltage: MutableMeasure<Voltage> = MutableMeasure.zero(Units.Volts)
    }
}