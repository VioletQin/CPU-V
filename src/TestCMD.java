import java.io.IOException;

/**
 * @author VioletQin
 * @since 2023/2/21
 */
public class TestCMD {
    public static void main(String[] args) throws IOException {
        Runtime run = Runtime.getRuntime();
        run.exec("cmd /c taskmgr");
    }
}
