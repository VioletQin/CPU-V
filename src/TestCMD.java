import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author VioletQin
 * @since 2023/2/21
 */
public class TestCMD {
    public static void main(String[] args) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        System.out.println(decimalFormat.format(31.145f));
    }
}
