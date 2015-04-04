package tool.te

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.nio.charset.StandardCharsets
import java.nio.file.Files

class TextEditorController {
    var stage: Stage? = null

    FXML var textArea: TextArea? = null

    FXML fun handleOpen() {
        val fc = FileChooser()
        fc.setTitle("ファイルを開く")
        val file = fc.showOpenDialog(stage)
        if (file == null) return
        textArea!!.setText(file.readText(StandardCharsets.UTF_8))
    }

    FXML fun handleExit(event: ActionEvent) {
        Platform.exit()
    }
}
