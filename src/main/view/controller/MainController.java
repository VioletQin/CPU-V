package main.view.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import main.core.ProcessorInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import static main.resources.ImagePath.*;


/**
 * @author VioletQin
 * @since 2023/2/22
 */
public class MainController {

    //对应窗口
    private final Stage stage;
    //处理器信息封装
    private final ProcessorInfo processorInfo;
    //处理器本身
    private final CentralProcessor cpu;

    public MainController(Stage stage, ProcessorInfo processorInfo) {
        this.stage = stage;
        this.processorInfo = processorInfo;
        this.cpu = processorInfo.getCpu();
        this.prevTicks = cpu.getSystemCpuLoadTicks();
    }

    @FXML
    private ImageView closeButton;

    @FXML
    private ImageView minimizeButton;

    @FXML
    private ImageView icon;

    @FXML
    private Pane topStage;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label vendorFreq;

    @FXML
    private Label physicalPackageCount;

    @FXML
    private Label physicalProcessorCount;

    @FXML
    private Label logicalProcessorCount;

    @FXML
    private Label family;

    @FXML
    private Label bit;

    @FXML
    private Label vendor;

    @FXML
    private Pane utilizationModule;

    //鼠标 的x轴位置
    double x;
    //鼠标 的y轴位置
    double y;
    double x_stage;
    double y_stage;

    /**
     * 点击关闭窗口
     */
    @FXML
    void onClose(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Runtime.getRuntime().exit(0);
    }

    /**
     * 点击最小化到任务栏
     */
    @FXML
    void onMinimize(MouseEvent event) {
        Stage stage = (Stage) topStage.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Label totalCpu;
    @FXML
    private Label idle;
    @FXML
    private Label user;
    @FXML
    private Label system;
    @FXML
    private Label contextSwitches;
    @FXML
    private Label interrupts;
    //对应量值
    long[] prevTicks;
    DecimalFormat format = new DecimalFormat("#.##");

    private void pollingTick() {
        long[] ticks = cpu.getSystemCpuLoadTicks();
        long user_ = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle_ = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long totalCpu_ = user_ + nice + sys + idle_ + iowait + irq + softirq + steal;

        //处理逻辑
        totalCpu.setText(format.format(cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100) + "%");
        idle.setText(format.format(100d * idle_ / totalCpu_) + "%");
        system.setText(format.format(100d * sys / totalCpu_) + "%");
        user.setText(format.format(100d * user_ / totalCpu_) + "%");
        contextSwitches.setText(cpu.getContextSwitches() + "");
        interrupts.setText(cpu.getInterrupts() + "");

        //旧辞新替
        prevTicks = ticks;
    }

    @FXML
    private void initialize() {
        //图片载入
        Image closeButtonImage = new Image(CLOSE_BUTTON);
        closeButton.setImage(closeButtonImage);

        Image minimizeButtonImage = new Image(MINIMIZE_BUTTON);
        minimizeButton.setImage(minimizeButtonImage);

        Image iconImage = new Image(ICON);
        icon.setImage(iconImage);

        //窗口拖拽功能载入
        topStage.setOnMousePressed(event -> {
            //记录当前鼠标位置和 窗口的位置
            x = event.getScreenX();
            y = event.getScreenY();
            x_stage = stage.getX();
            y_stage = stage.getY();
        });
        topStage.setOnMouseDragged(event -> {
            stage.setX(x_stage + event.getScreenX() - x);
            stage.setY(y_stage + event.getScreenY() - y);
        });

        //处理器参数赋值
        id.setText(processorInfo.getProcessorID());
        name.setText(processorInfo.getProcessSpecification());
        vendor.setText(processorInfo.getProcessorVendor());
        physicalPackageCount.setText(Integer.toString(processorInfo.getPhysicalPackageCount()));
        physicalProcessorCount.setText(Integer.toString(processorInfo.getPhysicalProcessorCount()));
        logicalProcessorCount.setText(Integer.toString(processorInfo.getLogicalProcessorCount()));
        family.setText(processorInfo.getProcessorFamily());
        bit.setText(processorInfo.isCpu64bit() ? "x64" : "x32/x86");
        vendorFreq.setText((float) processorInfo.getVendorFreq() / 1000000 + "MHz");

        //折线图初始化
        NumberAxis x = new NumberAxis(0, 60, 10);
        x.setTickLabelFill(Paint.valueOf("#ffffff"));
        NumberAxis y = new NumberAxis(0,100.0,10);
        y.setTickLabelFill(Paint.valueOf("#ffffff"));
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(x,y);
        //折线图布局
        lineChart.setLayoutX(5.0);
        lineChart.setLayoutY(10.0);
        lineChart.setPrefHeight(323.0);
        lineChart.setPrefWidth(584.0);
        utilizationModule.getChildren().add(lineChart);

        //监听CPU实时利用率定时任务
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                //The task you want to do
                Platform.runLater(() -> pollingTick());
            }
        };
        timer.schedule(task, 1000L, 1000L);
    }
}
