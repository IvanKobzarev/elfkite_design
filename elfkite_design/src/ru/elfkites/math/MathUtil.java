package ru.elfkites.math;

import ru.elfkites.L;

import java.util.Arrays;

/**
 * Created by ivan.kobzarev on 24.12.15.
 */
public class MathUtil {

    public static Point bezier(double x[], double[] y, double t) {
        double XA, YA, XB, YB, XC, YC, XD, YD, XE, YE, XF, YF, XG, YG, XH, YH, XI, YI, XJ, YJ;
        XA = x[0];
        YA = y[0];
        XB = x[1];
        YB = y[1];
        XC = x[2];
        YC = y[2];
        XD = x[3];
        YD = y[3];
        XE = XA + (XB - XA) * t;
        YE = YA + (YB - YA) * t;
        XF = XB + (XC - XB) * t;
        YF = YB + (YC - YB) * t;
        XG = XC + (XD - XC) * t;
        YG = YC + (YD - YC) * t;
        XH = XE + (XF - XE) * t;
        YH = YE + (YF - YE) * t;
        XI = XF + (XG - XF) * t;
        YI = YF + (YG - YF) * t;
        XJ = XH + (XI - XH) * t;
        YJ = YH + (YI - YH) * t;

        return new Point(XJ, YJ);
    }

    public static void bezierCurve(double[] x, double[] y, int n, double[] outX, double[] outY) {
        double step = 1.0f / (n - 1);
        for (int i = 0; i < n; i++) {
            double xi = step * i;
            Point p = bezier(x, y, xi);
            outX[i] = p.x;
            outY[i] = p.y;
        }
    }

    public static double interpLin(double[] x, double[] y, double xi) {
        int i = 0;
        while (i < (x.length - 1) && x[i] <= xi) {
            i++;
        }

        if (i > 0) {
            i--;
        }
        double yi = y[i] + (xi - x[i]) * (y[i + 1] - y[i]);
        return yi;
    }


    public static void interpLinMatrice(double[] x, double[] y, double[] xi, double yi[]) {
        for (int i = 0; i < xi.length; i++) {
            yi[i] = interpLin(x, y, xi[i]);
        }
    }

}
