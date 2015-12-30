package ru.elfkites.model;

import java.util.Arrays;

/**
 * Created by ivan.kobzarev on 24.12.15.
 */
public class KiteForm {

    public int nSections;
    public int nNervures;

    public double[] x;
    public double[] y;
    public double[] z;

    public double[] nervureLength;
    public double[] profileWidth;
    public double[] inclination;
    public double[] vrillage;
    public double[] morphing;
    public double[] lineA;
    public double[] lineB;
    public double[] lineC;
    public double[] lineD;
    public double[] lineE;

    public int frontEdgeN;
    public double[] frontEdgeX;
    public double[] frontEdgeY;

    public int backEdgeN;
    public double[] backEdgeX;
    public double[] backEdgeY;

    public int diedreN;
    public double[] diedreX;
    public double[] diedreY;

    public void initSectionsSize(int n) {
        this.nSections = n;
        this.nNervures = nSections % 2 == 0 ? nSections / 2 + 1 : (nSections + 1) / 2;
        x = new double[nNervures];
        y = new double[nNervures];
        z = new double[nNervures];
        nervureLength = new double[nNervures];
        profileWidth = new double[nNervures];
        inclination = new double[nNervures];
        vrillage = new double[nNervures];
        morphing = new double[nNervures];
        lineA = new double[nNervures];
        lineB = new double[nNervures];
        lineC = new double[nNervures];
        lineD = new double[nNervures];
        lineE = new double[nNervures];
    }

    public void initFrontEdgeN(int frontEdgeN) {
        this.frontEdgeN = frontEdgeN;
        frontEdgeX = new double[frontEdgeN];
        frontEdgeY = new double[frontEdgeN];
    }

    public void initBackEdgeN(int backEdgeN) {
        this.backEdgeN = backEdgeN;
        backEdgeX = new double[backEdgeN];
        backEdgeY = new double[backEdgeN];
    }

    public void initDiedreN(int n) {
        this.diedreN = n;
        diedreX = new double[n];
        diedreY = new double[n];
    }

    @Override
    public String toString() {
        return "KiteForm{" +
                "\\nn=" + nSections +
                "\\n, x=" + Arrays.toString(x) +
                "\\n, y=" + Arrays.toString(y) +
                "\\n, z=" + Arrays.toString(z) +
                "\\n, nervureLength=" + Arrays.toString(nervureLength) +
                "\\n, profileWidth=" + Arrays.toString(profileWidth) +
                "\\n, inclination=" + Arrays.toString(inclination) +
                "\\n, vrillage=" + Arrays.toString(vrillage) +
                "\\n, morphing=" + Arrays.toString(morphing) +
                "\\n, lineA=" + Arrays.toString(lineA) +
                "\\n, lineB=" + Arrays.toString(lineB) +
                "\\n, lineC=" + Arrays.toString(lineC) +
                "\\n, lineD=" + Arrays.toString(lineD) +
                "\\n, lineE=" + Arrays.toString(lineE) +
                "\\n, frontEdgeN=" + frontEdgeN +
                "\\n, frontEdgeX=" + Arrays.toString(frontEdgeX) +
                "\\n, frontEdgeY=" + Arrays.toString(frontEdgeY) +
                "\\n, backEdgeN=" + backEdgeN +
                "\\n, backEdgeX=" + Arrays.toString(backEdgeX) +
                "\\n, backEdgeY=" + Arrays.toString(backEdgeY) +
                "\\n}";
    }

}
