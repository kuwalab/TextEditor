package tool.te

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class TextEditor() : Application() {
    override fun start(stage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass<TextEditor>().getResource("TextEditor.fxml"))

        val scene: Scene = Scene(root)

        stage.setTitle("テキストエディタ")
        stage.setScene(scene)
        stage.show()
    }

    fun main(args: Array<String>) {
        Application.launch(javaClass<TextEditor>(), *args)
    }
}

