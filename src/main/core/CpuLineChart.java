package main.core;

import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author VioletQin
 * @since 2023/2/24
 */
public class CpuLineChart {

    private final LineChart<Number, Number> lineChart;
    private final XYChart.Series<Number, Number> xySeries;
    private final ObservableList<XYChart.Data<Number, Number>> dataList;
    private long index = 0;

    public CpuLineChart(LineChart<Number, Number> lineChart) {
        this.lineChart = lineChart;
        this.xySeries = new XYChart.Series<Number, Number>();

        lineChart.getData().add(this.xySeries);
        this.dataList = xySeries.getData();
    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public void add(double value) {
        dataList.add(new XYChart.Data<>(0, value));
        if (index > 0) {
            for (int i = 0; i < dataList.size() - 1; i++) {
                XYChart.Data<Number, Number> data = dataList.get(i);
                data.setXValue(data.getXValue().intValue() + 1);
            }
        }

        if (index > 60) {
            dataList.remove(0);
        }
        index++;
    }
}
