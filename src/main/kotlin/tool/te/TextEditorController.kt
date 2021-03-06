package tool.te

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.MenuItem
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.File
import java.net.URL
import java.util.ResourceBundle

class TextEditorController : Initializable {
    var stage: Stage? = null

    FXML var saveMenu: MenuItem? = null

    FXML var textArea: TextArea? = null

    var currentFile: File? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
    }

    FXML fun handleOpen() {
        val fc = FileChooser()
        fc.setTitle("ファイルを開く")
        val file = fc.showOpenDialog(stage)
        if (file == null) return
        textArea!!.clear()
        file.forEachLine(Charsets.UTF_8, {
            textArea!!.appendText("${it}\n")
        })
        currentFile = file
        saveMenu?.setDisable(false)
    }

    FXML fun handleSave() {
        // 開いているファイルがない場合。ほぼ考えられない
        if (currentFile == null) return
        writeFile()
    }

    FXML fun handleSaveAs() {
        val fc = FileChooser()
        fc.setTitle("名前を付けて保存")
        val file = fc.showSaveDialog(stage)
        if (file == null) return
        currentFile = file
        writeFile()
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
        val configDialog = Stage(StageStyle.UTILITY)
        configDialog.setScene(scene);
        configDialog.initOwner(stage);
        configDialog.initModality(Modality.WINDOW_MODAL);
        configDialog.setResizable(false);
        configDialog.setTitle("設定")
        configDialog.showAndWait()
    }

    private fun writeFile() {
        currentFile!!.writeText(textArea!!.getText(), Charsets.UTF_8)
    }
}
