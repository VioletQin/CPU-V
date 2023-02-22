package core;
import core.utils.ProcessorInfo;
import oshi.SystemInfo;

import java.util.Arrays;

/**
 * @author violetqin
 * @since 2023.2.21
 * 主启动类
 */
public class Main {

    public static void main(String[] args) {
        ProcessorInfo processorInfo = ProcessorInfo.getProcessorInfo();
        processorInfo.getFreqInfo();
    }

}
