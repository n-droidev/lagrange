package samir.alakbarov.lagrangecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    
    private TextView tv1;
    private EditText et1, et2;
    private Switch sw1;
    private int say;
    private double[] sonrec;
    private double[][] cavab;
    private String str = "";
    private int yfor;
    private double[] yarr;
    private double mex;
    private int one;
    private double[] mexrec;
    private double cmex;
    private double[] soncavab;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        Button bt1 = findViewById(R.id.bt1);
        tv1 = findViewById(R.id.tv1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        sw1 = findViewById(R.id.switch1);
        et1.requestFocus();
        
        
        bt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                
                int iterator = 0;
                String e1, e2;
                e1 = String.valueOf(et1.getText());
                e2 = String.valueOf(et2.getText());
                str = "";
                say = 0;
                cmex = 1;
                one = 0;
                
                
                Scanner sc = new Scanner(e1).useDelimiter(" ");
                
                while (sc.hasNextDouble()) {
                    sc.nextDouble();
                    say++;
                }
                
                
                cavab = new double[say][say];
                mexrec = new double[say];
                double[] xarr = new double[say];
                yarr = new double[say];
                soncavab = new double[say];
                
                sonrec = new double[say];
                mex = 1;
                double[][] ints = new double[say][say];
                
                //   try {
                Scanner sc2 = new Scanner(e2).useDelimiter(" ");
                
                while (sc2.hasNextDouble()) {
                    yarr[iterator] += sc2.nextDouble();
                    iterator++;
                }
                
                Scanner sc1 = new Scanner(e1).useDelimiter(" ");
                iterator = 0;
                
                while (sc1.hasNextDouble()) {
                    xarr[iterator] += sc1.nextDouble();
                    iterator++;
                }
                
                
                int t = 1;
                
                
                for (int r = 0; r < say; r++) {
                    mexrec[r] = 1;
                    ints[r][0] = 1;
                }
                
                for (yfor = 0; yfor < say; yfor++) {
                    for (int xfor = 0; xfor < say; xfor++) {
                        if (xfor != yfor) {
                            mex *= xarr[yfor] - xarr[xfor];
                            ints[yfor][t] = -xarr[xfor];
                            t++;
                        }
                    }
                    sonrec = ints[yfor];
                    mexrec[yfor] = mex;
                    cmex *= mex;
                    rec(say, -1, 1, 0);
                    one++;
                    t = 1;
                    mex = 1;
                }
                
                
                for (int p = 0; p < say; p++) {
                    for (int ii = say - 1; ii >= 0; ii--) {
                        cavab[p][ii] *= cmex / mexrec[p];
                    }
                }
                
                
                for (int ii = say - 1; ii >= 0; ii--) {
                    for (int p = 0; p < say; p++) {
                        soncavab[ii] += cavab[p][ii];
                    }
                }
                
                
                /////////////////// Ekrana cixarilma >>>>>>>>>>>>>
                
                String isare;
                if (sw1.isChecked()) {
                    for (int ii = say - 1; ii >= 0; ii--) {
                        if (soncavab[ii] != 0 && cmex != 0) {
                            if (ii != 0) {
                                if (soncavab[ii - 1] / cmex > 0) {
                                    isare = " +";
                                } else {
                                    isare = " ";
                                }
                                str += soncavab[ii] + "/" + cmex + " x^" + ii + isare;
                            } else {
                                str += soncavab[ii] + "/" + cmex;
                            }
                        }
                    }
                } else {
                    double d;
                    for (int ii = say - 1; ii >= 0; ii--) {
                        d = soncavab[ii] / cmex;
                        if (d != 0) {
                            if (ii != 0) {
                                if (soncavab[ii - 1] / cmex > 0) {
                                    isare = " +";
                                } else {
                                    isare = " ";
                                }
                                str += d + " x^" + ii + isare;
                            } else {
                                str += d;
                            }
                        }
                    }
                }
                
                
                tv1.setText(str);
                
                //   } catch (Exception e) {
                //       e.printStackTrace();
                //       Snackbar.make(view, "XÉ™ta baÅŸ verdi, YenidÉ™n cÉ™hd edin!", Snackbar.LENGTH_LONG).show();
                //    }
                
            }
        });
    }
    
    
    /**
     * ///////////////////////////////////////////////////////////////method
     */
    public void rec(int row, int f, double ed, int sira) {
        row--;
        
        if (row == 0) {
            for (int i = 0; i < say; i++) {
                
                if (sira < i || (sira == 0 && f == say - 2)) {
                    if (i == 0) {
                        cavab[one][f + 1] += ed * sonrec[i] * yarr[yfor];
                    } else {
                        cavab[one][f] += ed * sonrec[i] * yarr[yfor];
                    }
                }
                
            }
        } else {
            for (int i = 0; i < say; i++) {
                
                if (sira < i || sira == 0) {
                    if (i == 0) {
                        rec(row, f + 1, ed * sonrec[i], i);
                    } else {
                        rec(row, f, ed * sonrec[i], i);
                    }
                }
                
            }
        }
        
    }
    ////////////end method
    
}