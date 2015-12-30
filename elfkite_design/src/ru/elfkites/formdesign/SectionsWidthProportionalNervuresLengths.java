package ru.elfkites.formdesign;

import ru.elfkites.L;
import ru.elfkites.math.MathUtil;
import ru.elfkites.math.Point;
import ru.elfkites.model.KiteForm;

/**
 * Created by ivan.kobzarev on 24.12.15.
 */
public class SectionsWidthProportionalNervuresLengths {

    public final KiteForm form;
    private double[] frontEdgeCurveX;
    private double[] frontEdgeCurveY;
    private double[] backEdgeCurveX;
    private double[] backEdgeCurveY;
    private double[] diedreCurveX;
    private double[] diedreCurveY;

    public SectionsWidthProportionalNervuresLengths(KiteForm form) {
        this.form = form;
    }

    public double chordLength(double[] frontEdgeX, double[] frontEdgeY,
                              double[] backEdgeX, double[] backEdgeY,
                              double x) {
        double frontY = MathUtil.interpLin(frontEdgeX, frontEdgeY, x);
        double backY = MathUtil.interpLin(backEdgeX, backEdgeY, x);
        double chordLength = frontY - backY;
        L.d("chordLength(" + x + ") frontY " + frontY + " backY " + backY + " length " + chordLength);
        return chordLength;
    }

    public double chordLength(double x) {
        return chordLength(
                frontEdgeCurveX,
                frontEdgeCurveY,
                backEdgeCurveX,
                backEdgeCurveY,
                x
        );
    }

    public void calculateXNerv(double[] xnerv) {

        double cumulProg;
        double coeffProgGeom = 0.986f;
        double coeffExp = 0.007f;

        if (form.nSections % 2 == 0) {
            cumulProg = 0.0f;
        } else {
            cumulProg = 0.5f;
        }
        double multG = 1.0f;
        double multE;

        xnerv[0] = cumulProg;
        for (int i = 1; i < form.nNervures; i++) {
            multG *= coeffProgGeom;
            multE = Math.pow(Math.E, -coeffExp * (i - 1));
            cumulProg += multG * multE;
            xnerv[i] = cumulProg;
        }

        double k = form.frontEdgeX[form.frontEdgeN - 1] / cumulProg;
        for (int i = 0; i < form.nNervures; i++) {
            xnerv[i] *= k;
        }
    }

    public KiteForm process() {
        KiteForm ret = null;
        int NBEZ = 20;

        frontEdgeCurveX = new double[NBEZ];
        frontEdgeCurveY = new double[NBEZ];
        backEdgeCurveX = new double[NBEZ];
        backEdgeCurveY = new double[NBEZ];
        diedreCurveX = new double[NBEZ];
        diedreCurveY = new double[NBEZ];

        MathUtil.bezierCurve(form.frontEdgeX, form.frontEdgeY, NBEZ, frontEdgeCurveX, frontEdgeCurveY);
        MathUtil.bezierCurve(form.backEdgeX, form.backEdgeY, NBEZ, backEdgeCurveX, backEdgeCurveY);
        MathUtil.bezierCurve(form.diedreX, form.diedreY, NBEZ, diedreCurveX, diedreCurveY);

        double[] diedreLen = new double[NBEZ];
        for (int i = 0; i < NBEZ; i++) {
            if (i < NBEZ - 1) {
                diedreLen[i + 1] += diedreLen[i] + Math.sqrt(
                        (diedreCurveX[i + 1] - diedreCurveX[i]) * (diedreCurveX[i + 1] - diedreCurveX[i])
                                + (diedreCurveY[i + 1] - diedreCurveY[i]) * (diedreCurveY[i + 1] - diedreCurveY[i])
                );
            }
            L.d("diedre " + i + " (" + diedreCurveX[i] + ", " + diedreCurveY[i] + ") diedreLen " + diedreLen[i]);

        }
        double ratio = form.frontEdgeX[form.frontEdgeN - 1] / diedreLen[NBEZ - 1];
        L.d("ratio " + ratio);

        for (int i = 0; i < NBEZ; i++) {
            diedreCurveX[i] *= ratio;
            diedreCurveY[i] *= ratio;
            diedreLen[i] *= ratio;
            L.d("diedre_" + i + " (" + diedreCurveX[i] + ", " + diedreCurveY[i] + ") " + diedreLen[i]);
        }

        double[] xnerv = new double[form.nNervures];
        calculateXNerv(xnerv);
        for (int i = 0; i < form.nNervures; i++) {
            L.d("xnerv_" + i + " : " + xnerv[i]);
        }

        double xDiedre[] = new double[form.nNervures];
        double yDiedre[] = new double[form.nNervures];
        MathUtil.interpLinMatrice(diedreLen, diedreCurveX, xnerv, xDiedre);
        MathUtil.interpLinMatrice(diedreLen, diedreCurveY, xnerv, yDiedre);

//        double[] tabX = new double[]{0.087, 0.258, 0.425, 0.588, 0.747, 0.901, 1.051, 1.196, 1.335, 1.470, 1.599, 1.724, 1.842, 1.957, 2.064, 2.168, 2.265, 2.358, 2.445, 2.527, 2.605, 2.677, 2.745, 2.808, 2.866, 2.920, 2.969, 3.012, 3.050, 3.080, 3.098};
//        double[] tabY = new double[]{1.996, 1.990, 1.977, 1.958, 1.933, 1.902, 1.865, 1.822, 1.775, 1.723, 1.665, 1.604, 1.538, 1.470, 1.396, 1.323, 1.244, 1.164, 1.082, 0.999, 0.915, 0.829, 0.743, 0.655, 0.567, 0.479, 0.390, 0.301, 0.212, 0.122, 0.031};

        for (int i = 0; i < form.nNervures; i++) {
            double realX = xnerv[i];
            L.d("diedre_" + i + " (" + xDiedre[i] + ", " + yDiedre[i] + ") " + chordLength(realX));
        }
        return ret;
    }
}
