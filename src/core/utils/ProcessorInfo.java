package core.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * @author VioletQin
 * @since 2023/2/21
 * CPU 基本信息
 */
public class ProcessorInfo {
    //cpu信息
    private final CentralProcessor cpu;

    //单例
    private static final ProcessorInfo processorInfo = new ProcessorInfo(new SystemInfo().getHardware().getProcessor());
    private ProcessorInfo(CentralProcessor cpu){
        this.cpu = cpu;
    }

    //获取工具类
    public static ProcessorInfo getProcessorInfo() {
        return processorInfo;
    }

    //插槽(封装的CPU)的个数
    public int getPhysicalPackageCount() {
        return cpu.getPhysicalPackageCount();
    }

    //CPU内核个数
    public int getPhysicalProcessorCount() {
        return cpu.getPhysicalProcessorCount();
    }

    //逻辑处理器(线程)个数
    public int getLogicalProcessorCount() {
        return cpu.getLogicalProcessorCount();
    }

    //处理器规格 --> WindowsCentralProcessor --> AbstractCentralProcessor --> toString()
    public String getProcessSpecification() {
        return cpu.getProcessorIdentifier().getName();
    }

    //获取cpuid
    public String getProcessorID() {
        return cpu.getProcessorIdentifier().getProcessorID();
    }

    //获取供应商信息，例如:GenuineIntel(正版因特尔)
    public String getProcessorVendor() {
        return cpu.getProcessorIdentifier().getVendor();
    }

    //获取处理器的系列，对标的是cpu-z中的系列
    public String getProcessorFamily() {
        return cpu.getProcessorIdentifier().getFamily();
    }

    //是否为64位处理器
    public boolean isCpu64bit() {
        return cpu.getProcessorIdentifier().isCpu64bit();
    }

    //单个cpu核心睿频 单位Hz
    public long getVendorFreq() {
        return cpu.getProcessorIdentifier().getVendorFreq();
    }

    //返回缓存信息
    public List<CentralProcessor.ProcessorCache> getProcessorCaches() {
        /*
          等级  level : byte ;
          缓存大小  cacheSize : int ; in bytes
          缓存类型  type : Type ;
          路数 associativity : byte ;
          行大小 lineSize : short ;
         */
        return cpu.getProcessorCaches();
    }

    //测试
    public void getFreqInfo() {
        System.out.println("当前频率:" + Arrays.toString(cpu.getCurrentFreq()));
        System.out.println("最大频率:" + cpu.getMaxFreq());
    }
}
