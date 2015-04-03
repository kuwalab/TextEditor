package tool.te

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.stage.FileChooser
import javafx.stage.Stage

class TextEditorController {
    var stage: Stage? = null

    FXML fun handleOpen() {
        val fc = FileChooser()
        fc.setTitle("ファイルを開く")
        fc.showOpenDialog(stage)
    }

    FXML fun handleExit(event: ActionEvent) {
        Platform.exit();
    }
}
