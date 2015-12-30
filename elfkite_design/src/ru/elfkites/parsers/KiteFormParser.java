package ru.elfkites.parsers;

import ru.elfkites.L;
import ru.elfkites.model.KiteForm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ivan.kobzarev on 24.12.15.
 */
public class KiteFormParser {

    public static final String NB_ALVEOLES = "NB_ALVEOLES";
    public static final String BORD_ATTAQUE_FORME = "BORD_ATTAQUE_FORME";
    public static final String BORD_DE_FUITE_FORME = "BORD_DE_FUITE_FORME";
    public static final String DIEDRE = "DIEDRE";
    public final String path;

    public KiteFormParser(String path) {
        this.path = path;
    }

    public KiteForm parse() {
        L.d("KiteFormParser.parse " + path);

        KiteForm kiteForm = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineI = 0;
            int nervureI = 0;
            int n = 0;
            kiteForm = new KiteForm();
            while ((line = br.readLine()) != null) {
                L.d("line " + lineI + ">>" + line);
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith(NB_ALVEOLES)) {
                    String nSubstring = line.substring(NB_ALVEOLES.length() + 1);
                    n = Integer.parseInt(nSubstring);
                    kiteForm.initSectionsSize(n);
                }

                if (line.startsWith(BORD_ATTAQUE_FORME)) {
                    String[] tokens = line.split("\\s+");
                    int frontEdgeN = (tokens.length - 1) / 2;
                    kiteForm.initFrontEdgeN(frontEdgeN);
                    for (int i = 0; i < frontEdgeN; i++) {
                        L.d("token " + tokens[1 + 2 * i]);
                        L.d("token " + tokens[1 + 2 * i + 1]);
                        kiteForm.frontEdgeX[i] = Double.parseDouble(tokens[1 + 2 * i]);
                        kiteForm.frontEdgeY[i] = Double.parseDouble(tokens[1 + 2 * i + 1]);
                    }
                }

                if (line.startsWith(BORD_DE_FUITE_FORME)) {
                    String[] tokens = line.split("\\s+");
                    int backEdgeN = (tokens.length - 1) / 2;
                    kiteForm.initBackEdgeN(backEdgeN);
                    for (int i = 0; i < backEdgeN; i++) {
                        kiteForm.backEdgeX[i] = Double.parseDouble(tokens[1 + 2 * i]);
                        kiteForm.backEdgeY[i] = Double.parseDouble(tokens[1 + 2 * i + 1]);
                    }
                }

                if (line.startsWith(DIEDRE)) {
                    String[] tokens = line.split("\\s+");
                    int diedreN = (tokens.length - 1) / 2;
                    kiteForm.initDiedreN(diedreN);
                    for (int i = 0; i < diedreN; i++) {
                        kiteForm.diedreX[i] = Double.parseDouble(tokens[1 + 2 * i]);
                        kiteForm.diedreY[i] = Double.parseDouble(tokens[1 + 2 * i + 1]);
                    }
                }

                if (Character.isDigit(line.charAt(0)) && nervureI < n) {
                    String[] tokens = line.split("\\s+");
                    L.d("tokens " + Arrays.toString(tokens));
                    kiteForm.nervureLength[nervureI] = Double.parseDouble(tokens[1]);
                    kiteForm.profileWidth[nervureI] = Double.parseDouble(tokens[2]);
                    kiteForm.x[nervureI] = Double.parseDouble(tokens[3]);
                    kiteForm.y[nervureI] = Double.parseDouble(tokens[4]);
                    kiteForm.z[nervureI] = Double.parseDouble(tokens[5]);
                    kiteForm.inclination[nervureI] = Double.parseDouble(tokens[6]);
                    kiteForm.vrillage[nervureI] = Double.parseDouble(tokens[7]);
                    kiteForm.lineA[nervureI] = Double.parseDouble(tokens[8]);
                    kiteForm.lineB[nervureI] = Double.parseDouble(tokens[9]);
                    kiteForm.lineC[nervureI] = Double.parseDouble(tokens[10]);
                    kiteForm.lineD[nervureI] = Double.parseDouble(tokens[11]);
                    kiteForm.lineE[nervureI] = Double.parseDouble(tokens[12]);
                    nervureI++;
                }
                lineI++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kiteForm;
    }
}
