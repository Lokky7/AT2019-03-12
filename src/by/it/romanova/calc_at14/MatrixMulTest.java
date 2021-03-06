package by.it.romanova.calc_at14;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class MatrixMulTest {

    private Parser p;

    @Before
    public void parserInit(){
        p = new Parser();
    }

    @Parameters
    public static List<Object[]> parameters(){
        return Arrays.asList(new Object[][]{
                {"{{1,2.0,3},{4,5,6},{7, 8,9}}*{{1,2.5,3},{4,0,6},{7,8,-9}}", "{{30.0, 26.5, -12.0}, {66.0, 58.0, -12.0}, {102.0, 89.5, -12.0}}"},
                {"{{1,2,3},{4,5,6},{7, 8,9}}*{{1,0,3},{4,0,6},{7,8,-9}}", "{{30.0, 24.0, -12.0}, {66.0, 48.0, -12.0}, {102.0, 72.0, -12.0}}"},
                {"{{1,2},{4,5}}*{{1,2},{5,6}}", "{{11.0, 14.0}, {29.0, 38.0}}"},
                {"{{1,8},{4,5}}*{{1,-2},{5,6}}", "{{41.0, 46.0}, {29.0, 22.0}}"},
                {"{{1,2.5},{4,5.5}}*{{1,-2},{5,-6}}", "{{13.5, -17.0}, {31.5, -41.0}}"},

         });
    }

    @Parameter
    public String expression;

    @Parameter(1)
    public String expected;


    @Test
    public void main() throws CalcException {
        assertThat(p.calc(expression).toString(), is(expected));
    }
}