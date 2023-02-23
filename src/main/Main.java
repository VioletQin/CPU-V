package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.core.ProcessorInfo;
import main.view.controller.MainController;

import static main.resources.FxmlPath.MAIN_FXML;
import static main.resources.ImagePath.*;

/**
 * @author violetqin
 * @since 2023.2.21
 * 主启动类
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //载入场景
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_FXML));
        //构造场景控制器
        loader.setControllerFactory(t -> new MainController(primaryStage, ProcessorInfo.getProcessorInfo()));
        Scene scene = new Scene(loader.load());
        //添加样式表
        scene.getStylesheets().add("/main/view/chart.css");
        scene.getStylesheets().add("/main/view/table.css");

        //设置标题
        primaryStage.setTitle("CPU-V");
        //设置窗体图标
        primaryStage.getIcons().add(new Image(ICON));
        //窗口风格为极简
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //窗口不可拖拽改变大小
        primaryStage.setResizable(false);

        //将场景设置为透明
        scene.setFill(null);

        //载入场景
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
