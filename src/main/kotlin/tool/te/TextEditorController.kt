package tool.te

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import javax.print.attribute.standard.DialogTypeSelection

class TextEditorController {
    var stage: Stage? = null

    FXML var textArea: TextArea? = null

    FXML fun handleOpen() {
        val fc = FileChooser()
        fc.setTitle("ファイルを開く")
        val file = fc.showOpenDialog(stage)
        if (file == null) return
        textArea!!.setText(file.readText(Charsets.UTF_8))
    }

    FXML fun handleExit() {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.setTitle("終了しますか")
        alert.getDialogPane().setHeaderText("終了しますか")
        alert.getDialogPane().setContentText("")
        alert.showAndWait()

        Platform.exit()
    }

    FXML fun handleConfig() {
        val fxmlLoader: FXMLLoader = FXMLLoader(javaClass<TextEditor>().getResource("ConfigDialog.fxml"))
        val root: Parent = fxmlLoader.load()

        val scene = Scene(root)
        val confirmDialog = Stage(StageStyle.UTILITY)
        confirmDialog.setScene(scene);
        confirmDialog.initOwner(stage);
        confirmDialog.initModality(Modality.WINDOW_MODAL);
        confirmDialog.setResizable(false);
        confirmDialog.setTitle("設定")
        confirmDialog.showAndWait()
    }
}
