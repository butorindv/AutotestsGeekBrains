
import lesson4.Triangle;
import org.apache.hc.core5.util.Asserts;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

public class TriangleTest {

    @Test
    public void areaCalculationPositive() {
        boolean area = (Triangle.triangleArea(3, 4, 5) == 6);
        Assertions.assertTrue(area, "Ожидаемый результат: 6");
    }

    @Test
    public void areaCalculationNegative() {
        boolean area1 = (Triangle.triangleArea(3, 4, 5) == 8);
        Assertions.assertFalse(area1, "Ожидаемый результат: 6");
    }

    @Test
    public void theNegativeSideOfTheTriangleA() {
        boolean area1 = Triangle.triangleArea(-3, 4, 5) == null;
        Assertions.assertTrue(area1, "Не должно быть отрицательной стороны");

    }

    @Test
    public void theNegativeSideOfTheTriangleB() {
        boolean area1 = Triangle.triangleArea(3, -4, 5) == null;
        Assertions.assertTrue(area1, "Не должно быть отрицательной стороны");

    }

    @Test
    public void theNegativeSideOfTheTriangleC() {
        boolean area1 = Triangle.triangleArea(3, 4, -5) == null;
        Assertions.assertTrue(area1, "Не должно быть отрицательной стороны");

    }

    @Test
    public void invalidSideMaxA() {
        boolean area1 = Triangle.triangleArea(10, 6, 3) == null;
        Assertions.assertTrue(area1, "");
    }

    @Test
    public void invalidSideMinA() {
        boolean area1 = Triangle.triangleArea(2, 6, 3) == null;
        Assertions.assertTrue(area1, "");
    }

    @Test
    public void invalidSideMaxB() {
        boolean area1 = Triangle.triangleArea(6, 10, 3) == null;
        Assertions.assertTrue(area1, "");
    }

    @Test
    public void invalidSideMinB() {
        boolean area1 = Triangle.triangleArea(6, 2, 3) == null;
        Assertions.assertTrue(area1, "");
    }

    @Test
    public void invalidSideMaxC() {
        boolean area1 = Triangle.triangleArea(6, 3, 10) == null;
        Assertions.assertTrue(area1, "");
    }

    @Test
    public void invalidSideMinC() {
        boolean area1 = Triangle.triangleArea(6, 3, 2) == null;
        Assertions.assertTrue(area1, "");
    }


}
