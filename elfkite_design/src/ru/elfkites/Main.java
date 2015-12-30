package ru.elfkites;

import ru.elfkites.formdesign.SectionsWidthProportionalNervuresLengths;
import ru.elfkites.math.MathUtil;
import ru.elfkites.model.KiteForm;
import ru.elfkites.parsers.KiteFormParser;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello kite design!");
        System.out.println("working dir " + System.getProperty("user.dir"));

        String formeFilePath = "/Users/ivan.kobzarev/repos/elfkite_design/input/f8_c61.txt";
        KiteForm kiteForm = new KiteFormParser(formeFilePath).parse();
        L.d("KiteForm: " + kiteForm.toString());
        L.d("KiteForm.front x " + Arrays.toString(kiteForm.frontEdgeX)
                + " y " + Arrays.toString(kiteForm.frontEdgeY));


        L.d("KiteForm.front x " + Arrays.toString(kiteForm.backEdgeX)
                + " y " + Arrays.toString(kiteForm.backEdgeY));

//        int n = kiteForm.n;
//        double[] frontEdgeCurveX = new double[n];
//        double[] frontEdgeCurveY = new double[n];
//        double[] backEdgeCurveX = new double[n];
//        double[] backEdgeCurveY = new double[n];
//
//        MathUtil.bezierCurve(kiteForm.frontEdgeX, kiteForm.frontEdgeY, 20, frontEdgeCurveX, frontEdgeCurveY);
//        MathUtil.bezierCurve(kiteForm.backEdgeX, kiteForm.backEdgeY, 20, backEdgeCurveX, backEdgeCurveY);

        new SectionsWidthProportionalNervuresLengths(kiteForm).process();
    }
}
