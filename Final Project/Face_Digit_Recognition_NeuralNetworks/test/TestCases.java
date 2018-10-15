
import Face_digit_Recognition.ImageConversion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Face_digit_Recognition.Driver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ajaygoel
 */
@SuppressWarnings("ALL")
public class TestCases {

//    Driver Driver = new Driver(1);
    String workingDir;
    public TestCases()
    {
    }
    //passing 2 in Driver constructor for creating digits neural nets
    //passing 1 in Driver constructor for Face recognition neural nets
    @Test
    public void testDigit1() {
        Driver sketch = new Driver(2);
        sketch.train(sketch.targets, sketch.inputs);
        double[] result_arr = sketch.brain.feedForward(sketch.inputs.get(30093));
        assertEquals(3,sketch.maximum(result_arr));
    }
    
    
    @Test
    public void testDigit2() {
        Driver sketch = new Driver(2);
        sketch.train(sketch.targets, sketch.inputs);
        double[] result_arr = sketch.brain.feedForward(sketch.inputs.get(30069));
        assertEquals(0,sketch.maximum(result_arr));
    }
    
    @Test
    public void testFace1()
    {
        Driver sketch1 = new Driver(1);
        sketch1.train2(sketch1.targets,sketch1.inputs);
        workingDir = System.getProperty("user.dir");
        double[] inpu = ImageConversion.imageLoad(workingDir + "/Face_testimages/test1.png",50,50);
        double[] result_arr = sketch1.brain.feedForward(inpu);
        assertEquals(1,sketch1.maximum(result_arr)+1);
    }
    
    @Test
    public void testFace2()
    {
        Driver sketch1 = new Driver(1);
        sketch1.train2(sketch1.targets,sketch1.inputs);
        String workingDir = System.getProperty("user.dir");
        double[] inpu = ImageConversion.imageLoad(workingDir + "/Face_testimages/test29.png",50,50);
        double[] result_arr = sketch1.brain.feedForward(inpu);
        assertEquals(9,sketch1.maximum(result_arr)+1);
    }
    

}
