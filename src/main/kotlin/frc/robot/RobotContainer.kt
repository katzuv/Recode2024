package frc.robot

import com.pathplanner.lib.auto.AutoBuilder
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.CommandXboxController

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the [Robot]
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
object RobotContainer {
    private val driverController = CommandXboxController(0)
    private val operatorController = CommandXboxController(1)
    private val testController = CommandXboxController(2)
    private val autoChooser = AutoBuilder.buildAutoChooser()

    init {
        registerAutoCommands()
        configureButtonBindings()
        configureDefaultCommands()
    }

    private fun configureDefaultCommands() {}

    private fun configureButtonBindings() {}

    fun getAutonomousCommand(): Command = autoChooser.selected

    private fun registerAutoCommands() {
    }
}
