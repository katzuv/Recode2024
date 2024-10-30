package frc.robot

object Constants {
    val CURRENT_MODE: Mode = Mode.SIM
    val CURRENT_MODEEE: Mode = Mode.SIM

    enum class Mode {
        REAL,
        SIM,
        REPLAY,
    }
}
